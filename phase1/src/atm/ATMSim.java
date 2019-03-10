package atm;

import atm.db.BankDatabase;
import atm.model.*;
import atm.oldstuff.account.ATMData;
import atm.oldstuff.account.ATM;

public class ATMSim {

    /** Runs after a user has logged in **/

    /** The ATMSim class can run in two modes: Regular User (Bank Client) and Bank Manager mode.
     *
     * There are two runATM methods provided.
     * 1. runATM(database, current_user) ~ ATM is being accessed by Bank Client, which is the current_user.
     * 2. runATM(database) ~ ATM is being accessed by Bank Manager.
     */

    /* The second version of runATM is needed since BankManager doesn't have a User Model*/

    private Boolean running = true;
    public ATM atm = new ATM();

    public void runATM(BankDatabase database, UserModel current_user) {
        if (current_user.getAuthLevel() == UserModel.AuthLevel.BankManager) {
            ManagerMenu menu = new ManagerMenu(database);
            while (running) {
                menu.getOption();
                running = menu.running;
            }
        } else {
            ClientMenu menu = new ClientMenu(database, current_user);
            while (running) {
                menu.getOption();
                running = menu.running;
            }
        }
    }
}
