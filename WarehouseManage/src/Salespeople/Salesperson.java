package Salespeople;

import Database.Database;

import java.util.ArrayList;

public class Salesperson {
    private String mSalespersonName;
    private double mSalespersonCommission;
    private int mSalespersonID;
    private double mTotalSales;
    private double mSalary = -1;

    public Salesperson(){
        this.mSalespersonName = "John Doe";
        this.mSalespersonID = 12345;
        this.mSalespersonCommission = 10.0;
        this.mTotalSales = 10;
    }

    public Salesperson(String mSalespersonName, double mSalespersonCommission) {
        this.mSalespersonName = mSalespersonName;
        this.mSalespersonCommission = mSalespersonCommission;
    }

    /**Use in controller when add new sale person**/
    public Salesperson(String mSalespersonName, int mSalespersonID, double mSalespersonCommission, double mTotalSales) {
        this.mSalespersonName = mSalespersonName;
        this.mSalespersonID = mSalespersonID;
        this.mSalespersonCommission = mSalespersonCommission;
        this.mTotalSales = mTotalSales;
    }

    /**For use in the Database when retrieve **/
    public Salesperson(String mSalespersonName, int mSalespersonID, double mSalespersonCommission, double mTotalSales, double mSalary) {
        this.mSalespersonName = mSalespersonName;
        this.mSalespersonCommission = mSalespersonCommission;
        this.mSalespersonID = mSalespersonID;
        this.mTotalSales = mTotalSales;
        this.mSalary = mSalary;
    }

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

    public double getTotalSales() {
        return mTotalSales;
    }

    public void setTotalSales(int mTotalSales) {
        this.mTotalSales = mTotalSales;
    }

    public double getmSalary() {
        return mSalary;
    }

    public void addSales(double mSale){
        this.mTotalSales = this.mTotalSales + mSale;
    }

    public void calSalary(){
        mSalary = mTotalSales * mSalespersonCommission / 100;
    }

    @Override
    public String toString() {
        return ("Name: " + mSalespersonName + ", Commission Rate: " +
                mSalespersonCommission + ", ID: " + mSalespersonID + ", Total Sales: " + mTotalSales);
    }

    //fixme: main for testing saving person to database
    public static void main(String[] args) {
        Salesperson s1 = new Salesperson("Alex Smith", 5);
        Salesperson s2 = new Salesperson("John Brown", 7);
        Salesperson s3 = new Salesperson();

        ArrayList<Salesperson> personList= new ArrayList<>();
        personList.add(s1);
        personList.add(s2);
        personList.add(s3);

        Database sData = new Database();
        sData.update_Saleperson(personList);

        //test retriving salespeople
        ArrayList<Salesperson> salesTeam = sData.retrieve_salesPerson();
        for (Salesperson s : salesTeam) {
            System.out.println(s);
        }
    }
}
