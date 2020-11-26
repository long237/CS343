package Database;

import Invoices.Invoice;
import Products.Product;
import Salespeople.Salesperson;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import Customers.Customer;

public class Database {

    /** Overwrite the database and replace with info from the new Arraylist of invoice, NOT APPEND **/
    //Data base format: InvoiceID;CustomerName;Status;Taxrate;
    // DeliveryStat;Address;Date;TotalCost;Date;Product;Productname;Productcost;Quantity
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
            outfile.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Invoices.Invoice Data base does not exist.");
        }
        catch (IOException e) {
            System.out.println("IO exception !!!!");
        }
    }

    //fixme: fix the Date of object into Local Date.
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

           //recreate local date object
            int year = Integer.parseInt(invoiceData[6].substring(0, 4));
            int month = Integer.parseInt(invoiceData[6].substring(5, 7));
            int day = Integer.parseInt(invoiceData[6].substring(8));
            // recreates invoices
           Invoice tempInvoice = new Invoice(Integer.parseInt(invoiceData[0]), invoiceData[1],
                   Boolean.parseBoolean(invoiceData[2]), Double.parseDouble(invoiceData[3]),
                   Boolean.parseBoolean(invoiceData[4]), invoiceData[5], LocalDate.of(year,month,day));
            // recreates products
            for (int j = 9; j < invoiceData.length; j += 3) {
                Product tempProduct = new Product(invoiceData[j], Double.parseDouble(invoiceData[j + 1]),
                        Integer.parseInt(invoiceData[j + 2]));
                tempProducts.add(tempProduct);
            }
            // add products objects to Hashset in each invoice
            for (Product p : tempProducts) {
                tempInvoice.addProductsPurchased(p);
            }
            //re-caluclate total cost for invoice
            tempInvoice.calCost();
            // add all invoices ot arraylist
            invoices.add(tempInvoice);
        }
        // return arraylist
        return invoices;
    }

    // TODO: Given a Warehouse & a list of Products, rewrites that Warehouse's DB to contain those Products.
    public void update_products(int warehouseNumber, ArrayList<Product> warehouseProducts){
        try {
            String filename = "Warehouse" + warehouseNumber + ".txt";
            FileWriter outfile = new FileWriter(filename, false);
            PrintWriter printWriter = new PrintWriter(outfile);
            for (Product p : warehouseProducts) {
                printWriter.print(p.getName() + ";" + p.getQuantityInStock() + ";" + p.getCost() + ";" + p.getRetailPrice() + ";"
                        + p.getQuantitySold() + ";" + p.getTotalSales() + ";" + p.getTotalCost() + ";"
                        + p.getTotalProfit() + ";" + p.getTotalProfitPercent());
                printWriter.println();
            }
            printWriter.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Warehouse Database.Database does not exist.");
        }
        catch (IOException e) {
            System.out.println("IO exception !!!!");
        }
    }

    // TODO: Given a Warehouse, returns a list of Products currently stored in that Warehouse.
    public ArrayList<Product> retrieve_products(int warehouseNumber) {
        ArrayList<Product> products = new ArrayList<Product>();

        File file = new File("Warehouse" + warehouseNumber + ".txt");
        Scanner input = null;
        try { input = new Scanner(file); } catch (FileNotFoundException e) { e.printStackTrace(); }
        while (true) {
            assert input != null;
            if (!input.hasNextLine()) break;

            String[] productInfo = input.nextLine().split(";");

            String productName = productInfo[0];
            int quantityInStock = Integer.parseInt(productInfo[1]);
            double cost = Double.parseDouble(productInfo[2]);
            double retailPrice = Double.parseDouble(productInfo[3]);
            int quantitySold = Integer.parseInt(productInfo[4]);
            // the rest (totalSales, totalCost, totalProfit, totalProfitPercent)
            // should be calculated automatically in toString() ...

            Product product = new Product(productName, quantityInStock, cost, retailPrice, quantitySold);
            products.add(product);
        }
        return products;
    }

    /** Add an arrayList of customers object into the databse to save the value
     *  overwrite existing value, NOT APPEND**/
    // Database format: name;taxrate
    public void update_customer(ArrayList<Customer> customerList){
        try {
            FileWriter outfile = new FileWriter("CustomerData.txt");
            PrintWriter printWriter = new PrintWriter(outfile);

            for (Customer customer : customerList) {
                printWriter.println(customer.getmName() + ";" + customer.getmTaxrate());
            }
            printWriter.close();
            outfile.close();

        }
        catch (IOException e) {
            System.out.println("File not found for Customers!!!!");
        }
    }

    public ArrayList<Customer> retrieve_Customer() {
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<String> customerData = new ArrayList<>();

        try {
            File customerTxt = new File("CustomerData.txt");
            Scanner scanner = new Scanner(customerTxt);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                customerData.add(data);
            }

        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found for CustomerData.txt");
        }

        for (int i = 0; i < customerData.size(); i++) {
            String[] singleCustomer = customerData.get(i).split(";");
            customers.add(new Customer(singleCustomer[0], Double.parseDouble(singleCustomer[1])));

        }
        return customers;

    }

    /** Add saleperson info to data and overwrite previous value in database, NOT APPEND**/
    //format database: name;ID;commision;totalsales
    public void update_Saleperson(ArrayList<Salesperson> employeeList) {
        try{
            FileWriter outfile = new FileWriter("SalepersonData.txt");
            PrintWriter printWriter = new PrintWriter(outfile);

            for (Salesperson person : employeeList){
                printWriter.println(person.getSalespersonName() + ";" + person.getSalespersonID() + ";"
                        + person.getSalespersonCommission() + ";" + person.getTotalSales());
            }
            printWriter.close();
            outfile.close();

        }
        catch (IOException e) {
            System.out.println("File not found for Saleperson");
        }
    }

    public ArrayList<Salesperson> retrieve_salesPerson() {
        ArrayList<Salesperson> salespeople = new ArrayList<>();
        ArrayList<String> salesPeopleData = new ArrayList<>();

        try {
            File salesPersonTxt = new File("SalepersonData.txt");
            Scanner scanner = new Scanner(salesPersonTxt);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                salesPeopleData.add(data);
            }

        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found for SalespersonData.txt");
        }

        for (int i = 0; i < salesPeopleData.size(); i++) {
            String[] singleSalesPerson = salesPeopleData.get(i).split(";");
            salespeople.add(new Salesperson(singleSalesPerson[0], Integer.parseInt(singleSalesPerson[1]),
                    Double.parseDouble(singleSalesPerson[2]), Integer.parseInt(singleSalesPerson[3])));
        }
        return salespeople;
    }


    public int maxWarehouses() {
        int warehouseCount = 1;
        while (true) {
            File tempFile = new File("Warehouse" + Integer.toString(warehouseCount) + ".txt");
            boolean exists = tempFile.exists();
            if (!exists) {
                break;
            }
            warehouseCount++;
        }
        return warehouseCount-1;
    }
}
