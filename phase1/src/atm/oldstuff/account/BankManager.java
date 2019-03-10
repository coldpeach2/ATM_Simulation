package atm.oldstuff.account;
import atm.model;


public class BankManager {

    //TODO: not sure whats happening here. All my code has been modified .
    private static int idNum = 0;

    public BankManager(){
    }

    public atm.oldstuff.account.User createUser(String name) {
        //requires user class to be fixed to have a constructor without account param
        return atm.oldstuff.account.UserFactory.createUser(name, null);
    }


    public void createAccount(atm.oldstuff.account.User user, int type){
        atm.oldstuff.account.AccountFactory.createAccount(user, type);
    }


    public void undoTransaction(atm.oldstuff.account.Account acc, atm.model.TransactionModel m){
        acc.balance -= acc.lastTransaction;
    }

    public void restock(ATM atm, int denom, int amount){
        atm.addBills(denom, amount);
    }
}
