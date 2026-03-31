import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Menu {
    static Scanner sc = new Scanner(System.in);
    static String filename = "questions.txt";
    static List<String> quiz = new ArrayList<>();

    public static void main(String[] args) {
        // System.out.println("""
        // Welcome to Quiz Bee Game Menu!
        // [1] Player Registration
        // [2] Play
        // [0] Exit
        // """);

        int choice = 0;
        do {
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
                    playerRegister();
                    break;
                case 2:
                    play();
                    break;
                case 0:
                    System.out.println("Thank you for playing!");
                    System.out.println("Terminating program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Pick another one.\n");
                    break;
            }

        } while (choice != 0);
    }

    public static void playerRegister() {
        System.out.println("Enter Name: ");
        String name = sc.nextLine();

    }

    public static void play() {

    }

    // Load data for easy access for the whole menu
    public static void loadMovies() {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String title, year, genre, duration, director;

            while ((title = br.readLine()) != null &&
                    (year = br.readLine()) != null &&
                    (genre = br.readLine()) != null &&
                    (duration = br.readLine()) != null &&
                    (director = br.readLine()) != null) {

                movie.add(title.trim());
                movie.add(year.trim());
                movie.add(genre.trim());
                movie.add(duration.trim());
                movie.add(director.trim());
            }

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    // Save file for the whole menu
    public static void saveMovies() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (String data : movie) {
                bw.write(data);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving scores: " + e.getMessage());
        }
    }
}