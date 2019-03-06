package atm.oldstuff.account;

import atm.oldstuff.account.asset.Checking;

public class UserFactory {

    private static long nextId = 0;

    /**
     * Create a user with the specified name and default password "1111"
     * @param name name of the new user.
     * @return the user model object.
     */
    public User createUser(String name, Checking account) {
        return new User(nextId++, name,"1111", account);
    }
}
