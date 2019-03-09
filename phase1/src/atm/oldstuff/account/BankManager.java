package atm.oldstuff.account;


import java.util.ArrayList;

public class BankManager {

    private static int idNum = 0;
    private ArrayList<User> listOfUsers = new ArrayList<>();

    public BankManager(){
    }

    public User createUser(String name) {
        // Stub.
        return null;
    }

    /*
    //TODO: Requires method addAccount in atm.oldstuff.account.User.
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

    //TODO: Requires atm.oldstuff.account class to be completed first
    public void undoTransaction(Account acc){
        acc.balance -= acc.lastTransaction;
    }

    //TODO:
    public void restock(ATM atm, int denom, int amount){
        atm.addBills(denom, amount);
    }

    public ArrayList<User> getListOfUsers(){
        return listOfUsers;
    }*/
}
