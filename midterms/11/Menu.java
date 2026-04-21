import java.util.*;

public class Menu {
    static Scanner sc = new Scanner(System.in);
    static String filename = "questions.txt";
    static List<String> quiz = new ArrayList<>();

    public static void main(String[] args) {
        int choice = 0;
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
                    //register player
                    break;
                case 2:
                    //play module
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

}