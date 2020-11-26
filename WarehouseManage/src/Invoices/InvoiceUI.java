package Invoices;

import Products.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class InvoiceUI {

    public int invoiceMenu() {
        Scanner in = new Scanner(System.in);
        System.out.println("Invoice Menu: ");
        System.out.println("Option 1 - Edit Invoice: ");
        System.out.println("Option 2 - View Invoices: ");
        System.out.println("Enter -1 to exit");
        System.out.println("");
        try {
            int choice = in.nextInt();
            in.nextLine();
            return choice;
        } catch (Exception e) {
            System.out.println("Invalid input: Enter the number 1 or 2");
            return -2;
        }
    }

    public int chooseInvoice(ArrayList<Invoice> invoiceList){
        Scanner in = new Scanner(System.in);

        System.out.println("Which Invoice would you like to edit");
        viewAllInvoices(invoiceList);
        try {
            int choice = in.nextInt();
            in.nextLine();
            return choice;
        } catch (Exception e) {
            System.out.println("Invalid Input: Enter a numeric value");
            return -2;
        }

    }
    //which on to edit: 2

    public int editInvoiceMenu() {
        Scanner in = new Scanner(System.in);
        //display menu
        System.out.println("What in the invoice would you like to edit?");
        System.out.println("Option 1 - Edit Customer Name: ");
        System.out.println("Option 2 - Edit Tax Rate: ");
        System.out.println("Option 3 - Edit Delivery Status: ");
        System.out.println("Option 4 - Edit Delivery Address: ");
        System.out.println("Option 5 - Edit Date Opened: ");
        System.out.println("Option 6 - Add Product Purchased: ");
        System.out.println("Option 7 - Remove Product: ");

        //get input
        try {
            int choice = in.nextInt();
            in.nextLine();
            return choice;
        } catch (Exception e) {
            System.out.println("Invalid Input: Enter a numeric value between 1 and 7");
            return -2;
        }

    }

    public void editCustomerName(Invoice invoice) {
        Scanner in = new Scanner(System.in);
        System.out.println("What is the new name of the customer? ");
        String newName = in.nextLine();
        invoice.setmCustomerName(newName);
    }

    public void editTaxRate(Invoice invoice) {
        Scanner in = new Scanner(System.in);
        System.out.println("What is the new tax rate of the customer? ");
        double newRate = in.nextDouble();
        invoice.setmTaxRate(newRate);

    }

    public void editDeliveryStatus(Invoice invoice) {
        Scanner in = new Scanner(System.in);
        System.out.println("What is the new delivery status: type OPEN or CLOSED");
        String newDeliveryStatus = in.nextLine();

        if (newDeliveryStatus == "OPEN") {
            invoice.setmDeliveryStatus(true);
        } else {
            invoice.setmDeliveryStatus(false);
        }

    }

    public void editDeliveryAddress(Invoice invoice) {
        Scanner in = new Scanner(System.in);
        System.out.println("What is the new delivery address?");
        String newDeliveryAddr = in.nextLine();
        invoice.setmAddress(newDeliveryAddr);
    }

    public void changeDateOpened(Invoice invoice) {
        Scanner in = new Scanner(System.in);
        System.out.println("What is the year of the invoice: - Please enter in form(YYYY) ");
        int year = in.nextInt();

        System.out.println("What is the month of the invoice: - Please enter in form(MM)");
        int month = in.nextInt();

        System.out.println("What is the Day of the invoice: - Please enter in form(DD)");
        int day = in.nextInt();

        invoice.setmDateOpened(LocalDate.of(year, month, day));
    }

    public void addProductPurchased (Invoice invoice) {
        //maybe put some code from ProductUI in here
    }

    public void viewProductsPurchased(Invoice invoice) {
        System.out.println("Displaying all products in Invoice number: " + invoice.getInvoiceId());
        for (Product product : invoice.getProductsPurchased()) {
            System.out.println(product);
        }
    }
    public void removeProductPurchased(Invoice invoice) {
        if (!invoice.getProductsPurchased().isEmpty()) {

            Scanner in = new Scanner(System.in);
            System.out.println("Enter the name of the Product you want to remove?: ");
            Product currProduct = null;
            viewProductsPurchased(invoice);

            String choice = in.nextLine();
            boolean containsProduct = false;

            while (containsProduct == false) {

                for (Product product : invoice.getProductsPurchased()) {
                    if (product.getName() == choice) {
                        currProduct = product;
                        containsProduct = true;
                    }
                }
                if (containsProduct == false) {
                    System.out.println("Enter a valid product name: ");
                    choice = in.nextLine();
                }

            }
            // while (choice)
            System.out.println("Here is the current quantity for this Product: ");
            System.out.println("The Product: " + currProduct.getName() + " quantity is " + currProduct.getQuantitySold());
            System.out.println("What is the new quantity of " + currProduct.getName());
            int newQuantity = in.nextInt();
            currProduct.setQuantityInStock(newQuantity);

            if (newQuantity <= 0) {
                invoice.removePurchasedProduct(currProduct);
            }

        }
        System.out.println("There are no products to be removed.");

    }

    public void viewAllInvoices(ArrayList<Invoice> invoices) {
        //add formating later
        System.out.println("Displaying all invoices");
        for (int i = 1; i < invoices.size(); ++i) {
            System.out.println(i + " " + invoices.get(i - 1));
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
