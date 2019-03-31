package atm.view;

import atm.ATMSim;
import atm.model.AccountModel;
import atm.server.BankServerConnection;
import atm.server.ITServerConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ITHelperMenu extends Menu {

    /**
     * Displays options for Bank Clients
     **/

    private Scanner userInput = new Scanner(System.in);
    //TODO: is this a good design to pass database into this object? !!!!!!! YES.
    ITServerConnection serverConnection;

    public ITHelperMenu(ITServerConnection bankServerConnection) {
        this.serverConnection = bankServerConnection;
    }


    public int showOptions() {


        int selection;

        // Display a list of this User's accounts

        System.out.println("Your Accounts: \n");
        ArrayList<AccountModel> displayAccounts = new ArrayList<>();
        List<AccountModel> userAccounts = serverConnection.getUserAccounts();
        int idx = 1;
        for (AccountModel acc : userAccounts) {
            System.out.println(idx + " - " + acc.getType() + " Account id: " + acc.getId());
            displayAccounts.add(acc);
            System.out.println("Balance: " + acc.getBalance() + "\n \n");
            idx++;
        }

        System.out.println("Hello " + serverConnection.user.getFirstName() + "! Please select an option: \n ");
        System.out.println("1 - Transfer Between Accounts");
        System.out.println("2 - Transfer to User");
        System.out.println("3 - Pay a Bill");
        System.out.println("4 - Deposit Funds");
        System.out.println("5 - Withdraw Funds");
        System.out.println("6 - Request a New Account");
        System.out.println("7 - Back-up ATM Data");
        System.out.println("8 - Reboot ATM");
        System.out.println("9 - Shutdown ATM");
        System.out.println("10 - EXIT");

        selection = userInput.nextInt();

        switch (selection) {
            // TODO: finish writing case statements for the other User options.
            case 1:
                //
                System.out.println("Transfer Between Accounts:");
                int transferFrom;
                boolean validAccount = false;
                while (!validAccount) {
                    System.out.println("Please select the account you would like to transfer from:");

                    transferFrom = userInput.nextInt();
                    System.out.println("please select the account you would like to transfer funds to:");
                    //withdraw
                    for (int i = 1; i < idx + 1; i++) {
                        if (i == transferFrom) {
                            if (displayAccounts.get(i).getType() == AccountModel.AccountType.Checking) {
                                validAccount = true;
                                break;
                            } else if (displayAccounts.get(i).getType() == AccountModel.AccountType.LineOfCredit) {
                                validAccount = true;
                                break;
                            } else if (displayAccounts.get(i).getType() == AccountModel.AccountType.Saving) {
                                validAccount = true;
                                break;
                            } else {
                                System.out.println("invalid account to transfer out. Please select a different account");
                            }
                        }
                    }
                }
                //deposit
                int transferTo = userInput.nextInt();
                for (int i = 1; i < idx + 1; i++) {
                    if (i == transferTo) {
                        if (displayAccounts.get(i).getType() == AccountModel.AccountType.Checking) {

                            break;
                        } else if (displayAccounts.get(i).getType() == AccountModel.AccountType.LineOfCredit) {

                            break;
                        } else if (displayAccounts.get(i).getType() == AccountModel.AccountType.Saving) {

                            break;
                        } else {
                            break;

                        }
                    }
                }
            case 2:
                //
                System.out.println("Transfer Between Accounts:");
                int transferOut;
                validAccount = false;
                while (!validAccount) {
                    System.out.println("Please select the account you would like to transfer out of:");


                    transferOut = userInput.nextInt();
                    //withdraw
                    for (int i = 1; i < idx + 1; i++) {
                        if (i == transferOut) {
                            if (displayAccounts.get(i).getType() == AccountModel.AccountType.Checking) {

                                validAccount = true;
                                break;
                            } else if (displayAccounts.get(i).getType() == AccountModel.AccountType.LineOfCredit) {
                                validAccount = true;
                                break;
                            } else if (displayAccounts.get(i).getType() == AccountModel.AccountType.Saving) {
                                validAccount = true;
                                break;
                            } else {
                                System.out.println("Checking if valid selection");
                                System.out.print(".");
                                System.out.print(".");
                                System.out.print(".");


                            }
                        }
                    }


                }

                System.out.println("Please print the username of the person you would like to transfer your funds to");
                String userToTransfer = userInput.nextLine();
                //jus to spice visuals up a lil (lol)
                System.out.print("Checking for valid user");
                System.out.print(".");
                System.out.print(".");
                System.out.print(".");
                break;
            case 3:
                //
                break;
            case 4:
                //
                break;
            case 5:
                //
                break;
            case 6:
                //
                break;
            case 7:
                serverConnection.backupData();
                break;
            case 8:
                return ATMSim.STATUS_REBOOT;
            case 9:
                return ATMSim.STATUS_SHUTDOWN;
            case 10:
                return ATMSim.STATUS_EXIT;
            default:
                System.out.println("ERROR. Please select an option from the list above.");
                break;
        }
        return ATMSim.STATUS_RUNNING;
    }



}
