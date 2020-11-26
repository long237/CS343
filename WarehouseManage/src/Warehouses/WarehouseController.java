package Warehouses;

import Products.Product;
import java.util.ArrayList;
import Database.Database;

public class WarehouseController {


    private Database db = new Database();
    private WarehouseUI ui = new WarehouseUI();

    // keira: (Control Methods) ----------------------------------------------------------------------------------------

    public ArrayList<Product> getProducts(int warehouseNumber) {
        return db.retrieve_products(warehouseNumber);
    }

    public int getMaxNumOfWarehouses() {
        return db.maxWarehouses();
    }

    public Product getProduct(int warehouseNumber, String productName) {
        Product productToReturn = new Product(); // productToReturn.getName() => "none"
        for (Product product : getProducts(warehouseNumber)) {
            if (product.getName().equals(productName)) {
                productToReturn = product;
            }
        }
        return productToReturn;
    }

    // kkkkk: returns true if Product w/ given productName exists in getProducts()
    public boolean findProduct(int warehouseNumber, String productName) {
        boolean productExists = false;
        if (!getProduct(warehouseNumber, productName).getName().equals("none")) {
            productExists = true;
        }
        return productExists;
    }

    // kkkkk: returns TRUE if Product was successfully added
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

    // kkkkk: returns TRUE if Product was successfully removed
    public boolean removeProduct(int warehouseNumber, String productToRemove) {
        boolean removedProduct = false;

        if (findProduct(warehouseNumber, productToRemove)) {
            ArrayList<Product> warehouseProducts = getProducts(warehouseNumber);
            warehouseProducts.remove(getProduct(warehouseNumber, productToRemove));
            db.update_products(warehouseNumber, warehouseProducts);
            removedProduct = true;
        }
        return removedProduct;
    }

    // keira: (END of Control Methods) ---------------------------------------------------------------------------------

    // REVIEW: (What is the purpose of this method? When will it be called by the UI Boundary Object?)
    public void warehouseController() {
        int menuOption = 0;
        int warehouseNumber = 0;
        ArrayList<ArrayList<String>> productsToAdd = new ArrayList<>();

        // kkkkk: "MANAGE WAREHOUSES: " Window
        while (warehouseNumber != -1) { // User enters "-1" to exit / go back to Main Menu Window.
            menuOption = 0;
            warehouseNumber = ui.selectWarehouseNumber(0, getMaxNumOfWarehouses());
            //Runs the UI again with a flag to print an error message if the warehousenumber is wrong
            while (warehouseNumber > db.maxWarehouses() || (warehouseNumber < 1 && warehouseNumber != -1)) {
                warehouseNumber = ui.selectWarehouseNumber(1, getMaxNumOfWarehouses());
            }

            // kkkkk: "WAREHOUSE 1: "
            while (menuOption != -1) { // User enters "-1" to exit / go back to "Manage Warehouse: " Window.
                ui.printWarehouseProducts(warehouseNumber, getProducts(warehouseNumber)); // TODO: (create sort methods & input the List of Products into this method)
                // kkkkk: "MANAGE WAREHOUSE 1: " Window
                menuOption = ui.selectMenuOption(warehouseNumber);
                // kkkkk: "ADDING PRODUCT(s) TO WAREHOUSE 1 ... " Window
                if (menuOption == 1) {
                    productsToAdd = ui.selectAddProduct(warehouseNumber);
                    for (ArrayList<String> productInfo : productsToAdd) {
                        if (Integer.parseInt(productInfo.get(1)) < 0) {
                            break;
                        }
                        if (Double.parseDouble(productInfo.get(2)) < 0 || Double.parseDouble(productInfo.get(3)) < 0) {
                            break;
                        }
                        addProduct(warehouseNumber, productInfo.get(0), Integer.parseInt(productInfo.get(1)), Double.parseDouble(productInfo.get(2)), Double.parseDouble(productInfo.get(3)));
                    }
                }
                // kkkkk: "REMOVING PRODUCT(s) FROM WAREHOUSE 1 ..." Window
                else if (menuOption == 2) {
                    String productToRemove = ui.removeProductMenu();
                    removeProduct(warehouseNumber, productToRemove); // TODO: let the user know if was unable to add productToRemove
                }
                // kkkkk: "ADD PRODUCT QUANTITY: " Window
                //add product 1
                //add product 2
                //after addition or modification, removal
                //call update_products


                //while not -1 keep running
                // display menu
                // 1. add product
                // 2. Remove Product
            }
        }
    }
}
