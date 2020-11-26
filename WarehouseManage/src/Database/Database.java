package Database;

import Invoices.Invoice;
import Products.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {


    public static void update_invoices(ArrayList<Invoice> invoices) {
        // invoice 1 ...
        // incoice 2....
    }

    public static ArrayList<Invoice> retrieve_invoices() {
        ArrayList<Invoice> invoices = new ArrayList<Invoice>();
        // invoice 1  index 0
        // invoice 2   index 1
        // invoice 3  index 2

        // recreates invoices
        // recreates products
        // add products objects to Hashset in each invoice
        // add all invoices ot arraylist
        // return arraylist
        return invoices;
    }

    // TODO: Given a Warehouse & a list of Products, rewrites that Warehouse's DB to contain those Products.
    public void update_products(int warehouseNumber, ArrayList<Product> warehouseProducts){
        try {
            String filename = "Warehouse" + warehouseNumber + ".txt";
            FileWriter outfile = new FileWriter(filename, false);
            PrintWriter printWriter = new PrintWriter(outfile);
            for (Product p : warehouseProducts) {
                printWriter.print(p.getName() + ";" + p.getQuantityInStock() + ";" + p.getCost() + ";" + p.getRetailPrice() + ";"
                        + p.getQuantitySold() + ";" + p.getTotalSales() + ";" + p.getTotalCost() + ";"
                        + p.getTotalProfit() + ";" + p.getTotalProfitPercent());
                printWriter.println();
            }
            printWriter.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Warehouse Database.Database does not exist.");
        }
        catch (IOException e) {
            System.out.println("IO exception !!!!");
        }
    }

    // TODO: Given a Warehouse, returns a list of Products currently stored in that Warehouse.
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

    public int maxWarehouses() {
        int warehouseCount = 1;
        while (true) {
            File tempFile = new File("Warehouse" + Integer.toString(warehouseCount) + ".txt");
            boolean exists = tempFile.exists();
            if (!exists) {
                break;
            }
            warehouseCount++;
        }
        return warehouseCount-1;
    }
}
