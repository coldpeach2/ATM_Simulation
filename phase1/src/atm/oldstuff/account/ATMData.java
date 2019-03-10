package atm.oldstuff.account;

import java.time.Clock;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class ATMData {

    /** Class storing the ATM's bills and basic ATM System Data **/

    public Clock clock;

    private static final int DEFAULT_BILLS = 100;
    private static final int FIVES = 5;
    private static final int TENS = 10;
    private static final int TWENTIES = 20;
    private static final int FIFTIES = 50;

    /* Fill the atm.oldstuff.account.ATMData at start */
    private static Map<Integer, Integer> dollarBills = new TreeMap<Integer, Integer>(Collections.reverseOrder())
    {{
        put(FIVES, DEFAULT_BILLS);
        put(TENS, DEFAULT_BILLS);
        put(TWENTIES, DEFAULT_BILLS);
        put(FIFTIES, DEFAULT_BILLS);
    }};

    public int getDenomAmount(int denom) {
        return dollarBills.get(denom);
    }

    public static Map<Integer, Integer> getDollarBills () {
        return dollarBills;
    }

    public static void resetBills() {

        for (Map.Entry<Integer, Integer> entry: dollarBills.entrySet()) {

            entry.setValue(DEFAULT_BILLS);

        }

    }

}
