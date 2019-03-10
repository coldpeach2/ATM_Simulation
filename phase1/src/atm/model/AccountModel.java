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
