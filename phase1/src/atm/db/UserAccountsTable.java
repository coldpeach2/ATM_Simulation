package atm.db;

import atm.model.AccountModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class UserAccountsTable {
    HashMap<Long, List<Long>> userAccounts;

    private void save(String fileName) {
        try {
            PrintWriter writer = Util.openFileW(fileName);
            writer.println("userId,accId");
            for (Map.Entry<Long, List<Long>> userAccountsEntry : userAccounts.entrySet()) {
                long userId = userAccountsEntry.getKey();
                for (long accId : userAccountsEntry.getValue()) {
                    writer.println(userId + "," + accId);
                }

            }
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to write CSV: " + fileName + ".");
        }
    }

    public void load(String fileName) {
        // Load user's accounts (map accounts belonging to users)
        this.userAccounts = new HashMap<>();
        Util.loadCSV(fileName, row -> readUserAccountCSVRow(row));
    }

    private void readUserAccountCSVRow(String row) throws IOException {
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

}
