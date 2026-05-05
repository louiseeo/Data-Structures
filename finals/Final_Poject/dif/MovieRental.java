package dif;
import java.util.*;

public class MovieRental {
    public static void rental() {
        int rent = 0, sales = 0, comedy = 0, horror = 0, scifi = 0,
                drama = 0, cartoons = 0, dvdTotal = 0, vcdTotal = 0, tapeTotal = 0;
        char cRAnother;

        Scanner sc = new Scanner(System.in);

        do {
            FileLogger.log("\nRegistration");
            FileLogger.log("1. DVD");
            FileLogger.log("2. VCD");
            FileLogger.log("3. Tape");
            System.out.print("Choice: ");
            int choice = sc.nextInt();
            FileLogger.logAnswer("Choice: " + choice);

            if (choice == 1) {
                FileLogger.log("Type: DVD");
                dvdTotal = dvdTotal + 1;
            } else if (choice == 2) {
                FileLogger.log("Type: VCD");
                vcdTotal = vcdTotal + 1;
            } else if (choice == 3) {
                FileLogger.log("Type: Tape");
                tapeTotal = tapeTotal + 1;
            }

            System.out.print("Input title: ");
            sc.nextLine();
            String title = sc.nextLine();
            FileLogger.logAnswer("Input title: " + title);

            FileLogger.log("\n1. Horror");
            FileLogger.log("2. Scifi");
            FileLogger.log("3. Drama");
            FileLogger.log("4. Comedy");
            FileLogger.log("5. Cartoons");

            System.out.print("Category: ");
            int category = sc.nextInt();
            FileLogger.logAnswer("Category: " + category);

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
            int minutes = sc.nextInt();
            FileLogger.logAnswer("Minutes: " + minutes);

            System.out.print("Setting: ");
            sc.nextLine();
            String setting = sc.nextLine();
            FileLogger.logAnswer("Setting: " + setting);

            FileLogger.log("\n1. Rental");
            FileLogger.log("2. Sales");

            System.out.print("Transaction: ");
            int transaction = sc.nextInt();
            FileLogger.logAnswer("Transaction: " + transaction);

            if (transaction == 1)
                rent = rent + 1;
            else if (transaction == 2)
                sales = sales + 1;

            System.out.print("Price: ");
            int price = sc.nextInt();
            FileLogger.logAnswer("Price: " + price);

            System.out.print("Register another? (Y/N) ");
            cRAnother = sc.next().charAt(0);
            FileLogger.logAnswer("Register another? (Y/N) " + cRAnother);

        } while (cRAnother == 'y' || cRAnother == 'Y');

        FileLogger.log("\nREPORTS");
        FileLogger.log("For rent: " + rent);
        FileLogger.log("For sale: " + sales);
        FileLogger.log("VCD Total: " + vcdTotal);
        FileLogger.log("DVD Total: " + dvdTotal);
        FileLogger.log("Tape Total: " + tapeTotal);
        FileLogger.log("Horror Movies: " + horror);
        FileLogger.log("Scifi Movies: " + scifi);
        FileLogger.log("Drama Movies: " + drama);
        FileLogger.log("Comedy Movies: " + comedy);
        FileLogger.log("Catoons: " + cartoons);

    }
}