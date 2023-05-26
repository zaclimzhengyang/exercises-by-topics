package Day3TextBasedFileDatabase;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Day3TextBasedFileDatabase.ShoppingCartService.*;

public class ShoppingCart {
    private static ArrayList<String> cart;
    private static String cartDirectory;

    public static void main(String[] args) throws IOException {
        // Declare the directory path and the file name used to store the text database
        String directoryPath = "textFileDatabase";
        String fileName = "shoppingCartOne";

        // Create new directory if it does not exist
        File newDirectory = new File(directoryPath);
        if (newDirectory.exists()) {
            System.out.println("Directory already exists");
        } else {
            newDirectory.mkdir();
        }

        // Print welcome message and use Scanner to take in user input
        System.out.println("Welcome to my shopping cart");

        ArrayList<String> cartItems = new ArrayList<String>();

        Scanner sc = new Scanner(System.in);
        String input = "";

        // Use serialNo to keep track of the number of existing items in shopping cart database
        int serialNo = 0;
        File shoppingCartFile = new File(directoryPath + File.separator + fileName);
        Scanner scFile = new Scanner(shoppingCartFile);
        while (scFile.hasNextLine()) {
            serialNo++;
            scFile.nextLine();
        }

        /**
         * Navigation logic here:
         *  As long as user does not key in "quit", we will repeatedly prompt user for next input
         *      Option 1: "help" - list all the available and valid commands
         *      Option 2: "list" - list all the items that are already in the shopping cart
         *      Option 3: "users" - list all the text file in the targeted directory
         *      Option 4: "add <items>" - add items, separated by ","
         *      Option 5: "delete <item #>" - delete item by index
         *      Option 6: "quit" - exit the program
         */
        while (!input.equals("quit")) {
            System.out.println("What do you want to perform? Type quit to exit program");
            input = sc.nextLine();
            if (input.equals("help")) {
                System.out.println("'list' to show a list of items in shopping cart");
                System.out.println("'login <name>' to access your shopping cart");
                System.out.println("'add <item>' to add items into shopping cart");
                System.out.println("'delete <item #>'");
                System.out.println("'quit' to exit this program");
            } else if (input.equals("list")) {
                /**
                 * readCartItemsFromFile will return a list of items that are currently in shopping cart
                 * listCart will print out each items onto the console log
                 */
                cartItems = readCartItemsFromFile(directoryPath, fileName);
                listCart(cartItems);
            } else if (input.equals("users")) {
                /**
                 * listUsers will iterate and print all the files that are in the directory
                 */
                listUsers(directoryPath);
            }

            /**
             * given "add oranges, banana, mangoes"
             * 1. replace all the "," with "" -> add oranges banana mangoes"
             * 2. substring starts from index 4 -> "oranges banana mangoes"
             */
            String strValue = "";
            if (input.startsWith("add")) {
                input = input.replace(",", "");
                Scanner addSc = new Scanner(input.substring(4));

                /**
                 * Wrap PrintWriter around FileWriter to print formatted representation of object to text-output stream
                 */
                FileWriter fw = new FileWriter(directoryPath + File.separator + fileName, true);
                PrintWriter pw = new PrintWriter(fw);

                /**
                 * hasNext method of Scanner returns true if there is another token in its input
                 * We iterate through all the tokens in the Scanner input and add them to the list of items
                 * and, print the item together with the serial number onto the text database
                 */
                while (addSc.hasNext()) {
                    strValue = addSc.next();
                    cartItems.add(strValue);

                    // print to text database file on the next line
                    pw.println("Item " + serialNo + ": " + strValue);
                    serialNo++;
                }
                pw.flush();
                fw.flush();
                pw.close();
                fw.close();
            }

            if (input.startsWith("delete")) {
                /**
                 * deleteCartItem will delete the target element from the list of items
                 */
                cartItems = deleteCartItem(cartItems, input);
                updateCartItemsToFile(cartItems, directoryPath, fileName);
            }

        }

    }
}
