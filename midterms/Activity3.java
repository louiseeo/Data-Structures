import java.util.*;

public class Activity3 {
    // declare scanner as a global variable
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int choice = 0;
        while (choice != 0) {
            System.out.println("""
                    \n---------------------------------
                    Welcome to EFM Enterprise Systems
                    We've got it all for you!

                    Please choose one of the following:
                    1. EFM Grocery ShopperMart POS
                    2. EFM Movie Rental Registration
                    3. EFM Movie Registration
                    0. Exit
                    """);

            

            while (true) {
                System.out.print("Choice: ");

                if (!sc.hasNextInt()) {
                    System.out.println("Please");
                }
            
            choice = input.nextInt();
            input.nextLine();

            if (choice == 1) {
                Grocery.groceryProgram(); // call program 1
            } else if (choice == 2) {
               
            } else if (choice == 3) {
              
            } else if (choice == 4) {
                System.out.println("Exiting program... Goodbye!");
            } else {
                System.out.println("Invalid choice! Choose again.");
            }
        }
    }
    }
    
}