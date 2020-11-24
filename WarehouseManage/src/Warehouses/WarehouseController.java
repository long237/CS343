package Warehouses;

import Products.Product;
import java.util.ArrayList;
import Database.Database;

public class WarehouseController {
    Database database = new Database();
    // keira: (Control Methods) ----------------------------------------------------------------------------------------
//    public ArrayList<Product> getProducts(int warehouseNumber) {
//        return retrieve_products(warehouseNumber);
//    }
    public ArrayList<Product> getProducts(int warehouseNumber) {
        return database.retrieve_products(warehouseNumber);
    }
    // TODO: fix this so that it returns true if Product w/ productName exists in getProducts()
    public boolean productExists(int warehouseNumber, String productName) {
        return getProducts(warehouseNumber).contains(productName);
    }
    public void addProduct(int warehouseNumber, String productName, int quantityInStock, double cost, double retailPrice) {
        ArrayList<Product> warehouseProducts = getProducts(warehouseNumber);
        Product productToAdd = new Product(productName, quantityInStock, cost, retailPrice);
        warehouseProducts.add(productToAdd);
        update_products(warehouseNumber, warehouseProducts);
    }
    public void removeProduct(int warehouseNumber, Product productToRemove) {
        ArrayList<Product> warehouseProducts = getProducts(warehouseNumber);
        warehouseProducts.remove(productToRemove);
        update_products(warehouseNumber, warehouseProducts);
    }
    // keira: (END of Control Methods) ---------------------------------------------------------------------------------

    // REVIEW: (What is the purpose of this method? When will it be called by the UI Boundary Object?)
    public static void warehouseController() {
        //ArrayList<Product> pDatabase = retrieve_products();

        //add product 1
        //add product 2
        //after addition or modification, removal
        //call update_products

        // REVIEW: (Controller "controls" the data, UI Boundary should be the only thing that the user accesses/interacts with
        //  Shouldn't UI & Controller be separate from each other to minimize coupling.)

        //while not -1 keep running
        // display menu
        // 1. add product
        // 2. Remove Product
    }

}
