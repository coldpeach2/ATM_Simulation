package atm.server;

import atm.model.AccountRequestModel;
import atm.model.TransactionModel;
import atm.model.UserModel;

import java.util.List;

public class ManagerBankServerConnection extends BankServerConnection {

    public ManagerBankServerConnection(UserModel user, BankServer bankServer) {
        super(user, bankServer);
        if (user.getAuthLevel() != UserModel.AuthLevel.BankManager) throw new SecurityException("Unauthorized connection!");
    }

    public boolean grantAccount(long accRequestId) {
        return bankServer.grantAccount(accRequestId);
    }

    public void undoLastTransaction(long userId) {
        bankServer.undoLastTransaction(userId);
    }

    public boolean createUser(String firstName, String lastName, String userName, String initialPassword) {
        return bankServer.createUser(firstName, lastName, userName, initialPassword);
    }

    public TransactionModel getLastUserTransaction(long userId) {
        return bankServer.getLastUserTransaction(userId);
    }

    public List<AccountRequestModel> getPendingAccountRequests() {
        return bankServer.getPendingAccountRequests();
    }

    public void save() {
        bankServer.save();
    }
}
