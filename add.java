import java.io.*;
import java.util.*;

public class add {
    // Make this two accesible for the whole program
    static ArrayList<String> movie = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int choice;

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

            switch (choice) {
                case 1:
                    // Add function
                    addMovie();
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
    public static void addMovie(){
        System.out.print("Enter movie title: ");
        String newTitle = sc.next();
        System.out.print("Release date(year): ");
        String newDate = sc.next();
        System.out.print("Genre: ");
        String newGenre = sc.next();
        System.out.print("Duration(hrs): ");
        String newDuration = sc.next();
        System.out.print("Director: ");
        String newDirector = sc.next();

        System.out.println("New movie added successfully!");



        
    }
    // Method 2: Search
    // Method 3: Edit
    // Method 4: Delete
    // Method 5: Sort

    // Method 6: List/Display elements in the array
    public static void displayList() {
        String filename = "movies.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                movie.add(line);
            }

            // condition if there are no movies on the list
            if (movie.isEmpty()) {
                System.out.println("No movies available.");
            } else {
                for (String m : movie) {
                    System.out.println(m);
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading the file.");
        }
    }


}