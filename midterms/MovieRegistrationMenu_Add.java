import java.io.*;
import java.util.*;

public class MovieRegistrationMenu_Add {
    // Make these fields accesible for the whole program
    static String filename = "movies.txt";
    static ArrayList<String> movie = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        loadMovies(); // call the method to load movies to the array list

        do {
            System.out.println("""
                    \nWelcome to Movie Registration Menu!
                    [1] Add
                    [2] Search
                    [3] Edit
                    [4] Delete
                    [5] Sort
                    [6] List
                    [0] Exit
                    """);
            System.out.print("Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    // Add function
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
                    // List / Display items
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

    // Method 1: Adding movies to the txt
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

    // Method 2: Search
    // Method 3: Edit
    // Method 4: Delete
    // Method 5: Sort

    // Method 6: List/Display elements in the array
    public static void displayList() {
        if (movie.isEmpty()) {
            System.out.println("No movies found in the list.");
            return;
        }

        System.out.println("---------- MOVIE LIST ----------");
        int movieCount = 1;

        for (int i = 0; i < movie.size(); i++) {
            if (i % 5 == 0) {
                System.out.print(movieCount + ". Title: ");
                movieCount++;
            } else if (i % 5 == 1) {
                System.out.print("   Year: ");
            } else if (i % 5 == 2) {
                System.out.print("   Genre: ");
            } else if (i % 5 == 3) {
                System.out.print("   Duration(hrs): ");
            } else if (i % 5 == 4) {
                System.out.print("   Director: ");
            }
            System.out.println(movie.get(i)); // print the infos
            if (i % 5 == 4) {
                System.out.println(); // add space to move to next movie
            }
        }
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