package atm.server.db;

import atm.model.AccountModel;
import atm.model.RewardsModel;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

public class RewardsTable {
    HashMap<Long, RewardsModel> accountsById = new HashMap<>();
    private long nextAccountId = 0;

    public void save(String fileName) {

        try {
            PrintWriter writer = Util.openFileW(fileName);
            writer.println("id,type,balance,date,points");
            for (RewardsModel rewardsModel : accountsById.values()) writer.println(rewardsModel.toCSVRowString());
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to write CSV: " + fileName + ".");
        }
    }

    public void load(String fileName) {
        this.accountsById.clear();
        this.nextAccountId = 0;
        Util.loadCSV(fileName, row -> addAccount(RewardsModel.fromCSVRowString(row)));
    }

    public void addAccount(RewardsModel rewardsModel) {
        if (rewardsModel.getId() > nextAccountId) nextAccountId = rewardsModel.getId() + 1;
        this.accountsById.put(rewardsModel.getId(), rewardsModel); // balance "-10.40" -> -10.40
    }


    public RewardsModel createAccount() {
        RewardsModel newRewardsModel = new RewardsModel(nextAccountId, 0, new Date(), 0);
        addAccount(newRewardsModel);
        return newRewardsModel;
    }

    public RewardsModel getAccountModelForId(long accountId) {
        return accountsById.get(accountId);
    }

    public Collection<RewardsModel> getAllAccountModels() {
        return accountsById.values();
    }
}
