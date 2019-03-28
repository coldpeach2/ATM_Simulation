package atm.server;

import atm.model.AccountModel;
import atm.model.UserModel;

import java.util.List;

public class BankServerConnection {
    public final UserModel user;
    protected BankServer bankServer;

    public BankServerConnection(UserModel user, BankServer bankServer){
        this.user = user;
        this.bankServer = bankServer;
    }

    public boolean requestTransfer(long srcAccountId, long destAccountId, double amount) {
        return bankServer.requestTransfer(user.getId(), srcAccountId, destAccountId, amount);
    }
    public boolean requestWithdrawal(long accountId, double amount) {
        return bankServer.requestWithdrawal(accountId, amount);
    }

    public boolean requestAccount(long userId, AccountModel.AccountType type){
        return bankServer.requestAccount(userId, type);
    }

    public boolean tryDeposit(long accountId, double amount) {
        return bankServer.tryDeposit(accountId, amount);
    }

    public List<AccountModel> getUserAccounts() {
        return bankServer.getUserAccounts(user.getId());
    }

}
