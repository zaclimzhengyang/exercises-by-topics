package Day3TextBasedFileDatabase;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCartService {
    public static ArrayList<String> readCartItemsFromFile(String directoryPath, String fileName) throws IOException {
        ArrayList<String> items = new ArrayList<String>();
        File file = new File(directoryPath + File.separator + fileName); // "textFileDatabase/shoppingCartOne"

        /**
         * Wrap BufferedReader around FileReader
         * While we have not reached end of file, we will add each line we read from the file, to items list
         * At the end, return items list
         */

        BufferedReader br = new BufferedReader(new FileReader(file));
        String sr;
        while ((sr = br.readLine()) != null) {
            items.add(sr);
        }
        br.close();
        return items;
    }

    public static void listCart(ArrayList<String> cartItems) {
        if (cartItems.size() > 0) {
            for (String item: cartItems) {
                System.out.println(item);
            }
        } else {
            System.out.println("Your cart is empty");
        }
    }

    public static void listUsers(String dirPath) {
        File directoryPath = new File(dirPath);

        // list method returns an array of files that are in the directory, else returns null
        String[] contents = directoryPath.list();
        for (String file: contents) {
            System.out.println(file);
        }
    }

    public static void updateCartItemsToFile(ArrayList<String> cartItems, String dirPath, String fileName) throws IOException {
        FileWriter fw = new FileWriter(dirPath + File.separator + fileName, true);
        BufferedWriter bw = new BufferedWriter(fw);

        // Delete every line from text database
        PrintWriter writer = new PrintWriter(dirPath + File.separator + fileName);
        writer.print("");
        writer.close();

        int listCount = 0;
        while (listCount < cartItems.size()) {
            bw.write(cartItems.get(listCount));
            bw.newLine();
            listCount ++;
        }

        bw.flush();
        fw.flush();
        bw.close();
        fw.close();
    }

    public static ArrayList<String> deleteCartItem(ArrayList<String> cartItems, String input) {

        String strValue = "";
        //delete, starts from posiiton 6.
        if (input.startsWith("delete")) {

            /**
             * Given "delete 4"
             * Substring it to start at index 6 -> " 4"
             */
            Scanner scanner = new Scanner(input.substring(6));

            while (scanner.hasNext()) {
                strValue = scanner.next();
                int listItemIndex = Integer.parseInt(strValue);

                // Check if index is valid and within range
                if (listItemIndex >= 0 && listItemIndex < cartItems.size()) {
                    cartItems.remove(listItemIndex);
                } else {
                    System.out.println("Incorrect item index");
                }

            }

        }
        return cartItems;

    }

}
