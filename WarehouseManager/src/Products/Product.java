package Products;


// XXXXX: (fix ASAP!)                (#c6001a)
// KEIRA: (main headers)             (#ff0070)
// kkkkk: (sub-headers)              (#fcb9c5)
// KKKKK: (sub sub-headers)          (#b97474)
//
// TODO: (incomplete)                (#ccff00)
// QUESTION: (wtf did i just do)     (#00b9ff)



public class Product {

    private String mName;
    private double mRetailPrice;
    private double mCost;
    private int mQuantityInStock;
    private int mQuantitySold;

    // QUESTION: (Needed to add these variables b/c these must update as products are sold or re-ordered from a supplier.)
    private double mTotalSales;
    private double mTotalCost;

    public Product() {
        this.mName = "none";
        this.mRetailPrice = 0.0;
        this.mCost = 0.0;
        this.mQuantityInStock = 0;
        this.mQuantitySold = 0;
        this.mTotalSales = 0.0;
        this.mTotalCost = 0.0;
    }

    public Product(String mName, double mRetailPrice, double mCost, int mQuantityInStock) {
        this.mName = mName;
        this.mRetailPrice = mRetailPrice;
        this.mCost = mCost;
        this.mQuantityInStock = mQuantityInStock;
        this.mQuantitySold = 0;
        this.mTotalSales = 0.0;
        this.mTotalCost = mQuantityInStock * mCost;
    }

    public Product(String mName, double mRetailPrice, double mCost, int mQuantityInStock, int mQuantitySold) {
        this.mName = mName;
        this.mRetailPrice = mRetailPrice;
        this.mCost = mCost;
        this.mQuantityInStock = mQuantityInStock;
        this.mQuantitySold = mQuantitySold;
        this.mTotalSales = mQuantitySold * mRetailPrice;
        this.mTotalCost = mQuantityInStock * mCost;
    }

    public String getName() { return mName; }
    public void setName(String name) { mName = name; }

    public double getRetailPrice() { return mRetailPrice; }
    public void setRetailPrice(double price) { mRetailPrice = price; }

    public double getCost() { return mCost; }
    public void setCost(double cost) { mCost = cost; }

    public int getQuantityInStock() { return mQuantityInStock; }
    public void addQuantityInStock(int quantityReordered) {
        mQuantityInStock += quantityReordered;
        mTotalCost += quantityReordered * mCost;
    }
    public boolean isLowStock() { return mQuantityInStock <= 5; }

    public int getQuantitySold() { return mQuantitySold; }
    public void addQuantitySold(int quantitySold) {
        mQuantitySold += quantitySold;
        mQuantityInStock -= quantitySold;
        mTotalSales += quantitySold * mRetailPrice;
    }

    public double getTotalSales() {
        return mTotalSales;
    }
    public double getTotalCost() {
        return mTotalCost;
    }
    public double getTotalProfit() {
        double totalProfit = mTotalSales - mTotalCost;
        return totalProfit;
    }
    // QUESTION: ( double-check )
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

        return String.format("%-20s %15.2f %15.2f %15s %10s %18.2f %18.2f %18.2f %17.1f%%",
                             mName, mRetailPrice, mCost, mQuantityInStock, mQuantitySold,
                             mTotalSales, mTotalCost, getTotalProfit(), getTotalProfitPercent());
    }
}
