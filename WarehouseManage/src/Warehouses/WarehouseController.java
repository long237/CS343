package Warehouses;

import Products.Product;
import java.util.ArrayList;

public class WarehouseController extends WarehouseDB {

    public ArrayList<Product> getProducts() {
        return retrieve_products(1);
    }

    public static void Wcontroller() {
        //ArrayList<Product> pDatabase = retrieve_products();

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
