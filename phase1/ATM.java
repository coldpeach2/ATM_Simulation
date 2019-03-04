import java.time.Clock;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class ATM {

    public Clock clock;
    private static BankManager my_manager;

    private static final int DEFAULT_BILLS = 100;
    private static final int FIVES = 5;
    private static final int TENS = 10;
    private static final int TWENTIES = 20;
    private static final int FIFTIES = 50;

    /* Fill the ATM at start */
    private Map<Integer, Integer> dollarBills = new TreeMap<Integer, Integer>(Collections.reverseOrder())
    {{
        put(FIVES, DEFAULT_BILLS);
        put(TENS, DEFAULT_BILLS);
        put(TWENTIES, DEFAULT_BILLS);
        put(FIFTIES, DEFAULT_BILLS);
    }};

    private static User current_user;
    private static  List<Account> current_bank_accounts;

    public void checkBills(){

        for (Map.Entry<Integer, Integer> entry : dollarBills.entrySet()) {
            if (entry.getValue() < 20) {
                //TODO: write to Alerts.txt

            }
            else {}
        }
    }

    public void giveOutBills(int amount){

        int x = amount;

        //TODO: need a try catch in withdrawal for if the amount requested isn't divisible by 5.

        for (Map.Entry<Integer, Integer> entry : dollarBills.entrySet()) {
            while (x - entry.getKey() >= 0) {
                //update <x> and reduce denomination amount by one
                x -= entry.getKey();
                entry.setValue(entry.getValue() - 1);

            }
        }

    }

    public static List<Account> getAccounts() {
        return current_user.getAccounts();
    }

    public static void viewBalance(Account account) {

    }

    //TODO: Bianca I'm gonna let you do the login stuff lmaoo

    public static void main() {

        /* main execution happens here */

        my_manager = new BankManager();

        /* POST-LOGIN.. */

        current_bank_accounts = getAccounts();

    }


}

