package dif;
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
            System.out.println("\n-------------------------------------");
            System.out.println("Question # " + (index + 1) + " | Answered: " + countAnswered() + "/" + qs.length);
            System.out.println(qs[index]);
            if (answered[index]) {
                if (correct[index])
                    System.out.println("You got it correctly here!");
                else if (!(correct[index]))
                    System.out.println("You got this wrong!");
            }
            System.out.println("[1] Answer  [2] Back  [3] Next  [4] Exit\n");
            System.out.print("Choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Enter another.");
                continue;
            }

            switch (choice) {
                case 1: // Answer
                    answer();
                    break;

                case 2: // Back
                    if (oStack.isEmpty())
                        System.out.println("No previous question!");
                    else {
                        index = oStack.pop();
                    }
                    break;

                case 3: // Next
                    if (index < qs.length - 1) {
                        oStack.push(index);
                        index++;
                    } else
                        System.out.println("You are at the last question.");
                    break;

                case 4: // Exit
                    break;

                default:
                    System.out.println("Invalid choice! Enter another.");
            }

        } while (choice != 4);

        // Summary after quiz
        System.out.println("\n========== QUIZ OVER ==========");
        System.out.println("Final Score: " + points + "/" + qs.length);
        System.out.println("--------------------------------");
        for (int i = 0; i < qs.length; i++) {
            if (!answered[i])
                System.out.println("Q" + (i + 1) + ": Not answered");
            else if (correct[i])
                System.out.println("Q" + (i + 1) + ": Correct");
            else
                System.out.println("Q" + (i + 1) + ": Wrong (Answer: " + as[i] + ")");
        }
        System.out.println("================================");
    }

    // Method for checking the answer
    public static void answer() {
        if (answered[index])
            System.out.println("You have already answered that question!");
        else {
            System.out.print("Answer: ");
            ans = sc.nextLine().trim();
            if (ans.equalsIgnoreCase(as[index])) {
                System.out.println("Correct ka mhie!");
                correct[index] = true;
                points++;
            } else {
                System.out.println("Malii ka beh!");
                correct[index] = false;
            }
            answered[index] = true;
            System.out.println("Points: " + points);
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