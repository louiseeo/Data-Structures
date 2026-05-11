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
                FileLogger.log("\n=========================");
                FileLogger.log("Welcome to EFM Grocery!");
                System.out.print("Input product name: ");
                strProdName = sc.nextLine();
                FileLogger.logAnswer("Input product name: " + strProdName);
                System.out.print("Price: ");
                dPrice = sc.nextDouble();
                FileLogger.logAnswer("Price: " + dPrice);
                System.out.print("Quantity: ");
                dQty = sc.nextDouble();
                FileLogger.logAnswer("Quantity: " + dQty);
                sc.nextLine(); // consume newline

                dTotal = dQty * dPrice;
                FileLogger.log("Total: " + dTotal);
                dBill += dTotal;

                System.out.print("Another product Y/N? ");
                strAnotherP = sc.nextLine();
                FileLogger.logAnswer("Another product Y/N? " + strAnotherP);
                cAnotherP = strAnotherP.charAt(0);
            } while (cAnotherP == 'Y' || cAnotherP == 'y');

            FileLogger.log("Bill: " + dBill);
            System.out.print("Payment: ");
            dPay = sc.nextDouble();
            FileLogger.logAnswer("Payment: " + dPay);
            sc.nextLine();

            if (dPay >= dBill) {
                dChange = dPay - dBill;
                FileLogger.log("Change: " + dChange);
                FileLogger.log("Thank you for shopping!");
            } else {
                FileLogger.log("Money is not enough!");
            }

            System.out.print("\nAnother customer Y/N? ");
            strCustomer = sc.nextLine();
            FileLogger.logAnswer("\nAnother customer Y/N? " + strCustomer);

            cCustomer = strCustomer.charAt(0);
        } while ((cCustomer == 'Y') || (cCustomer == 'y'));
        FileLogger.log("Grocery program is terminating...");

    }

}