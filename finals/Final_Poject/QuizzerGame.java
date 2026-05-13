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
        players.clear(); // clear both to prevent duplicates
        questions.clear(); 
        loadPlayers(); // load the players
        loadQuestions();

        FileLogger.log("\n=============== WELCOME TO QUIZZER GAME ================");
        boolean running = true;

        while (running) {
            FileLogger.log("""
                    \n------- MAIN MENU -------
                    [1] Player Registration
                    [2] Question Bank
                    [3] Play Game
                    [4] Exit
                    """);
            System.out.print("Choice: ");
            String choice = sc.nextLine().trim();
            FileLogger.logAnswer("Choice: " + choice);

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
                    FileLogger.log("\nSaving data and exiting... Goodbye!");
                    running = false;
                    break;
                default:
                    FileLogger.log("Invalid choice! Try again.");
            }
        }

    }

    public static void playerMenu() {
        boolean back = false;

        while (!back) {
            FileLogger.log("""
                    \n------- PLAYER REGISTRATION -------
                    [1] Add Player
                    [2] Edit Player
                    [3] Delete Player
                    [4] List Players
                    [5] Search Player
                    [6] Leaderboard
                    [0] Back
                    """);
            System.out.print("Choice: ");
            String choice = sc.nextLine().trim();
            FileLogger.logAnswer("Choice: " + choice);

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
                    FileLogger.log("Invalid choice! Try again.");
            }
        }
    }

    public static void questionMenu() {
        boolean back = false;

        while (!back) {
            FileLogger.log("""
                    \n------- QUESTION BANK -------
                    [1] Add Question
                    [2] Edit Question
                    [3] Delete Question
                    [4] List Questions
                    [5] Search Question
                    [0] Back
                    """);
            System.out.print("Choice: ");
            String choice = sc.nextLine().trim();
            FileLogger.logAnswer("Choice: " + choice);

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
                    FileLogger.log("Invalid choice! Try again.");
            }
        }
    }

    // Player Registration case 1: adding player
    public static void addPlayer() {
        System.out.print("Enter player name: ");
        String name = sc.nextLine();
        FileLogger.logAnswer("Enter player name: " + name);
        if (name.isEmpty()) {
            FileLogger.log("Player name must not be empty!");
        } else {
            players.add(name + "|0");
            savePlayers();
            FileLogger.log("Player added successfully!");
        }

    }

    // Player Registration case 2: editing player
    public static void editPlayer() {
        if (players.isEmpty()) {
            FileLogger.log("No players found.");
            return;
        }

        listPlayers();
        System.out.print("Enter player number to edit: ");
        String input = sc.nextLine().trim();
        FileLogger.logAnswer("Enter player number to edit: " + input);
        int index;
        try {
            index = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            FileLogger.log("Invalid input! Please enter a number.");
            return;
        }

        if (index < 0 || index >= players.size()) {
            FileLogger.log("Invalid number!");
            return;
        }

        // keep the old scores
        String[] parts = players.get(index).split("\\|");
        String oldName = parts[0];
        String score = parts[1];

        System.out.print("Enter new name (old: " + oldName + "): ");
        String newName = sc.nextLine().trim();
        FileLogger.logAnswer("Enter new name (old: " + oldName + "): " + newName);
        if (newName.isEmpty()) {
            FileLogger.log("Name must not be empty!");
            return;
        }

        // reassemble with same scores
        players.set(index, newName + "|" + score);
        savePlayers();
        FileLogger.log("Player updated successfully!");
    }

    // Player registration case 3: deleting players
    public static void deletePlayer() {
        if (players.isEmpty()) {
            FileLogger.log("No players found.");
            return;
        }

        listPlayers();
        System.out.print("Enter player number to delete: ");
        String input = sc.nextLine().trim();
        FileLogger.logAnswer("Enter player number to delete: " + input);
        int index;
        try {
            index = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            FileLogger.log("Invalid input! Please enter a number.");
            return;
        }

        if (index < 0 || index >= players.size()) {
            FileLogger.log("Invalid number!");
            return;
        }

        String[] parts = players.get(index).split("\\|");
        System.out.print("Are you sure you want to delete " + parts[0] + "? (y/n): ");
        String confirm = sc.nextLine().trim();
        FileLogger.logAnswer("Are you sure you want to delete " + parts[0] + "? (y/n): " + confirm);
        if (confirm.equalsIgnoreCase("y")) {
            players.remove(index);
            savePlayers();
            FileLogger.log("Player deleted successfully!");
        } else {
            FileLogger.log("Deletion cancelled.");
        }
    }

    // Player registration case 4: listing players
    public static void listPlayers() {
        if (players.isEmpty()) {
            FileLogger.log("No players saved yet.");
            return;
        }

        FileLogger.log("\n---------- PLAYER LIST ----------");
        for (int i = 0; i < players.size(); i++) {
            String[] parts = players.get(i).split("\\|");
            FileLogger.log("[" + (i + 1) + "] " + parts[0]);
        }
    }

    // Player registration case 5: searching players
    public static void searchPlayer() {
        System.out.print("Enter player name to search: ");
        String name = sc.nextLine().trim();
        FileLogger.logAnswer("Enter player name to search: " + name);
        boolean found = false;
        for (int i = 0; i < players.size(); i++) {
            String[] parts = players.get(i).split("\\|");
            if (parts[0].equalsIgnoreCase(name)) {
                FileLogger.log("Found: [" + (i + 1) + "] " + parts[0]);
                found = true;
                break;
            }
        }

        if (!found) {
            FileLogger.log("Player not found.");
        }
    }

    // Player registration case 6: displaying leaderboard
    public static void leaderboard() {
        if (players.isEmpty()) {
            FileLogger.log("No players found.");
            return;
        }

        // sort by score descending
        List<String> sorted = new ArrayList<>(players);
        sorted.sort((a, b) -> {
            int scoreA = Integer.parseInt(a.split("\\|")[1]);
            int scoreB = Integer.parseInt(b.split("\\|")[1]);
            return scoreB - scoreA;
        });

        FileLogger.log("\n---------- LEADERBOARD ----------");
        for (int i = 0; i < sorted.size(); i++) {
            String[] parts = sorted.get(i).split("\\|");
            FileLogger.log("[" + (i + 1) + "] " + parts[0] + " - " + parts[1] + " pts");
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
            FileLogger.log("Error saving players: " + e.getMessage());
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
            FileLogger.log("Error loading players: " + e.getMessage());
        }
    }

    // Question registration case 1: adding question
    public static void addQuestion() {
        System.out.print("Enter question: ");
        String question = sc.nextLine().trim();
        FileLogger.logAnswer("Enter question: " + question);
        if (question.isEmpty()) {
            FileLogger.log("Question must not be empty!");
            return;
        }

        System.out.print("Enter answer: ");
        String answer = sc.nextLine().trim();
        FileLogger.logAnswer("Enter answer: " + answer);
        if (answer.isEmpty()) {
            FileLogger.log("Answer must not be empty!");
            return;
        }

        questions.add(question + "|" + answer);
        saveQuestions();
        FileLogger.log("Question added successfully!");
    }

    // Question registration case 2: editing question
    public static void editQuestion() {
        if (questions.isEmpty()) {
            FileLogger.log("No questions found.");
            return;
        }

        listQuestions();
        System.out.print("Enter question number to edit: ");
        String input = sc.nextLine().trim();
        FileLogger.logAnswer("Enter question number to edit: " + input);
        int index;
        try {
            index = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            FileLogger.log("Invalid input! Please enter a number.");
            return;
        }

        if (index < 0 || index >= questions.size()) {
            FileLogger.log("Invalid number!");
            return;
        }

        System.out.print("Enter new question: ");
        String newQuestion = sc.nextLine().trim();
        FileLogger.logAnswer("Enter new question: " + newQuestion);
        if (newQuestion.isEmpty()) {
            FileLogger.log("Question must not be empty!");
            return;
        }

        System.out.print("Enter new answer: ");
        String newAnswer = sc.nextLine().trim();
        FileLogger.logAnswer("Enter new answer: " + newAnswer);
        if (newAnswer.isEmpty()) {
            FileLogger.log("Answer must not be empty!");
            return;
        }

        // reassemble with same scores
        questions.set(index, newQuestion + "|" + newAnswer);
        saveQuestions();
        FileLogger.log("Question updated successfully!");
    }

    // Question registration case 3: deleting questions
    public static void deleteQuestion() {
        if (questions.isEmpty()) {
            FileLogger.log("No questions found.");
            return;
        }

        listQuestions();
        System.out.print("Enter question number to delete: ");
        String input = sc.nextLine().trim();
        FileLogger.logAnswer("Enter question number to delete: " + input);
        int index;
        try {
            index = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            FileLogger.log("Invalid input! Please enter a number.");
            return;
        }

        if (index < 0 || index >= questions.size()) {
            FileLogger.log("Invalid number!");
            return;
        }

        System.out.print("Are you sure you want to delete this question? (y/n): ");
        String confirm = sc.nextLine().trim();
        FileLogger.logAnswer("Are you sure you want to delete this question? (y/n): " + confirm);

        if (confirm.equalsIgnoreCase("y")) {
            questions.remove(index);
            saveQuestions();
            FileLogger.log("Question deleted successfully!");
        } else {
            FileLogger.log("Deletion cancelled.");
        }
    }

    // Question registration case 4: listing questions
    public static void listQuestions() {
        if (questions.isEmpty()) {
            FileLogger.log("No questions saved yet.");
            return;
        }

        FileLogger.log("\n---------- QUESTIONS ----------");
        for (int i = 0; i < questions.size(); i++) {
            String[] parts = questions.get(i).split("\\|");
            FileLogger.log("[" + (i + 1) + "] " + parts[0]);
        }
    }

    // Question registration case 5: search questions
    public static void searchQuestion() {
        System.out.print("Enter question to search: ");
        String keyword = sc.nextLine().trim();
        FileLogger.logAnswer("Enter question to search: " + keyword);

        boolean found = false;
        for (int i = 0; i < questions.size(); i++) {
            String[] parts = questions.get(i).split("\\|");
            if (parts[0].equalsIgnoreCase(keyword)) {
                FileLogger.log("Found: [" + (i + 1) + "] " + parts[0]);
                found = true;
                break;
            }
        }

        if (!found) {
            FileLogger.log("Question not found.");
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
            FileLogger.log("Error loading questions: " + e.getMessage());
        }
    }

    public static void saveQuestions() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(questionFilename))) {
            for (String data : questions) {
                bw.write(data);
                bw.newLine();
            }
        } catch (IOException e) {
            FileLogger.log("Error saving questions: " + e.getMessage());
        }
    }

    // PLAY GAME METHOD
    public static void playGame() {
        if (players.isEmpty()) {
            FileLogger.log("No players registered yet!");
            return;
        }
        if (questions.isEmpty()) {
            FileLogger.log("No questions in the bank yet!");
            return;
        }

        // pick a player
        listPlayers();
        System.out.print("Enter player number: ");

        int playerIndex;
        try {
            playerIndex = Integer.parseInt(sc.nextLine().trim()) - 1;
            FileLogger.logAnswer("Enter player number: " + playerIndex);
        } catch (NumberFormatException e) {
            FileLogger.log("Invalid input! Please enter a number.");
            return;
        }

        if (playerIndex < 0 || playerIndex >= players.size()) {
            FileLogger.log("Invalid number!");
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

            FileLogger.log("\n-------------------------------------");
            FileLogger.log(
                    "Question # " + (index + 1) + " | Answered: " + countAnswered(answered) + "/" + shuffled.size());
            FileLogger.log(question);
            if (answered[index]) {
                if (correct[index])
                    FileLogger.log("You got this correctly!");
                else
                    FileLogger.log("You got this wrong!");
            }
            FileLogger.log("[1] Answer  [2] Back  [3] Next  [4] Exit");
            System.out.print("Choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
                FileLogger.logAnswer("Choice: " + choice);
            } catch (NumberFormatException e) {
                FileLogger.log("Invalid input!");
                continue;
            }

            switch (choice) {
                case 1: // answer
                    if (answered[index]) {
                        FileLogger.log("Already answered!");
                    } else {
                        System.out.print("Answer: ");
                        String userAnswer = sc.nextLine().trim();
                        FileLogger.logAnswer("Answer: " + userAnswer);
                        if (userAnswer.equalsIgnoreCase(answer)) {
                            FileLogger.log("Correct!");
                            correct[index] = true;
                            points++;
                        } else {
                            FileLogger.log("Wrong! Answer was: " + answer);
                            correct[index] = false;
                        }
                        answered[index] = true;
                        FileLogger.log("Points: " + points);
                    }
                    break;
                case 2: // back
                    if (oStack.isEmpty())
                        FileLogger.log("No previous question!");
                    else
                        index = oStack.pop();
                    break;
                case 3: // next
                    if (index < shuffled.size() - 1) {
                        oStack.push(index);
                        index++;
                    } else
                        FileLogger.log("You are at the last question.");
                    break;
                case 4: // exit
                    break;
                default:
                    FileLogger.log("Invalid choice!");
            }

        } while (choice != 4);

        // summary
        FileLogger.log("\n========== QUIZ OVER ==========");
        FileLogger.log("Player: " + playerName);
        FileLogger.log("Final Score: " + points + "/" + shuffled.size());
        FileLogger.log("--------------------------------");
        for (int i = 0; i < shuffled.size(); i++) {
            String q = shuffled.get(i).split("\\|")[0];
            if (!answered[i])
                FileLogger.log("Q" + (i + 1) + ": Not answered - " + q);
            else if (correct[i])
                FileLogger.log("Q" + (i + 1) + ": Correct - " + q);
            else
                FileLogger.log("Q" + (i + 1) + ": Wrong - " + q);
        }
        FileLogger.log("================================");

        // update score
        int oldScore = Integer.parseInt(playerParts[1]);
        players.set(playerIndex, playerName + "|" + (oldScore + points));
        savePlayers();
        FileLogger.log("Score saved!");
    }

    public static int countAnswered(boolean[] answered) {
        int count = 0;
        for (boolean b : answered)
            if (b)
                count++;
        return count;
    }
}