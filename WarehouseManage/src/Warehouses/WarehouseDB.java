package Warehouses;

import Invoices.Invoice;
import Products.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WarehouseDB {

    public void update_products(ArrayList<Product> products){
        //Product 1         price
        //Prduct 2          price       ...
    }

    public ArrayList<Product> retrieve_products(int warehouseNumber) {
        ArrayList<Product> products = new ArrayList<Product>();

        File file = new File("Warehouse" + warehouseNumber + ".txt");
        Scanner input = null;
        try { input = new Scanner(file); } catch (FileNotFoundException e) { e.printStackTrace(); }
        while (true) {
            assert input != null;
            if (!input.hasNextLine()) break;

            String[] productInfo = input.nextLine().split(";");

            String productName = productInfo[0];
            int quantityInStock = Integer.parseInt(productInfo[1]);
            double cost = Double.parseDouble(productInfo[2]);
            double retailPrice = Double.parseDouble(productInfo[3]);
            int quantitySold = Integer.parseInt(productInfo[4]);
            // the rest (totalSales, totalCost, totalProfit, totalProfitPercent)
            // should be calculated automatically in toString() ...

            Product product = new Product(productName, quantityInStock, cost, retailPrice, quantitySold);
            products.add(product);
        }
        return products;
    }

    // kkkkk: FOR TESTING PURPOSES ONLY ...
    public void main(String[] args) throws FileNotFoundException {
        ArrayList<Product> products = retrieve_products(1);
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
