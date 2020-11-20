
import Products.Product;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

public class Invoice {

    private int mInvoiceId;
    private String mCustomerName;
    private boolean mInvoiceStatus;
    private double mTaxRate;
    private boolean mDeliveryStatus;
    private String mAddress;
    private HashSet<Product> mProductsPurchased;


    public Invoice() {
        this.mInvoiceId = 1234;
        this.mCustomerName = "john doe";
        this.mInvoiceStatus = false;
        this.mTaxRate = 0.0;
        this.mDeliveryStatus = false;
        this.mAddress = "1234 address";
        this.mProductsPurchased = new HashSet<Product>();
    }

    public Invoice(int mInvoiceId, String mCustomerName, boolean mInvoiceStatus, double mTaxRate,
                   boolean mDeliveryStatus, String mAddress) {
        this.mInvoiceId = mInvoiceId;
        this.mCustomerName = mCustomerName;
        this.mInvoiceStatus = mInvoiceStatus;
        this.mTaxRate = mTaxRate;
        this.mDeliveryStatus = mDeliveryStatus;
        this.mAddress = mAddress;
        this.mProductsPurchased = new HashSet<Product>();
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

    @Override
    public String toString() {
        return "Invoice{" +
                "mInvoiceId=" + mInvoiceId +
                ", mCustomerName='" + mCustomerName + '\'' +
                ", mInvoiceStatus=" + mInvoiceStatus +
                ", mTaxRate=" + mTaxRate +
                ", mDeliveryStatus=" + mDeliveryStatus +
                ", mAddress='" + mAddress + '\'' +
                ", mProductsPurchased=" + mProductsPurchased +
                '}';
    }

    public void Save_Database (){
        try {
            FileWriter outfile = new FileWriter("InvoiceData.txt", true);
            PrintWriter printWriter = new PrintWriter(outfile);
            printWriter.print(this.getInvoiceId() + ";" + this.getCustomerName() + ";" + this.getInvoiceStatus() + ";"
                    + this.getTaxRate() + ";" + this.getDeliveryStatus() + ";" + this.getAddress() + ";Product;");
            for (Product temp : mProductsPurchased) {
                printWriter.print(temp.getName() + ";");
            }
            printWriter.println("");
            printWriter.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Invoice Data base does not exist.");
        }
        catch (IOException e) {
            System.out.println("IO exception !!!!");
        }
    }

    //Code for testing
    public static void main(String[] args) {
        Invoice invoice1 = new Invoice();
        System.out.println("This is invoice testing. ");
        System.out.println("Invoice: " + invoice1);

        Invoice invoice2 = new Invoice(4567, "Fatalis", true, 10, true, "123 Main st");
        System.out.println("Invoice 2: " + invoice2);
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

        invoice1.addProductsPurchased(product1);
        invoice1.addProductsPurchased(product2);
        invoice1.addProductsPurchased(product3);

        invoice2.addProductsPurchased(product1);
        invoice2.addProductsPurchased(product2);
        invoice2.addProductsPurchased(product3);

        invoice1.Save_Database();
        invoice2.Save_Database();

//        try {
//            FileWriter outfile = new FileWriter("InvoiceData.txt", true);
//            PrintWriter printWriter = new PrintWriter(outfile);
//            printWriter.println(invoice1.getInvoiceId() + " " + invoice1.getCustomerName() + " " + invoice1.getTaxRate());
//            printWriter.println(invoice2.getInvoiceId() + " " + invoice2.getCustomerName() + " " + invoice2.getTaxRate());
//            printWriter.close();
//        }
//        catch (FileNotFoundException e) {
//            System.out.println("Invoice Data base does not exist.");
//        }
//        catch (IOException e) {
//            System.out.println("IO exception !!!!");
//        }
    }
}
