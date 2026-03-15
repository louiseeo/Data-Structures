import java.util.*;

public class Act7 {
    // declare scanner as a global variable
    static Scanner input = new Scanner(System.in);

    // main method
    public static void main(String[] args) {
        int choice = 0;
        while (choice != 4) {
            System.out.println("""
                    \n---------------------------------
                    Welcome to EFM Enterprise Systems
                    We've got it all for you!

                    Please choose one of the following:
                    1. EFM Grocery ShopperMart POS
                    2. EFM Movie Rental Registration
                    3. EFM Movie Registration
                    4. Exit
                    """);

            System.out.print("Choice: ");
            choice = input.nextInt();
            input.nextLine();

            if (choice == 1) {
                groceryProgram(); // call program 1
            } else if (choice == 2) {
                movieRentalRegistrationProgram();
            } else if (choice == 3) {
                movieRegistrationProgram();
            } else if (choice == 4) {
                System.out.println("Exiting program... Goodbye!");
            } else {
                System.out.println("Invalid choice! Enter between 1 and 4");
            }
        }
    }

    // program 1
    public static void groceryProgram() {
        String strProdName, strAnotherP, strCustomer;
        char cAnotherP, cCustomer;
        double dQty, dBill, dPrice;
        double dTotal, dPay, dChange;

        do {
            dBill = 0;
            do {
                System.out.println("\nWelcome to EFM Grocery");
                System.out.print("Input product name: ");
                strProdName = input.nextLine();
                System.out.print("Price: ");
                dPrice = input.nextDouble();
                System.out.print("Quantity: ");
                dQty = input.nextDouble();
                input.nextLine(); // consume newline

                dTotal = dQty * dPrice;
                System.out.println("Total: " + dTotal);
                dBill += dTotal;

                System.out.print("Another product Y/N? ");
                strAnotherP = input.nextLine();
                cAnotherP = strAnotherP.charAt(0);
            } while (cAnotherP == 'Y' || cAnotherP == 'y');

            System.out.println("Bill: " + dBill);
            System.out.print("Payment: ");
            dPay = input.nextDouble();
            input.nextLine();

            if (dPay >= dBill) {
                dChange = dPay - dBill;
                System.out.println("Change: " + dChange);
                System.out.println("Thank you for shopping!");
            } else {
                System.out.println("Money is not enough!");
            }

            System.out.print("\nAnother customer Y/N? ");
            strCustomer = input.nextLine();

            cCustomer = strCustomer.charAt(0);
        } while ((cCustomer == 'Y') || (cCustomer == 'y'));
        System.out.println("Grocery program is terminating...");

    }

    // program 2
    public static void movieRentalRegistrationProgram() {

        int rent = 0, sales = 0, comedy = 0, horror = 0, scifi = 0, drama = 0,
                cartoons = 0, dvdTotal = 0, vcdTotal = 0, tapeTotal = 0;
        char cRAnother;

        do {
            System.out.println("\nRegistration");
            System.out.println("1. DVD");
            System.out.println("2. VCD");
            System.out.println("3. Tape");
            System.out.print("Choice: ");
            int choice = input.nextInt();
            if (choice == 1) {
                System.out.println("\nType: DVD");
                dvdTotal = dvdTotal + 1;
            } else if (choice == 2) {
                System.out.println("\nType: VCD");
                vcdTotal = vcdTotal + 1;
            } else if (choice == 3) {
                System.out.println("\nType: Tape");
                tapeTotal = tapeTotal + 1;
            }
            System.out.print("Input title: ");
            String title = input.nextLine();
            input.nextLine();
            System.out.println("\n1. Horror");
            System.out.println("2. Scifi");
            System.out.println("3. Drama");
            System.out.println("4. Comedy");
            System.out.println("5. Cartoons");
            System.out.print("Category: ");
            int category = input.nextInt();
            if (category == 1)
                horror = horror + 1;
            else if (category == 2)
                scifi = scifi + 1;
            else if (category == 3)
                drama = drama + 1;
            else if (category == 4)
                comedy = comedy + 1;
            else if (category == 5)
                cartoons = cartoons + 1;
            System.out.print("Minutes: ");
            int minutes = input.nextInt();
            System.out.print("Setting: ");
            String setting = input.nextLine();
            input.nextLine();
            System.out.println("\n1. Rental");
            System.out.println("2. Sales");
            System.out.print("Transaction: ");
            int transaction = input.nextInt();
            if (transaction == 1)
                rent = rent + 1;
            else if (transaction == 2)
                sales = sales + 1;
            System.out.print("Price: ");
            int price = input.nextInt();
            System.out.print("Register another?(Y/N): ");
            cRAnother = input.next().charAt(0);
        } while (cRAnother == 'y' || cRAnother == 'Y');
        System.out.println("----------REPORTS---------");
        System.out.println("For rent: " + rent);
        System.out.println("For sale: " + sales);
        System.out.println("VCD Total: " + vcdTotal);
        System.out.println("DVD Total: " + dvdTotal);
        System.out.println("Tape Total: " + tapeTotal);
        System.out.println("Horror Movies: " + horror);
        System.out.println("Scifi Movies: " + scifi);
    }

    // program 3
    public static void movieRegistrationProgram() {
        int var = 0; // declare needed variables
        char choice;
        do {
            System.out.println("\nWelcome to Movie Box Registration!");
            String movieTitle = inputString("Enter movie title: ");
            int yearReleased = inputInteger("Release date(year): ");
            String genre = inputString("Genre: ");
            double duration = inputDouble("Duration(hrs): ");
            String director = inputString("Director: ");

            var++; // increment to count the movie registered

            choice = inputString("Register another? (Y/N): ").charAt(0);
            System.out.println();

        } while (choice == 'Y' || choice == 'y');
        print(var + " movies registered today\n");
    }

    // string method
    public static String inputString(String content) {
        System.out.print(content);
        return input.nextLine();
    }

    // int method
    public static int inputInteger(String content) {
        System.out.print(content);
        int value = input.nextInt();
        input.nextLine();
        return value;
    }

    // double method
    public static double inputDouble(String content) {
        System.out.print(content);
        double value = input.nextDouble();
        input.nextLine();
        return value;
    }

    // print method
    public static void print(String content) {
        System.out.print(content);
    }
}
