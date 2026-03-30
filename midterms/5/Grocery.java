import java.util.*;

public class Grocery {
    public static void grocery() {
        String strProdName, strAnotherP, strCustomer;
        char cAnotherP, cCustomer;
        double dQty, dBill, dPrice;
        double dTotal, dPay, dChange;

        Scanner sc = new Scanner(System.in);

        do {
            dBill = 0;
            do {
                System.out.println("\n=========================");
                System.out.println("Welcome to EFM Grocery!");
                System.out.print("Input product name: ");
                strProdName = sc.nextLine();
                System.out.print("Price: ");
                dPrice = sc.nextDouble();
                System.out.print("Quantity: ");
                dQty = sc.nextDouble();
                sc.nextLine(); // consume newline

                dTotal = dQty * dPrice;
                System.out.println("Total: " + dTotal);
                dBill += dTotal;

                System.out.print("Another product Y/N? ");
                strAnotherP = sc.nextLine();
                cAnotherP = strAnotherP.charAt(0);
            } while (cAnotherP == 'Y' || cAnotherP == 'y');

            System.out.println("Bill: " + dBill);
            System.out.print("Payment: ");
            dPay = sc.nextDouble();
            sc.nextLine();

            if (dPay >= dBill) {
                dChange = dPay - dBill;
                System.out.println("Change: " + dChange);
                System.out.println("Thank you for shopping!");
            } else {
                System.out.println("Money is not enough!");
            }

            System.out.print("\nAnother customer Y/N? ");
            strCustomer = sc.nextLine();

            cCustomer = strCustomer.charAt(0);
        } while ((cCustomer == 'Y') || (cCustomer == 'y'));
        System.out.println("Grocery program is terminating...");

    }

}