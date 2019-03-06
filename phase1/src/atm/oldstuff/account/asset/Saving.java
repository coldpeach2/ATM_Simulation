package atm.oldstuff.account.asset;

public class Saving extends AssetAccount {

    private final double monthlyInterest;

    public Saving(long id, double monthlyInterest) {
        super(id, 0);
        this.monthlyInterest = monthlyInterest;
    }

    public void applyInterest() {
        // 1 = 100% => 0.01 = 1% => 0.001 = 0.1%
        this.balance *= (1 + monthlyInterest);
    }
}
