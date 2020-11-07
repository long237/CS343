import java.util.HashSet;

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
                   boolean mDeliveryStatus, String mAddress, HashSet<Product> mProductsPurchased) {
        this.mInvoiceId = mInvoiceId;
        this.mCustomerName = mCustomerName;
        this.mInvoiceStatus = mInvoiceStatus;
        this.mTaxRate = mTaxRate;
        this.mDeliveryStatus = mDeliveryStatus;
        this.mAddress = mAddress;
        this.mProductsPurchased = mProductsPurchased;
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

    public void addProductsPurchased(HashSet<Product> newProducts) {
        mProductsPurchased.addAll(newProducts);
    }

    public void removePurchasedProduct(Product product) {
        mProductsPurchased.remove(product);
    }
}
