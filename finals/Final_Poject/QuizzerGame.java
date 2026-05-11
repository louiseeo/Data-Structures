import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class QuizzerGame {

    static Scanner sc = new Scanner(System.in);

    // Static fields for Player Registration
    static String filename = "players.txt";
    static List<String> players = new ArrayList<>();

    // Static fields for question
    static String questionFilename = "questions_quizzergame.txt";
    static List<String> questions = new ArrayList<>();

    public static void main(String[] args) {
        loadPlayers(); // load the players
        loadQuestions();

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
                    playerMenu();
                    break;
                case "2":
                    questionMenu();
                    break;
                case "3":
                    playGame();
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
                    editPlayer();
                    break;
                case "3":
                    deletePlayer();
                    break;
                case "4":
                    listPlayers();
                    break;
                case "5":
                    searchPlayer();
                    break;
                case "6":
                    leaderboard();
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
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
                    addQuestion();
                    break;
                case "2":
                    editQuestion();
                    break;
                case "3":
                    deleteQuestion();
                    break;
                case "4":
                    listQuestions();
                    break;
                case "5":
                    searchQuestion();
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // Player Registration case 1: adding player
    public static void addPlayer() {
        System.out.print("Enter player name: ");
        String name = sc.nextLine();
        if (name.isEmpty()) {
            System.out.println("Player name must not be empty!");
        } else {
            players.add(name + "|0");
            savePlayers();
            System.out.println("Player added succesfully!");
        }

    }

    // Player Registration case 2: editing player
    public static void editPlayer() {
        if (players.isEmpty()) {
            System.out.println("No players found.");
            return;
        }

        listPlayers();
        System.out.print("Enter player number to edit: ");
        String input = sc.nextLine().trim();
        int index = Integer.parseInt(input) - 1;

        if (index < 0 || index >= players.size()) {
            System.out.println("Invalid number!");
            return;
        }

        // keep the old scores
        String[] parts = players.get(index).split("\\|");
        String oldName = parts[0];
        String score = parts[1];

        System.out.print("Enter new name (old: " + oldName + "): ");
        String newQuestion = sc.nextLine().trim();

        if (newQuestion.isEmpty()) {
            System.out.println("Name must not be empty!");
            return;
        }

        // reassemble with same scores
        players.set(index, newQuestion + "|" + score);
        savePlayers();
        System.out.println("Player updated successfully!");
    }

    // Player registration case 3: deleting players
    public static void deletePlayer() {
        if (players.isEmpty()) {
            System.out.println("No players found.");
            return;
        }

        listPlayers();
        System.out.print("Enter player number to delete: ");
        String input = sc.nextLine().trim();
        int index = Integer.parseInt(input) - 1;

        if (index < 0 || index >= players.size()) {
            System.out.println("Invalid number!");
            return;
        }

        String[] parts = players.get(index).split("\\|");
        System.out.print("Are you sure you want to delete " + parts[0] + "? (y/n): ");
        String confirm = sc.nextLine().trim();

        if (confirm.equalsIgnoreCase("y")) {
            players.remove(index);
            savePlayers();
            System.out.println("Player deleted successfully!");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    // Player registration case 4: listing players
    public static void listPlayers() {
        if (players.isEmpty()) {
            System.out.println("No players saved yet.");
            return;
        }

        System.out.println("\n---------- PLAYER LIST ----------");
        for (int i = 0; i < players.size(); i++) {
            String[] parts = players.get(i).split("\\|");
            System.out.println("[" + (i + 1) + "] " + parts[0]);
        }
    }

    // Player registration case 5: searching players
    public static void searchPlayer() {
        System.out.print("Enter player name to search: ");
        String name = sc.nextLine().trim();

        boolean found = false;
        for (int i = 0; i < players.size(); i++) {
            String[] parts = players.get(i).split("\\|");
            if (parts[0].equalsIgnoreCase(name)) {
                System.out.println("Found: [" + (i + 1) + "] " + parts[0]);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Player not found.");
        }
    }

    // Player registration case 6: displaying leaderboard
    public static void leaderboard() {
        if (players.isEmpty()) {
            System.out.println("No players found.");
            return;
        }

        // sort by score descending
        List<String> sorted = new ArrayList<>(players);
        sorted.sort((a, b) -> {
            int scoreA = Integer.parseInt(a.split("\\|")[1]);
            int scoreB = Integer.parseInt(b.split("\\|")[1]);
            return scoreB - scoreA;
        });

        System.out.println("\n---------- LEADERBOARD ----------");
        for (int i = 0; i < sorted.size(); i++) {
            String[] parts = sorted.get(i).split("\\|");
            System.out.println("[" + (i + 1) + "] " + parts[0] + " - " + parts[1] + " pts");
        }
    }

    // HELPER METHODS FOR PLAYER REGISTRATION
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

    public static void loadPlayers() {
        File file = new File(filename);
        if (!file.exists())
            return;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    players.add(line.trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading players: " + e.getMessage());
        }
    }

    // Question registration case 1: adding question
    public static void addQuestion() {
        System.out.print("Enter question: ");
        String question = sc.nextLine().trim();
        if (question.isEmpty()) {
            System.out.println("Question must not be empty!");
            return;
        }

        System.out.print("Enter answer: ");
        String answer = sc.nextLine().trim();
        if (answer.isEmpty()) {
            System.out.println("Answer must not be empty!");
            return;
        }

        questions.add(question + "|" + answer);
        saveQuestions();
        System.out.println("Question added successfully!");
    }

    // Question registration case 2: editing question
    public static void editQuestion() {
        if (questions.isEmpty()) {
            System.out.println("No questions found.");
            return;
        }

        listQuestions();
        System.out.print("Enter question number to edit: ");
        String input = sc.nextLine().trim();
        int index = Integer.parseInt(input) - 1;

        if (index < 0 || index >= questions.size()) {
            System.out.println("Invalid number!");
            return;
        }

        System.out.print("Enter new question: ");
        String newQuestion = sc.nextLine().trim();

        if (newQuestion.isEmpty()) {
            System.out.println("Question must not be empty!");
            return;
        }

        System.out.print("Enter new answer: ");
        String newAnswer = sc.nextLine().trim();

        if (newAnswer.isEmpty()) {
            System.out.println("Question must not be empty!");
            return;
        }

        // reassemble with same scores
        questions.set(index, newQuestion + "|" + newAnswer);
        saveQuestions();
        System.out.println("Question updated successfully!");
    }

    // Question registration case 3: deleting questions
    public static void deleteQuestion() {
        if (questions.isEmpty()) {
            System.out.println("No questions found.");
            return;
        }

        listQuestions();
        System.out.print("Enter question number to delete: ");
        String input = sc.nextLine().trim();
        int index = Integer.parseInt(input) - 1;

        if (index < 0 || index >= questions.size()) {
            System.out.println("Invalid number!");
            return;
        }

        System.out.print("Are you sure you want to delete this questoin ? (y/n): ");
        String confirm = sc.nextLine().trim();

        if (confirm.equalsIgnoreCase("y")) {
            questions.remove(index);
            saveQuestions();
            System.out.println("Question deleted successfully!");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    // Question registration case 4: listing questions
    public static void listQuestions() {
        if (questions.isEmpty()) {
            System.out.println("No questions saved yet.");
            return;
        }

        System.out.println("\n---------- QUESTIONS ----------");
        for (int i = 0; i < questions.size(); i++) {
            String[] parts = questions.get(i).split("\\|");
            System.out.println("[" + (i + 1) + "] " + parts[0]);
        }
    }

    // Question registration case 5: search questions
    public static void searchQuestion() {
        System.out.print("Enter question to search: ");
        String keyword = sc.nextLine().trim();

        boolean found = false;
        for (int i = 0; i < questions.size(); i++) {
            String[] parts = questions.get(i).split("\\|");
            if (parts[0].equalsIgnoreCase(keyword)) {
                System.out.println("Found: [" + (i + 1) + "] " + parts[0]);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Question not found.");
        }
    }

    // HELPER METHODS FOR QUESTIONS REGISTRATION
    public static void loadQuestions() {
        File file = new File(questionFilename);
        if (!file.exists())
            return;

        try (BufferedReader br = new BufferedReader(new FileReader(questionFilename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    questions.add(line.trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading questions: " + e.getMessage());
        }
    }

    public static void saveQuestions() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(questionFilename))) {
            for (String data : questions) {
                bw.write(data);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving questions: " + e.getMessage());
        }
    }

    // PLAY GAME METHOD
    public static void playGame() {
        if (players.isEmpty()) {
            System.out.println("No players registered yet!");
            return;
        }
        if (questions.isEmpty()) {
            System.out.println("No questions in the bank yet!");
            return;
        }

        // pick a player
        listPlayers();
        System.out.print("Enter player number: ");
        int playerIndex = Integer.parseInt(sc.nextLine().trim()) - 1;

        if (playerIndex < 0 || playerIndex >= players.size()) {
            System.out.println("Invalid number!");
            return;
        }

        String[] playerParts = players.get(playerIndex).split("\\|");
        String playerName = playerParts[0];

        // shuffle questions
        List<String> shuffled = new ArrayList<>(questions);
        Collections.shuffle(shuffled);

        // game state
        int index = 0;
        int points = 0;
        Stack<Integer> oStack = new Stack<>();
        boolean[] answered = new boolean[shuffled.size()];
        boolean[] correct = new boolean[shuffled.size()];

        int choice = -1;
        do {
            String[] qParts = shuffled.get(index).split("\\|");
            String question = qParts[0];
            String answer = qParts[1];

            System.out.println("\n-------------------------------------");
            System.out.println(
                    "Question # " + (index + 1) + " | Answered: " + countAnswered(answered) + "/" + shuffled.size());
            System.out.println(question);
            if (answered[index]) {
                if (correct[index])
                    System.out.println("You got this correctly!");
                else
                    System.out.println("You got this wrong!");
            }
            System.out.println("[1] Answer  [2] Back  [3] Next  [4] Exit");
            System.out.print("Choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
                continue;
            }

            switch (choice) {
                case 1: // answer
                    if (answered[index]) {
                        System.out.println("Already answered!");
                    } else {
                        System.out.print("Answer: ");
                        String userAnswer = sc.nextLine().trim();
                        if (userAnswer.equalsIgnoreCase(answer)) {
                            System.out.println("Correct!");
                            correct[index] = true;
                            points++;
                        } else {
                            System.out.println("Wrong! Answer was: " + answer);
                            correct[index] = false;
                        }
                        answered[index] = true;
                        System.out.println("Points: " + points);
                    }
                    break;
                case 2: // back
                    if (oStack.isEmpty())
                        System.out.println("No previous question!");
                    else
                        index = oStack.pop();
                    break;
                case 3: // next
                    if (index < shuffled.size() - 1) {
                        oStack.push(index);
                        index++;
                    } else
                        System.out.println("You are at the last question.");
                    break;
                case 4: // exit
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 4);

        // summary
        System.out.println("\n========== QUIZ OVER ==========");
        System.out.println("Player: " + playerName);
        System.out.println("Final Score: " + points + "/" + shuffled.size());
        System.out.println("--------------------------------");
        for (int i = 0; i < shuffled.size(); i++) {
            String q = shuffled.get(i).split("\\|")[0];
            if (!answered[i])
                System.out.println("Q" + (i + 1) + ": Not answered - " + q);
            else if (correct[i])
                System.out.println("Q" + (i + 1) + ": Correct - " + q);
            else
                System.out.println("Q" + (i + 1) + ": Wrong - " + q);
        }
        System.out.println("================================");

        // update score
        int oldScore = Integer.parseInt(playerParts[1]);
        players.set(playerIndex, playerName + "|" + (oldScore + points));
        savePlayers();
        System.out.println("Score saved!");
    }

    public static int countAnswered(boolean[] answered) {
        int count = 0;
        for (boolean b : answered)
            if (b)
                count++;
        return count;
    }
}