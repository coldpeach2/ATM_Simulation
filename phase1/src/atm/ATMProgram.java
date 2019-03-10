package atm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ATMProgram {
    public static void main(String[] args) {
        // Loads the CSV files as java objects.
        BankDatabase centralDatabase = new BankDatabase();

        Scanner scanInput = new Scanner(System.in);
        System.out.println("Please enter your user name and press enter");
        String enteredUsername = scanInput.nextLine();
        System.out.println("Please enter your password and press enter");
        String enteredPassword = scanInput.nextLine();
        if (centralDatabase.tryLogin(enteredUsername, enteredPassword) != null) {
            System.out.println("LOGGED IN!");
        }
        scanInput.close();
    }

}
