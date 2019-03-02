import java.util.ArrayList;

public class User {

    private String name;
    private final Integer id; // id should never be changed



    private String password;

    //TODO: need an Account superclass to implement this ArrayList.
    /*private ArrayList<Account> accounts;*/


    /**
     * Basic constructor for User class.
     * Every user should have a Name, id & Password.
     *
     * @param name   User's full name
     * @param id    a unique character sequence associated with this User
     * @param password User's password
     */


    User(String name, Integer id, String password){
        this.name = name;
        this.id = id;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    /*
    Writes a given transaction to a transactions.txt file.

    A line in transactions.txt is a single transaction, separated by semi-colons:
    (<type of transaction>; <money from>; <amount from>; <money to>; <amount to>)

     */ //TODO: define how transactions will appear on file.
    //TODO: should this method be in this class?
    private void writeTransaction(String type) {}

}


