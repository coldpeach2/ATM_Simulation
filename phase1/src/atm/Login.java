package atm; /**
 * Created by biancapokhrel on 2019-03-03.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Login {

    private static String name;

    public Login() {
        name = "";
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        int lines = 0;
        try {
            // this is just a check to see how many lines are in the csv file
            FileReader f = new FileReader("users.csv");
            BufferedReader reader = new BufferedReader(f);

            while (reader.readLine() != null) {
                lines++;
            }

            reader.close();

        } catch (IOException ex) {
            ex.printStackTrace();
            // System.out.println("buffered reading issue");
        }


        // scanners to read input from keyboard as well as read through the csv file

        String successfulUser = "";
        boolean userExists = false;
        boolean passwordCorrect = false;
        try {

            Scanner scanFile = new Scanner(new File("users.csv"));
            Scanner scanInput = new Scanner(System.in);

            System.out.println("Please enter your user name and press enter");
            String enteredUN = scanInput.nextLine();
            System.out.println("Please enter your password and press enter");
            String enteredPW = scanInput.nextLine();
            int countLines = 0;
            //going through the csv file and trying to find a match
            while (scanFile.hasNextLine()) {
                String currentLine = scanFile.nextLine();
                String[] userInfo = currentLine.split(",");

                //perform a quick check to see if it's reads properly
                System.out.println(userInfo.length == 2);

                if (enteredUN.equals(userInfo[0])) {
                    userExists = true;
                    if (enteredPW.equals(userInfo[1])) {
                        passwordCorrect = true;
                        successfulUser = userInfo[0];
                        break;
                    }

                }
                countLines++;

            }

            if (countLines == lines) {
                if (!userExists) {
                    System.out.println("atm.User does not exist");
                }
            } else {
                if (!passwordCorrect) {
                    System.out.println("Username/Password incorrect");
                } else {
                    System.out.println("atm.Login successful");
                    name = successfulUser;
                }
            }
            //always close the scanner if you're done with it
            scanFile.close();
            scanInput.close();

        } catch (IOException e) {

            System.out.println("Log-in system is currently down");

        }
    }

}
