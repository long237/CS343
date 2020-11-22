package Warehouses;
import Products.Product;
import java.util.ArrayList;
import java.util.Scanner;

// REVIEW: (Is "extends" really the proper way to connect the Controller & Database Boundary Object?
//  cannot access getProducts() / addProduct() without this.)
public class WarehouseUI extends WarehouseController{

    private int mWarehouseNumber;
    private ArrayList<Product> mProductsToDisplay = getProducts(mWarehouseNumber);

    public void selectWarehouseNumber() {
        String menu = "MANAGE WAREHOUSES: \n" +
                "\t 1. Warehouse 1 \n" +
                "\t 2. Warehouse 2 \n" +
                "\t 3. Create a New Warehouse.";
        mWarehouseNumber = inputInteger(menu, 3);
    }

    public int selectMenuOption() {
        displayProducts();
        String menu = "MANAGE WAREHOUSE " + mWarehouseNumber +": \n" +
                "\t 1. Add a Product. \n" +
                "\t 2. Add a Quantity. \n" +
                "\t 3. Display Products by Decreasing Profit Percent. \n" +
                "\t 4. Display Low-In-Stock Products. \n" +
                "\t 5. Display Quantity-In-Stock for each Product by Warehouse.";
        return inputInteger(menu, 5);
    }

    // keira: (OPTION methods) -----------------------------------------------------------------------------------------
    public void selectAddProduct() {
        Scanner in = new Scanner(System.in);
        boolean contLoop = true;
        System.out.println("ADDING PRODUCTS to WAREHOUSE " + mWarehouseNumber + " ... ");
        while (contLoop) {

            // TODO: validate whether product name already exists (here or in controller?)
            String productName = inputString("Enter product name: ", 20);

            int quantityInStock = inputInteger("Enter quantity in stock: ", Integer.MAX_VALUE);
            double cost = inputDouble("Enter cost: ", Double.MAX_VALUE);
            double retailPrice = inputDouble("Enter retail price: ", Double.MAX_VALUE);

            // REVIEW: (WarehouseController does stuff in response; Should implement as Observer of WarehouseUI?)
            addProduct(mWarehouseNumber, productName, quantityInStock, cost, retailPrice);

            // Continue?
            System.out.print("Do you want to enter more products? (y/n): ");
            String enterProducts = in.nextLine();
            if (enterProducts.equals("n") || enterProducts.equals("N")){
                contLoop = false;
            }
        }
    }

    public void selectAddQuantity() {}
    public void selectDecrProfitPercent() {}
    public void selectLowInStock() {}
    public void selectQuantityInStock() {}

    // keira: (VALIDATION methods) -------------------------------------------------------------------------------------

    public String inputString(String userPrompt, int maxNumOfChars) {
        String input = "none";
        System.out.println(userPrompt);
        // while (not string || non-alphabetical-letters || >maxNumOfChars) keep going
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
        // while (not int || non-negative || >maxDouble) keep going
        return input;
    }

    // keira: (DISPLAY methods) ----------------------------------------------------------------------------------------
    public void displayProducts() {
        System.out.println("WAREHOUSE " + mWarehouseNumber);
        for (Product product : mProductsToDisplay) {
            System.out.println(product.toString());
        }
    }
    // TODO: Given a warehouseNumber & its list of Products, prints the Warehouse's list of Products sorted by QuantityInStock.
    public void byQuantityInStock(int warehouseNumber, ArrayList<Product> products) {
        ArrayList<Product> productsByQuantityInStock = products;
        mProductsToDisplay = productsByQuantityInStock;
    }
}
