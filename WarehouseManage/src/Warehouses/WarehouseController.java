package Warehouses;
import Products.Product;
import java.lang.reflect.Array;
import java.util.*;
import Database.Database;
import Products.ProfitPercentComparator;
import Products.QuantityInStockComparator;

public class WarehouseController {

    private Database db = new Database();
    private WarehouseUI ui = new WarehouseUI();

    public ArrayList<Product> getProducts(int warehouseNumber) { return db.retrieve_products(warehouseNumber); }
    public int getMaxNumOfWarehouses() { return db.maxWarehouses(); }

    public Product getProduct(int warehouseNumber, String productName) {
        Product productToReturn = new Product(); // productToReturn.getName() => "none"
        for (Product product : getProducts(warehouseNumber)) {
            if (product.getName().equals(productName)) {
                productToReturn = product;
            }
        }
        return productToReturn;
    }

    // kkkkk: returns TRUE if Product w/ given productName exists in getProducts()
    public boolean findProduct(int warehouseNumber, String productName) {
        boolean productExists = false;
        if (!getProduct(warehouseNumber, productName).getName().equals("none")) {
            productExists = true;
        }
        return productExists;
    }

    // kkkkk: returns TRUE if Product w/ given productInfo was successfully added to database
    public boolean addProduct(int warehouseNumber, String productName, int quantityInStock, double cost, double retailPrice) {
        boolean addedProduct = false;
        Product productToAdd = new Product(productName, quantityInStock, cost, retailPrice);
        if (!findProduct(warehouseNumber, productToAdd.getName())) {
            ArrayList<Product> warehouseProducts = getProducts(warehouseNumber);
            warehouseProducts.add(productToAdd);
            db.update_products(warehouseNumber, warehouseProducts);
            addedProduct = true;
        }
        return addedProduct;
    }

    // kkkkk: (returns TRUE if productToRemove was successfully removed from database)
    public boolean removeProduct(int warehouseNumber, String productToRemove) {
        boolean removedProduct = false;
        if (findProduct(warehouseNumber, productToRemove)) {
            ArrayList<Product> warehouseProducts = getProducts(warehouseNumber);
            //System.out.println("REMOVING: " + getProduct(warehouseNumber, productToRemove).getName());
            warehouseProducts.remove(getProduct(warehouseNumber, productToRemove));
            //System.out.println("WAREHOUSE AFTER REMOVING: " + warehouseProducts);
            db.update_products(warehouseNumber, warehouseProducts);
            removedProduct = true;
        }
        return removedProduct;
    }

    public boolean addProductQuantity(int warehouseNumber, String productName, int quantityToAdd) {
        boolean addedQuantity = false;
        ArrayList<Product> warehouseProducts = getProducts(warehouseNumber);
        for (Product product : warehouseProducts) {
            if (product.getName().equals(productName)) {
                //System.out.println("ADDING " + quantityToAdd + " " + productName.toUpperCase() + "'s...");
                product.addQuantityInStock(quantityToAdd);
                //System.out.println(product);
                db.update_products(warehouseNumber, warehouseProducts);
                addedQuantity = true;
            }
        }
        return addedQuantity;
    }

    public void warehouseController() {
        int menuOption = 0;
        int warehouseNumber = 0;
        ArrayList<ArrayList<String>> productsToAdd = new ArrayList<>();

        // kkkkk: ("MANAGE WAREHOUSES: " Window)
        while (warehouseNumber != -1) { // User enters "-1" to exit / go back to Main Menu Window.
            menuOption = 0;
            warehouseNumber = ui.selectWarehouseNumber(0, getMaxNumOfWarehouses());
            //Runs the UI again with a flag to print an error message if the warehousenumber is wrong
            while (warehouseNumber > db.maxWarehouses() || (warehouseNumber < 1 && warehouseNumber != -1)) {
                warehouseNumber = ui.selectWarehouseNumber(1, getMaxNumOfWarehouses());
            }
            // kkkkk: ("WAREHOUSE 1: ")
            while (menuOption != -1) { // User enters "-1" to exit / go back to "Manage Warehouse: " Window.

                ArrayList<Product> productsToDisplay = getProducts(warehouseNumber);
                ui.printWarehouseProducts(warehouseNumber, productsToDisplay);

                // kkkkk: ("MANAGE WAREHOUSE 1: " Window)
                menuOption = ui.selectMenuOption(warehouseNumber);

                // kkkkk: (1. Add Products.)
                if (menuOption == 1) {
                    // KKKKK: ("ADDING PRODUCT(s) TO WAREHOUSE 1 ... " Window)
                    while(true) {
                        boolean addFlag = true;
                        ArrayList<String> productInfo = ui.addProductMenu();
                        if (productInfo.get(4).equals("-1")){
                            break;
                        }
                        if (Integer.parseInt(productInfo.get(1)) < 0) {
                            ui.badNumber(0);
                            addFlag = false;
                        }
                        if (productInfo.get(2).length() <= 4 && (Double.parseDouble(productInfo.get(2)) < 0 || Double.parseDouble(productInfo.get(3)) < 0)) {
                            ui.badNumber(1);
                            addFlag = false;
                        }
                        // TODO: let the user know if was unable to add productInfo[0] (productToAdd's name) & remprompt user for input.
                        if (addFlag) {
                            addProduct(warehouseNumber, productInfo.get(0), Integer.parseInt(productInfo.get(1)), Double.parseDouble(productInfo.get(2)), Double.parseDouble(productInfo.get(3)));
                        }
                    }
                }
                // kkkkk: (2. Remove Products.)
                else if (menuOption == 2) {
                    // KKKKK: ("REMOVING PRODUCT(s) FROM WAREHOUSE 1 ..." Window)
                    ArrayList<String> productsToRemove = ui.removeProductMenu();
                    // TODO: let the user know if was unable to remove productToRemove & remprompt user for input.
                    for (String productToRemove : productsToRemove) {
                        removeProduct(warehouseNumber, productToRemove);
                    }
                }
                // kkkkk: 3. Add Product Quantity.
                else if (menuOption == 3) {
                    // KKKKK: "ADD PRODUCT QUANTITY: " Window
                    HashMap<String, Integer> productsToAddQuantityTo = ui.addQuantityMenu();
                    for (Map.Entry<String, Integer> product : productsToAddQuantityTo.entrySet()) {
                        // TODO: let user know if productName or productQuantity is invalid / unable to add productQuantity & remprompt user for input.
                        addProductQuantity(warehouseNumber, product.getKey(), product.getValue());
                    }
                }
                // kkkkk: (4. View Products By Decreasing Profit Percent.)
                else if (menuOption == 4) {
                    Collections.sort(productsToDisplay, new ProfitPercentComparator());
                    ui.printWarehouseProducts(warehouseNumber, productsToDisplay);
                    ui.exitValidation();
                }
                // kkkkk: (5. View Low-In-Stock products.)
                else if (menuOption ==5) {
                    ArrayList<Product> temp = new ArrayList<>();
                    for (Product p : productsToDisplay) {
                        if (p.isLowStock()) {
                            temp.add(p);
                        }
                    }
                    ui.printWarehouseProducts(warehouseNumber, temp);
                    ui.exitValidation();
                }
                // kkkkk: (6. View Products By Increasing Quantity-In-Stock.)
                else if (menuOption == 6) {
                    Collections.sort(productsToDisplay, new QuantityInStockComparator());
                    ui.printWarehouseProducts(warehouseNumber, productsToDisplay);
                    ui.exitValidation();
                }
            }
        }
    }
}
