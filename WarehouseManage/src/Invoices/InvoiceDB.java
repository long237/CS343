package Invoices;
import Products.Product;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class InvoiceDB {

    public static void update_invoices(ArrayList<Invoice> invoices) {
        // invoice 1 ...
        // incoice 2....
    }

    public static ArrayList<Invoice> retrieve_invoices() {
        ArrayList<Invoice> invoices = new ArrayList<Invoice>();
        ArrayList<String> invoiceString = new ArrayList<>();
        int numOfInvoices = 0;
        try {
            File invoiceTxt = new File("InvoiceData.txt");
            Scanner scanner = new Scanner(invoiceTxt);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                invoiceString.add(data);
                numOfInvoices++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        for (int i = 0; i < numOfInvoices; i++) {
           String[] invoiceData = invoiceString.get(i).split(";");
           HashSet<Product> tempProducts = new HashSet<>();

            // recreates invoices
           Invoice tempInvoice = new Invoice(Integer.parseInt(invoiceData[0]), invoiceData[1],
                   Boolean.parseBoolean(invoiceData[2]), Double.parseDouble(invoiceData[3]),
                   Boolean.parseBoolean(invoiceData[4]), invoiceData[5], new Date());
            // recreates products
            for (int j = 8; j < invoiceData.length; j += 3) {
                Product tempProduct = new Product(invoiceData[j], Double.parseDouble(invoiceData[j + 1]),
                        Integer.parseInt(invoiceData[j + 2]));
                tempProducts.add(tempProduct);
            }
            // add products objects to Hashset in each invoice
            for (Product p : tempProducts) {
                tempInvoice.addProductsPurchased(p);
            }
            // add all invoices ot arraylist
            invoices.add(tempInvoice);
        }
        // return arraylist
        return invoices;
    }

    //public Invoice createInvoice()
    public static void main(String[] args) {
        //test retrieve invoice
       ArrayList<Invoice> invoices =  retrieve_invoices();
       for (int i = 0; i < invoices.size(); i++) {
           System.out.println(invoices.get(i));
       }

       //Date d1 = new Date(2000, 0, 1);
        //System.out.println(d1);
    }

}
