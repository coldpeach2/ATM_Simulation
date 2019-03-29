package atm.view;

import atm.ATMSim;
import atm.model.AccountModel;
import atm.server.BankServerConnection;
import atm.server.ITServerConnection;

import javax.sound.midi.SysexMessage;
import java.util.*;

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

    public ArrayList<AccountModel> displayAccounts;





    public int showOptions() {


        int selection;

        // Display a list of this User's accounts

        System.out.println("Your Accounts: \n");
        displayAccounts = new ArrayList<>();
        List<AccountModel> userAccounts = serverConnection.getUserAccounts();
        int idx = 1;
        for (AccountModel acc : userAccounts) {
            System.out.println(idx + " - " + acc.getType() + " Account id: " + acc.getId());
            displayAccounts.add(acc);
            System.out.println("Balance: " + acc.getCurrency() + " " + acc.getBalance() + "\n \n");
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
        boolean successful = false;

        switch (selection) {
            // TODO: finish writing case statements for the other User options.
            case 1:
                //

                successful = false;
                System.out.println("Transfer Between Accounts:");

                System.out.println("What account would you like to transfer from?");
                AccountModel withdrawFrom = makeAccountSelection(idx);

                System.out.println("What account would you like to transfer to?");
                AccountModel transferTo = makeAccountSelection(idx);

                System.out.println("How much money would you like to transfer?");
                double amount = userInput.nextDouble();

                try {
                    successful = serverConnection.requestTransfer(withdrawFrom.getId(), transferTo.getId(), amount);
                }

                catch(SecurityException sE){

                    System.out.println("This account does not belong to you, you will be redirected to the main menu.");

                    showOptions();

                }
                catch (IllegalArgumentException e){

                    System.out.println("Error encountered. Please try again. You will be redirected ot the main menu.");
                    showOptions();

                    }

                if (successful)
                    System.out.println("Transfer successful. You will be redirected to main menu");
                else
                    System.out.println("Transfer unsuccessful. You will be redirected to main menu. Please try again later");

                showOptions();

            case 2:
                //
                System.out.println("Transfer To User:");

                successful = false;

                System.out.println("What account would you like to transfer from?");
                AccountModel transferFrom = makeAccountSelection(idx);

                System.out.println("Please enter the account ID of the destination user's account you are transferring" +
                        "funds to:");
                long destAcc = userInput.nextLong();

                System.out.println("Please enter the amount you would like to transfer");

                double amountToTransfer = userInput.nextDouble();
                try {
                    successful = serverConnection.requestTransfer(transferFrom.getId(), destAcc, amountToTransfer);
                }

                catch(SecurityException sE){

                    System.out.println("This account does not belong to you, you will be redirected to the main menu.");
                    showOptions();

                }
                catch (IllegalArgumentException e){

                    System.out.println("Error encountered. Please try again. You will be redirected ot the main menu.");
                    showOptions();

                }

                if (successful)
                    System.out.println("Transfer to user successful. You will be redirected to main menu");

                else
                    System.out.println("Transfer unsuccessful. You will be redirected to main menu. Please try again later");

                showOptions();
                break;
            case 3:
                //Pay Bill
                //
                break;
            case 4:
                // deposit
                successful = false;
                System.out.println("Please select the account you would like to deposit funds into");
                AccountModel deposit = makeAccountSelection(idx);

                System.out.println("What amount would you like to deposit?");
                double amountDeposit = userInput.nextDouble();
                try{
                    successful = serverConnection.tryDeposit(deposit.getId(), amountDeposit);
                }
                catch(IllegalArgumentException e){

                    if (amountDeposit <= 0){
                        System.out.println("Amount must be greater than $0, You will be redirected to main menu");
                        showOptions();
                    }
                    else{
                        System.out.println("Account does not exist! You will be redirected to main menu");
                        showOptions();
                    }
                }

                if (successful)
                    System.out.println("Deposit successful! You will now be redirected to main menu");
                else
                    System.out.println("Deposit unsuccessful. You will be redirected to main menu. Please try again later");

                showOptions();
                break;
            case 5:
                successful = false;

                System.out.println("Please select the account you would like to withdraw funds from");

                AccountModel withdraw = makeAccountSelection(idx);

                System.out.println("What amount would you like to withdraw?");

                double amountWithdraw = userInput.nextDouble();

                try{
                    successful = serverConnection.requestWithdrawal(withdraw.getId(), amountWithdraw);
                }

                catch(IllegalArgumentException e){

                    if (!withdraw.getType().canWithdraw()){
                        System.out.println("Credit accounts can't be withdrawn from! You will be redirected to the main menu");
                    }
                    else if (withdraw.getBalance() - amountWithdraw < withdraw.getType().getMinBalance()){
                        System.out.println("Cannot withdraw more than the allowed amount! You will be redirected to the main menu");
                    }
                    else if (amountWithdraw <= 0){
                        System.out.println("You must withdraw more than $0. You will be redirected to the main menu");

                    }
                    else
                        System.out.println("Account does not exist. Please try again. You will be redirected to the main menu");

                    showOptions();
                }

                if (successful)
                    System.out.println("Withdraw successful! Please allow time for changes to be reflected in your accounts." +
                            "You will now be redirected to the main menu");

                else
                    System.out.println("Withdrawal unsuccessful. You will be redirected to the main menu. Please try again" +
                            "at a later time");

                showOptions();
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


    /*
    idea for changing currencies

    1. present a list of currency options
    2. store all the currencies of the countries that are available DONE
    3. ask user whether they want to withdraw or deposit a currency amount
    4. ask them for the type of currency
    4. if they want to withdraw, first ask how much they want to withdraw, then convert that amount and see if they can
    5. if they want to deposit, first ask how much they are depositing, then convert that amount and deposit into their account

    all done!
     */

    public void displayExchangeRates() {

        TreeMap<String, Double> rates = serverConnection.getExchangeRates();

        for (Map.Entry<String, Double> entry : rates.entrySet()) {
            System.out.println(entry.getKey() + ":  " + entry.getValue());
        }
    }
}
