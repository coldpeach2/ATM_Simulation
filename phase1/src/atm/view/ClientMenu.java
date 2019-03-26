package atm.view;

import atm.ATMSim;
import atm.model.AccountModel;
import atm.server.BankServerConnection;
import atm.server.ITServerConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientMenu extends Menu {

    /**
     * Displays options for Bank Clients
     **/

    private Scanner userInput = new Scanner(System.in);
    //TODO: is this a good design to pass database into this object? !!!!!!! YES.
    BankServerConnection serverConnection;

    public ClientMenu(BankServerConnection bankServerConnection) {
        this.serverConnection = bankServerConnection;
    }

    private ArrayList<AccountModel> displayAccounts;
    int numAcc;
    AccountModel withdrawFrom;
    AccountModel accTransferTo;


    public int showOptions() {
        int numAcc = 0;
        int selection;

        // Display a list of this User's accounts

        System.out.println("Your Accounts: \n");
        displayAccounts = new ArrayList<>();
        List<AccountModel> userAccounts = serverConnection.getUserAccounts();
        int idx = 1;
        for (AccountModel acc : userAccounts) {
            System.out.println(idx + " - " + acc.getType() + " Account id: " + acc.getId());
            displayAccounts.add(acc);
            System.out.println("Balance: " + acc.getBalance() + "\n \n");
            idx++;
        }
        numAcc = idx;

        System.out.println("Hello " + serverConnection.user.getFirstName() + "! Please select an option: \n ");
        System.out.println("1 - Transfer Between Accounts");
        System.out.println("2 - Transfer to User");
        System.out.println("3 - Pay a Bill");
        System.out.println("4 - Deposit Funds");
        System.out.println("5 - Withdraw Funds");
        System.out.println("6 - Request a New Account");
        System.out.println("7 - EXIT");

        selection = userInput.nextInt();

        switch (selection) {
            // TODO: finish writing case statements for the other User options.
            case 1:
                //
                System.out.println("Transfer Between Accounts:");

                withdraw();

                if (withdrawFrom == null){
                    System.out.println("No account selected. Return to main menu");
                    showOptions();
                }


                //deposit
                deposit();

                if (accTransferTo == null){
                    System.out.println("No account selected. Return to main menu");
                    showOptions();
                }


                // ask how much they want to transfer

                System.out.println("What amount would you like to transfer?");
                System.out.print("$");
                int amount = userInput.nextInt();

                if(withdrawFrom.getBalance() - amount < -1000){
                    System.out.println("Insufficient funds. Return to main menu");
                    showOptions();
                }

                //ask Areej how to model a transaction


            case 2:
                //
                System.out.println("Transfer To User:");

                withdraw();

                if (withdrawFrom == null){
                    System.out.println("No account selected. Return to main menu");
                    showOptions();
                }


                System.out.println("Please print the username of the person you would like to transfer your funds to");
                String userToTransfer = userInput.nextLine();
                //jus to spice visuals up a lil (lol)
                System.out.print("Checking for valid user");
                System.out.print(".");
                System.out.print(".");
                System.out.print(".");

                //ask areej how to model transfer using bank server
                break;
            case 3:
                //Pay Bill
                //
                break;
            case 4:
                withdraw();
                break;
            case 5:
                deposit();
                break;
            case 6:
                // Request a new account
                break;
            case 7:
                return ATMSim.STATUS_EXIT;
            default:
                System.out.println("ERROR. Please select an option from the list above.");
                showOptions();
        }
        return ATMSim.STATUS_RUNNING;
    }

    public void deposit(){


        System.out.println("please select the account you would like to transfer funds to:");
        int transferTo = userInput.nextInt();
        accTransferTo = null;
        for (int i = 1; i < numAcc + 1; i++) {
            if (i == transferTo) {
                if (displayAccounts.get(i).getType() == AccountModel.AccountType.Checking) {
                    accTransferTo = displayAccounts.get(i);
                    break;
                }
                else if (displayAccounts.get(i).getType() == AccountModel.AccountType.LineOfCredit) {
                    accTransferTo = displayAccounts.get(i);
                    break;
                }
                else if (displayAccounts.get(i).getType() == AccountModel.AccountType.Saving) {
                    accTransferTo = displayAccounts.get(i);
                    break;
                }
                else
                    break;
            }
            else{
                System.out.println("Please make a valid selection");
                deposit();
            }
        }

    }

    public void withdraw(){

        int transferFrom;
        boolean validAccount = false;

        withdrawFrom = null;
        while (!validAccount) {
            System.out.println("Please select the account you would like to transfer from:");

            transferFrom = userInput.nextInt();

            //withdraw
            System.out.println("Checking if valid account to transfer out of");
            for (int i = 1; i < numAcc + 1; i++) {
                if (i == transferFrom) {
                    if (displayAccounts.get(i).getType() == AccountModel.AccountType.Checking) {
                        validAccount = true;
                        withdrawFrom = displayAccounts.get(i);
                        break;
                    }
                    else if (displayAccounts.get(i).getType() == AccountModel.AccountType.LineOfCredit) {
                        validAccount = true;
                        withdrawFrom = displayAccounts.get(i);
                        break;
                    }
                    else if (displayAccounts.get(i).getType() == AccountModel.AccountType.Saving) {
                        validAccount = true;
                        withdrawFrom = displayAccounts.get(i);
                        break;
                    }
                    else {
                        System.out.println("invalid account to transfer out. Please select a different account");
                    }
                }
            }
        }

        double balance = withdrawFrom.getBalance();

        if (balance < -1000){
            System.out.println("You do not have enough funds to withdraw. Please select a different account to withdraw");
            withdraw();

        }


    }



}
