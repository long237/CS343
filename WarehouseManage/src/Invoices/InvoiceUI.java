package Invoices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class InvoiceUI {

    public void editInvoice(Invoice invoice, ArrayList<Invoice> invoices) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the ID of the invoice you would like to edit?");
        int invoiceID = scanner.nextInt();
        //confirm ID exists in existing invoices
        boolean invoiceIsReal = false;
        while (invoiceIsReal == false) {
            for (Invoice inv : invoices) {
                if (inv.getInvoiceId() == invoiceID) {
                    invoiceIsReal = true;
                }
            }
            if (invoiceIsReal == false) {
                System.out.println("Invoice does not exist. Please enter a new ID");
                invoiceID = scanner.nextInt();
            }
        }
        //ask for customer name
        System.out.println("What is the customer's name?");
        String custName = scanner.nextLine();
        invoice.setmCustomerName(custName);

        //ask for product purchased
        //damn...

        //ask for task rate
        System.out.println("What is the new tax rate");
        double newTaxRate = scanner.nextDouble();
        invoice.setmTaxRate(newTaxRate);

        //ask for new Delivery status
        System.out.println("What is the new delivery status: type OPEN or CLOSED");
        String newDeliveryStatus = scanner.nextLine();
        if (newDeliveryStatus == "OPEN") {
            invoice.setmDeliveryStatus(true);
        } else {
            invoice.setmDeliveryStatus(false);
        }

        //ask for delivery address
        System.out.println("What is the new delivery address?");
        String newDeliveryAddr = scanner.nextLine();
        invoice.setmAddress(newDeliveryAddr);

        //change date opened
        //add later
        ///perhaps should break this all up


    }

    public void viewAllInvoices(ArrayList<Invoice> invoices) {
        //add formating later
        System.out.println("Displaying all invoices");
        for (Invoice invoice : invoices) {
            System.out.println(invoice);
        }
        
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
