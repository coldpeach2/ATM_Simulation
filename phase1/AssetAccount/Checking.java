package AssetAccount;

public class Checking extends AssetAccount {
    private double limit = 0;
    public boolean defaultacc;
    public double amount;
    private int Id;
    public double balance;

    Checking(int Id, double balance){}

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    public void setDefaultacc(){}


    @Override
    public void withdraw(double amount){
        if (canWithdraw(amount)) {
            balance = balance - amount;
        }
    }
    public boolean canWithdraw(double amount) {
        if (balance > 0 || (balance - amount) <= -100){
            return false;
        }
        else return true;
    }

    @Override
    public void deposit(double amount) {
        balance = balance + amount;
    }
    @Override
    public void transfer(double amount, ) {
    }
    @Override
    public void payBill(){

    }
}

