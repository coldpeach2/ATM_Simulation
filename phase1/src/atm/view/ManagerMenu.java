package atm.view;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import atm.ATMSim;
import atm.server.BankServer;

import atm.model.AccountRequestModel;
import atm.server.ManagerBankServerConnection;

public class ManagerMenu extends Menu {

    /** Displays options for the Bank Manager **/

    private Scanner userInput = new Scanner(System.in);
    ManagerBankServerConnection serverConnection;
    // BankManager manager = new BankManager();

    public ManagerMenu(ManagerBankServerConnection managerBankServerConnection) {
        this.serverConnection = managerBankServerConnection;
    }

    public int showOptions() {

        int selection;

        System.out.println("\n Hello. Please select an option: \n");
        System.out.println("1 - Add New Client");
        System.out.println("2 - Undo Transaction");
        System.out.println("3 - Add An Account for a Client");
        System.out.println("4 - Manage ATM Funds");
        System.out.println("5 - Save Data");
        System.out.println("6 - Reboot ATM");
        System.out.println("7 - Shutdown ATM");
        System.out.println("8 - EXIT");

        selection = userInput.nextInt();

        switch (selection) {

            //TODO: finish writing case statements for BankManager options

            case 1:
                addClient();
                break;
            case 2:
                undo();
                break;
            case 3:
                manageAccountRequests();
                break;
            case 4:
                serverConnection.readAlerts();
                manageFunds();
            case 5:
                serverConnection.save();
                break;
            case 6:
                return ATMSim.STATUS_REBOOT;
            case 7:
                return ATMSim.STATUS_SHUTDOWN;
            case 8:
                return ATMSim.STATUS_EXIT;
            default:
                System.out.println("ERROR. Please select an option from the list above.");
        }
        return ATMSim.STATUS_RUNNING;
    }

    private void addClient() {
        String username, firstName, lastName, password;
        userInput.nextLine();
        System.out.println("Enter a First Name: ");
        firstName = userInput.nextLine();
        System.out.println("Enter a Last Name: ");
        lastName = userInput.nextLine();
        System.out.println("Create a username: ");
        username = userInput.nextLine();
        System.out.println("Create a password: ");
        password = userInput.nextLine();
        if (serverConnection.createUser(firstName, lastName, username, password))
            System.out.println("User: " + username + " Added Successfully.");

    }

    private void manageAccountRequests() {
        userInput.nextLine();
        List<AccountRequestModel> accountRequestModelList = serverConnection.getPendingAccountRequests();
        if (accountRequestModelList.isEmpty()) {
            System.out.println("There are no pending account requests. Press enter to continue.");
            userInput.nextLine();
            return;
        }
        System.out.println("The following account requests are pending:");
        int idx = 0;
        for (AccountRequestModel accountRequestModel : accountRequestModelList) {
            System.out.println(idx + " - User: " + accountRequestModel.getRequesterUserId() + " Type: " + accountRequestModel.getRequestedAccountType().getName());
            idx++;
        }
        System.out.println((idx + 1) + " - Grant ALL");
        System.out.println((idx + 2) + " - Back");
        int requestIdx = -1;
        while (requestIdx != (idx + 2)) {
            System.out.println("Select an option:");
            requestIdx = userInput.nextInt();
            if (requestIdx == idx + 1) { // Grant all.
                for (AccountRequestModel accountRequestModel : accountRequestModelList) {
                    if (serverConnection.grantAccount(accountRequestModel.getId()))
                        System.out.println("Granted user " + accountRequestModel.getRequesterUserId() + " access to a new " + accountRequestModel.getRequestedAccountType() + " account.");
                }
            } else if (requestIdx >= 0 && requestIdx <= idx) {
                AccountRequestModel accountRequestModel = accountRequestModelList.get(requestIdx);
                if (serverConnection.grantAccount(accountRequestModel.getId()))
                    System.out.println("Granted user " + accountRequestModel.getRequesterUserId() + " access to a new " + accountRequestModel.getRequestedAccountType() + " account.");
            }
        }
    }

    private void manageFunds() {

        System.out.println("Please select an option: ");
        System.out.println("1 - Restock all");
        System.out.println("2 - See ATM Funds");
        System.out.println("3 - Exit");

        int sel = userInput.nextInt();

        switch (sel) {
            case 1:
                serverConnection.restock();
                System.out.println(" \n Successfully restocked ATM. \n");
                break;
            case 2:
                System.out.println("\n");
                Map<Integer, Integer> bills = serverConnection.getBills();
                for (Map.Entry<Integer, Integer> e : bills.entrySet()) {
                    System.out.println("CAD " + e.getKey() + " bills: " + e.getValue());
                }
                System.out.println("\n");
                manageFunds();
            case 3:
                break;
            default:
                manageFunds();
        }
    }

    public InputStreamReader openFile(String fileName) {
        return new InputStreamReader(this.getClass().getResourceAsStream("/" + fileName));
    }

    private void undo() {
        //TODO: Needs to be completed.
    }

}
