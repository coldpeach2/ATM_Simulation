===========
    ATM
===========

by Group 0325

=== SETTING UP THE PROGRAM ===

In order to run the program, simply run it from the main entry-point in ATMProgram.java with no arguments.
Project SDK should be set to 1.8, Languagae Level to 8.
We have provided sample simulation data in the project folder. Please see the .csv files in /res.

The project makes use of the JSoup web-scraping library. Please find the associated .jar in /my_lib.

=== USING THE ATM ===

This program uses a command-line user interface.
First login with one of the following sample accounts:
    1. Bank Manager - username: areejn, password: test
    2. User/Client - username: biancap, password: test2
    3. IT Helper - username: terryk, password: terry

After logging in, you will be greeted with a menu and a list of possible actions.
Whenever user-input is required, you will be prompted. Menu options are different depending on the type of account registred.

~ BANK MANAGER is granted the following options:

Menu Options:

'Add New Client':
'Undo Transaction':
'Add a New Account for Client':
'Manage ATM Funds': View system alerts and manage the amount of bills stocked in the ATM.
'Save Data': Saves changes you make to the System. MUST Save Data before exiting or shutting down the ATM.
'Reboot ATM': Restarts the ATM
'Shutdown ATM': Shuts down the ATM without restarting.
'EXIT': Returns user to menu screen

~ USER has the following options:

Menu Options:

'Transfer Between Accounts':
'Transfer to User':
'Pay a Bill':
'Deposit Funds':
'Withdraw Funds':
'Request a New Account':
'EXIT':

~ IT HELPER 

Menu Options:

'Back up Data'


=== PROJECT STRUCTURE ===

> /res

 stores ...

> /src



ATMProgram.java contains the main method

=== ADDITIONAL FEATURES ===

=== MISC ===
