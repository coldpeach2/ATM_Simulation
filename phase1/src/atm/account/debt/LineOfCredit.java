package atm.account.debt;

public class LineOfCredit extends DebtAccount {

    public LineOfCredit(long id) {
        super(id);
    }

    public boolean transferOut(double amount){
        if (amount <= 0 || amount > this.balance) throw new IllegalArgumentException("withdraw(): 0 < amount <= balance!");
        this.balance -= amount;
        return true;
    }
}
