package src.atm.model;

import java.time.Clock;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class ATMModel {

    // TODO: Do I still need to instantiate a bank manager and list of accounts? Probably not right.
    // So

    public Clock clock;

    private static final int DEFAULT_BILLS = 100;
    private static final int FIVES = 5;
    private static final int TENS = 10;
    private static final int TWENTIES = 20;
    private static final int FIFTIES = 50;

    /* Fill the atm.oldstuff.account.ATM at start */
    private Map<Integer, Integer> dollarBills = new TreeMap<Integer, Integer>(Collections.reverseOrder())
    {{
        put(FIVES, DEFAULT_BILLS);
        put(TENS, DEFAULT_BILLS);
        put(TWENTIES, DEFAULT_BILLS);
        put(FIFTIES, DEFAULT_BILLS);
    }};

    public int getDenomAmount(int denom) {
        return dollarBills.get(denom);
    }

    public Map getDollarBills () {
        return this.dollarBills;
    }

}
