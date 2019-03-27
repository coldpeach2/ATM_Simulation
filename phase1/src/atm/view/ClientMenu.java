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
    private BankServerConnection serverConnection;

    public ClientMenu(BankServerConnection bankServerConnection) {
        this.serverConnection = bankServerConnection;
    }

    public ArrayList<AccountModel> displayAccounts;





    public int showOptions() {

        int selection;

        // Display a list of this User's accounts
        System.out.println("Hello Bianca!");
        System.out.println("Your Accounts: \n");
        displayAccounts = new ArrayList<>();
        List<AccountModel> userAccounts = serverConnection.getUserAccounts();
        int idx = 1;
        for (AccountModel acc : userAccounts) {
            System.out.println(idx + " - " + acc.getType() + " Account id: " + acc.getId());
            System.out.println("Balance: " + acc.getBalance() + "\n \n");
            displayAccounts.add(acc);
            idx++;

        }

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

                System.out.println("What account would you like to transfer from?");
                AccountModel withdrawFrom = makeAccountSelection(idx);

                System.out.println("What account would you like to transfer to?");
                AccountModel transferTo = makeAccountSelection(idx);

                System.out.println("How much money would you like to transfer?");
                double amount = userInput.nextDouble();

                try {
                    serverConnection.requestTransfer(withdrawFrom.getId(), transferTo.getId(), amount);
                }

                catch(SecurityException sE){

                    System.out.println("This account does not belong to you, you will be redirected to the main menu.");

                    showOptions();

                }
                catch (IllegalArgumentException e){

                    System.out.println("Error encountered. Please try again. You will be redirected ot the main menu.");
                    showOptions();

                    }




            case 2:
                //
                System.out.println("Transfer To User:");



                System.out.println("What account would you like to transfer from?");
                AccountModel transferFrom = makeAccountSelection(idx);

                System.out.println("Please enter the account ID of the destination user's account you are transferring" +
                        "funds to:");
                long destAcc = userInput.nextLong();

                System.out.println("Please enter the amount you would like to transfer");

                double amountToTransfer = userInput.nextDouble();
                try {
                    serverConnection.requestTransfer(transferFrom.getId(), destAcc, amountToTransfer);
                }

                catch(SecurityException sE){

                    System.out.println("This account does not belong to you, you will be redirected to the main menu.");
                    showOptions();

                }
                catch (IllegalArgumentException e){

                    System.out.println("Error encountered. Please try again. You will be redirected ot the main menu.");
                    showOptions();

                }


                break;
            case 3:
                //Pay Bill
                //
                break;
            case 4:
                System.out.println();
                break;
            case 5:
                deposit();
                break;
            case 6:
                requestNewAcc();
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


    }

    public AccountModel makeAccountSelection(int index){

        int transferTo = userInput.nextInt();
        AccountModel accTransferTo = null;
        for (int i = 1; i < index; i++) {
            if (i == transferTo) {
                if (displayAccounts.get(i-1).getType() == AccountModel.AccountType.Checking) {
                    accTransferTo = displayAccounts.get(i);
                    break;
                }
                else if (displayAccounts.get(i-1).getType() == AccountModel.AccountType.LineOfCredit) {
                    accTransferTo = displayAccounts.get(i);
                    break;
                }
                else if (displayAccounts.get(i-1).getType() == AccountModel.AccountType.Saving) {
                    accTransferTo = displayAccounts.get(i);
                    break;
                }
                else if(displayAccounts.get(i-1).getType() == AccountModel.AccountType.Credit)
                    accTransferTo = displayAccounts.get(i-1);
                    break;
            }

        }

        if (accTransferTo == null){
            System.out.println("Please make a valid selection");
            makeAccountSelection(index);
        }
        return accTransferTo;

    }



    public void requestNewAcc(){

        System.out.println("Enter a number for the type of account would you like to create.\n0 - Checking " +
                "1 - Saving 2 - Credit 3 - Line of Credit ");
        int accTypeNum = userInput.nextInt();

        while (accTypeNum > 3){
            System.out.println("Invalid account type. Re-enter.\n0 - Checking 1 - Saving 2 - Credit " +
                    "3 - Line of Credit ");
            accTypeNum = userInput.nextInt();
        }

        AccountModel.AccountType accType = AccountModel.AccountType.getType(accTypeNum);

        //atm.model.AccountRequestModel.createAccountRequest(numAcc, accType);
        System.out.println("Account successfully requested.");
    }

    public void payBill(){
        //not sure where the bill is
    }

}
