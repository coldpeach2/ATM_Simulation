package atm;

import java.util.Map;
import java.util.Scanner;

import atm.db.BankDatabase;
import atm.model.*;
import java.util.ArrayList;
import java.util.List;

public class ClientMenu extends Menu{

    /** Displays options for Bank Clients **/

    private Scanner userInput = new Scanner(System.in);
    //TODO: is this a good design to pass database into this object?
    BankDatabase centralDatabase;
    UserModel currentUser;


    public ClientMenu(BankDatabase database, UserModel currentUser) {
        this.centralDatabase = database;
        this.currentUser = currentUser;
    }


    public void getOption() {

        int selection;

        // Display a list of this User's accounts
        int x = 1;

        System.out.println("Your Accounts: \n");
        ArrayList <AccountModel> displayAccounts = new ArrayList<>();
        /*List<Long> list_account_ids = centralDatabase.userAccounts.get(currentUser.getId());
        for (Long id: list_account_ids) {
            AccountModel acc = centralDatabase.accountsById.get(id);
            System.out.println(x + " - " + acc.getType() + " Account id: " + acc.getId());
            displayAccounts.add(acc);
            System.out.println("Balance: " + acc.getBalance() + "\n \n");
            x++;
        }*/

        System.out.println("Hello " + currentUser.getFirstName() + "! Please select an option: \n ");
        System.out.println("1 - Transfer Between Accounts");
        System.out.println("2 - Transfer to User");
        System.out.println("3 - Pay a Bill");
        System.out.println("4 - Deposit Funds");
        System.out.println("5 - Withdraw Funds");
        System.out.println("6 - Request a New Account");
        System.out.println("7 - EXIT");

        selection = userInput.nextInt();

        switch (selection) {
            //TODO: finish writing case statements for the other User options.
            case 1:
                //
                getOption();

                System.out.println("Transfer Between Accounts:");
                int transferFrom;
                boolean validAccount = false;
                while (!validAccount) {
                    System.out.println("Please select the account you would like to transfer from:");


                    transferFrom = userInput.nextInt();
                    //withdraw
                    for (int i =1; i<x+1; i++){
                        if (i == transferFrom){
                            if (displayAccounts.get(i).getType() == AccountModel.AccountType.Checking){
                                validAccount = true;
                                break;
                            }
                            else if (displayAccounts.get(i).getType() == AccountModel.AccountType.LineOfCredit){
                                validAccount = true;
                                break;
                            }
                            else if (displayAccounts.get(i).getType() == AccountModel.AccountType.Saving){
                                validAccount = true;
                                break;
                            }
                            else{
                                System.out.println("invalid account to transfer out. Please select a different account");


                            }
                        }
                    }


                }
                //deposit
                System.out.println("select an account that you would like to transfer funds into");
                int transferTo = userInput.nextInt();
                for (int i =1; i<x+1; i++){
                    if (i == transferTo){
                        if (displayAccounts.get(i).getType() == AccountModel.AccountType.Checking){

                            break;
                        }
                        else if (displayAccounts.get(i).getType() == AccountModel.AccountType.LineOfCredit){

                            break;
                        }
                        else if (displayAccounts.get(i).getType() == AccountModel.AccountType.Saving){

                            break;
                        }
                        else{
                            break;

                        }
                    }
                }

                //TODO: lastly, write the transaction date/time to the transaction file in this user's history




            case 2:
                //
                getOption();
                break;
            case 3:
                //
                getOption();
                break;
            case 4:
                //
                getOption();
                break;
            case 5:
                //
                getOption();
                break;
            case 6:
                //
                getOption();
                break;
            case 7:
                running = false;
                break;

            default:
                System.out.println("ERROR. Please select an option from the list above.");
                getOption();
        }

    }

}
