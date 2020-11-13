package Products;

import java.io.*;
import java.util.Scanner;
/*Name: Selina Do and Long Nguyen
  Date: 03/16/2020
  Purpose: Prompt the user for input information including their names, type of service, the cost of
  service and the date of service
  Inputs: Username, type of service, cost of service follows by the date
  Outputs: information of each users are printed on one line separated by semicolon*/
public class ProductWriter {
    public static void main(String[] args) {
        File productsDatabase = new File("ProductsDatabase.txt");
        try {

            PrintWriter printWriter = new PrintWriter(productsDatabase);
            System.out.println("Input products information ...");

            boolean contLoop = true;
            while (contLoop) {

                // Verifies that productName is not blank
                System.out.print("Enter product name: ");
                String productName = getString();
                while (productName.length() <= 0) {
                    System.out.print("Enter product name: ");
                    productName = getString();
                }

                // Verifies that quantityInStock is positive
                System.out.print("Enter quantity in stock: ");
                int quantityInStock = getInt();
                while (quantityInStock <= 0) {
                    System.out.print("Enter type of service: ");
                    quantityInStock = getInt();
                }

                // Verifies that cost is positive
                System.out.print("Enter unit cost: ");
                double cost = getDouble();
                while (cost <= 0) {
                    System.out.print("Enter amount of sale: ");
                    cost = getDouble();
                }

                // Verifies that retail price is positive
                System.out.print("Enter unit retail price: ");
                double retailPrice = getDouble();
                while (retailPrice <= 0) {
                    System.out.print("Enter date of service: ");
                    retailPrice = getDouble();
                }

                Product newProduct = new Product(productName, quantityInStock, cost, retailPrice);
                //Add all user info to the file(appending)
                printWriter.println(newProduct.toString());

                // Keeps looping until user doesn't want enter anymore sales
                System.out.println("Do you want to enter more products? (y/n): ");
                String enterProducts = getString();
                if (enterProducts.equals("n") || enterProducts.equals("N")){
                    contLoop = false;
                }
            }
            printWriter.close();
        }
        catch (FileNotFoundException fnf){
            System.out.println("Products file does not exist");
        }
    }

    /**
     * Gets string input from user
     * @return user input as a string
     */
    public static String getString() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    /**
     * Gets amount input from user as a double
     * @return amount as a double
     */
    public static double getDouble(){
        Scanner in = new Scanner(System.in);
        return in.nextDouble();
    }

    /**
     * Gets amount input from user as a double
     * @return amount as a double
     */
    public static int getInt(){
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }


    /**
     * Returns true if the user Input is one of the provided service.
     * @param userInput service that user inputs
     * @return true if service exists
     */
    public static boolean isService(String userInput) {
        if (userInput.equals("Dinner") || userInput.equals("Lodging") || userInput.equals("Conference")
                || userInput.equals("Breakfast") || userInput.equals("Lunch")) {
            return true;
        }
        else {
            return false;
        }
    }
}