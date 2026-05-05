import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizzerGame {

    static Scanner sc = new Scanner(System.in);

    // Static fields for Player Registration
    static String filename = "players.txt";
    static List<String> players = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("\n=============== WELCOME TO QUIZZER GAME ================");
        boolean running = true;

        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("[1] Player Registration");
            System.out.println("[2] Question Bank");
            System.out.println("[3] Play Game");
            System.out.println("[4] Exit");
            System.out.print("Choice: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    playerMenu(); // not done yet
                    break;
                case "2":
                    questionMenu(); // not done yet
                    break;
                case "3":
                    // play game
                    break;
                case "4":
                    System.out.println("\nSaving data and exiting... Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }

        sc.close();
    }

    public static void playerMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- PLAYER REGISTRATION ---");
            System.out.println("[1] Add Player");
            System.out.println("[2] Edit Player");
            System.out.println("[3] Delete Player");
            System.out.println("[4] List Players");
            System.out.println("[5] Search Player");
            System.out.println("[6] Leaderboard");
            System.out.println("[0] Back");
            System.out.print("Choice: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    addPlayer();
                    break;
                case "2":
                    // edit player
                    break;
                case "3":
                    // delete player
                    break;
                case "4":
                    // list players
                    break;
                case "5":
                    // search players
                    break;
                case "6":
                    // display leaderboard
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void questionMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- QUESTION BANK ---");
            System.out.println("[1] Add Question");
            System.out.println("[2] Edit Question");
            System.out.println("[3] Delete Question");
            System.out.println("[4] List Questions");
            System.out.println("[5] Search Question");
            System.out.println("[0] Back");
            System.out.print("Choice: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    // add question
                    break;
                case "2":
                    // edit question
                    break;
                case "3":
                    // delete question
                    break;
                case "4":
                    // list questions
                    break;
                case "5":
                    // search questions
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // Player Registration case 1: adding player
    public static void addPlayer() {
        System.out.print("Enter player name: ");
        String name = sc.nextLine();
        if (name.isEmpty()) {
            System.out.println("Player name must not be blank!");
        } else {
            players.add(name);
        }

        savePlayers();
    }

    // Player Registration case 2: editing player
    public static void editPlayer() {

    }

    // HELPER METHODS
    public static void savePlayers() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (String data : players) {
                bw.write(data);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving players: " + e.getMessage());
        }
    }

}