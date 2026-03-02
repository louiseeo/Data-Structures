import java.util.*;

public class Act4 {

    public static Scanner input = new Scanner(System.in); // declare a global variable scanner to access for each method

    public static void main(String[] args) {

        // declare the needed varibles
        int iprodId;
        String strProdName, strProdDesc;
        double dQty, dPrice, dDiscount, dSubTotal;

        System.out.println("Please Input the following:");

        System.out.print("ProductID: ");
        iprodId = InputInt();

        System.out.print("Name: ");
        strProdName = InputString();

        System.out.print("Description: ");
        strProdDesc = InputString();

        System.out.print("Quantity: ");
        dQty = InputDouble();

        System.out.print("Price: ");
        dPrice = InputDouble();

        System.out.print("Discount: ");
        dDiscount = InputDouble();

        dSubTotal = computeSubTotal(dPrice, dQty, dDiscount);

        displayDetails(iprodId, strProdName, dQty, dPrice, dDiscount, dSubTotal);
    }

    // method that accepts and returns a String value
    public static String InputString() {
        String value = input.nextLine(); input.nextLine();
        return value;
    }

    // method that accepts and returns an int value
    public static int InputInt() {
        int value = input.nextInt();
        return value;
    }

    // method that accepts and returns a double value
    public static double InputDouble() {
        double value = input.nextDouble();
        return value;
    }

    // method that computes the subtotal
    public static double computeSubTotal(double dPrice, double dQty, double dDiscount) {
        return (dPrice * dQty) - dDiscount;
    }

    // method that displays product the details
    public static void displayDetails(int iprodId, String strProdName, double dQty, double dPrice, double dDiscount,
            double dSubTotal) {
        System.out.println();
        System.out.println(iprodId + " " + strProdName);
        System.out.println("Priced at " + dPrice + " for " + dQty + " pieces");
        System.out.println("Discounted at " + dDiscount);
        System.out.println("Subtotal: " + dSubTotal);
    }
}

/*
 * Write a program that would compute for the salary of an employee. At main,
 * declare variables for iprodid, strprodName, strProdDesc, dQty, dPrice,
 * dDiscount. The program should also provide the following methods:
 * 
 * 1. a method InputString() that will accept and retum a String value
 * 2. a method InputInt() that will accept and return an int value
 * 3. a method InputDouble() that will accept accept and return a double value
 * 4. a method that will compute for the salary using the formula: dSubTotal =
 * (dPrice x dQty) -dDiscount
 * 5. a method that will display product details and computed subtotal
 * 
 * Sample final output:
 * 
 * Please Input the following:
 * ProductID: 123123
 * Name: Chippy
 * Description: Flores
 * Quantity: 500
 * Price: 10
 * Discount: 500
 * 
 * 12123 Chippy
 * Priced at 10 for 500 pieces
 * Discounted at 500
 * Subtotal: 4500
 */