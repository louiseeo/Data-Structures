import java.io.*;
import java.util.*;

public class MovieMenu_Add {
    // Make these fields accesible for the whole program
    static String filename = "movies.txt";
    static ArrayList<String> movie = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = 0;

        loadMovies(); // call the method to load movies to the array list

        do {
            System.out.println("""
                    \n===================================
                    Welcome to Movie Registration Menu!
                    [1] Add
                    [2] Search
                    [3] Edit
                    [4] Delete
                    [5] Sort
                    [6] List
                    [0] Exit
                    """);
            System.out.print("Choice: ");
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Enter another.");
                sc.nextLine();
                continue;
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addMovies();
                    break;
                case 2:
                    // Search
                    break;
                case 3:
                    // Edit
                    break;
                case 4:
                    // Delete
                    break;
                case 5:
                    // Sort
                    break;
                case 6:
                    displayList();
                    break;
                case 0:
                    System.out.println("Thank you for using the Movie Registration System!");
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid input! Enter a valid choice.");
            }

        } while (choice != 0);
    }

    /**
     * Adds a new movie entry to the text file.
     * Handles user input for movie details and persists them for later retrieval.
     */
    public static void addMovies() {
        System.out.print("Enter title: ");
        String title = sc.nextLine();
        movie.add(title);
        System.out.print("Enter year released: ");
        String year = sc.nextLine();
        movie.add(year);
        System.out.print("Enter genre: ");
        String genre = sc.nextLine();
        movie.add(genre);
        System.out.print("Enter duration(e.g., 2h 30m): ");
        String duration = sc.nextLine();
        movie.add(duration);
        System.out.print("Enter name of director: ");
        String director = sc.nextLine();
        movie.add(director);

        saveMovies();
        System.out.println("\nMovie successfully added!");
    }

    /**
     * Displays all movies in the list.
     * Prints each movie’s details (title, year, genre, duration, director) in a
     * structured format.
     */
    public static void displayList() {
        if (movie.isEmpty()) {
            System.out.println("No movies found in the list.");
            return;
        }

        System.out.println("============================================== MOVIE LIST ==============================================");

        // Print table header with borders
        System.out.println("+----+------------------------------+------+-------------------------+----------+----------------------+");
        System.out.printf("| %-2s | %-28s | %-4s | %-23s | %-8s | %-20s |%n",
                "#", "Title", "Year", "Genre", "Duration", "Director");
        System.out.println("+----+------------------------------+------+-------------------------+----------+----------------------+");

        int movieCount = 1;
        for (int i = 0; i < movie.size(); i += 5) {
            System.out.printf("| %-2d | %-28s | %-4s | %-23s | %-8s | %-20s |%n",
                    movieCount,
                    movie.get(i), // Title
                    movie.get(i + 1), // Year
                    movie.get(i + 2), // Genre
                    movie.get(i + 3), // Duration
                    movie.get(i + 4)); // Director
            movieCount++;
        }

        // Print bottom border
        System.out.println("+----+------------------------------+------+-------------------------+----------+----------------------+");
    }

    // Helper Methods
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
            System.out.println("Error saving movies: " + e.getMessage());
        }
    }

}