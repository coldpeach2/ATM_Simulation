package AssetAccount;
public class Saving{

    private int Id;
    private double balance;


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
}