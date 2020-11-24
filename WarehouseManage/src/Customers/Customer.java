package Customers;

public class Customer {
    private String mName;
    private double mTaxrate;

    public Customer(String mName, double mTaxrate) {
        this.mName = mName;
        this.mTaxrate = mTaxrate;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public double getmTaxrate() {
        return mTaxrate;
    }

    public void setmTaxrate(double mTaxrate) {
        this.mTaxrate = mTaxrate;
    }
}
