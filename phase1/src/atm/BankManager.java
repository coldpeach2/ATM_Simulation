package atm;


import atm.account.Account;
import atm.account.asset.Checking;
import atm.account.asset.Saving;
import atm.account.debt.Credit;
import atm.account.debt.LineOfCredit;

import java.util.ArrayList;

public class BankManager {

    private static int idNum = 0;
    private ArrayList<User> listOfUsers = new ArrayList<>();

    BankManager(){
    }

    public User createUser(String name) {
        // Stub.
    }

    //TODO: Requires method addAccount in atm.User.
    public void createAccount(User user, int type){
        // Stub.
    }

    public void checkRequest(User name){
      ArrayList<String> requests = name.getRequest();
      for(String request: requests) {
          performRequest(request);
      }
    }

    //TODO:
//    public void performRequest(String request){
//
//    }

    //TODO: Requires atm.account class to be completed first
    public void undoTransaction(Account acc){
        acc.balance -= acc.lastTransaction;
    }

    //TODO:
    public void restock(ATM atm, int denom, int amount){
        atm.addBills(denom, amount);
    }

    public ArrayList<User> getListOfUsers(){
        return listOfUsers;
    }
}
