package Invoices;

import Database.Database;
import Products.Product;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import Customers.Customer;
import java.util.HashSet;

public class Invoice {
    public static double DELIVERY_CHARGE = 3.99;


    private int mInvoiceId;
    private String mCustomerName;
    private boolean mInvoiceStatus;
    private double mTaxRate;
    private boolean mDeliveryStatus;
    private String mAddress;
    private HashSet<Product> mProductsPurchased;
    private double totalCost = 0;
    private LocalDate mDateOpened;
    private String mSalePName = "John Doe";


    public Invoice() {
        this.mInvoiceId = -1234;
        this.mCustomerName = "john doe";
        this.mInvoiceStatus = false;
        this.mTaxRate = 0.0;
        this.mDeliveryStatus = false;
        this.mAddress = "1234 address";
        this.mProductsPurchased = new HashSet<Product>();
        this.mDateOpened = LocalDate.of(2020,2,22);
        this.mSalePName = "John Doe";
    }

    /**Temporary constructor for adding invoice without the product list **/
    public Invoice(int mInvoiceId, String mCustomerName, double mTaxRate,
                   boolean mDeliveryStatus, String mAddress, LocalDate mDateOpened, String mSalePName) {
        this.mInvoiceId = mInvoiceId;
        this.mCustomerName = mCustomerName;
        this.mInvoiceStatus = true;
        this.mTaxRate = mTaxRate;
        this.mDeliveryStatus = mDeliveryStatus;
        this.mAddress = mAddress;
        this.mProductsPurchased = new HashSet<Product>();
        this.mDateOpened = mDateOpened;
        this.mSalePName = mSalePName;
    }

    public Invoice(int mInvoiceId, String mCustomerName, boolean mInvoiceStatus, double mTaxRate,
                   boolean mDeliveryStatus, String mAddress, LocalDate dateOpened, double totalCost, String mSalePName) {
        this.mInvoiceId = mInvoiceId;
        this.mCustomerName = mCustomerName;
        this.mInvoiceStatus = mInvoiceStatus;
        this.mTaxRate = mTaxRate;
        this.mDeliveryStatus = mDeliveryStatus;
        this.mAddress = mAddress;
        this.mProductsPurchased = new HashSet<Product>();
        this.mDateOpened = dateOpened;
        this.totalCost = totalCost;
        this.mSalePName = mSalePName;
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

    public LocalDate getDateOpened() { return mDateOpened; }

    public double getTotalCost() { return  totalCost; }

    public String getmSalePName() {
        return mSalePName;
    }

    public void setmSalePName(String mSalePName) {
        this.mSalePName = mSalePName;
    }

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

    public void setmDateOpened(LocalDate newDate) { this.mDateOpened = newDate; }



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
                ", mTotalCost = " + totalCost +
                '}';
    }

    public void calCost() {
        for (Product product : mProductsPurchased){
            this.totalCost += product.getRetailPrice() * product.getQuantitySold();
        }
        //After tax:
        //this.totalCost = Math.round((totalCost + (totalCost * mTaxRate / 100.0)) * 100.0) / 100.0;
    }

    public void calCostWithTax(){
        this.totalCost = Math.round((totalCost + (totalCost * mTaxRate / 100.0)) * 100.0) / 100.0;
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

    public void addDeliveryCharge() {

        totalCost += DELIVERY_CHARGE;
    }

    public void addDiscount(LocalDate dateDelivered) {
        //first we calculate distance in days
        int year = dateDelivered.getYear() - mDateOpened.getYear();
        int month = dateDelivered.getMonthValue() - mDateOpened.getMonthValue();
        int day = dateDelivered.getDayOfMonth() - mDateOpened.getDayOfMonth();

        int distanceInDays = (year * 365) + (month * 30) + day;

        if (distanceInDays <= 10) {
            totalCost = Math.round((totalCost - (totalCost * .10)) * 100.0) / 100.0;
            System.out.println("Distance in days is less than 30: " + distanceInDays);
        }
        else if (distanceInDays > 30) {
            int month_late = distanceInDays / 30;           //Multiplier or run time.
            for (int i = 0; i < month_late; i++) {
                this.totalCost = Math.round((totalCost + (totalCost * 0.02)) * 100.0) / 100.0;
            }
        }
    }

    //Code for testing
//    public static void main(String[] args) {
//        Invoice testDate = new Invoice();
//        Invoice testDate1 = new Invoice();
//        testDate.addProductsPurchased(new Product("prod", 50.0, 8));
//        testDate.calCost();
//        System.out.println("Cost before discount:  " + testDate.getTotalCost());
//        testDate.setmDateOpened(LocalDate.of(2000, 5, 5));
//        testDate.addDiscount(LocalDate.of(2000, 8, 20));
//        System.out.println("Cost after discount:  " + testDate.getTotalCost());
//
//
//        Invoice invoice1 = new Invoice();
//        System.out.println("This is invoice testing. ");
//        System.out.println("Invoices.Invoice: " + invoice1);
//
//        Invoice invoice2 = new Invoice(4567, "Fatalis", true, 10, true, "123 Main st", LocalDate.of(2010, 12, 21));
//
//        invoice2.setmAddress("123 Second st");
//        invoice2.setmCustomerName("Alatreon");
//        invoice2.setmDeliveryStatus(false);
//        invoice2.setmTaxRate(15);
//        System.out.println("After: " + invoice2);
//
//        Invoice invoice3 = new Invoice();
//        invoice3.setmDateOpened(LocalDate.of(2020, 11, 24));
//        invoice3.setmCustomerName("Lunastra");
//
//        Product product1 = new Product();
//        Product product2 = new Product();
//        Product product3 = new Product();
//        product1.setName("RTX 3070");
//        product2.setName("RX 6800");
//        product3.setName("R7 5700");
//        product1.setCost(30);
//        product2.setCost(20);
//        product3.setCost(50);
//        product1.addQuantitySold(3);
//        product2.addQuantitySold(2);
//        product3.addQuantitySold(5);
//
//        invoice1.addProductsPurchased(product1);
//        invoice1.addProductsPurchased(product2);
//        invoice1.addProductsPurchased(product3);
//        invoice1.calCost();
//
//        invoice2.addProductsPurchased(product1);
//        invoice2.addProductsPurchased(product2);
//        invoice2.addProductsPurchased(product3);
//        invoice2.calCost();
//
//        invoice3.addProductsPurchased(product2);
//        invoice3.addProductsPurchased(product3);
//
//        System.out.println("Invoices.Invoice: " + invoice2);
//        System.out.println("Date of Invoice 1: " + invoice1.getDateOpened());
//
//        ArrayList<Invoice> invoicesList = new ArrayList<Invoice>();
//        invoicesList.add(invoice1);
//        invoicesList.add(invoice2);
//        invoicesList.add(invoice3);
//        Database Idata= new Database();
//        Idata.update_invoices(invoicesList);
//        System.out.println("Print new line:");
////        invoice1.Save_Database();
////        invoice2.Save_Database();
////        invoice3.
//        ArrayList<Invoice> retriveInvoices = Idata.retrieve_invoices();
//
//        InvoiceUI invUI = new InvoiceUI();
//        System.out.println("All Invoices : ");
//        invUI.viewAllInvoices(retriveInvoices);
//        System.out.println("\n closed invoices: ");
//        invUI.viewClosedInvoices(retriveInvoices);
//        System.out.println("\n Open Invoices");
//        invUI.viewOpenInvoices(retriveInvoices);
//        System.out.println("Hello Wold");
//
//    }
}
