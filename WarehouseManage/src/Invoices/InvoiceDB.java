package Invoices;
import Products.Product;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

public class InvoiceDB {

    public static void update_invoices(ArrayList<Invoice> invoice_list) {
        try {
            FileWriter outfile = new FileWriter("InvoiceData.txt");
            PrintWriter printWriter = new PrintWriter(outfile);
            for (Invoice invoice : invoice_list) {
                printWriter.print(invoice.getInvoiceId() + ";" + invoice.getCustomerName() + ";" + invoice.getInvoiceStatus() + ";"
                        + invoice.getTaxRate() + ";" + invoice.getDeliveryStatus() + ";"
                        + invoice.getAddress() + ";" + invoice.getTotalCost() + ";Product;");
                HashSet<Product> Product_list = invoice.getProductsPurchased();
                for (Product temp : Product_list) {
                    printWriter.print(temp.getName() + ";" + temp.getCost() + ";" + temp.getQuantitySold() + ";");
                }
                printWriter.println("");
            }
            printWriter.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Invoices.Invoice Data base does not exist.");
        }
        catch (IOException e) {
            System.out.println("IO exception !!!!");
        }
    }

    public static ArrayList<Invoice> retrieve_invoices() {
        ArrayList<Invoice> invoices = new ArrayList<Invoice>();
        // invoice 1  index 0
        // invoice 2   index 1
        // invoice 3  index 2

        // recreates invoices
        // recreates products
        // add products objects to Hashset in each invoice
        // add all invoices ot arraylist
        // return arraylist
        return invoices;
    }

}
