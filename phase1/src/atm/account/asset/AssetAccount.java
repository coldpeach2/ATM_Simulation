package atm.account.asset;

import atm.account.Account;

public abstract class AssetAccount extends Account {

    private final double lowerBound;

    public AssetAccount(long id, double lowerBound) {
        super(id);
        this.lowerBound = lowerBound;
    }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("deposit(): amount must be greater than $0!");
        this.balance += amount;
    }

    public boolean withdraw(double amount){
        if (amount <= 0 || amount > (this.balance - this.lowerBound)) throw new IllegalArgumentException("withdraw(): 0 < amount <= balance!");
        this.balance -= amount;
        return true;
    }

    public void transfer() {
        // Stub
    }

    public void payBill(){
        // Stub
    }
}