package com.louiseeo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.louiseeo.ClientHandler;
import com.louiseeo.enums.GamePhase;
import com.louiseeo.model.Player;
import com.louiseeo.model.WordPair;
import com.louiseeo.model.ImposterPlayer;
import com.louiseeo.model.CitizenPlayer;

/**
 * Handles the main game logic of UnderCoven.
 * Manages players, roles, game phases,
 * word assignment, and win conditions.
 *
 * @author louiseeo
 */
public class GameService {
    private static GamePhase currentPhase = GamePhase.LOBBY;
    private static WordPair currentWordPair;
    private static final List<Player> players = Collections.synchronizedList(new ArrayList<>());
    private static final Set<ClientHandler> readyPlayers = Collections.synchronizedSet(new HashSet<>());

    public static List<Player> getPlayers() {
        return players;
    }

    public static void setCurrentPhase(GamePhase phase) {
        currentPhase = phase;
    }

    /**
     * Marks a player as ready before the game starts.
     * Starts the game once all players are ready.
     *
     * @param client : the client who typed ready
     */
    public static synchronized void handleReady(ClientHandler client) {

        // already readied!!
        if (readyPlayers.contains(client)) {
            client.sendMessage("----- You already typed ready!! -----");
            return;
        }

        if (getPlayers().size() < 3) {
            client.sendMessage("----- Need at least 3 players to start!! -----");
            return;
        }

        readyPlayers.add(client);
        ChatService.broadcastAll(client.getPlayer().getUsername() + " is ready!! (" + readyPlayers.size() + "/"
                + getPlayers().size() + ")");

        if (readyPlayers.size() >= getPlayers().size()) {
            readyPlayers.clear();
            startGame();
        }
    }

    /**
     * Starts a new game session.
     * Resets voting data, assigns player roles,
     * and changes the game phase to CHAT.
     */
    public static void startGame() {
        readyPlayers.clear();
        VoteService.resetVotes();
        ChatService.broadcastAll("===== All Players connected! Game is starting! =====");

        assignRoles();
        currentPhase = GamePhase.CHAT;
        ChatService.broadcastAll(
                "===== CHAT PHASE: Give clues about your word! Type 'vote' when you know who the imposter is! =====");

    }

    /**
     * Randomly assigns one imposter and multiple citizens.
     * Citizens receive the secret word while the imposter
     * only receives a hint related to the word.
     */
    public static void assignRoles() {
        List<WordPair> wordBank = FileService.loadWordbank("data/words.json");

        if (wordBank.isEmpty()) {
            ChatService.broadcastAll("Error: Word bank is empty!!");
            return;
        }

        Random random = new Random();
        WordPair selectedPair = wordBank.get(random.nextInt(wordBank.size()));
        int imposterIndex = random.nextInt(players.size());

        // Assign Roles and Words/Hints
        for (int i = 0; i < players.size(); i++) {
            Player oldPlayer = players.get(i);
            String username = oldPlayer.getUsername();

            if (i == imposterIndex) {
                // The Imposter gets the category hint stored as their "word"
                ImposterPlayer imposter = new ImposterPlayer(username, selectedPair.getHint());
                players.set(i, imposter);
            } else {
                // Citizens get the actual secret word
                CitizenPlayer citizen = new CitizenPlayer(username, selectedPair.getReal());
                players.set(i, citizen);
            }
        }

        currentWordPair = selectedPair;

        synchronized (ChatService.getClients()) {
            for (ClientHandler client : ChatService.getClients()) {

                // Sync ClientHandler's player reference to the new subclass instance
                for (Player updatedPlayer : players) {
                    if (updatedPlayer.getUsername().equals(client.getPlayer().getUsername())) {
                        client.setPlayer(updatedPlayer);
                        break;
                    }
                }

                // Check if this specific connection belongs to the Imposter or a Citizen
                if (client.getPlayer() instanceof ImposterPlayer) {
                    client.sendMessage("----- YOU ARE THE IMPOSTER! -----");
                    client.sendMessage(
                            "You don't know the word! Your word hint is: " + client.getPlayer().getWord());
                    client.sendMessage("Blend in! Try to figure out the real word from others' clues.");
                } else {
                    client.sendMessage("----- YOU ARE A CITIZEN! -----");
                    client.sendMessage("Your secret word is: " + client.getPlayer().getWord());
                    client.sendMessage("Give clever clues to find out who doesn't know the word!");
                }
            }
        }
    }

    /**
     * Determines the winner after voting ends.
     * Updates leaderboard points and starts
     * the play-again phase.
     *
     * @param eliminated : the player eliminated during voting
     */
    public static void checkWinCondition(Player eliminated) {
        ChatService.broadcastAll("**** " + eliminated.getUsername() + " has been eliminated!! ****");

        Player imposter = null;
        for (Player p : players) {
            if (p.getRole().equals("Imposter")) {
                imposter = p;
                break;
            }
        }

        String imposterName = imposter != null ? imposter.getUsername() : "unknown";
        ChatService.broadcastAll("**** The Imposter was: " + imposterName + "!! ****");
        ChatService.broadcastAll("**** The real word was: " + currentWordPair.getReal() + "!! ****");

        if (eliminated.getRole().equals("Imposter")) {
            ChatService.broadcastAll("**** CITIZENS WIN!! ****");

            for (Player p : players) {

                if (p.getRole()
                        .equals("Citizen")) {

                    LeaderboardService.updatePoints(p.getUsername(), 20);

                } else {

                    LeaderboardService.updatePoints(p.getUsername(), -10);
                }
            }
        } else {
            ChatService.broadcastAll("**** IMPOSTER WINS!! ****");

            for (Player p : players) {

                if (p.getRole()
                        .equals("Imposter")) {

                    LeaderboardService.updatePoints(
                            p.getUsername(),
                            20);

                } else {

                    LeaderboardService.updatePoints(
                            p.getUsername(),
                            -10);
                }
            }

        }

        currentPhase = GamePhase.RESULTS;
        handlePlayAgain();

    }

    /**
     * Starts the play-again voting phase.
     * Players must answer yes or no.
     */
    public static void handlePlayAgain() {

        currentPhase = GamePhase.PLAY_AGAIN;

        ChatService.broadcastAll(
                "Play again? Type 'yes' or 'no':");
    }

    public static void addPlayer(Player player) {
        players.add(player);
    }

    public static void removePlayer(Player player) {
        players.remove(player);
    }

    public static GamePhase getCurrentPhase() {
        return currentPhase;
    }
}
