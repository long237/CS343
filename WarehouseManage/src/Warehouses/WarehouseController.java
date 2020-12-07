package Warehouses;
import Products.Product;

import java.util.*;
import Database.Database;
import Products.ProfitPercentComparator;
import Products.QuantityInStockComparator;

public class WarehouseController {

    private Database db = new Database();
    private WarehouseUI ui = new WarehouseUI();

    // REVIEW: this makes me want to throw up but hey - it works ¯\_(ツ)_/¯
    // kkkkk: returns a list of products where quantityInStock = the TOTAL quantityInStock (not just in 1 warehouse)
    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> allProducts = new ArrayList<>();
        ArrayList<ArrayList<Product>> productsFromEachWarehouse = getProductsFromEachWarehouse();
        for (ArrayList<Product> productsFromOneWarehouse : productsFromEachWarehouse) {
            for (Product p : productsFromOneWarehouse) {
                if (allProducts.contains(p)) {
                    for (Product product : allProducts) {
                        if (product.equals(p)){
                            product.addQuantityInStock(p.getQuantityInStock());
                        }
                    }
                }
                else {
                    allProducts.add(p);
                }
            }
        }
        return allProducts;
    }

    // kkkkk: returns a list of products from the given warehouse
    public ArrayList<Product> getProducts(int warehouseNumber) { return db.retrieve_warehouse(warehouseNumber); }

    // kkkkk: returns list of products from each & every warehouse (ie. allProducts.get(0) == the products in WAREHOUSE 1)
    public ArrayList<ArrayList<Product>> getProductsFromEachWarehouse() {
        ArrayList<ArrayList<Product>> allProducts = new ArrayList<>();
        for (int i = 1; i <= db.maxWarehouses(); i++) {
            allProducts.add(getProducts(i));
        }
        return allProducts;
    }
    public int getMaxNumOfWarehouses() { return db.maxWarehouses(); }

    // kkkkk: returns the Product in the given warehouseNum w/ the given productName
    public Product getProduct(int warehouseNumber, String productName) {
        Product productToReturn = null; // productToReturn = null if not there
        for (Product product : getProducts(warehouseNumber)) {
            if (product.getName().equals(productName)) {
                productToReturn = product;
            }
        }
        return productToReturn;
    }

    // kkkkk: returns TRUE if Product w/ given productName exists in getProducts()
    public boolean findProduct(int warehouseNumber, String productName) {
        for (Product product : getProducts(warehouseNumber)) {
            if (product.getName().toLowerCase().equals(productName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    // kkkkk: returns TRUE if Product w/ given productInfo was successfully added to database
    public boolean addProduct(int warehouseNumber, String productName, int quantityInStock, double cost, double retailPrice) {
        boolean addedProduct = false;
        Product productToAdd = new Product(productName, quantityInStock, cost, retailPrice);
        if (!findProduct(warehouseNumber, productToAdd.getName())) {
            ArrayList<Product> warehouseProducts = getProducts(warehouseNumber);
            warehouseProducts.add(productToAdd);
            db.update_warehouse(warehouseNumber, warehouseProducts);
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
            db.update_warehouse(warehouseNumber, warehouseProducts);
            removedProduct = true;
        }
        return removedProduct;
    }

    public boolean addProductQuantity(int warehouseNumber, String productName, int quantityToAdd) {
        boolean addedQuantity = false;
        ArrayList<Product> warehouseProducts = getProducts(warehouseNumber);
        for (Product product : warehouseProducts) {
            if (quantityToAdd < 0 ) { // if quantityToAdd is negative -> return FALSE
                break;
            }
            if (product.getName().equals(productName)) {
                //System.out.println("ADDING " + quantityToAdd + " " + productName.toUpperCase() + "'s...");
                product.addQuantityInStock(quantityToAdd);
                //System.out.println(product);
                db.update_warehouse(warehouseNumber, warehouseProducts);
                addedQuantity = true;
            }
        }
        return addedQuantity;
    }

    public void warehouseController() {

        int mainMenuOption = 0;
        while (mainMenuOption != -1) {
            // kkkkk: ("WAREHOUSES ...")
            ui.printWarehousesProducts(getMaxNumOfWarehouses(), getProductsFromEachWarehouse());
            // kkkkk: ("MANAGE WAREHOUSES: " Window) -------------------------------------------------------------------
            mainMenuOption = ui.selectMainMenuOption();

            // kkkkk: (1. Create a Warehouse)
            if (mainMenuOption == 1) {
                db.create_warehouse();
            }

            // kkkkk: (2. Manage a Warehouse.)
            else if (mainMenuOption == 2) {
                int warehouseNumber = 0;
                while (warehouseNumber != -1) { // User enters "-1" to exit / go back to Main Menu Window.
                    int menuOption = 0;
                    // kkkkk: ("MANAGE A WAREHOUSE: " Window) ----------------------------------------------------------
                    warehouseNumber = ui.selectWarehouseNumber(0, getMaxNumOfWarehouses());

                    //Runs the UI again with a flag to print an error message if the warehousenumber is wrong
                    while (warehouseNumber > db.maxWarehouses() || (warehouseNumber < 1 && warehouseNumber != -1)) {
                        warehouseNumber = ui.selectWarehouseNumber(1, getMaxNumOfWarehouses());
                    }
                    if (warehouseNumber == -1) { // User entered "-1" to exit / go back to Main Menu Window.
                        break;
                    }
                    // kkkkk: ("WAREHOUSE 1: ")
                    while (menuOption != -1) { // User enters "-1" to exit / go back to "Manage Warehouse: " Window.

                        ArrayList<Product> warehouseToDisplay = getProducts(warehouseNumber);
                        ArrayList<Product> productsToView = getAllProducts();
                        ui.printWarehouseProducts(warehouseNumber, warehouseToDisplay);

                        // kkkkk: ("MANAGE WAREHOUSE 1: " Window) ------------------------------------------------------
                        menuOption = ui.selectMenuOption(warehouseNumber);

                        // kkkkk: (1. Add Products.)
                        if (menuOption == 1) {
                            // KKKKK: ("ADDING PRODUCT(s) TO WAREHOUSE 1 ... " Window)
                            boolean cont = true;
                            while(cont) {
                                boolean dontPrint = false;
                                boolean addFlag = true;
                                ArrayList<String> productInfo = ui.addProductMenu();
                                if (productInfo.get(0).equals("-1")) { // User wants to abort.
                                    break;
                                }
                                if (findProduct(warehouseNumber, productInfo.get(0))) {
                                    ui.productExists(true); // "ERROR: product w/ given product name already exists"
                                    dontPrint = true;
                                }
                                if (Integer.parseInt(productInfo.get(1)) < 0) {
                                    ui.badNumber(0);
                                    addFlag = false;
                                    dontPrint = true;
                                }

                                if (productInfo.get(2).length() <= 4 && (Double.parseDouble(productInfo.get(2)) < 0 || Double.parseDouble(productInfo.get(3)) < 0)) {
                                    ui.badNumber(1);
                                    addFlag = false;
                                    dontPrint = true;
                                }
                                if (addFlag) {
                                    addProduct(warehouseNumber, productInfo.get(0), Integer.parseInt(productInfo.get(1)), Double.parseDouble(productInfo.get(2)), Double.parseDouble(productInfo.get(3)));
                                }
                                if (!dontPrint) {
                                    cont = ui.continueThingProducts(menuOption);
                                }
                            }
                        }
                        // kkkkk: (2. Remove Products.)
                        else if (menuOption == 2) {
                            boolean cont = true;
                            while(cont) {
                                boolean dontPrint = false;
                                String productToRemove = ui.removeProductMenu();
                                if (productToRemove.equals("-1")) { // User wants to abort.
                                    break;
                                }
                                if (!findProduct(warehouseNumber, productToRemove)) {
                                    ui.productExists(false); // "ERROR: product w/ entered product name does not exist"
                                    dontPrint = true;
                                }
                                removeProduct(warehouseNumber, productToRemove);
                                if (!dontPrint) {
                                    cont = ui.continueThingProducts(menuOption);
                                }
                            }
                        }
                        // kkkkk: (3. Add Product Quantity.)
                        else if (menuOption == 3) {
                            // KKKKK: "ADD PRODUCT QUANTITY: " Window
                            boolean cont = true;
                            while(cont) {
                                boolean dontPrint = false;
                                HashMap<String, Integer> productToAddQuantityTo = ui.addQuantityMenu();
                                for (Map.Entry<String, Integer> product : productToAddQuantityTo.entrySet()) {
                                    if (product.getKey().equals("-1") || product.getValue() == -1) { // User wants to abort.
                                        cont = false;
                                        break;
                                    }
                                    if (!findProduct(warehouseNumber, product.getKey())) {
                                        ui.productExists(false);
                                        dontPrint = true;
                                    }
                                    if (product.getValue() < 0) {
                                        ui.badNumber(0);
                                        dontPrint = true;
                                    }
                                    addProductQuantity(warehouseNumber, product.getKey(), product.getValue());
                                    if (!dontPrint) {
                                        cont = ui.continueThingProducts(menuOption);
                                    }
                                }
                            }
                        }
                        // kkkkk: (4. View Low-In-Stock products.)
                        else if (menuOption ==4) {
                            ArrayList<Product> temp = new ArrayList<>();
                            for (Product p : productsToView) {
                                if (p.isLowStock()) {
                                    temp.add(p);
                                }
                            }
                            ui.printProductsWOBusinessLogic(temp);
                            ui.exitValidation();
                        }
                    }
                }
            }
            // kkkkk: (3. View All Products.)
            else if (mainMenuOption == 3) {
//                // kkkkk: (4. View Products By Decreasing Profit Percent.)
//                Collections.sort(productsToView, new ProfitPercentComparator());
//                ui.printProducts(productsToView);
//                ui.exitValidation();
                ArrayList<Product> productsToView = getAllProducts();
                Collections.sort(productsToView, new ProfitPercentComparator());
                ui.printProducts(productsToView);
                ui.exitValidation();
            }
            // (END of mainMenuOption == 3)

            // kkkkk: (5. View Products By Increasing Quantity-In-Stock.)
            else if (mainMenuOption == 4) {
                ArrayList<ArrayList<Product>> warehouses = getProductsFromEachWarehouse();
                for (ArrayList<Product> warehouse : warehouses) {
                    Collections.sort(warehouse, new QuantityInStockComparator());
                }
                //ui.printWarehouseProducts(warehouseNumber, productsToView);
                ui.printWarehousesProducts(getMaxNumOfWarehouses(), warehouses);
                ui.exitValidation();
            }
        }
    }
}
