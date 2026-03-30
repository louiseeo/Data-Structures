import java.util.Scanner;

public class MainMenu {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice = 0;

        do {
            System.out.println("\n=================================");
            System.out.println("Welcome to EFM Enterprise Systems");
            System.out.println("We've got it all for you!");
            System.out.println("\nPlease choose one of the following:");
            System.out.println("1. EFM Grocery ShopperMart POS");
            System.out.println("2. EFM Movie Rental Registration");
            System.out.println("3. EFM Movie Registration");
            System.out.println("0. Exit");

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
                    Grocery.grocery();
                    break;
                case 2:
                    MovieRental.rental();
                    break;
                case 3:
                    MovieMenu_Delete.main(args);
                    break;
                case 0:
                    System.out.println("Thank you for using EFM Enterprise Systems! Have a good day!");
                    System.out.println("Terminating program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Pick another one.\n");
                    break;
            }

        } while (choice != 0);
    }
}