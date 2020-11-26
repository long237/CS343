package Warehouses;
import Products.Product;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;


public class WarehouseUI {
    Scanner in = new Scanner(System.in);

    public void printWarehouseProducts(int warehouseNumber, ArrayList<Product> products) {
        System.out.println("\n WAREHOUSE " + warehouseNumber + ": ");
        System.out.println(getProductsTable(products));
    }

    public String getProductsTableHeader() {
        return " " + String.format("%-20s %15s %15s %15s %10s %18s %18s %18s %18s",
                "PRODUCT-NAME", "#-IN-STOCK", "COST", "RETAIL-PRICE", "#-SOLD", "TOTAL-SALES",
                "TOTAL-COST", "TOTAL-PROFIT", "TOTAL-PROFIT-%");
    }
    public String getProductsTable(ArrayList<Product> products) {
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

    public int selectWarehouseNumber(int flag, int maxNumOfWarehouses) {
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

    public int selectMenuOption(int warehouseNumber) {
        int input;
        System.out.println("MANAGE WAREHOUSE 1: \n" +
                "\t 1. Add Products. \n" +
                "\t 2. Remove Products. \n" +
                "\t 3. Add Product Quantity. \n" +
                "\t 4. View Products by Decreasing Profit Percent. \n" +
                "\t 5. View Low-In-Stock Products. \n" +
                "\t 6. View Quantity-In-Stock for each Product by Warehouse.\n" + // TODO: (Display BOTH Warehouses)
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
        System.out.println("ADDING PRODUCT(s) to WAREHOUSE " + warehouseNumber + " ... ");
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

    public ArrayList<String> removeProductMenu() {
        ArrayList<String> productsToRemove = new ArrayList<>();
        boolean flag = true;
        System.out.println("REMOVING PRODUCT(s) ... ");
        while(flag) {
            try {
                System.out.println("\tWhich product would you like to remove? ");
                System.out.print("\t");
                productsToRemove.add(in.nextLine());

                // Continue?
                System.out.print("CONTINUE REMOVING PRODUCT(s)? (Enter (-1) to EXIT): ");
                String enterProducts = in.nextLine();
                if (enterProducts.equals("-1") || enterProducts.equals("N")){
                    flag = false;
                }
            } catch (Exception e) {
                System.out.println("Invalid input, please try again.");
            }
        }
        return productsToRemove;
    }

    public HashMap<String, Integer> addQuantityMenu() {
        HashMap<String, Integer> temp = new HashMap<>();
        boolean flag = true;
        System.out.println("ADDING QUANTITY to PRODUCT(s) ... ");
        while(flag) {
            try {
                System.out.println("Enter a product to add a quantity to: ");
                String productName = in.nextLine();
                System.out.println("Enter a quantity to add: ");
                int quantityToAdd = in.nextInt();
                in.nextLine();
                temp.put(productName, quantityToAdd);

                // Continue?
                System.out.print("CONTINUE ADDING QUANTITY to PRODUCT(s)? (Enter (-1) to EXIT): ");
                String enterProducts = in.nextLine();
                if (enterProducts.equals("-1") || enterProducts.equals("N")){
                    flag = false;
                }
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

    public void exitValidation() {
        System.out.print("Press ENTER to return to MAIN MENU: ");
        String input = in.nextLine();
    }
}
