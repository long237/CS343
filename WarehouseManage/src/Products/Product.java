package Products;

// XXXXX:       (fix ASAP!)         (#c6001a)
// KEIRA:       (main headers)      (#ff0070)
// kkkkk:       (sub-headers)       (#fcb9c5)
// KKKKK:       (sub sub-headers)   (#b97474)
//
// TODO:        (incomplete)        (#ccff00)
// REVIEW:      (check / relay)     (#00b9ff)

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Product {

    private String mName;
    private int mQuantityInStock;
    private double mCost;
    private double mTotalCost;
    private double mRetailPrice;
    private int mQuantitySold;       //how many sold per invoice     //Warehouse_sold = isold1 + isold2 + ....
    private double mTotalSales;

    public Product() {
        this.mName = "none";
        this.mQuantityInStock = 0;
        this.mCost = 0.0;
        this.mTotalCost = 0.0;
        this.mRetailPrice = 0.0;
        this.mQuantitySold = 0;
        this.mTotalSales = 0.0;
    }

    public Product(String mName, int mQuantityInStock, double mCost, double mRetailPrice) {
        this.mName = mName;
        this.mQuantityInStock = mQuantityInStock;
        this.mCost = mCost;
        this.mTotalCost = mQuantityInStock * mCost;
        this.mRetailPrice = mRetailPrice;
        this.mQuantitySold = 0;
        this.mTotalSales = 0.0;
    }

    public String getName() { return mName; }
    public void setName(String name) { mName = name; }

    public int getQuantityInStock() { return mQuantityInStock; }
    public void setQuantityInStock(int quantityInStock) { mQuantityInStock = quantityInStock; }
    public void addQuantityInStock(int quantityReordered) {
        mQuantityInStock += quantityReordered;
        mTotalCost += quantityReordered * mCost;
    }
    public boolean isLowStock() { return mQuantityInStock <= 5; }

    public double getCost() { return mCost; }
    public void setCost(double cost) { mCost = cost; }

    public double getTotalCost() {
        return mTotalCost;
    }

    public double getRetailPrice() { return mRetailPrice; }
    public void setRetailPrice(double price) { mRetailPrice = price; }

    public int getQuantitySold() { return mQuantitySold; }
    public void addQuantitySold(int quantitySold) {
        mQuantitySold += quantitySold;
        mQuantityInStock -= quantitySold;
        mTotalSales += quantitySold * mRetailPrice;
    }

    public double getTotalSales() {
        return mTotalSales;
    }
    public double getTotalProfit() {
        double totalProfit = mTotalSales - mTotalCost;
        return totalProfit;
    }

    // REVIEW: ( getTotalProfitPercent() )
    public double getTotalProfitPercent() {
        double totalProfitPercent = (getTotalProfit() / mTotalCost) * 100;
        return totalProfitPercent;
    }

    @Override
    public String toString() {

        // FOR FUTURE REFERENCE...
        // "%":      1 for each variable listed.
        // "-":      left-align (w/o -> right-align)
        // "[int]":  # of spaces designated for each variable
        // "s":      string
        // "f":      float (".2f": formats floats as "0.00")

        return String.format(" %-20s %15s %15.2f %15.2f %10s %18.2f %18.2f %18.2f %17.1f%%",
                             mName, mQuantityInStock, mCost, mRetailPrice, mQuantitySold,
                             mTotalSales, mTotalCost, getTotalProfit(), getTotalProfitPercent());
    }

    public void Save_Database (){
        try {
            FileWriter outfile = new FileWriter("ProductsDatabase.txt", true);
            PrintWriter printWriter = new PrintWriter(outfile);
            printWriter.println(this.getName() + ";" + this.getQuantityInStock() + ";" + this.getCost() + ";"
                    + this.getTotalSales() + ";" + this.getTotalProfit() + ";" + this.getTotalProfitPercent() + ";");
            printWriter.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Invoice Data base does not exist.");
        }
        catch (IOException e) {
            System.out.println("IO exception !!!!");
        }
    }

    public static void main(String[] args) {
        boolean contLoop = true;
        Scanner in = new Scanner(System.in);
        while (contLoop) {
            System.out.print("Enter product Name: ");
            String productName = in.nextLine();
            System.out.print("Enter quantityInStock: ");
            int quantityInStock = in.nextInt();
            in.nextLine();
            System.out.print("Enter cost: ");
            double cost = in.nextDouble();
            in.nextLine();
            System.out.print("Enter retailPrice: ");
            double retailPrice = in.nextDouble();
            in.nextLine();
            Product newProduct = new Product(productName, quantityInStock, cost, retailPrice);
            newProduct.Save_Database();

            // Continue?
            System.out.print("Do you want to enter more products? (y/n): ");

            String enterProducts = in.nextLine();
            if (enterProducts.equals("n") || enterProducts.equals("N")){
                contLoop = false;
            }
        }
    }
}
