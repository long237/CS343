import Invoices.Invoice;
import Products.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseBoundary {

    public static void save_product(ArrayList<Product> products){
        //Product 1         price
        //Prduct 2          price       ...
    }

    public static ArrayList<Product> read_product(int warehouseNumber) throws FileNotFoundException {
        ArrayList<Product> products = new ArrayList<Product>();

        File file = new File("Warehouse" + warehouseNumber + ".txt");
        Scanner input = new Scanner(file);
        while (input.hasNextLine()) {
            // kkkkk: For each line in a warehouse's .txt file ...

            // KKKKK: 1. Separate the line by ";"
            String[] productInfo = input.nextLine().split(";");
            // KKKKK: 2. Save each string in "productInfo" as a variable.
            String productName = productInfo[0];
            int quantityInStock = Integer.parseInt(productInfo[1]);
            double cost = Double.parseDouble(productInfo[2]);
            double retailPrice = Double.parseDouble(productInfo[3]);
            int quantitySold = Integer.parseInt(productInfo[4]);

            // the rest should be calculated automatically in toString() ...
//            double totalSales = Double.parseDouble(productInfo[5]);
//            double totalCost = Double.parseDouble(productInfo[6]);
//            double totalProfit = Double.parseDouble(productInfo[7]);
//            double totalProfitPercent = Double.parseDouble(productInfo[8]);

            // KKKKK: 3. Create a new product w/ the given info.
            Product product = new Product(productName, quantityInStock, cost, retailPrice, quantitySold);
            // KKKKK: 4. Save the product to ArrayList of "products"
            products.add(product);
        }
        return products;
    }

    public static void save_invoice(ArrayList<Invoice> list) {
        // invoice 1 ...
        // incoice 2....

    }

    public static ArrayList<Invoice> read_invoice() {
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

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Product> products = read_product(1);
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
