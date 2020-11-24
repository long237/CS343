package Customers;

import Database.Database;

import java.util.ArrayList;

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

    //fixme: for testing customer class purpose only delete afterward
    public static void main(String[] args) {
        Customer c1 = new Customer("Alex Smith", 5.5);
        Customer c2 = new Customer("Elizabeth ", 10);
        Customer c3 = new Customer("John Brown", 7.3);

        ArrayList<Customer> cList = new ArrayList<Customer>();
        cList.add(c1);
        cList.add(c2);
        cList.add(c3);

        Database cData = new Database();
        cData.update_customer(cList);
    }
}
