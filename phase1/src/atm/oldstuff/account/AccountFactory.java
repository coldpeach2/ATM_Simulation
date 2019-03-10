package atm.oldstuff.account;

import atm.oldstuff.account.asset.Checking;
import atm.oldstuff.account.asset.Saving;
import atm.oldstuff.account.debt.Credit;
import atm.oldstuff.account.debt.LineOfCredit;
import atm.oldstuff.account.Account;
import atm.model.*;
import java.util.*;

public class AccountFactory {

    private static long nextId = 0;
    private Date date = new java.util.Date();


    /*
    public void createAccount(int type){
        Account acc;
        switch (type) {
            case 1:
                nextId = Integer.valueOf("11" + nextId);
                acc = new Credit(nextId);
                break;
            case 2:
                nextId = Integer.valueOf("12" + nextId);
                acc = new LineOfCredit(nextId);
                break;
            case 3:
                nextId = Integer.valueOf("21" + nextId);
                acc = new Checking(nextId);
                break;
            case 4:
                nextId = Integer.valueOf("22" + nextId);
                acc = new Saving(nextId, 0.001);
                break;
            default:
                throw new IllegalArgumentException();
        }
        //user.addAccount(acc); // Need to be implemented in user.
        nextId++;
    }
    */

    public AccountModel createAccount(int type){
        AccountModel acc;
        AccountModel.AccountType accType = AccountModel.AccountType.getType(type);

                nextId = Integer.valueOf("11" + nextId);
                acc = new AccountModel(nextId, accType, 0.00, date);

        nextId++;

        return acc;
        }
        //user.addAccount(acc); // Need to be implemented in user.


}
