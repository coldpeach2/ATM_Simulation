package atm.oldstuff.account;

public abstract class Account {

    private final long id;
    protected double balance;

    public Account(long id) {
        this(id, 0);
    }

    public Account(long id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

}
