package atm.db;

import atm.model.AccountModel;
import atm.model.AccountRequestModel;
import atm.model.TransactionModel;
import atm.model.UserModel;

import java.util.*;

public class BankDatabase {
    UserTable userTable;
    AccountsTable accountTable;
    UserAccountsTable userAccountsTable;
    AccountRequestTable accountRequestTable;
    LastUserTransactionTable userTransactionTable;

    public BankDatabase() {
        userTable = new UserTable();
        accountTable = new AccountsTable();
        userAccountsTable = new UserAccountsTable();
        accountRequestTable = new AccountRequestTable();
        userTransactionTable = new LastUserTransactionTable();
        load();
        if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == 1) applySavingsInterests();
    }

    public void load() {
        userTable.load("users.csv");
        accountTable.load("accounts.csv");
        userAccountsTable.load("useraccounts.csv");
        accountRequestTable.load("accountrequest.csv");
        userTransactionTable.load("lasttransactions.csv");
    }

    public void save() {
        userTable.save("users.csv");
        accountTable.save("accounts.csv");
        userAccountsTable.save("useraccounts.csv");
        accountRequestTable.save("accountrequest.csv");
        userTransactionTable.save("lasttransactions.csv");
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
        if (amount <= 0) throw new IllegalArgumentException("Must withdraw more than $0");
        AccountModel accountModel = accountTable.accountsById.get(accountId);
        if (accountModel == null) throw new IllegalArgumentException("Account does not exist!");
        if (!accountModel.getType().canWithdraw()) throw new IllegalArgumentException("Credit accounts can't be withdrawn from!");
        double newBalance = accountModel.getBalance() - amount;
        if (newBalance < accountModel.getType().getMinBalance()) throw new IllegalArgumentException("Cannot withdraw more than the allowed amount!");
        accountModel.setBalance(newBalance);
        return true;
    }

    public boolean tryDeposit(long accountId, double amount) {
        // 0. Check if amount is valid.
        // 1. Find AccountModel for accountId using your hashmap.
        // 2. Check that account type IS ALLOWED to deposit
        // 3. Finally return success!
        if (amount <= 0) throw new IllegalArgumentException("Must deposit more than $0");
        AccountModel accountModel = accountTable.accountsById.get(accountId);
        if (accountModel == null) throw new IllegalArgumentException("Account does not exist!");
        accountModel.setBalance(accountModel.getBalance() + amount);
        return true;
    }

    public boolean requestTransfer(long srcUserId, long srcAccountId, long destAccountId, double amount) {
        if (!userAccountsTable.userAccounts.get(srcUserId).contains(srcAccountId)) throw new SecurityException("srcAccountId does not belong to srcUserId!");
        AccountModel destAccountModel = accountTable.accountsById.get(destAccountId);
        if (destAccountModel == null) throw new IllegalArgumentException("destAccountId does not exist in our database!");
        AccountModel srcAccountModel = accountTable.accountsById.get(srcAccountId);
        double newSrcBalance = srcAccountModel.getBalance() - amount;
        if (newSrcBalance < srcAccountModel.getType().getMinBalance()) throw new IllegalArgumentException("srcAccount does not have enough balance to transfer money!");
        userTransactionTable.createTransactionModel(srcUserId, srcAccountId, destAccountId, amount);
        srcAccountModel.setBalance(newSrcBalance);
        destAccountModel.setBalance(destAccountModel.getBalance() + amount);
        return true; // If success
    }

    public void applySavingsInterests() {
        for (AccountModel accountModel : accountTable.accountsById.values()) {
            if (accountModel.getType() == AccountModel.AccountType.Saving)
                accountModel.setBalance(accountModel.getBalance() * 1.001);
        }
    }

    public boolean grantAccount(long accRequestId) {
        AccountRequestModel requestModel = accountRequestTable.accountRequestModelById.remove(accRequestId);
        if (requestModel == null) throw new IllegalArgumentException("accRequestId does not exist in our database!");
        AccountModel newAccountModel = accountTable.createAccount(requestModel.getRequestedAccountType());
        userAccountsTable.createEntryForUser(requestModel.getRequesterUserId(), newAccountModel.getId());
        return true;
    }

    public void undoLastTransaction(long userId) {
        TransactionModel transactionModel = userTransactionTable.lastTransactionForUserId.remove(userId);
        if (transactionModel == null) throw new IllegalArgumentException("userId does not have a last transaction in our database!");
        AccountModel destAccountModel = accountTable.accountsById.get(transactionModel.getDestAccountId());
        AccountModel srcAccountModel = accountTable.accountsById.get(transactionModel.getSrcAccountId());
        double newDestBalance = destAccountModel.getBalance() - transactionModel.getAmount();
        if (newDestBalance < destAccountModel.getType().getMinBalance()) throw new IllegalArgumentException("destAccount does not have enough balance to undo transaction!");
        destAccountModel.setBalance(newDestBalance);
        srcAccountModel.setBalance(srcAccountModel.getBalance() + transactionModel.getAmount());
    }

    public boolean createUser(String firstName, String lastName, String userName, String initialPassword) {
        // 1. Create new checking account.
        AccountModel primaryAccountModel = accountTable.createAccount(AccountModel.AccountType.Checking);
        UserModel newUserModel = userTable.createUser(firstName, lastName, userName, initialPassword, primaryAccountModel.getId());
        userAccountsTable.createEntryForUser(newUserModel.getId(), primaryAccountModel.getId());
        return true;
    }

    public List<AccountModel> getUserAccounts(long userId) {
        HashSet<Long> userAccountIds = userAccountsTable.userAccounts.get(userId);
        List<AccountModel> accountModels = new ArrayList<>();
        for (long accId : userAccountIds) {
            accountModels.add(accountTable.accountsById.get(accId));
        }
        return accountModels;
    }

    public TransactionModel getLastUserTransaction(long userId) {
        return userTransactionTable.lastTransactionForUserId.get(userId);
    }

    public List<AccountRequestModel> getPendingAccountRequests() {
        return new ArrayList<>(accountRequestTable.accountRequestModelById.values());
    }
}
