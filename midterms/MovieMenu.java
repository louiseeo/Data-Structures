import java.util.*;

public class MovieMenu {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = 0;

        do {
            System.out.println("""
                    \n===================================
                    Welcome to Movie Registration Menu!
                    [1] Add
                    [2] Search
                    [3] Edit
                    [4] Delete
                    [5] Sort
                    [6] List
                    [0] Exit
                    """);
            System.out.print("Choice: ");
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Enter another.");
                sc.nextLine();
                continue;
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    // Add function
                    break;
                case 2:
                    // Search
                    break;
                case 3:
                    // Edit
                    break;
                case 4:
                    // Delete
                    break;
                case 5:
                    // Sort
                    break;
                case 6:
                    // List / Display items
                    break;
                case 0:
                    System.out.println("Thank you for using the Movie Registration System!");
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid input! Enter a valid choice.");
            }

        } while (choice != 0);
    }
}