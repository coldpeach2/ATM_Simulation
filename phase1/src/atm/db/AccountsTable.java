package atm.db;

import atm.model.AccountModel;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class AccountsTable {
    HashMap<Long, AccountModel> accountsById;
    private long nextAccountId;

    private void save(String fileName) {
        try {
            PrintWriter writer = Util.openFileW(fileName);
            writer.println("id,type,balance,date");
            for (AccountModel accountModel : accountsById.values()) writer.println(accountModel.toCSVRowString());
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to write CSV: " + fileName + ".");
        }
    }

    public void load(String fileName) {
        this.nextAccountId = 0;
        this.accountsById = new HashMap<>();
        Util.loadCSV(fileName, row -> addAccount(AccountModel.fromCSVRowString(row)));
    }

    public void addAccount(AccountModel accountModel) {
        // TODO: Check if Date == 1st of month, if SO, if accountModel.type == Savings then applySavingsInterest(accountModel)
        if (accountModel.getId() > nextAccountId) nextAccountId = accountModel.getId() + 1;
        this.accountsById.put(accountModel.getId(), accountModel); // balance "-10.40" -> -10.40
    }

}
