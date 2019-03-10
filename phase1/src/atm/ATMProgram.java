package atm;

import atm.db.BankDatabase;
import atm.model.UserModel;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class ATMProgram {
    public static void main(String[] args) {
        // Loads the CSV files as java objects.
        BankDatabase centralDatabase = new BankDatabase();
        while (true) {
            Date startUpDate = new Date();
            Scanner scanInput = new Scanner(System.in);
            while (hoursDifference(new Date(), startUpDate) < 24) {
                System.out.println("Please enter your user name and press enter:");
                String enteredUsername = scanInput.nextLine();
                System.out.println("Please enter your password and press enter:");
                String enteredPassword = scanInput.nextLine();
                try {
                    UserModel loginUserModel = centralDatabase.tryLogin(enteredUsername, enteredPassword);
                    new ATMSim().runATM(centralDatabase, loginUserModel);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
            scanInput.close();
            // Back-up data every 24 hours.
            centralDatabase.save();
        }
    }

    private static int hoursDifference(Date date1, Date date2) {
        final int MILLI_TO_HOUR = 1000 * 60 * 60;
        return (int) (date1.getTime() - date2.getTime()) / MILLI_TO_HOUR;
    }
}
