package atm.oldstuff.account;

import java.util.*;

import java.io.*;

public class ATM {

    private User current_user;
    private List<Account> current_bank_accounts;


    //need to figure out how this module is supposed to store/interact with the bank data.
    private TreeMap<Integer, Integer> dollarBills = (TreeMap<Integer, Integer>) ATMData.getDollarBills();

    public void checkBills(){

        for (Map.Entry<Integer, Integer> entry : dollarBills.entrySet()) {
            if (entry.getValue() < 20) {
                try {
                    writeAlert(entry.getKey(), entry.getValue(), new Date());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void giveOutBills(int amount){

        int x = amount;

        if (isDivisibleBy5(amount)) {

            for (Map.Entry<Integer, Integer> entry : dollarBills.entrySet()) {
                while (x - entry.getKey() >= 0) {
                    //update <x> and reduce denomination amount by one
                    x -= entry.getKey();
                    entry.setValue(entry.getValue() - 1);

                }
            }
        }
        else {
            System.err.println("Please enter a combination of $5, $10, $20 or $50 bills.");
        }

    }

    public void addBills(int denom, int amount){
        dollarBills.replace(denom, dollarBills.get(denom) + amount);
    }

    private boolean isDivisibleBy5 (int amount) {

        return amount % 5 == 0;

    }

    private void writeAlert(int denom, int amount, Date date) throws IOException {

        try {
            File file = new File("alerts.txt");
            FileWriter fw = new FileWriter(file, true);
            PrintWriter pw = new PrintWriter(file);

            pw.println(denom + "," + amount + "," + date);

            pw.close();
        } catch (IOException e) {
            System.err.println("Could not write alert to file.");
        }

    }


    public List<Account> getAccounts() {
   //     return current_user.getAccounts();
        return null;
    }


    /*public static void main() {

        *//* main execution happens here *//*

        my_manager = new BankManager();

        *//* POST-LOGIN.. *//*

        current_bank_accounts = getAccounts();
    }*/

}

