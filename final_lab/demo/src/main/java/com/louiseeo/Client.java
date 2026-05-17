package com.louiseeo;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Client program for connecting to the UnderCoven server.
 * Handles user input and displays messages from the server.
 *
 * @author louiseeo
 */
public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 8000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner scanner = new Scanner(System.in)) {

            // Listener thread
            Thread listener = new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        if (msg.startsWith("|") || msg.startsWith("+")) {
                            System.out.println(msg);
                        } else {
                            System.out.println("\n" + msg);
                            if ((msg.contains("CHAT PHASE")) ||
                                    (msg.startsWith("[")) ||
                                    (msg.contains("wants")) ||
                                    (msg.contains("need")) ||
                                    (msg.contains("joined!")) ||
                                    (msg.contains("ready!!")) ||
                                    (msg.contains("Invalid")) ||
                                    (msg.contains("cannot")) ||
                                    (msg.contains("again?")) ||
                                    (msg.contains("number")) ||
                                    (msg.contains("username")) ||
                                    (msg.contains("password")) ||
                                    (msg.contains("Enter choice"))) {
                                System.out.print("> ");
                            }
                        }
                    }
                    System.out.println("\n===== Server has shut down. =====");
                    System.exit(0);
                } catch (IOException e) {
                    System.out.println("\n===== Disconnected from server. =====");
                    System.exit(0);
                }
            });

            listener.setDaemon(true);
            listener.start();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true) {
                String input = scanner.nextLine().trim();

                if (!input.isEmpty()) {
                    out.println(input);
                }
            }

        } catch (IOException e) {
            System.err.println("Client Error: " + e.getMessage());
        }
    }
}
