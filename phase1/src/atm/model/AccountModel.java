package atm.model;

import java.io.IOException;
import java.sql.Timestamp;
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

    public String toCSVRowString() {
        return id + "," + type.code + "," + balance + "," + creationDate.getTime();
    }

    public static AccountModel fromCSVRowString(String row) throws IOException {
        String[] cells = row.split(","); // We expect 5 strings.
        if (cells.length != 4) throw new IOException("Incorrect number of cells in a row!");
        Date creationDate = new Date(Long.parseLong(cells[3])); // TODO: PARSE CELLS[3] TO GET DATE OBJECT
        return new AccountModel(Long.parseLong(cells[0]), // user id "0" -> 0
                AccountModel.AccountType.getType(Integer.parseInt(cells[1])), // type "1" -> 1 -> AccountType.Savings
                Double.parseDouble(cells[2]),
                creationDate);
    }

    public enum AccountType {
        Checking(0, true, -100, 15.95, "Checking", null),
        Saving(1, true, 0, 4.95, "Saving", null),
        Credit(2, false, -1000, 10, "Credit", null),
        LineOfCredit(3, true, -10000, 10, "Line of Credit", null),
        Rewards(4, false,0,10, "Rewards",0.0);

        private final int code;
        private final boolean canWithdraw;
        private final double minBalance;
        private final String name;
        private final double monthlyFee;
        private double points;


        AccountType(int code, boolean canWithdraw, double minBalance, double monthlyFee, String name, Double points) {
            this.code = code;
            this.canWithdraw = canWithdraw;
            this.minBalance = minBalance;
            this.monthlyFee = monthlyFee;
            this.name = name;
            this.points = points;
        }

        public int getCode() {
            return code;
        }

        public double getMinBalance() {
            return minBalance;
        }

        public boolean canWithdraw() {
            return canWithdraw;
        }

        public double getMonthlyFee() {
            return monthlyFee;
        }

        public String getName() {
            return name;
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
                case 4:
                    return Rewards;
                default:
                    throw new IllegalArgumentException("code does not match an AccountType!");
            }
        }
    }
}

