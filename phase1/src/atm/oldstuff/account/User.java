package atm.oldstuff.account;

import atm.oldstuff.account.Account;
import atm.oldstuff.account.asset.Checking;

public class User {

    private final long id; // id should never be changed
    private String name;
    private String password;

    //TODO: need an atm.oldstuff.account superclass to implement this ArrayList.
   // private ArrayList<Account> accounts;

    private Checking primaryAccount;

    /**
     * Basic constructor for atm.oldstuff.account.User class.
     * Every user should have a Name, id & Password.
     *
     * @param name   atm.oldstuff.account.User's full name
     * @param id    a unique character sequence associated with this atm.oldstuff.account.User
     * @param password atm.oldstuff.account.User's password
     */


    User(long id, String name, String password, Account primaryAccount){
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

/*    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {

        accounts.add(account);

    }*/


    /*
    Writes a given transaction to a transactions.txt file.

    A line in transactions.txt is a single transaction, separated by semi-colons:
    (<type of transaction>; <money from>; <amount from>; <money to>; <amount to>)

     */ //TODO: define how transactions will appear on file.
    //TODO: should this method be in this class?
    private void writeTransaction(String type) {}

}


