package com.louiseeo;

import java.io.*;
import java.net.Socket;

import com.louiseeo.enums.GamePhase;
import com.louiseeo.model.CitizenPlayer;
import com.louiseeo.model.Player;
import com.louiseeo.service.ChatService;
import com.louiseeo.service.GameService;
import com.louiseeo.service.VoteService;
import com.louiseeo.service.AccountService;
import com.louiseeo.service.LeaderboardService;

/**
 * Handles communication between the server and one client.
 * Processes login, chat, voting, and gameplay interactions.
 *
 * @author louiseeo
 */
public class ClientHandler implements Runnable {
    private final Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Player player;
    private int messageCount = 0;

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    /**
     * Main execution method for the client thread.
     * Handles setup, registration, chat processing,
     * and cleanup when disconnected.
     */
    @Override
    public void run() {
        try {
            setupStreams(); // initialize I/O streams
            registerPlayer(); // register player

            // Add client to server list and notify others
            ChatService.addClient(this);

            handleChat(); // start receiving messages
        } catch (IOException e) {
            System.err.println("Connection error: " + e.getMessage());
        } finally {
            cleanup(); // Always run when client disconnects
        }
    }

    /**
     * Initializes input and output streams
     * for client communication.
     *
     * @throws IOException if stream setup fails
     */
    public void setupStreams() throws IOException {
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    /**
     * Displays the main menu and handles
     * login, signup, leaderboard viewing,
     * or exiting the program.
     *
     * @throws IOException if communication fails
     */
    public void registerPlayer() throws IOException {

        while (true) {

            String menu = "+-+-+-+-+ Welcome to UnderCoven! +-+-+-+-+\n" +
                    "1. Log In\n" +
                    "2. Sign Up\n" +
                    "3. Display Leaderboard\n" +
                    "0. Exit \n" +
                    "Enter choice:";

            out.println(menu);
            String choice = in.readLine();

            switch (choice) {

                case "1":
                    handleLogin();
                    return;
                case "2":
                    handleSignup();
                    return;
                case "3":
                    sendMessage(LeaderboardService.displayLeaderboard());
                    break;
                case "0":
                    sendMessage("===== Thank you for playing UnderCoven! Goodbye! =====");
                    closeConnection();
                    return;
                default:
                    sendMessage("----- Incorrect choice! -----\n");
            }
        }
    }

    /**
     * Processes player login credentials
     * and adds the player to the game.
     *
     * @throws IOException if communication fails
     */
    private void handleLogin() throws IOException {

        out.println("Enter username:");
        String username = in.readLine();

        out.println("Enter password:");
        String password = in.readLine();

        if (GameService.getCurrentPhase() != GamePhase.LOBBY) {
            sendMessage("===== A game is already in progress. Please wait. =====\n");
            registerPlayer();
            return;
        }

        boolean success = AccountService.login(username, password);

        if (success) {
            player = new CitizenPlayer(username, "");
            GameService.addPlayer(player);

            sendMessage("Login successful!");
            System.out.println(username + " logged in.");

        } else {
            sendMessage("----- Invalid credentials! -----\n");
            registerPlayer();
        }
    }

    /**
     * Creates a new player account and
     * adds the player to the game.
     *
     * @throws IOException if communication fails
     */
    private void handleSignup() throws IOException {

        out.println("Choose username:");
        String username = in.readLine();

        out.println("Choose password:");
        String password = in.readLine();

        if (GameService.getCurrentPhase() != GamePhase.LOBBY) {
            sendMessage("===== A game is already in progress. Please wait. =====\n");
            registerPlayer();
            return;
        }

        boolean success = AccountService.signup(username, password);

        if (success) {
            player = new CitizenPlayer(username, "");
            GameService.addPlayer(player);

            sendMessage("Account created successfully!");
            System.out.println(username + " signed up.");

        } else {
            sendMessage("----- Username already exists! -----\n");
            registerPlayer();
        }
    }

    /**
     * Continuously handles player messages,
     * gameplay actions, voting, and chat communication.
     *
     * @throws IOException          if communication fails
     * @throws InterruptedException if thread is interrupted
     */
    public void handleChat() throws IOException {

        while (true) {

            String message = in.readLine();

            if (message == null) {
                break;
            }

            if (GameService.getCurrentPhase() == GamePhase.LOBBY) {

                if (message.equalsIgnoreCase("ready")) {

                    GameService.handleReady(this);

                } else {

                    sendMessage(
                            "Type 'ready' to start the game!!");
                }

                continue;
            }

            // PLAY AGAIN PHASE
            if (GameService.getCurrentPhase() == GamePhase.PLAY_AGAIN) {

                VoteService.handlePlayAgain(this, message);
                continue;
            }

            // VOTING PHASE
            else if (GameService.getCurrentPhase() == GamePhase.VOTING) {

                VoteService.submitVote(this, message);
            }

            // PLAYER WANTS TO START VOTING
            else if (message.equalsIgnoreCase("vote")) {

                if (messageCount >= 3) {

                    VoteService.handleVote(this);

                } else {
                    sendMessage(
                            "----- You need at least 3 messages before voting! -----");
                }
            }

            // NORMAL CHAT
            else {

                messageCount++;
                String formatted = "[" + player.getUsername() + "]: " + message;
                ChatService.broadcast(formatted, this);
                sendMessage("[You]: " + message);
            }
        }
    }

    /**
     * Sends a message to this specific client.
     *
     * @param message : message to send
     */
    public void sendMessage(String message) {
        if (out != null) {
            out.println(message);
        }
    }

    /**
     * Removes the player, closes the socket,
     * and cleans up resources after disconnecting.
     */
    public void cleanup() {
        try {
            if (player != null) {
                String leaveMsg = player.getUsername() + " left the game.";
                System.out.println(leaveMsg);
                ChatService.broadcastAll(leaveMsg);
                GameService.removePlayer(player);
            }

            ChatService.removeClient(this); // removes client in server list

            // close socket connection
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }

            if (GameService.getPlayers().size() < 3) {
                GameService.setCurrentPhase(GamePhase.LOBBY);
                ChatService.broadcastAll("Not enough players. Returning to lobby.");
            }

        } catch (IOException e) {
            System.err.println("Cleanup error: " + e.getMessage());
        }
    }

    public void closeConnection() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }

}
