import java.util.*;

public class Act3 {
    public static void main(String[] args) {
        int rent = 0, sales = 0, comedy = 0, horror = 0, scifi = 0, drama = 0, cartoons = 0, dvdTotal = 0, vcdTotal = 0, tapeTotal = 0;
        char cRAnother;
        
        do {
            System.out.println("Registration");
            System.out.println("1. DVD");
            System.out.println("2. VCD");
            System.out.println("3. Tape");

            Scanner input = new Scanner(System.in);
            System.out.print("Choice: ");
            int choice = input.nextInt();

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
            String title = input.nextLine();
            input.nextLine();

            System.out.println("1. Horror");
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

            System.out.println("1. Rental");
            System.out.println("2. Sales");

            System.out.print("Transaction: ");
            int transaction = input.nextInt();

            if (transaction == 1)
                rent = rent + 1;
            else if (transaction == 2)
                sales = sales + 1;

            System.out.print("Price: ");
            int price = input.nextInt();

            System.out.print("Register another? ");
            cRAnother = input.next().charAt(0);

        } while (cRAnother == 'y' || cRAnother == 'Y');

        System.out.println("REPORTS");
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
