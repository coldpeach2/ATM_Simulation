package atm;

import java.util.Scanner;
import atm.oldstuff.account.*;

public class ManagerMenu extends Menu{

    /** Displays options for the Bank Manager **/

    private Scanner userInput = new Scanner(System.in);
    BankDatabase centralDatabase;
    BankManager manager = new BankManager();

    public ManagerMenu(BankDatabase database) {
        this.centralDatabase = database;
    }

    public void getOption() {

        int selection;

        System.out.println("\n Hello. Please select an option: \n");
        System.out.println("1 - Add New Client");
        System.out.println("2 - Restock Bills");
        System.out.println("3 - Undo Transaction");
        System.out.println("4 - EXIT");

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


    }

    private void restockBills() {
        //TODO: Needs to be completed.
    }

    private void undo() {
        //TODO: Needs to be completed.
    }

}
