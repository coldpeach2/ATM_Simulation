package atm;

import atm.server.BankServerConnection;
import atm.server.ITServerConnection;
import atm.server.ManagerBankServerConnection;
import atm.view.ClientMenu;
import atm.view.ITHelperMenu;
import atm.view.ManagerMenu;
import atm.view.Menu;

public class ATMSim {

    /** Runs after a user has logged in **/

    /**
     * The ATMSim class can run in two modes: Regular User (Bank Client) and Bank Manager mode.
     * <p>
     * There are two runATM methods provided.
     * 1. runATM(database, current_user) ~ ATM is being accessed by Bank Client, which is the current_user.
     * 2. runATM(database) ~ ATM is being accessed by Bank Manager.
     */

    public static final int STATUS_RUNNING = 0;
    public static final int STATUS_EXIT = 1;
    public static final int STATUS_REBOOT = 2;
    public static final int STATUS_SHUTDOWN = 3;

    public int runATM(BankServerConnection connection) {
        Menu menu;
        if (connection instanceof ManagerBankServerConnection) {
            menu = new ManagerMenu((ManagerBankServerConnection) connection);
        } else if (connection instanceof ITServerConnection) {
            menu = new ITHelperMenu((ITServerConnection) connection);
        } else {
            menu = new ClientMenu(connection);
        }
        int status = STATUS_RUNNING;
        while (status == STATUS_RUNNING) {
            status = menu.showOptions();
        }
        return status;
    }
}
