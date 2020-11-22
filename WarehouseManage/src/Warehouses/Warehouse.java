package Warehouses;
import Products.Product;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

public class Warehouse {

    private int mWarehouseNumber;
    private HashSet<Product> mWarehouseProducts;

    public Warehouse() {
        mWarehouseNumber = 0;
        mWarehouseProducts = new HashSet<Product>();
    }

    public int getWarehouseNumber() { return mWarehouseNumber; }
    public void setWarehouseNumber(int mWarehouseNumber) { this.mWarehouseNumber = mWarehouseNumber; }

    public HashSet<Product> getWarehouseProducts() { return mWarehouseProducts; }
    public void addWarehouseProducts(Product p) {
        mWarehouseProducts.add(p);
    }

}
