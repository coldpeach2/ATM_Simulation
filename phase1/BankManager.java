public class BankManager {

    static Integer IdNum = new Integer();

    BankManager(){
    }

    public User createUser(String name, String id){
        //create a user with name, id, and pw.
        //default password is 1111.
        return new User(name, id ,"1111");
    }

    //TODO:
    public Account createAccount(User name, int balance){
        acc = new Account(idNum, balnce);
        name.addAccount(acc); //neeed to be implemented in user.
        idNum += 1;
    }

//    public void checkRequest(User name){
//
//    }
    //TODO:
    public void undoTransaction(Account acc){
        acc.balance -= acc.lastTransaction
    }

    //TODO:
    public void restock(ATM atm, int denom, int amount){
        atm.denom += amount;
    }
}
