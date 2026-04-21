package emehan;
import java.io.*;
import java.util.*;

public class MovieMenu_List {
    // Make these fields accesible for the whole program
    static String filename = "movies.txt";
    static ArrayList<String> movie = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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
                    // Add function
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
        sc.close();
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

}