package atm.oldstuff.account;

import atm.oldstuff.account.asset.Checking;
import atm.model.*;

public class UserFactory {

    private static long nextId = 0;

    /**
     * Create a user with the specified name and default password "1111"
     * @param name name of the new user.
     * @return the user model object.
     */


    /*public static User createUser(String name, Checking account) {
        return new User(nextId++, name,"1111", account);
    }*/

    public static UserModel createUser(String firstName, String lastName, String username, String password) {

        //call account factory to make primary account
        AccountFactory factory = new AccountFactory();
        AccountModel primaryAcc = factory.createAccount(3);

        //create new user
        UserModel new_user = new UserModel(nextId, firstName, lastName, username, password, primaryAcc.getId());
        nextId++;

        return new_user;

    }
}
