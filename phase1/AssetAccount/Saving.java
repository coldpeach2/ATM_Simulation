package AssetAccount;

public class Saving extends AssetAccount{

    private int Id;
    public double balance;


    Saving(int Id, double balance){
    }

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

    @Override
    public void withdraw() {
    }
    @Override
    public void deposit() {
    }
    @Override
    public void transfer() {
    }
    @Override
    public void payBill(){

    }
}
