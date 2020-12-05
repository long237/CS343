package Products;

// XXXXX:       (fix ASAP!)         (#c6001a)
// KEIRA:       (main headers)      (#ff0070)
// kkkkk:       (sub-headers)       (#fcb9c5)
// KKKKK:       (sub sub-headers)   (#b97474)
//
// TODO:        (incomplete)        (#ccff00)
// REVIEW:      (check / relay)     (#00b9ff)

public class Product {

    private String mName;
    private int mQuantityInStock;
    private double mCost;
    private double mTotalCost;
    private double mRetailPrice;
    private int mQuantitySold;       //Warehouse_sold = isold1 + isold2 + ....
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

    /**This constructor is for recreating Product obj when retrive from invoice database **/
    public Product(String name, double retail, int quantitySold ){
        this.mName = name;
        this.mQuantityInStock = 0;
        this.mCost = 0;
        this.mTotalCost = 0.0;
        this.mRetailPrice = retail;
        this.mQuantitySold = quantitySold;
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

    // REVIEW: (11/21: Added this constructor to use to add to list of Products when reading from .txt file)
    public Product(String mName, int mQuantityInStock, double mCost, double mRetailPrice, int mQuantitySold) {
        this.mName = mName;
        this.mQuantityInStock = mQuantityInStock;
        this.mCost = mCost;
        this.mTotalCost = (mQuantityInStock * mCost);
        this.mRetailPrice = mRetailPrice;
        this.mQuantitySold = mQuantitySold;
        this.mTotalSales = mQuantitySold * mRetailPrice;
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
    public void setQuantitySold(int amount){
        mQuantitySold = amount;
    }

    @Override
    public boolean equals(Object obj) {
//        if (obj == null) return false;
//        if (obj == this) return true;
//        if (!(obj instanceof Product)) return false;
//        Product o = (Product) obj;
//        return o.mName.toLowerCase().equals(this.mName.toLowerCase());
        if(obj instanceof Product){
            Product toCompare = (Product) obj;
            return this.mName.equals(toCompare.mName);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return mName.hashCode();
    }
//    @Override
//    public int hashCode() {
//
//        return 0;
//    }

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

    public String toWarehouseString() {

        // FOR FUTURE REFERENCE...
        // "%":      1 for each variable listed.
        // "-":      left-align (w/o -> right-align)
        // "[int]":  # of spaces designated for each variable
        // "s":      string
        // "f":      float (".2f": formats floats as "0.00")

        return String.format(" %-20s %15s %15.2f %15.2f %10s",
                mName, mQuantityInStock, mCost, mRetailPrice, mQuantitySold);
    }


//    @Override
//    public String toString() {
//        return "Product{" +
//                "mName='" + mName + '\'' +
//                ", mCost=" + mCost +
//                ", mRetailPrice" + mRetailPrice +
//                ", mQuantitySold=" + mQuantitySold +
//                '}';
//    }


//    @Override
//    public String toString() {
//        return mName;
//    }
}
