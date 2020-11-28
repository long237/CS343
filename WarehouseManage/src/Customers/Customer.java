package Customers;

import Database.Database;

import java.util.ArrayList;

public class Customer {
    private String mName;
    private double mTaxrate;

    public Customer(){
        this.mName = "John Doe";
        this.mTaxrate = 10;
    }

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

    @Override
    public String toString() {
        return ("Name: " + mName + ", Tax Rate: " + mTaxrate);
    }
}
