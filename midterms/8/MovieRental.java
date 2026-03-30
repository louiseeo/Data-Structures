import java.util.*;

public class MovieRental {
    public static void rental() {
        int rent = 0, sales = 0, comedy = 0, horror = 0, scifi = 0,
                drama = 0, cartoons = 0, dvdTotal = 0, vcdTotal = 0, tapeTotal = 0;
        char cRAnother;

        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("\nRegistration");
            System.out.println("1. DVD");
            System.out.println("2. VCD");
            System.out.println("3. Tape");
            System.out.print("Choice: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.println("Type: DVD");
                dvdTotal = dvdTotal + 1;
            } else if (choice == 2) {
                System.out.println("Type: VCD");
                vcdTotal = vcdTotal + 1;
            } else if (choice == 3) {
                System.out.println("Type: Tape");
                tapeTotal = tapeTotal + 1;
            }

            System.out.print("Input title: ");
            String title = sc.nextLine();
            sc.nextLine();

            System.out.println("\n1. Horror");
            System.out.println("2. Scifi");
            System.out.println("3. Drama");
            System.out.println("4. Comedy");
            System.out.println("5. Cartoons");

            System.out.print("Category: ");
            int category = sc.nextInt();

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

            System.out.print("Setting: ");
            String setting = sc.nextLine();
            sc.nextLine();

            System.out.println("\n1. Rental");
            System.out.println("2. Sales");

            System.out.print("Transaction: ");
            int transaction = sc.nextInt();

            if (transaction == 1)
                rent = rent + 1;
            else if (transaction == 2)
                sales = sales + 1;

            System.out.print("Price: ");
            int price = sc.nextInt();

            System.out.print("Register another? (Y/N) ");
            cRAnother = sc.next().charAt(0);

        } while (cRAnother == 'y' || cRAnother == 'Y');

        System.out.println("\nREPORTS");
        System.out.println("For rent: " + rent);
        System.out.println("For sale: " + sales);
        System.out.println("VCD Total: " + vcdTotal);
        System.out.println("DVD Total: " + dvdTotal);
        System.out.println("Tape Total: " + tapeTotal);
        System.out.println("Horror Movies: " + horror);
        System.out.println("Scifi Movies: " + scifi);
        System.out.println("Drama Movies: " + drama);
        System.out.println("Comedy Movies: " + comedy);
        System.out.println("Catoons: " + cartoons);

    }
}