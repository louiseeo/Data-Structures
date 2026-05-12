import java.io.File;
import java.util.Scanner;
import java.util.Stack;

public class StackQuiz {
    static Scanner sc = new Scanner(System.in);
    static int choice, points = 0, index = 0;
    static Stack<Integer> oStack = new Stack<>();
    static String ans;
    static boolean answered[] = new boolean[10];
    static boolean correct[] = new boolean[10];

    static String qs[] = {
            "What is the largest organ in our body?",
            "What is the first book in the Philippines?",
            "What is 5432 + 2367?",
            "Who is the first programmer in the world?",
            "What country colonized the philippines for over 300 years?",
            "What do you call an animal with no backbone?",
            "What is the structure of DNA?",
            "What is the smallest prime number?",
            "What year did world war 2 end?",
            "Who is the first president of the Philippines?"
    };

    static String as[] = { "Skin", "Doctrina Cristiana", "7799", "Ada Lovelace", "Spain", "Invertebrate",
            "double-helix", "2", "1945", "Emilio Aguinaldo" };

    public static void main(String[] args) {
        int choice = -1;
        do {
            FileLogger.log("\n-------------------------------------");
            FileLogger.log("Question # " + (index + 1) + " | Answered: " + countAnswered() + "/" + qs.length);
            FileLogger.log(qs[index]);
            if (answered[index]) {
                if (correct[index])
                    FileLogger.log("You got it correctly here!");
                else if (!(correct[index]))
                    FileLogger.log("You got this wrong!");
            }
            FileLogger.log("[1] Answer  [2] Back  [3] Next  [4] Exit\n");
            System.out.print("Choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                FileLogger.log("Invalid input! Enter another.");
                continue;
            }

            FileLogger.logAnswer("Choice: " + choice);

            switch (choice) {
                case 1: // Answer
                    answer();
                    break;

                case 2: // Back
                    if (oStack.isEmpty())
                        FileLogger.log("No previous question!");
                    else {
                        index = oStack.pop();
                    }
                    break;

                case 3: // Next
                    if (index < qs.length - 1) {
                        oStack.push(index);
                        index++;
                    } else
                        FileLogger.log("You are at the last question.");
                    break;

                case 4: // Exit
                    break;

                default:
                    FileLogger.log("Invalid choice! Enter another.");
            }

        } while (choice != 4);

        // Summary after quiz
        FileLogger.log("\n========== QUIZ OVER ==========");
        FileLogger.log("Final Score: " + points + "/" + qs.length);
        FileLogger.log("--------------------------------");
        for (int i = 0; i < qs.length; i++) {
            if (!answered[i])
                FileLogger.log("Q" + (i + 1) + ": Not answered");
            else if (correct[i])
                FileLogger.log("Q" + (i + 1) + ": Correct");
            else
                FileLogger.log("Q" + (i + 1) + ": Wrong (Answer: " + as[i] + ")");
        }
        FileLogger.log("================================");
    }

    // Method for checking the answer
    public static void answer() {
        if (answered[index])
            FileLogger.log("You have already answered that question!");
        else {
            System.out.print("Answer: ");
            ans = sc.nextLine().trim();
            FileLogger.logAnswer("Answer: " + ans);
            if (ans.equalsIgnoreCase(as[index])) {
                FileLogger.log("You are correct!");
                correct[index] = true;
                points++;
            } else {
                FileLogger.log("Oh no! You got it wrong...");
                correct[index] = false;
            }
            answered[index] = true;
            FileLogger.log("Points: " + points);
        }
    }

    // Method that counts the correct answered
    public static int countAnswered() {
        int count = 0;
        for (boolean b : answered)
            if (b)
                count++;
        return count;
    }
}