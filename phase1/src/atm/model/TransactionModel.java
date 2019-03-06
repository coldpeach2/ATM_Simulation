package atm.model;

import java.util.Date;

public class TransactionModel {
    private static final long ATM_ACCOUNT_ID = -1;
    // If src = ATM_ACCOUNT_ID the transaction represents deposit in into destAccountId
    // If dest = ATM_ACCOUNT_ID the transaction represents withrdrawal from srcAccountId to ATM
    // TODO: Make these final.
    private long id; // Transaction Id
    private long srcAccountId;
    private long destAccountId;
    private double amount;
    private Date date;
}
