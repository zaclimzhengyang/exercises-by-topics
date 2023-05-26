package Day1Scanner;

import java.util.ArrayList;
import java.util.Scanner;

public class ScannerShoppingCart {
    public static void main(String[] args) {
        ArrayList<String> shoppingCart = new ArrayList<String>();
        System.out.println("Welcome to your shopping cart");
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.print("> ");
            String userInput = sc.nextLine();
            String[] userInputSplit = userInput.split(" ");
            if (userInputSplit[0].equals("list")) {
                if (shoppingCart.isEmpty()){
                    System.out.println("Your cart is empty");
                } else {
                    for (int i = 0; i < shoppingCart.size(); i++) {
                        int serialNo = i + 1;
                        String item = shoppingCart.get(i);
                        System.out.println(serialNo + ". " + item);
                    }
                }
            } else if (userInputSplit[0].equals("add")){
                for (int j = 1; j < userInputSplit.length; j++) {
                    String currFruit = userInputSplit[j];
                    String trimCurrFruit = currFruit.trim().replace(",","");
                    if (!shoppingCart.contains(trimCurrFruit)) {
                        shoppingCart.add(trimCurrFruit);
                        System.out.println(trimCurrFruit + " added to cart");
                    } else {
                        System.out.println("You have " + trimCurrFruit + " in your cart");
                    }
                }
            } else if (userInputSplit[0].equals("delete")) {
                if (userInputSplit.length > 1) {
                    int indexToDelete = Integer.parseInt(userInputSplit[1]) - 1;
                    if (indexToDelete >= 0 && indexToDelete < shoppingCart.size()) {
                        String removedItem = shoppingCart.remove(indexToDelete);
                        System.out.println(removedItem + " removed from cart");
                    } else {
                        System.out.println("Incorrect item index");
                    }
                }
            } else {
                System.out.println("Invalid command");
            }
        }
    }
}