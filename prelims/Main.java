import java.util.Scanner;

public class Main {

    Scanner sc = new Scanner(System.in);

    int InputInteger(String msg) {
        System.out.print(msg);
        return sc.nextInt();
    }
    
    String InputString(String msg) {
        sc.nextLine(); // clear buffer
        System.out.print(msg);
        return sc.nextLine();
    }

    double InputDouble(String msg) {
        System.out.print(msg);
        return sc.nextDouble();
    }

    void Print(String msg) {
        System.out.println(msg);
    }

    public static void main(String[] args) {

        Main app = new Main();
        int count = 0;
        char choice;

        do {
            app.Print("Welcome to EFM Hospital - Patient Registration");

            int iPatientNum = app.InputInteger("Patient number: ");
            String strPatientName = app.InputString("Patient name: ");

            count++; // tally registered patients

            System.out.print("Register another patient? (Y/N): ");
            choice = app.sc.next().charAt(0);

        } while (choice == 'Y' || choice == 'y');

        app.Print(count + " patients registered today");
    }
}

