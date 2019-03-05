package atm;

import atm.model.AccountModel;
import atm.model.TransactionModel;
import atm.model.UserModel;
import atm.oldstuff.account.debt.LineOfCredit;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class BankDatabase {
    List<UserModel> users;
    HashMap<String, UserModel> usersByUsername;
    HashMap<Long, AccountModel> accountsById;
    HashMap<Long, List<Long>> userAccounts;
    HashMap<Long, TransactionModel> lastTransactionForAccountId;

    private long nextAccoundId = 0;

    public BankDatabase() {
        // Load users.
        this.users = new ArrayList<>();
        this.usersByUsername = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(openFile("users.csv"));
            reader.readLine(); // Skip the column name row.
            String row;
            while ((row = reader.readLine()) != null) {
                System.out.println(row);
                String[] cells = row.split(","); // We expect 5 strings.
                if (cells.length != 6) throw new IOException("Incorrect number of cells in a row!");
                addUser(new UserModel(Long.parseLong(cells[0]), // user id "0" -> 0
                        cells[1], // first name
                        cells[2], // last name
                        cells[3], // username
                        cells[4], // password
                        Long.parseLong(cells[5]))); // user's primary account id "99" -> 99
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Load accounts.
        this.accountsById = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(openFile("accounts.csv"));
            reader.readLine(); // Skip the column name row.
            String row;
            while ((row = reader.readLine()) != null) {
                String[] cells = row.split(","); // We expect 5 strings.
                if (cells.length != 4) throw new IOException("Incorrect number of cells in a row!");
                Date creationDate = null; // TODO: PARSE CELLS[3] TO GET DATE OBJECT
                addAccount(new AccountModel(Long.parseLong(cells[0]), // user id "0" -> 0
                        AccountModel.AccountType.getType(Integer.parseInt(cells[1])), // type "1" -> 1 -> AccountType.Savings
                        Double.parseDouble(cells[2]),
                        creationDate));
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Load user's accounts (map accounts belonging to users)
        this.userAccounts = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(openFile("useraccounts.csv"));
            reader.readLine(); // Skip the column name row.
            String row;
            while ((row = reader.readLine()) != null) {
                String[] cells = row.split(","); // We expect 5 strings.
                if (cells.length != 2) throw new IOException("Incorrect number of cells in a row!");

                long userId = Long.parseLong(cells[0]);
                long accountId = Long.parseLong(cells[1]);
                List<Long> userEntryAccountList;
                if ((userEntryAccountList = userAccounts.get(userId)) != null) {
                    userEntryAccountList.add(accountId);
                } else {
                    userEntryAccountList = new ArrayList<>();
                    userEntryAccountList.add(accountId);
                    userAccounts.put(userId, userEntryAccountList);
                }
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public InputStreamReader openFile(String fileName) {
        return new InputStreamReader(this.getClass().getResourceAsStream("/" + fileName));
    }

    public void addUser(UserModel userModel) {
        this.users.add(userModel);
        this.usersByUsername.put(userModel.getUsername(), userModel);
    }

    public void addAccount(AccountModel accountModel) {
        // TODO: Check if Date == 1st of month, if SO, if accountModel.type == Savings then applySavingsInterest(accountModel)
        if (accountModel.getId() > nextAccoundId) nextAccoundId = accountModel.getId() + 1;
        this.accountsById.put(accountModel.getId(), accountModel); // balance "-10.40" -> -10.40
    }

    public UserModel tryLogin(String username, String password) {
        UserModel userModel = usersByUsername.get(username);
        if (userModel != null) {
            if (userModel.getPassword().equals(password)) {
                return userModel;
            } else {
                throw new SecurityException("Incorrect username/password combination!");
            }
        } else {
            throw new IllegalArgumentException("User does not exist!");
        }
    }

    public boolean requestWithdrawal(long accountId, double amount) {
        // 1. Find AccountModel for accountId using your hashmap.
        // 2. Check that account type IS ALLOWED to withdrawal
        // 3. Check if enough balance.
        // 4. Finally return success!
        return true;
    }

    public boolean tryDeposit(long accountId, double amount) {
        // 0. Check if amount is valid.
        // 1. Find AccountModel for accountId using your hashmap.
        // 2. Check that account type IS ALLOWED to deposit
        // 3. Finally return success!
        return true;
    }

    public boolean requestTransfer(long srcUserId, long srcAccountId, long destAccountId) {

        return true; // If success
    }

    public void applySavingsInterest(AccountModel accountModel) {
        accountModel.setBalance(accountModel.getBalance() * 1.001);
    }

}
