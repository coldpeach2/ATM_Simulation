package atm.db;

import atm.model.AccountModel;
import atm.model.TransactionModel;
import atm.model.UserModel;

import java.util.HashMap;

public class BankDatabase {
    UserTable userTable;
    AccountsTable accountTable;
    UserAccountsTable userAccountsTable;
    AccountRequestTable accountRequestTable;
    LastUserTransactionTable userTransactionTable;

    public BankDatabase() {
        userTable = new UserTable();
    }

    public UserModel tryLogin(String username, String password) {
        UserModel userModel = userTable.usersByUsername.get(username);
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
