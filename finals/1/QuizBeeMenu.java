import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class QuizBeeMenu {
    static Scanner sc = new Scanner(System.in);
    static String filename = "questions.txt";
    static List<Question> quiz = new ArrayList<>();

    public static void main(String[] args) {
        int choice = 0;
        boolean isPlayerRegistered = false;

        loadQuestions();
        do {
            System.out.println("""
                    Welcome to Quiz Bee Game Menu!
                    [1] Player Registration
                    [2] Play
                    [3] Exit
                    """);
            System.out.print("Choice: ");
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Enter another.\n");
                sc.nextLine();
                continue;
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    playerRegister();
                    isPlayerRegistered = true;
                    break;
                case 2:
                    if (isPlayerRegistered)
                        displayQuiz();
                    else
                        System.out.println("\nCannot start the quiz bee. Register first!\n");
                    break;
                case 3:
                    System.out.println("Thank you for playing!");
                    System.out.println("Terminating program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Pick another one.\n");
                    break;
            }

        } while (choice != 3);
    }

    // Player Register
    public static void playerRegister() {
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.println("\nWelcome to Quiz Bee " + name + "!");
        System.out.println("You may now proceed to menu 2.\n");
    }

    // Extract the array from the txt file
    public static void loadQuestions() {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            String currentType = "";

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty())
                    continue;

                // Check if it's a type tag
                if (line.equals("MC") || line.equals("ID") || line.equals("FILL")) {
                    currentType = line;
                    continue;
                }

                // Otherwise it's a question — read based on current type
                if (currentType.equals("MC")) {
                    String text = line;
                    String a = br.readLine();
                    String b = br.readLine();
                    String c = br.readLine();
                    String d = br.readLine();
                    String answer = br.readLine();
                    quiz.add(new Question("MC", text, new String[] { a, b, c, d }, answer));

                } else if (currentType.equals("ID") || currentType.equals("FILL")) {
                    String text = line;
                    String answer = br.readLine();
                    quiz.add(new Question(currentType, text, null, answer));
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    // Display the array of quizzes
    public static void displayQuiz() {
        if (quiz.isEmpty()) {
            System.out.println("No quiz found on the list.");
            return;
        }

        int score = 0;
        int questionNum = 1;

        System.out.println("--- Starting the Quiz! ---\n");

        for (Question q : quiz) {
            // 1. Display the Question
            System.out.println("Question " + questionNum + ": " + q.text);
            questionNum++;

            // 2. If it's Multiple Choice, show the options
            if (q.type.equals("MC") && q.options != null) {
                for (String option : q.options) {
                    System.out.println(option);
                }
            }

            // 3. Get the user's answer
            System.out.print("Your Answer: ");
            String userAnswer = sc.nextLine();

            // 4. Check if correct (case-insensitive for ID/FIB)
            if (userAnswer.equalsIgnoreCase(q.answer)) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Wrong. The correct answer was: " + q.answer + "\n");
            }
        }

        // 5. Final Score
        System.out.println("========== QUIZ OVER ==========");
        System.out.println("Final score: " + score + "/" + quiz.size());

    }
}

// HELPER CLASS
class Question {
    String type, text, answer;
    String[] options;

    public Question() {
    }

    public Question(String type, String text, String[] options, String answer) {
        this.type = type;
        this.text = text;
        this.options = options;
        this.answer = answer;

    }
}