package atm;

import atm.account.Account;
import atm.account.asset.Checking;
import atm.account.asset.Saving;
import atm.account.debt.Credit;
import atm.account.debt.LineOfCredit;

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
