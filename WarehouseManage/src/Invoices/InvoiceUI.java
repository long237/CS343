package Invoices;

import java.util.ArrayList;
import java.util.Collections;

public class InvoiceUI {

    public void viewAllInvoices(ArrayList<Invoice> invoices) {
        //add formating later
        System.out.println("Displaying all invoices");
    }

    public void viewOpenInvoices (ArrayList<Invoice> invoices){
        System.out.println("Displaying Open Invoices");
        //sort by date opened
        Collections.sort(invoices, new OrderDateComparator());
        for (Invoice inv : invoices) {
            if (inv.getInvoiceStatus() == true) {
                System.out.println(inv);
            }
        }
    }

    public void viewClosedInvoices (ArrayList<Invoice> invoices){
        //update total costs for each invoice
        for (Invoice inv: invoices) {
            inv.calCost();
        }
        //sort by decreasing order of total cost
        Collections.sort(invoices, new TotalCostComparator());
        System.out.println("Displaying Closed Invoices");
        for (Invoice inv : invoices) {
            if (inv.getInvoiceStatus() == false) {
                System.out.println(inv);
            }
        }
    }


    public String customerName(){
        System.out.println("input customer name: ");
        //keep looping while not string
        return "hello";
    }

    public void Menu(){
        System.out.println("1. Add invoice");
        System.out.println("2. Edit invoice");
    }


}
