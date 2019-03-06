package atm.model;

import java.util.Date;

public class AccountModel {
    private final long id;
    private final AccountType type;
    private double balance;
    private final Date creationDate;

    public AccountModel(long id, AccountType type, double balance, Date creationDate) {
        this.id = id;
        this.type = type;
        this.balance = balance;
        this.creationDate = creationDate;
    }

    public long getId() {
        return id;
    }

    public AccountType getType() {
        return type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public enum AccountType {
        Checking(0),
        Saving(1),
        Credit(2),
        LineOfCredit(3);

        private int code;

        AccountType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public static AccountType getType(int code) {
            switch (code) {
                case 0:
                    return Checking;
                case 1:
                    return Saving;
                case 2:
                    return Credit;
                case 3:
                    return LineOfCredit;
                default:
                    throw new IllegalArgumentException("code does not match an AccountType!");
            }
        }
    }
}
