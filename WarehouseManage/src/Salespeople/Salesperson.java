package Salespeople;

public class Salesperson {
    private String mSalespersonName;
    private double mSalespersonCommission;
    private int mSalespersonID;
    private int mTotalSales;


    public String getSalespersonName() {
        return mSalespersonName;
    }

    public void setSalespersonName(String mSalespersonName) {
        this.mSalespersonName = mSalespersonName;
    }

    public double getSalespersonCommission() {
        return mSalespersonCommission;
    }

    public void setSalespersonCommission(double mSalespersonCommission) {
        this.mSalespersonCommission = mSalespersonCommission;
    }

    public int getSalespersonID() {
        return mSalespersonID;
    }

    public void setSalespersonID(int mSalespersonID) {
        this.mSalespersonID = mSalespersonID;
    }

    public int getTotalSales() {
        return mTotalSales;
    }

    public void setTotalSales(int mTotalSales) {
        this.mTotalSales = mTotalSales;
    }
}
