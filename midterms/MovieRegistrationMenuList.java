import java.io.*;
import java.util.*;

public class MovieRegistrationMenuList {
    // Make this two accesible for the whole program
    static String filename = "movies.txt";
    static ArrayList<String> movie = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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
            choice = sc.nextInt(); sc.nextLine();

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
                    // List / Display items
                    displayList();

                    break;
                case 0:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid input! Enter a valid choice.");
            }

        } while (choice != 0);
        sc.close();
    }

    // Method 1: Adding
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

        for (int i = 0; i < movie.size(); i++){
            if (i % 5 == 0){
                System.out.print(movieCount + ". Title: ");
                movieCount++;
            } else if (i % 5 == 1){
                System.out.print("Year: ");
            } else if (i % 5 == 2){
                System.out.print("Genre: ");
            } else if (i % 5 == 3){
                System.out.print("Duration(hrs): ");
            } else if (i % 5 == 4){
                System.out.print("Director: ");
            }

            System.out.println(movie.get(i));
    
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

                movie.add(title);
                movie.add(year);
                movie.add(genre);
                movie.add(duration);
                movie.add(director);
            }

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

}