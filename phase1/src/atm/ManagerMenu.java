package atm;

import java.util.Scanner;

import atm.db.BankDatabase;

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

        String username, firstName, lastName;

        userInput.nextLine();

        System.out.println("Enter a First Name: ");
        firstName = userInput.nextLine();

        System.out.println("Enter a Last Name: ");
        lastName = userInput.nextLine();

        System.out.println("Create a username: ");
        username = userInput.nextLine();

        //TODO: Needs to be completed. Should call a createUser/addUser method. Left incomplete because I can't tell where we wanted to implement that.

        //manager.createUser(firstName);


    }

    private void addAccount(){
        userInput.nextLine();
        System.out.println("What type of account would you like to create?");
        String typeOfAccount = userInput.nextLine();
        System.out.println("What user would you like to create this account for?");
        String userAccount = userInput.nextLine();

        /* TODO: How are we creating accounts? Bank manager seems to be blank for this?? I don't think that we should be
         calling AccountFactory for this- I feel like bank manager should be calling accountFactory and having a
         "front-end method" (for lack of a better term lol) that I can use */



    }

    private void restockBills() {
        //TODO: Needs to be completed.
    }

    private void undo() {
        //TODO: Needs to be completed.
    }

}
