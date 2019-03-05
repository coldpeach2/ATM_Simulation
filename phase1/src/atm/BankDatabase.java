package atm;

import atm.model.AccountModel;
import atm.model.UserModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BankDatabase {
    List<UserModel> users;
    List<AccountModel> accounts;
    HashMap<Long,List<AccountModel>> userAccounts;

    public BankDatabase() {
        // Load users.
        this.users = new ArrayList<>();
        try {
            FileReader f = new FileReader("users.csv");
            BufferedReader reader = new BufferedReader(f);
            reader.readLine(); // Skip the column name row.
            String row;
            while ((row = reader.readLine()) != null) {
                String[] cells = row.split(","); // We expect 5 strings.
                if (cells.length != 5) throw new IOException("Incorrect number of cells in a row!");
                users.add(new UserModel(Long.parseLong(cells[0]), // user id "0" -> 0
                        cells[1], // first name
                        cells[2], // last name
                        cells[3], // password
                        Long.parseLong(cells[4]))); // user's primary account id "99" -> 99
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Load accounts.
        this.accounts = new ArrayList<>();
        try {
            FileReader f = new FileReader("accounts.csv");
            BufferedReader reader = new BufferedReader(f);
            reader.readLine(); // Skip the column name row.
            String row;
            while ((row = reader.readLine()) != null) {
                String[] cells = row.split(","); // We expect 5 strings.
                if (cells.length != 3) throw new IOException("Incorrect number of cells in a row!");
                accounts.add(new AccountModel(Long.parseLong(cells[0]), // user id "0" -> 0
                        AccountModel.AccountType.getType(Integer.parseInt(cells[1])), // type "1" -> 1 -> AccountType.Savings
                        Double.parseDouble(cells[2])); // balance "-10.40" -> -10.40
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
