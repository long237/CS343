
// XXXXX: (fix ASAP!)                (#ff0070)
// KEIRA: (main headers)             (#ff0070)
// kkkkk: (sub-headers)              (#fcb9c5)
// KKKKK: (sub sub-headers)          (#b97474)
//
// TODO: (incomplete)                (#ccff00)
//# QUESTION: (wtf did i just do)    (#00b9ff)

import java.util.HashSet;

public class Product {

    private String mName;
    private int mQuantityAvailable;
    private double mPrice;
    private int mAmountSold;

    //private double mTotalSales;
    //private double mTotalCost;
    //private double mTotalProfit;
    //private double mProfitPercent;

    public Product() {
        this.mName = "john doe";
        this.mQuantityAvailable = 1234;
        this.mPrice = 0.0;
        this.mAmountSold = 1234;
    }

    public Product(String mName, int mQuantityAvailable, double mPrice, int mAmountSold) {
        this.mName = mName;
        this.mQuantityAvailable = mQuantityAvailable;
        this.mPrice = mPrice;
        this.mAmountSold = mAmountSold;
    }
}
