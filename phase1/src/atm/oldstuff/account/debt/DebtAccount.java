package atm.oldstuff.account.debt;

import atm.oldstuff.account.Account;

public abstract class DebtAccount extends Account {

    public DebtAccount(long id) {
        super(id);
    }

    public void transferIn(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("deposit(): amount must be greater than $0!");
        this.balance += amount;
    }
}
