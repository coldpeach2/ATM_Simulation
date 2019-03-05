import AssetAccount.Saving;
import AssetAccount.Checking;
import DebtAccount.Credit;
import DebtAccount.LineOfCredit;

import java.util.ArrayList;

public class BankManager{

    private static int idNum = 0;
    private ArrayList<User> listOfUsers = new ArrayList<>();

    BankManager(){
    }

    public User createUser(String name, Integer id){
        //create a user with name, id, and pw.
        //default password is 1111.
        return new User(name, id ,"1111");
    }

    //TODO: Write on csv file of list of users and pw everytime i create  user on accountfactory.

    //TODO: Requires method addAccount in User.
    public void createAccount(User name, float balance, int type){
        if(type == 1) {
            idNum = Integer.valueOf("11" + String.valueOf(idNum));
            Checking acc = new Checking(idNum, balance);
        } else if(type == 2){
            idNum = Integer.valueOf("12" + String.valueOf(idNum));
            Saving acc = new Saving(idNum, balance);
        } else if(type == 3){
            idNum = Integer.valueOf("21" + String.valueOf(idNum));
            Credit acc = new Credit(idNum, balance);
        } else {
            idNum = Integer.valueOf("22" + String.valueOf(idNum));
            LineOfCredit acc = new LineOfCredit(idNum, balance);
        }
        name.addAccount(acc); //need to be implemented in user.
        idNum += 1;
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

    //TODO: Requires Account class to be completed first
    public void undoTransaction(Account acc){
        acc.balance -= acc.lastTransaction
    }

    //TODO:
    public void restock(ATM atm, int denom, int amount){
        atm.addBills(denom, amount);
    }

    public ArrayList<User> getListOfUsers(){
        return listOfUsers;
    }
}
