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

    public HashSet<Product> getProducts() { return mWarehouseProducts; }

    public void addWarehouseProducts(Product p) {
        mWarehouseProducts.add(p);
    }

    public void Save_Database(){
        try {
            String filename = "Warehouse" + this.getWarehouseNumber() + ".txt";
            FileWriter outfile = new FileWriter(filename, true);
            PrintWriter printWriter = new PrintWriter(outfile);
            for (Product p : mWarehouseProducts) {
                printWriter.print(p.getName() + ";" + p.getQuantityInStock() + ";" + p.getCost() + ";" + p.getRetailPrice() + ";"
                        + p.getQuantitySold() + ";" + p.getTotalSales() + ";" + p.getTotalCost() + ";"
                        + p.getTotalProfit() + ";" + p.getTotalProfitPercent());
                printWriter.println();
            }
            printWriter.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Warehouse Database does not exist.");
        }
        catch (IOException e) {
            System.out.println("IO exception !!!!");
        }
    }

    // kkkkk: FOR TESTING PURPOSES ONLY ...
    public static void main(String[] args) {

        // keira: WAREHOUSE 1 ------------------------------------------------------------------------------------------

        boolean contLoop = true;
        Scanner in = new Scanner(System.in);
        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseNumber(1); // Warehouse set as "Warehouse 1"
        System.out.println("ADDING PRODUCTS to WAREHOUSE " + warehouse.getWarehouseNumber() + "...");
        while (contLoop) {
            System.out.print("Enter product name: ");
            String productName = in.nextLine();
            System.out.print("Enter quantity in stock: ");
            int quantityInStock = in.nextInt();
            in.nextLine();
            System.out.print("Enter cost: ");
            double cost = in.nextDouble();
            in.nextLine();
            System.out.print("Enter retail price: ");
            double retailPrice = in.nextDouble();
            in.nextLine();
            Product newProduct = new Product(productName, quantityInStock, cost, retailPrice);
            warehouse.addWarehouseProducts(newProduct);

            // Continue?
            System.out.print("Do you want to enter more products? (y/n): ");
            String enterProducts = in.nextLine();
            if (enterProducts.equals("n") || enterProducts.equals("N")){
                contLoop = false;
            }
        }
        warehouse.Save_Database(); // adds to .txt for "Warehouse 1"


        // keira: WAREHOUSE 2 ------------------------------------------------------------------------------------------

        contLoop = true;
        warehouse = new Warehouse();
        warehouse.setWarehouseNumber(2);
        System.out.println("ADDING PRODUCTS to WAREHOUSE " + warehouse.getWarehouseNumber() + "...");
        while (contLoop) {
            System.out.print("Enter product name: ");
            String productName = in.nextLine();
            System.out.print("Enter quantity in stock: ");
            int quantityInStock = in.nextInt();
            in.nextLine();
            System.out.print("Enter cost: ");
            double cost = in.nextDouble();
            in.nextLine();
            System.out.print("Enter retail price: ");
            double retailPrice = in.nextDouble();
            in.nextLine();
            Product newProduct = new Product(productName, quantityInStock, cost, retailPrice);
            warehouse.addWarehouseProducts(newProduct);

            // Continue?
            System.out.print("Do you want to enter more products? (y/n): ");
            String enterProducts = in.nextLine();
            if (enterProducts.equals("n") || enterProducts.equals("N")){
                contLoop = false;
            }
        }
        warehouse.Save_Database();
    }
}
