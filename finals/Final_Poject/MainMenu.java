import java.io.IOException;
import java.util.Scanner;

public class MainMenu {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileLogger.startLogging();

        int choice = 0;

        do {
            FileLogger.log("""
                    \n====================================
                    Welcome to EFM Enterprise Systems
                    We've got it all for you!
                    \nPlease choose one of the following:
                    1. EFM Grocery ShopperMart POS
                    2. EFM Movie Rental Registration
                    3. EFM Movie Registration
                    4. EFM Quizzer Game
                    0. Exit""");

            System.out.print("Choice: ");
            if (!sc.hasNextInt()) {
                FileLogger.log("Invalid input! Enter another.\n");
                sc.nextLine();
                continue;
            }
            choice = Integer.parseInt(sc.nextLine());

            FileLogger.logAnswer("Choice: " + choice); // log user input

            switch (choice) {
                case 1:
                    Grocery.grocery();
                    break;
                case 2:
                    MovieRental.rental();
                    break;
                case 3:
                    MovieMenu_OwnRegistration.main(args);
                    break;
                case 4:
                    QuizzerGame.main(args);
                    break;
                case 0:
                    FileLogger.log("Thank you for using EFM Enterprise Systems! Have a good day!");
                    FileLogger.log("Terminating program...");
                    System.exit(0);
                    break;
                default:
                    FileLogger.log("Invalid choice! Pick another one.\n");
                    break;
            }

        } while (choice != 0);
    }
}