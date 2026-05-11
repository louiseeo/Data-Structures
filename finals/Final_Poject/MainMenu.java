import java.io.IOException;
import java.util.Scanner;

public class MainMenu {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileLogger.startLogging();

        int choice = 0;

        do {
            FileLogger.log("\n=================================");
            FileLogger.log("Welcome to EFM Enterprise Systems");
            FileLogger.log("We've got it all for you!");
            FileLogger.log("\nPlease choose one of the following:");
            FileLogger.log("1. EFM Grocery ShopperMart POS");
            FileLogger.log("2. EFM Movie Rental Registration");
            FileLogger.log("3. EFM Movie Registration");
            FileLogger.log("4. EFM Stack Quiz");
            FileLogger.log("5. EFM Quiz Bee");
            FileLogger.log("6. EFM Quizzer Game");
            FileLogger.log("0. Exit");

            System.out.print("Choice: ");
            if (!sc.hasNextInt()) {
                FileLogger.log("Invalid input! Enter another.\n");
                sc.nextLine();
                continue;
            }
            choice = sc.nextInt();
            sc.nextLine();

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
                    StackQuiz.main(args);
                    break;
                case 5:
                    QuizBeeMenu.main(args);
                    break;
                case 6:
                    QuizzerGame.main(args);
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