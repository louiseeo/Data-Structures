import java.util.*;

public class GroceryProgram {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String strProdName, strAnotherP;
        String strCustomer;
        char cCustomer = 'y', cAnotherP = 'Y';
        double dQty, dBill, dPrice;
        double dTotal, dPay, dChange = 0;

        do {
            dBill = 0;
            do {
                System.out.println("\nWelcome to EFM Grocery");
                System.out.print("Input product name: ");
                strProdName = input.nextLine();
                System.out.print("Price: ");
                dPrice = input.nextDouble(); input.nextLine();
                System.out.print("Quantity: ");
                dQty = input.nextDouble(); input.nextLine();
                dTotal = dQty * dPrice;
                System.out.println("Total: " + dTotal);
                dBill = dBill + dTotal;
                System.out.print("Another product Y/N? ");
                strAnotherP = input.nextLine();
                cAnotherP = strAnotherP.charAt(0);

            } while ((cAnotherP == 'Y' || cAnotherP == 'y'));
            System.out.println("Bill: " + dBill);
            System.out.print("Payment: ");
            dPay = input.nextDouble(); input.nextLine();
            if (dPay >= dBill) {
                dChange = dPay - dBill;
                System.out.println("Change: " + dChange);
                System.out.println("Thank you for shopping");
            } 
            
            else 
                System.out.println("Money is not enough!");

                System.out.print("Another customer Y/N? ");
                strCustomer = input.nextLine();
                cCustomer = strCustomer.charAt(0);

        } while ((cCustomer == 'Y' || cCustomer == 'y'));
        System.out.println("Grocery program terminating...");
    }
}

/*
 * Welcome to EFM Grocery <-- display
 * Input productname: Chippy <-- input
 * Price: 15.00 <-- input
 * Quantity: 3 <-- input
 * Total: 45.00 <-- display
 * Another product Y/N? y
 * Input productname: C2
 * Price: 10.00
 * Quantity: 3
 * Total: 30.00
 * Another product Y/N? n
 * Bill: 75.00 <-- display
 * Payment: 100
 * Change: 25 <-- display
 * Thank you for shopping <-- display
 * Another customer Y/N? n
 * Grocery program is terminating <-- display
 */