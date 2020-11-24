package Invoices;
import Products.Product;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.io.*;
import java.util.*;

public class InvoiceDB {

    public void update_invoices(ArrayList<Invoice> invoice_list) {
        try {
            FileWriter outfile = new FileWriter("InvoiceData.txt");
            PrintWriter printWriter = new PrintWriter(outfile);
            for (Invoice invoice : invoice_list) {
                printWriter.print(invoice.getInvoiceId() + ";" + invoice.getCustomerName() + ";" + invoice.getInvoiceStatus() + ";"
                        + invoice.getTaxRate() + ";" + invoice.getDeliveryStatus() + ";"
                        + invoice.getAddress() + ";" + invoice.getDateOpened() + ";" + invoice.getTotalCost() + ";Product;");
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

//    public static ArrayList<Invoice> retrieve_invoices() {
//        ArrayList<Invoice> invoices = new ArrayList<Invoice>();
//        ArrayList<String> invoiceString = new ArrayList<>();
//        int numOfInvoices = 0;
//        try {
//            File invoiceTxt = new File("InvoiceData.txt");
//            Scanner scanner = new Scanner(invoiceTxt);
//            while (scanner.hasNextLine()) {
//                String data = scanner.nextLine();
//                invoiceString.add(data);
//                numOfInvoices++;
//            }
//
//        } catch (FileNotFoundException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
//
//        for (int i = 0; i < numOfInvoices; i++) {
//           String[] invoiceData = invoiceString.get(i).split(";");
//           HashSet<Product> tempProducts = new HashSet<>();
//
//            // recreates invoices
//           Invoice tempInvoice = new Invoice(Integer.parseInt(invoiceData[0]), invoiceData[1],
//                   Boolean.parseBoolean(invoiceData[2]), Double.parseDouble(invoiceData[3]),
//                   Boolean.parseBoolean(invoiceData[4]), invoiceData[5], new Date());
//            // recreates products
//            for (int j = 8; j < invoiceData.length; j += 3) {
//                Product tempProduct = new Product(invoiceData[j], Double.parseDouble(invoiceData[j + 1]),
//                        Integer.parseInt(invoiceData[j + 2]));
//                tempProducts.add(tempProduct);
//            }
//            // add products objects to Hashset in each invoice
//            for (Product p : tempProducts) {
//                tempInvoice.addProductsPurchased(p);
//            }
//            // add all invoices ot arraylist
//            invoices.add(tempInvoice);
//        }
//        // return arraylist
//        return invoices;
//    }

    //public Invoice createInvoice()
    public static void main(String[] args) {
        //test retrieve invoice
//       ArrayList<Invoice> invoices =  retrieve_invoices();
//       for (int i = 0; i < invoices.size(); i++) {
//           System.out.println(invoices.get(i));
//       }

       //Date d1 = new Date(2000, 0, 1);
        //System.out.println(d1);
    }

}
