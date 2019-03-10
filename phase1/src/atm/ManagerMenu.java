package atm;

import java.io.*;
import java.util.Scanner;

import atm.db.BankDatabase;

import atm.model.AccountModel;
import atm.model.UserModel;
import atm.oldstuff.account.*;

public class ManagerMenu extends Menu{

    /** Displays options for the Bank Manager **/

    private Scanner userInput = new Scanner(System.in);
    BankDatabase centralDatabase;
    // BankManager manager = new BankManager();

    public ManagerMenu(BankDatabase database) {
        this.centralDatabase = database;
    }

    public void getOption() {

        int selection;

        System.out.println("\n Hello. Please select an option: \n");
        System.out.println("1 - Add New Client");
        System.out.println("2 - Restock Bills");
        System.out.println("3 - Undo Transaction");
        System.out.println("4 - Add An Account for a Client");
        System.out.println("5 - EXIT");

        selection = userInput.nextInt();

        switch (selection) {

            //TODO: finish writing case statements for BankManager options

            case 1:
                addClient();
                getOption(); // display options again after you're done with one option.
                break;
            case 2:
                restockBills();
                getOption();
                break;
            case 3:
                undo();
                getOption();
                break;
            case 4:
                addAccount();
                break;
            case 5:
                running = false;
                break;
            default:
                System.out.println("ERROR. Please select an option from the list above.");
                getOption();
        }

    }

    private void addClient() {

        String username, firstName, lastName, password;
        UserModel new_user;

        userInput.nextLine();

        System.out.println("Enter a First Name: ");
        firstName = userInput.nextLine();

        System.out.println("Enter a Last Name: ");
        lastName = userInput.nextLine();

        System.out.println("Create a username: ");
        username = userInput.nextLine();

        System.out.println("Create a password: ");
        password = userInput.nextLine();

        new_user = UserFactory.createUser(firstName, lastName, username, password);

        centralDatabase.addUser(new_user);

        System.out.println("User: " + new_user.getUsername() + " Added Successfully.");

        //manager.createUser(firstName);


    }

    private void addAccount(){
        userInput.nextLine();
        System.out.println("What type of account would you like to create? Enter a number from 0 - 3");
        System.out.println("0 - Checking");
        System.out.println("1 - Savings");
        System.out.println("2 - Credit");
        System.out.println("3 - LineOfCredit");
        int typeOfAccount = userInput.nextInt();

        if (typeOfAccount < 0 || typeOfAccount > 3) {
            System.out.println("Please enter a valid account type.");
            addAccount();
            return;
        }
        userInput.nextLine();
        System.out.println("What user would you like to create this account for?");
        int userId = userInput.nextInt(); //should get an id.
        userInput.nextLine();

        /* TODO: How are we creating accounts? Bank manager seems to be blank for this?? I don't think that we should be
         calling AccountFactory for this- I feel like bank manager should be calling accountFactory and having a
         "front-end method" (for lack of a better term lol) that I can use */

        AccountFactory factory = new AccountFactory();
        AccountModel acc = factory.createAccount(typeOfAccount);
        centralDatabase.addAccount(acc);

        centralDatabase.userAccounts.get(Long.valueOf(userId)).add(acc.getId());
    }

    private void restockBills() {

        // read alerts txt

        // for denom in alerts, add bills

        try {
            BufferedReader reader = new BufferedReader(openFile("alerts.txt"));
            reader.readLine(); // Skip the column name row.
            String row;

            if (reader.readLine() != null) {
                ATMData.resetBills();
            }

            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        File file = new File("alerts.txt");
        file.delete();

        try {
            File temp_file = new File("alerts.txt");
            FileWriter fw = new FileWriter(temp_file);
            PrintWriter pw = new PrintWriter(fw);

            pw.println("denom, amount, date");

        } catch (IOException f) {
            f.printStackTrace();
        }

    }

    public InputStreamReader openFile(String fileName) {
        return new InputStreamReader(this.getClass().getResourceAsStream("/" + fileName));
    }


    private void undo() {
        //TODO: Needs to be completed.
    }

}
