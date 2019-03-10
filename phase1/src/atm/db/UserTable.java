package atm.db;

import atm.model.UserModel;
import atm.oldstuff.account.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserTable {

    List<UserModel> users;
    HashMap<String, UserModel> usersByUsername;



    private void save(String fileName) {
        try {
            PrintWriter writer = Util.openFileW(fileName);
            writer.println("id,firstName,lastName,username,password,primaryAccId,authLevel");
            for (UserModel userModel : users) writer.println(userModel.toCSVRowString());
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to write CSV: " + fileName + ".");
        }
    }

    public void load(String fileName) {
        this.users = new ArrayList<>();
        this.usersByUsername = new HashMap<>();
        Util.loadCSV(fileName, row -> addUser(UserModel.fromCSVRowString(row)));
    }

    public void addUser(UserModel userModel) {
        this.users.add(userModel);
        this.usersByUsername.put(userModel.getUsername(), userModel);
    }

}
