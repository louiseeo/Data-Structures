import java.util.*;

public class Act5 {
    // declare scanner as a global variable
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int var = 0; // declare needed variables
        char choice;
        do {
            System.out.println("Welcome to Movie Box Registration!");
            String movieTitle = inputString("Enter movie title: ");
            int yearReleased = inputInteger("Release date(year): ");
            String genre = inputString("Genre: ");
            double duration = inputDouble("Duration(hrs): ");
            String director = inputString("Director: ");

            var++; // increment to count the movie registered

            choice = inputString("Register another? (Y/N): ").charAt(0);
            System.out.println();

        } while (choice == 'Y' || choice == 'y');
        print(var + " movies registered today");
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