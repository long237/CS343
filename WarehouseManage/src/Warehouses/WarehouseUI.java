package Warehouses;
import Products.Product;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;


public class WarehouseUI {
    Scanner in = new Scanner(System.in);

    public int selectWarehouseNumber(int flag, int maxNumOfWarehouses) { // TODO max num warehouses
        if (flag == 1) {
            System.out.println("That warehouse does not exist, please try again: ");
        }
        int input;
        System.out.println("\nMANAGE WAREHOUSES:");
        for (int i = 1; i <= maxNumOfWarehouses; i++) {
            System.out.println("\t " + i + ". Warehouse " + i);;
        }
        System.out.print("Select a warehouse (Enter (-1) to exit): ");
        try {
            input = in.nextInt();
            in.nextLine();
            return (input);
        } catch (Exception e) {
            return -2;
        }
    }

    public void printWarehouseProducts(int warehouseNumber, ArrayList<Product> products) {
        System.out.println("\n WAREHOUSE " + warehouseNumber + ": ");
        System.out.println(getProductsTable(products));
    }

    public static String getProductsTableHeader() {
        return " " + String.format("%-20s %15s %15s %15s %10s %18s %18s %18s %18s",
                "PRODUCT-NAME", "#-IN-STOCK", "COST", "RETAIL-PRICE", "#-SOLD", "TOTAL-SALES",
                "TOTAL-COST", "TOTAL-PROFIT", "TOTAL-PROFIT-%");
    }
    public static String getProductsTable(ArrayList<Product> products) {
        StringBuilder productsTable = new StringBuilder();
        String header = getProductsTableHeader();
        productsTable.append(header);
        productsTable.append("\n");
        for (Product product : products) {
            productsTable.append(product.toString());
            productsTable.append("\n");
        }
        return productsTable.toString();
    }

    public int selectMenuOption(int warehouseNumber) {
        int input;
        System.out.println("MANAGE WAREHOUSE 1: \n" +
                "\t 1. Add Products. \n" +
                "\t 2. Remove Products. \n" +
                "\t 2. Add Product Quantity. \n" +
                "\t 3. Display Products by Decreasing Profit Percent. \n" +
                "\t 4. Display Low-In-Stock Products. \n" +
                "\t 5. Display Quantity-In-Stock for each Product by Warehouse.\n" +
                "Select a menu option (Enter (-1) to exit): ");
        try {
            input = in.nextInt();
            in.nextLine();
            return (input);
        } catch (Exception e) {
            System.out.println("Invalid input, please try again.");
            return -2;
        }
    }

    //addProductMenu returns an ArrayList so that we can return more than one input at a time
    public ArrayList<String> addProductMenu() {
        ArrayList<String> outputList = new ArrayList<>();
        outputList.add("name? ");
        outputList.add("quantity in stock?: ");
        outputList.add("cost?: ");
        outputList.add("retail price?: ");
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            if (i < 4) {
                System.out.println("\tWhat is the product's " + outputList.get(i));
                System.out.println("\t(Enter (-1) to ABORT)");
            }
            else {
                System.out.println("CONTINUE ADDING PRODUCTS? (Enter (-1) to EXIT): ");
            }
            System.out.print("\t");
            String input = in.nextLine();
            if (input.equals("-1") && i != 4) {
                temp.clear();
                temp.add("-1");
                return temp;
            }
            if (i == 1) {
                try {
                    Integer.parseInt(input);
                }
                catch(Exception e) {
                    System.out.println("Invalid input, please try again: ");
                }
            }
            if (i > 1 && i != 4) {
                try {
                    Double.parseDouble(input);
                } catch (Exception e) {
                    System.out.println("Invalid input, please try again: ");
                }
            }
            temp.add(input);
        }
        return temp;
    }

    // returns an array list of an array list containing info for each product to add
    public ArrayList<ArrayList<String>> selectAddProduct(int warehouseNumber) {

        boolean contLoop = true;

        ArrayList<ArrayList<String>> productsInfo = new ArrayList<ArrayList<String>>();
        System.out.println("ADDING PRODUCT(s) to WAREHOUSE " + warehouseNumber + " ... "); // XXXXX: Replace "1" w/ getWarehouseNum() from Controller
        while (contLoop) {

            ArrayList<String> productMenuOption = addProductMenu();
            boolean add = true;
            for (int i = 0; i < productMenuOption.size(); i++) {
                if (productMenuOption.get(i).equals("-1") && i != 4) {
                    add = false;
                }
                if (productMenuOption.get(i).equals("-1")){
                    contLoop = false;
                }
            }
            if (add) {
                productsInfo.add(productMenuOption);
            }

        }
        return productsInfo;
    }

    public String removeProductMenu() {
        System.out.println("Which product would you like to remove?");
        return in.nextLine();
    }


    /*
     *addQuantityMenu takes in a string for all the products in the database to print it out (avoids coupling with database),
     *this returns an ArrayList of integers so that we can return multiple inputs and add quantities in one method, can possibly
     */
    public ArrayList<Integer> addQuantityMenu(String s) {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        boolean flag = true;
        while(flag) {
            try {
                String output = "Add quantity to which product? " + s;
                temp.add(in.nextInt());
                in.nextLine();
                System.out.println("Add how much quantity?: ");
                temp.add(in.nextInt());
                in.nextLine();
                flag = false;
            } catch (Exception e) {
                System.out.println("Invalid input, please try again.");
            }
        }
        return temp;
    }

    public void selectAddQuantity() {}
    public void selectDecrProfitPercent() {}
    public void selectLowInStock() {}
    public void selectQuantityInStock() {}

    // keira: (VALIDATION methods) -------------------------------------------------------------------------------------

    // TODO: finish validation methods
    public String inputString(String userPrompt, int maxNumOfChars) {
        String input = "none";
        System.out.println(userPrompt);
        // while (not string || non-alphabetical-letters || >maxNumOfChars) keep going
        return input;
    }
    public int inputInteger(String userPrompt) {
        int input = 0;
        System.out.println(userPrompt);
        // while (not int || non-negative) keep going
        return input;
    }
    public int inputInteger(String userPrompt, int maxInt) {
        int input = 0;
        System.out.println(userPrompt);
        // while (not int || non-negative || >maxInt) keep going
        return input;
    }
    public double inputDouble(String userPrompt, double maxDouble) {
        double input = 0.0;
        System.out.println(userPrompt);
        // while (not int || non-negative || >maxDouble) ke
        return input;
    }

}
