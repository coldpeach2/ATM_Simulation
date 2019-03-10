package atm.db;

import atm.model.AccountRequestModel;
import atm.model.TransactionModel;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;


public class LastUserTransactionTable {
    private HashMap<Long, TransactionModel> lastTransactionForUserId;
    private long nextTransactionId;

    private void save(String fileName) {
        try {
            PrintWriter writer = Util.openFileW(fileName);
            writer.println("id,userId,srcAccId,destAccId,amount");
            for (TransactionModel transactionModel : lastTransactionForUserId.values()) writer.println(transactionModel.toCSVRowString());
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to write CSV: " + fileName + ".");
        }
    }

    public void load(String fileName) {
        this.lastTransactionForUserId = new HashMap<>();
        this.nextTransactionId = 0;
        Util.loadCSV(fileName, row -> addTransactionModel(TransactionModel.fromCSVRowString(row)));
    }

    public void addTransactionModel(TransactionModel transactionModel) {
        if (transactionModel.getId() > nextTransactionId) nextTransactionId = transactionModel.getId() + 1;
        lastTransactionForUserId.put(transactionModel.getUserId(), transactionModel);
    }

    public void createTransactionModel(long userId, long srcAccId, long destAccId, double amount) {
        addTransactionModel(new TransactionModel(nextTransactionId, userId, srcAccId, destAccId, amount));
        nextTransactionId++;
    }
}
