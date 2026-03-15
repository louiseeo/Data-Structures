import java.io.*;
import java.util.*;

public class MovieRegistrationMenuList {
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
        String filename = "movies.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                movie.add(line);
            }

            int count = 0;
            int m = 0;
            if (movie.isEmpty()) {
                System.out.println("No movies available."); // condition if there are no movies on the list
            } else {
                for (String movie : movie) {
                    if (count % 5 == 0 ){
                        System.out.print(m + ". Title: ");
                        m++;
                        
                    }

                    if (count % 5 == 0){
                            System.out.println();
                        }
                    System.out.println(movie);
                    System.out.println();
                    count++;
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading the file.");
        }
    }
}