import java.util.*;
import java.io.*;

public class Act6 {
    public static void main(String[] args) {
        int rent = 0, sales = 0, comedy = 0, horror = 0, scifi = 0, drama = 0, cartoons = 0, dvdTotal = 0, vcdTotal = 0,
                tapeTotal = 0;
        char cRAnother;

        Scanner input = new Scanner(System.in);

        try {
            FileWriter fw = new FileWriter("movies.txt");

            do {
                System.out.println("Registration");
                System.out.println("1. DVD");
                System.out.println("2. VCD");
                System.out.println("3. Tape");
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

                System.out.print("Register another? (Y/N) ");
                cRAnother = input.next().charAt(0);

            } while (cRAnother == 'y' || cRAnother == 'Y');

            fw.write("REPORTS\n");
            fw.write("For rent: " + rent + "\n");
            fw.write("For sale: " + sales + "\n");
            fw.write("VCD Total: " + vcdTotal + "\n");
            fw.write("DVD Total: " + dvdTotal + "\n");
            fw.write("Tape Total: " + tapeTotal + "\n");
            fw.write("Horror Movies: " + horror + "\n");
            fw.write("Scifi Movies: " + scifi + "\n");
            fw.write("Drama Movies: " + drama + "\n");
            fw.write("Comedy Movies: " + comedy + "\n");
            fw.write("Catoons: " + cartoons + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error po");
        } finally {
            input.close();
        }
    }
}