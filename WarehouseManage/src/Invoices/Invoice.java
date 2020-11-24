package Invoices;

import Products.Product;

import java.io.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

public class Invoice {

    private int mInvoiceId;
    private String mCustomerName;
    private boolean mInvoiceStatus;
    private double mTaxRate;
    private boolean mDeliveryStatus;
    private String mAddress;
    private HashSet<Product> mProductsPurchased;
    private double totalCost = 0;
    private Date mDateOpened;


    public Invoice() {
        this.mInvoiceId = 1234;
        this.mCustomerName = "john doe";
        this.mInvoiceStatus = false;
        this.mTaxRate = 0.0;
        this.mDeliveryStatus = false;
        this.mAddress = "1234 address";
        this.mProductsPurchased = new HashSet<Product>();
        this.mDateOpened = new Date();
    }

    public Invoice(int mInvoiceId, String mCustomerName, boolean mInvoiceStatus, double mTaxRate,
                   boolean mDeliveryStatus, String mAddress, Date dateOpened) {
        this.mInvoiceId = mInvoiceId;
        this.mCustomerName = mCustomerName;
        this.mInvoiceStatus = mInvoiceStatus;
        this.mTaxRate = mTaxRate;
        this.mDeliveryStatus = mDeliveryStatus;
        this.mAddress = mAddress;
        this.mProductsPurchased = new HashSet<Product>();
        this.mDateOpened = dateOpened;
    }

    public int getInvoiceId() {
        return mInvoiceId;
    }

    public String getCustomerName() {
        return mCustomerName;
    }

    public boolean getInvoiceStatus() {
        return mInvoiceStatus;
    }

    public double getTaxRate() {
        return mTaxRate;
    }

    public boolean getDeliveryStatus() {
        return mDeliveryStatus;
    }

    public String getAddress() {
        return mAddress;
    }

    public Date getDateOpened() { return mDateOpened; }

    public double getTotalCost() { return  totalCost; }

    public HashSet<Product> getProductsPurchased() {
        return mProductsPurchased;
    }

    public void addProductsPurchased(Product newProducts) {
        mProductsPurchased.add(newProducts);
    }

    public void removePurchasedProduct(Product product) {
        mProductsPurchased.remove(product);
    }

    public void setmInvoiceId(int mInvoiceId) {
        this.mInvoiceId = mInvoiceId;
    }

    public void setmCustomerName(String mCustomerName) {
        this.mCustomerName = mCustomerName;
    }

    public void setmInvoiceStatus(boolean mInvoiceStatus) {
        this.mInvoiceStatus = mInvoiceStatus;
    }

    public void setmTaxRate(double mTaxRate) {
        this.mTaxRate = mTaxRate;
    }

    public void setmDeliveryStatus(boolean mDeliveryStatus) {
        this.mDeliveryStatus = mDeliveryStatus;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public void setmDateOpened(Date newDate) { this.mDateOpened = newDate; }



    @Override
    public String toString() {
        return "Invoices.Invoice{" +
                "mInvoiceId=" + mInvoiceId +
                ", mCustomerName='" + mCustomerName + '\'' +
                ", mInvoiceStatus=" + mInvoiceStatus +
                ", mTaxRate=" + mTaxRate +
                ", mDeliveryStatus=" + mDeliveryStatus +
                ", mAddress='" + mAddress + '\'' +
                ", mProductsPurchased=" + mProductsPurchased +
                ", mDateOpened= " + mDateOpened +
                '}';
    }

    public void calCost() {
        for (Product product : mProductsPurchased){
            this.totalCost += product.getCost();
        }
    }

    public void Save_Database (){
        try {
            FileWriter outfile = new FileWriter("InvoiceData.txt", true);
            PrintWriter printWriter = new PrintWriter(outfile);
            printWriter.print (this.getInvoiceId() + ";" + this.getCustomerName() + ";" + this.getInvoiceStatus() + ";"
                    + this.getTaxRate() + ";" + this.getDeliveryStatus() + ";"
                    + this.getAddress() + ";" + this.totalCost + ";Product;");
            for (Product temp : mProductsPurchased) {
                printWriter.print(temp.getName() + ";" + temp.getCost() + ";" + temp.getQuantitySold() + ";");
            }
            printWriter.println("");
            printWriter.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Invoices.Invoice Data base does not exist.");
        }
        catch (IOException e) {
            System.out.println("IO exception !!!!");
        }
    }

    //Code for testing
    public static void main(String[] args) {
        Invoice invoice1 = new Invoice();
        System.out.println("This is invoice testing. ");
        System.out.println("Invoices.Invoice: " + invoice1);

        Invoice invoice2 = new Invoice(4567, "Fatalis", true, 10, true, "123 Main st", new Date(2010, 12, 20));
        System.out.println("Invoices.Invoice 2: " + invoice2);
        invoice2.setmAddress("123 Second st");
        invoice2.setmCustomerName("Alatreon");
        invoice2.setmDeliveryStatus(false);
        invoice2.setmTaxRate(15);
        System.out.println("After: " + invoice2);

        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();
        product1.setName("RTX 3070");
        product2.setName("RX 6800");
        product3.setName("R7 5700");
        product1.setCost(30);
        product2.setCost(20);
        product3.setCost(50);
        product1.addQuantitySold(3);
        product2.addQuantitySold(2);
        product3.addQuantitySold(5);

        invoice1.addProductsPurchased(product1);
        invoice1.addProductsPurchased(product2);
        invoice1.addProductsPurchased(product3);
        invoice1.calCost();

        invoice2.addProductsPurchased(product1);
        invoice2.addProductsPurchased(product2);
        invoice2.addProductsPurchased(product3);
        invoice2.calCost();

        Date d1  = new Date(120, Calendar.NOVEMBER, 12);
        Date d2 = new Date();
        LocalDate ld1 = LocalDate.of(2000, 5, 20);
        String date = "2000 5 20";

        System.out.println("Local Date: " + ld1);
        System.out.println("Local Date year: " + ld1.getYear());
        System.out.println("Date: " + d1);
        System.out.println("D1 Year: " + d1.getYear());
        System.out.println("Date 2: " + d2);

//        invoice1.Save_Database();
//        invoice2.Save_Database();

//        try {
//            FileWriter outfile = new FileWriter("InvoiceData.txt", true);
//            PrintWriter printWriter = new PrintWriter(outfile);
//            printWriter.println(invoice1.getInvoiceId() + " " + invoice1.getCustomerName() + " " + invoice1.getTaxRate());
//            printWriter.println(invoice2.getInvoiceId() + " " + invoice2.getCustomerName() + " " + invoice2.getTaxRate());
//            printWriter.close();
//        }
//        catch (FileNotFoundException e) {
//            System.out.println("Invoices.Invoice Data base does not exist.");
//        }
//        catch (IOException e) {
//            System.out.println("IO exception !!!!");
//        }
    }
}
