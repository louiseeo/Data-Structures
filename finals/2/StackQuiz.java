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
            System.out.println("Question # " + (index + 1));
            System.out.println(qs[index]);
            if (answered[index]) {
                if (correct[index])
                    System.out.println("Tama ka dito beh!");
                else if (!(correct[index]))
                    System.out.println("Minali mo to tey!");
            }
            System.out.println("[1] Answer  [2] Back  [3] Next  [4] Exit\n");
            System.out.print("Choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Enter another.\n");
                continue;
            }
      
            switch (choice) {
                case 1: // Answer

                    break;

                case 2: // Back
                    if (oStack.isEmpty())
                        System.out.println("No previous question yet!");
                    else {
                        index = oStack.pop();
                    }
                    break;

                case 3: // next
                    if (index < qs.length - 1) {
                        oStack.push(index);
                        index++;
                    } else
                        System.out.println("Already at the last question");
                    break;

                case 4:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice!");

            }

        } while (choice != 4);

    }

    public static void answer() {
        if (answered[index])
            System.out.println("You have already answered that question!");
        else {
            System.out.print("Answer: ");
            ans = sc.nextLine();
            if (ans.equalsIgnoreCase(as[index])) {
                System.out.println("Absolutely correct!");
                correct[index] = true; // tama sya!
                points++;

            } else {
                System.out.println("Do better next time!");
                correct[index] = false; // mali sya!

            }
            answered[index] = true;
            System.out.println("Points: " + points);
        }
    }
}