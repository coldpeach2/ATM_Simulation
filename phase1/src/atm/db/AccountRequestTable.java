package atm.db;

import atm.model.AccountModel;
import atm.model.AccountRequestModel;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class AccountRequestTable {
    private LinkedList<AccountRequestModel> accountRequestQueue;
    private long nextAccountId;

    private void save(String fileName) {
        try {
            PrintWriter writer = Util.openFileW(fileName);
            writer.println("id,userId,accType");
            for (AccountRequestModel accountRequestModel : accountRequestQueue)
                writer.println(accountRequestModel.toCSVRowString());
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to write CSV: " + fileName + ".");
        }
    }

    public void load(String fileName) {
        this.accountRequestQueue = new LinkedList<>();
        this.nextAccountId = 0;
        Util.loadCSV(fileName, row -> addAccountRequest(AccountRequestModel.fromCSVRowString(row)));
    }

    public void addAccountRequest(AccountRequestModel accountRequestModel) {
        if (accountRequestModel.getId() > nextAccountId) nextAccountId = accountRequestModel.getId() + 1;
        this.accountRequestQueue.addLast(accountRequestModel);
    }

    public void createAccountRequest(long userId, AccountModel.AccountType accountType) {
        addAccountRequest(new AccountRequestModel(nextAccountId, userId, accountType));
        nextAccountId++;
    }
}
