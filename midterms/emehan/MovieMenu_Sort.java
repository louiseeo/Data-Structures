package emehan;
import java.io.*;
import java.util.*;

public class MovieMenu_Sort {
    // Make these fields accessible for the whole program
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
            System.out.println();
            switch (choice) {
                case 1:
                    addMovies();
                    break;
                case 2:
                    searchMovie();
                    break;
                case 3:
                    editMovie();
                    break;
                case 4:
                    deleteMovie();
                    break;
                case 5:
                    sortMovies();
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
        System.out.println("\nMovie successfully added!\n");
    }

    /**
     * Searches for a movie in the list.
     * Allows lookup by user input index and displays matching results.
     */
    public static void searchMovie() {
        if (isMovieListEmpty())
            return; // check if movie list is empty

        int start = getMovieStartIndex();
        System.out.println("\n---- Movie Found ----");
        System.out.println("Title: " + movie.get(start));
        System.out.println("Year : " + movie.get(start + 1));
        System.out.println("Genre: " + movie.get(start + 2));
        System.out.println("Duration: " + movie.get(start + 3));
        System.out.println("Director: " + movie.get(start + 4));
    }

    /**
     * Edits an existing movie entry.
     * Enables modification of stored movie details such as title, year, genre,
     * duration, or director.
     */
    public static void editMovie() {
        if (isMovieListEmpty())
            return; // check if movie list is empty

        int start = getMovieStartIndex();

        // display movie details
        System.out.println("\n----------Movie Details----------");
        System.out.println("Title: " + movie.get(start));
        System.out.println("Year : " + movie.get(start + 1));
        System.out.println("Genre: " + movie.get(start + 2));
        System.out.println("Duration: " + movie.get(start + 3));
        System.out.println("Director: " + movie.get(start + 4));

        int choice;

        while (true) {
            System.out.print("Do you want to edit this movie? (y/n): ");
            String ans = sc.nextLine();

            if (!ans.equalsIgnoreCase("y")) {
                return;
            }

            System.out.println("""
                    \nWhich part do you want to edit?
                    [1] Title
                    [2] Year
                    [3] Genre
                    [4] Duration
                    [5] Director
                    [0] Back to Menu
                    """);
            System.out.print("Choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Try again.");
                sc.nextLine();
                continue;
            }

            choice = sc.nextInt();
            sc.nextLine();

            if (choice == 0) {
                return;
            }

            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice! Pick again.");
                continue;
            }

            // ask for new value
            System.out.print("Enter new value: ");
            String newVal = sc.nextLine();

            // update
            movie.set(start + (choice - 1), newVal);

            saveMovies();

            System.out.println("\nMovie updated successfully!\n");
            break; // back to menu
        }
    }

    /**
     * Deletes a movie entry from the list.
     * Removes the specified movie and updates the stored data accordingly.
     */
    public static void deleteMovie() {
        if (isMovieListEmpty())
            return; // check if movie list is empty

        int start = getMovieStartIndex();

        // display movie details
        System.out.println("\n----------Movie Details----------");
        System.out.println("Title: " + movie.get(start));
        System.out.println("Year : " + movie.get(start + 1));
        System.out.println("Genre: " + movie.get(start + 2));
        System.out.println("Duration: " + movie.get(start + 3));
        System.out.println("Director: " + movie.get(start + 4));

        while (true) {
            System.out.print("Do you want to delete this movie? (y/n): ");
            String ans = sc.nextLine();

            if (!ans.equalsIgnoreCase("y")) {
                return;
            }

            // delete all movie details
            for (int i = 0; i < 5; i++) {
                movie.remove(start);
            }

            saveMovies();
            System.out.println("\nMovie deleted successfully!\n");
            break;
        }
    }

    /**
     * Sorts the movie list.
     * Organizes movies based on either descending or ascending
     */
    public static void sortMovies() {
        if (isMovieListEmpty())
            return;

        int choice;
        while (true) {
            System.out.println("""
                    Choose the order of sorting:
                    [1] Ascending
                    [2] Descending
                    """);
            System.out.print("Choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Enter a number.\n");
                sc.nextLine();
                continue;
            }
            choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1 || choice == 2) {
                break;
            } else {
                System.out.println("Invalid choice! Pick between 1 or 2.\n");
            }
        }
        int m = movie.size();

        // use bubble sort by title of the movie
        for (int i = 0; i < m - 5; i += 5) {
            for (int j = 0; j < m - 5 - i; j += 5) {

                // get movies to compare
                String title1 = movie.get(j);
                String title2 = movie.get(j + 5);

                boolean swap = false;
                if (choice == 1) { // ascending
                    if (title1.compareToIgnoreCase(title2) > 0)
                        swap = true;
                } else if (choice == 2) { // descending
                    if (title1.compareToIgnoreCase(title2) < 0)
                        swap = true;
                }

                if (swap) {
                    // swap along with all 5 fields
                    for (int k = 0; k < 5; k++) {
                        String temp = movie.get(j + k);
                        movie.set(j + k, movie.get(j + 5 + k));
                        movie.set(j + 5 + k, temp);
                    }
                }
            }
        }
        saveMovies();
        System.out.println("\nMovie sorted successfully!\n");
        displayList();
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

    // ---------- Helper Methods ----------
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

    // Helper method to get the movie index for overall access in the program
    public static int getMovieStartIndex() {
        while (true) {
            // ask user to enter a movie number to search
            System.out.println("Available movies: " + movie.size() / 5);
            System.out.print("Enter movie number: ");

            // chech if user entered a valid input
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.\n");
                sc.nextLine();
                continue;
            }
            int num = sc.nextInt();
            sc.nextLine();

            // validate the entered number, search, and display
            if (num >= 1 && num <= movie.size() / 5) {
                return (num - 1) * 5; // compute the starting index
            } else {
                System.out.println("Invalid number! Please enter another.\n");
            }
        }
    }

    // If movie list is empty helper for the whole program
    public static boolean isMovieListEmpty() {
        if (movie.isEmpty()) {
            System.out.println("Movie list is empty.");
            return true;
        }
        return false;
    }

}