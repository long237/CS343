package Warehouses;
import Products.Product;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;


public class WarehouseUI {
    Scanner in = new Scanner(System.in);

    // REVIEW: pretty sure this should go under "View Products" in ProductUI / MainUI,
    //         kinda awk. to have this option hidden in "Manage Warehouses" but ok
    public void printProducts(ArrayList<Product> products) {
        System.out.println();
        System.out.println(getProductsTable(products));
    }

    public void printWarehouseProducts(int warehouseNumber, ArrayList<Product> products) {
        System.out.println(); // XXXXX fix extra line when printing multiple warehouses
        System.out.println(" WAREHOUSE " + warehouseNumber + ": ");
        System.out.println(getProductsTable(products));
    }

    public void printWarehousesProducts(int maxNumOfWarehouses, ArrayList<ArrayList<Product>> allProducts) {
        for (int i = 0; i < maxNumOfWarehouses; i++) {
            int warehouseNum = i + 1;
            printWarehouseProducts(warehouseNum, allProducts.get(i));
        }
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
            System.out.println("(ERROR: Warehouse does not exist; Please try again.)");
        }
        int input;
        System.out.println("\nMANAGE A WAREHOUSE:");
        for (int i = 1; i <= maxNumOfWarehouses; i++) {
            System.out.println("\t " + i + ". Warehouse " + i);
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

    public int selectMainMenuOption() {
        int input;
        System.out.println("\nMANAGE WAREHOUSES: \n" +
                "\t 1. Create a Warehouse. \n" +
//                "\t 2. Remove a Warehouse. \n" +
                "\t 2. Manage a Warehouse. \n" +
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

    public int selectMenuOption(int warehouseNumber) {
        int input;
        System.out.println("MANAGE WAREHOUSE 1: \n" +
                "\t 1. Add Products. \n" +
                "\t 2. Remove Products. \n" +
                "\t 3. Add Product Quantity. \n" +
                "\t 4. View Products by Decreasing Profit Percent. \n" +
                "\t 5. View Low-In-Stock Products. \n" +
                "\t 6. View Quantity-In-Stock for each Product by Warehouse.\n" +
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

    public ArrayList<String> addProductMenu() {
        ArrayList<String> outputList = new ArrayList<>();
        outputList.add("name? ");
        outputList.add("quantity in stock?: ");
        outputList.add("cost?: ");
        outputList.add("retail price?: ");
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < 4; i++){
            System.out.println("\tWhat is the product's " + outputList.get(i));
            System.out.println("\t(Enter (-1) to ABORT)");
            System.out.print("\t");
            String input = in.nextLine();
            if (input.equals("-1")) {
                temp.clear();
                temp.add("-1");
                return temp;
            }
            if (i == 1) {
                try {
                    Integer.parseInt(input);
                }
                catch(Exception e) {
                    System.out.println("(ERROR: Invalid input; Please try again.)");
                }
            }
            if (i > 1) {
                try {
                    double dollarAmount = Double.parseDouble(input);
                    DecimalFormat df = new DecimalFormat("#.00");
                    dollarAmount = Double.parseDouble(df.format(dollarAmount));
                    input = Double.toString(dollarAmount);
                } catch (Exception e) {
                    System.out.println("(ERROR: Invalid input, please try again.)");
                    temp.clear();
                    temp.add("-1");
                    return temp;
                }
            }
            temp.add(input);
        }
        return temp;
    }

    public boolean continueThingProducts(int thing) {
        String[] things = {"", "adding", "removing", "adding quantity to"};
        boolean continueAddingProducts = false;
        try {
            System.out.println("\tCONTINUE " + things[thing].toUpperCase() + " PRODUCTS? (ENTER to continue or -1 to exit): ");
            System.out.print("\t");
            String input = in.nextLine();

            continueAddingProducts = !input.equals("-1") && !input.equals("N");
        } catch (Exception e) {
            System.out.println("\t(ERROR: Invalid input; Please try again.)");
        }
        return continueAddingProducts;
    }

    public String removeProductMenu() {
        String productToRemove = "";
        boolean flag = true;
        try {
            System.out.println("\tWhich product would you like to remove? ");
            System.out.println("\t(Enter (-1) to ABORT)");
            System.out.print("\t");
            productToRemove = in.nextLine();
        } catch (Exception e) {
            System.out.println("(ERROR: Invalid input; Please try again.)");
        }
        return productToRemove;
    }

    public HashMap<String, Integer> addQuantityMenu() {
        HashMap<String, Integer> productToAddQuantityTo = new HashMap<>();
        try {
            System.out.println("\tEnter a product to add a quantity to: ");
            //System.out.println("\t(Enter (-1) to ABORT)");
            System.out.print("\t");
            String productName = in.nextLine();
            System.out.println("\tEnter a quantity to add: ");
            System.out.println("\t(Enter (-1) to ABORT)");
            System.out.print("\t");
            int quantityToAdd = in.nextInt();
            in.nextLine();
            productToAddQuantityTo.put(productName, quantityToAdd);
        } catch (Exception e) {
            System.out.println("\t(ERROR: Invalid input; Please try again.)");
        }
        return productToAddQuantityTo;
    }

    public void selectAddQuantity() {}
    public void selectDecrProfitPercent() {}
    public void selectLowInStock() {}
    public void selectQuantityInStock() {}


    // kkkkk: ERROR MESSAGES -------------------------------------------------------------------------------------------

    //There isn't a reason to save a string here, why are we doing this?
    public void exitValidation() {
        System.out.print("Press ENTER to return to MAIN MENU: ");
        in.nextLine();
    }

    public void badNumber(int num) {
        if (num == 0) {
            System.out.println("\t(ERROR: Invalid Quantity; Please try again.)");
        }
        else{
            System.out.println("\t(ERROR: Invalid Cost / Retail Price; Please try again.)");
        }
    }

    public void productExists(boolean productExists) {
        if (productExists) {
            System.out.println("\t(ERROR: Product already exists; Please try again.)");
        }
        else {
            System.out.println("\t(ERROR: Product does not exist; Please try again.)");
        }
    }
}
