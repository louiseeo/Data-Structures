package com.louiseeo.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.louiseeo.ClientHandler;
import com.louiseeo.enums.GamePhase;
import com.louiseeo.model.Player;

/**
 * Handles all voting-related features in the game.
 * Manages vote requests, vote submissions,
 * vote counting, and play-again responses.
 *
 * @author louiseeo
 */
public class VoteService {
    private static int voteCount;
    private static Map<ClientHandler, Boolean> voteRequests = new HashMap<>();
    private static Map<ClientHandler, Integer> votes = new HashMap<>();
    private static Map<ClientHandler, String> playAgainResponses = Collections.synchronizedMap(new HashMap<>());

    public static void setVoteCount(int voteCount) {
        VoteService.voteCount = voteCount;
    }

    public static int getVoteCount() {
        return voteCount;
    }

    /**
     * Handles a player's request to start voting.
     * Begins the voting phase once majority is reached.
     *
     * @param voter : the player requesting to vote
     */
    public static synchronized void handleVote(ClientHandler voter) {

        if (voteRequests.containsKey(voter)) {
            voter.sendMessage("----- You already requested voting! -----");
            return;
        }

        voteRequests.put(voter, true);
        voteCount++;

        ChatService.broadcastAll(
                voter.getPlayer().getUsername()
                        + " wants to vote!! ("
                        + voteCount + "/"
                        + GameService.getPlayers().size() + ")");

        int majority = (GameService.getPlayers().size() / 2) + 1;

        if (voteCount >= majority) {
            voteCount = 0;
            voteRequests.clear();
            GameService.setCurrentPhase(GamePhase.VOTING);
            startVoting();
        }
    }

    /**
     * Displays the list of players and
     * starts the voting phase.
     */
    public static void startVoting() {
        StringBuilder table = new StringBuilder();
        table.append("===== VOTING PHASE! Who is the Imposter? =====\n");
        table.append("+====+====================+\n");
        table.append("|  # | Player             |\n");
        table.append("+====+====================+\n");

        synchronized (ChatService.getClients()) {
            for (int i = 0; i < ChatService.getClients().size(); i++) {
                String name = ChatService.getClients().get(i).getPlayer().getUsername();
                table.append(String.format("|  %d | %-18s |\n", i + 1, name));
            }
        }

        table.append("+====+====================+\n");
        table.append("Enter the number of who you think is the Imposter:");

        ChatService.broadcastAll(table.toString());
    }

    /**
     * Records a player's vote during the voting phase.
     * Counts votes once all players have voted.
     *
     * @param voter : the client submitting a vote
     * @param input : selected player number
     */
    public static synchronized void submitVote(ClientHandler voter, String input) {

        try {

            int voteIndex = Integer.parseInt(input) - 1;

            // invalid player number
            if (voteIndex < 0 ||
                    voteIndex >= ChatService.getClients().size()) {

                voter.sendMessage("------ Invalid player number! -------");
                return;
            }

            // already voted
            if (votes.containsKey(voter)) {
                voter.sendMessage("-------- You already voted! ---------");
                return;
            }

            if (ChatService.getClients().get(voteIndex) == voter) {
                voter.sendMessage("--- You cannot vote for yourself! ---");
                return;
            }

            votes.put(voter, voteIndex);
            voter.sendMessage("----------- Vote submitted! ----------");

            // everyone voted
            if (votes.size() == GameService.getPlayers().size()) {
                countVotes();
            }

        } catch (NumberFormatException e) {
            voter.sendMessage("---- Please enter a valid number! ----");
        }
    }

    /**
     * Counts all submitted votes and determines
     * which player is eliminated.
     * Handles tie situations when necessary.
     */
    public static void countVotes() {

        Map<Integer, Integer> tally = new HashMap<>();
        boolean tie = false;
        for (int vote : votes.values()) {
            tally.put(vote, tally.getOrDefault(vote, 0) + 1);
        }

        int maxVotes = 0;
        int eliminatedIndex = 0;

        for (Map.Entry<Integer, Integer> entry : tally.entrySet()) {

            if (entry.getValue() > maxVotes) {
                maxVotes = entry.getValue();
                eliminatedIndex = entry.getKey();
                tie = false;
            } else if (entry.getValue() == maxVotes) {
                tie = true;
            }
        }

        if (tie) {
            ChatService.broadcastAll("=== It's a tie! Nobody eliminated! Vote again!! ===");
            votes.clear();
            voteRequests.clear();
            GameService.setCurrentPhase(GamePhase.VOTING);
            startVoting();
            return;
        }

        Player eliminated = ChatService.getClients()
                .get(eliminatedIndex)
                .getPlayer();

        votes.clear();

        GameService.checkWinCondition(eliminated);
    }

    /**
     * Clears all stored voting data
     * for a new game round.
     */
    public static void resetVotes() {
        voteCount = 0;
        votes.clear();
        voteRequests.clear();
        playAgainResponses.clear();
    }

    /**
     * Handles player responses for playing again.
     * Starts a new game if majority votes yes,
     * otherwise disconnects all clients.
     *
     * @param client   : responding client
     * @param response : yes or no response
     */
    public static synchronized void handlePlayAgain(ClientHandler client, String response) {
        // only accept yes or no!!
        if (!response.equalsIgnoreCase("yes") && !response.equalsIgnoreCase("no")) {
            client.sendMessage("--- Please type 'yes' or 'no' only!! ---");
            return;
        }

        // prevent duplicate responses
        if (playAgainResponses.containsKey(client)) {
            client.sendMessage("------- You already responded!! --------");
            return;
        }

        playAgainResponses.put(client, response);

        int totalPlayers = GameService.getPlayers().size();

        ChatService.broadcastAll(client.getPlayer().getUsername()
                + " voted " + response + " ("
                + playAgainResponses.size() + "/" + totalPlayers + " responded)");

        if (playAgainResponses.size() < totalPlayers) {
            return;
        }
        // everyone responded
        long yesCount = playAgainResponses.values().stream()
                .filter(r -> r.equalsIgnoreCase("yes")).count();

        playAgainResponses.clear();

        if (yesCount >= (totalPlayers / 2) + 1) {
            resetVotes();
            GameService.startGame();
        } else {

            ChatService.broadcastAll(
                    "===== Majority voted 'no'. Server closing... =====");

            resetVotes();

            synchronized (ChatService.getClients()) {

                for (ClientHandler c : ChatService.getClients()) {

                    c.sendMessage(
                            "===== Thank you for playing UnderCoven! =====");

                    c.closeConnection();
                }
            }
        }
    }
}
