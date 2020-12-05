package Invoices;

import Products.Product;
import java.util.stream.*;
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
        System.out.println("Option 3 - Add Invoice");
        System.out.println("Enter -1 to exit");
        System.out.println("");
        try {
            int choice = in.nextInt();
            in.nextLine();
            return choice;
        } catch (Exception e) {
            System.out.println("Invalid input: Enter the number 1 or 2 or 3");
            return -2;
        }
    }

    public int viewInvMenu(){
        Scanner in = new Scanner(System.in);
        System.out.println("View Invoice Menu: ");
        System.out.println("1. View all Invoices");
        System.out.println("2. View open Invoices");
        System.out.println("3. View close Invoices");
        System.out.println("Enter -1 to exit");
        try{
            int choice = in.nextInt();
            if (choice < -1 || choice > 3) {
                throw new Exception();
            }
            in.nextLine();
            return choice;
        }
        catch (Exception e) {
            System.out.println("Invalid Input: Enter a numeric value");
            return -2;
        }
    }

    public int chooseInvoice(ArrayList<Invoice> invoiceList){
        Scanner in = new Scanner(System.in);

        System.out.println("Which Invoice would you like to edit");
        viewAllInvoices2(invoiceList);
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
        System.out.println("Option 7 - Edit invoice status: ");

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

    public String editCustomerName() {
        Scanner in = new Scanner(System.in);
        String newName = "";
        System.out.println("What is the new name of the customer? ");
        try{
            newName = in.nextLine();
            return (newName);
        } catch (Exception e) {
            System.out.println("Invalid input, please try again.");
            return "-2";
        }
    }

    public double editTaxRate() {
        Scanner in = new Scanner(System.in);
        System.out.println("What is the new tax rate of the customer? ");
        try {
            double newRate = in.nextDouble();
            return newRate;
        }
        catch (Exception e) {
            System.out.println("Invalid input, please try again.");
            return -2;
        }
    }


    public String editDeliveryStatus() {
        Scanner in = new Scanner(System.in);
        System.out.println("Do you want delivery: Type 'Y' for Yes and 'N' for No");
        String newDeliveryStatus = in.nextLine();
        return newDeliveryStatus;

    }
    public String getInvoiceStatus() {
        Scanner in = new Scanner(System.in);
        System.out.println("What is the new invoice status: type OPEN or CLOSED");
        String newDeliveryStatus = in.nextLine();
        return newDeliveryStatus;

    }

    public String editDeliveryAddress() {
        Scanner in = new Scanner(System.in);
        System.out.println("What is the new delivery address?");
        String newDeliveryAddr = in.nextLine();
        return newDeliveryAddr;
    }

    public int[] changeDateOpened() {
        Scanner in = new Scanner(System.in);
        try {
            int[] input = new int[3];
            System.out.println("What is the year of the invoice: - Please enter in form(YYYY) ");
            input[0] = in.nextInt();
            if (input[0] < 0) {
                throw new Exception();
            }

            System.out.println("What is the month of the invoice: - Please enter in form(MM)");
            input[1] = in.nextInt();
            if (input[1] < 0 || input[1] > 13){
                throw new Exception();
            }

            System.out.println("What is the Day of the invoice: - Please enter in form(DD)");
            input[2] = in.nextInt();
            if (input[2] < 0 || input[2] > 31) {
                throw new Exception();
            }
            return input;
        }
        catch (Exception e) {
            System.out.println("Invalid input, please try again.");
            int[] error = new int[3];
            error[0] = -2;
            return error;
        }
    }

    public String getProductName() {
        Scanner in = new Scanner(System.in);
        System.out.println("What is the name of the Product you would like to add?: ");
        String productName = in.nextLine();
        return productName;
    }

    public int getProductquant(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quantity bought: ");
        try{
            int quan = scanner.nextInt();
            return quan;
        }
        catch(Exception e) {
            System.out.println("Please enter a numberic value");
            return -2;
        }
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

    public boolean addMoreProducts() {
        Scanner in = new Scanner(System.in);
        System.out.println("Would you like to add more Products? Type 'Y' for Yes and 'N' for No");
        String response = in.nextLine();
        if (response.equals("Y")) {
            return true;
        }
        else {
            return false;
        }
    }

    public void viewAllInvoices(ArrayList<Invoice> invoices) {
        //add formating later
        System.out.println("Displaying all invoices");
        for (int i = 0; i < invoices.size(); i++) {
            int num = i + 1;
            System.out.println(num + " " + invoices.get(i));
        }
    }

    public void viewAllInvoices2(ArrayList<Invoice> invoices){
        System.out.println("Displaying all invoices");
        System.out.println(getInvoiceTableHeader());
        for (int i = 0; i < invoices.size(); i++){
            Invoice tempInvoice = invoices.get(i);
            int num = i + 1;
//            System.out.printf("%-5s %15s %15s %15s %10s %18s %18s %18s %25s \n",
//                    num, tempInvoice.getInvoiceId(), tempInvoice.getDateOpened(), tempInvoice.getInvoiceStatus(),
//                    tempInvoice.getCustomerName(), tempInvoice.getTotalCost(), tempInvoice.getTaxRate(),
//                    tempInvoice.getDeliveryStatus(),tempInvoice.getProductsPurchased());
            System.out.printf("%-5s %15s %15s %15s %10s %18s %18s %18s %25s \n",
                    num, tempInvoice.getInvoiceId(), tempInvoice.getDateOpened(), tempInvoice.getInvoiceStatus(),
                    tempInvoice.getCustomerName(), tempInvoice.getTotalCost(), tempInvoice.getTaxRate(),
                    tempInvoice.getDeliveryStatus(),tempInvoice.getProductsPurchased().stream().map(Product::getName).collect(Collectors.joining(", ")));
        }
    }

    public String getInvoiceTableHeader() {
        return String.format("%-5s %15s %15s %15s %10s %18s %18s %18s %25s",
                "#", "INVOICE ID", "DATE OPEN", "STATUS", "#CUSTOMER", "AMOUNT", "TAX RATE", "DELIVERY",
                "PRODUCT");
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

    public void viewOpenInvoices2 (ArrayList<Invoice> invoices){
        System.out.println("Displaying Open Invoices");
        System.out.println(getInvoiceTableHeader());
        //sort by date opened
        Collections.sort(invoices, new OrderDateComparator());
        for (int i = 0; i < invoices.size(); i++){
            Invoice inv = invoices.get(i);
            if (inv.getInvoiceStatus() == true) {
                System.out.printf("%-5s %15s %15s %15s %10s %18s %18s %18s %25s \n",
                        i + 1, inv.getInvoiceId(), inv.getDateOpened(), inv.getInvoiceStatus(),
                        inv.getCustomerName(), inv.getTotalCost(), inv.getTaxRate(),
                        inv.getDeliveryStatus(),inv.getProductsPurchased());
            }

        }
    }



    public void viewClosedInvoices (ArrayList<Invoice> invoices){

        //sort by decreasing order of total cost
        Collections.sort(invoices, new TotalCostComparator());
        System.out.println("Displaying Closed Invoices");
        System.out.println(getInvoiceTableHeader());
        for (int i = 0; i < invoices.size(); i++){
            Invoice inv = invoices.get(i);
            if (inv.getInvoiceStatus() == false) {
                System.out.printf("%-5s %15s %15s %15s %10s %18s %18s %18s %25s \n",
                        i + 1, inv.getInvoiceId(), inv.getDateOpened(), inv.getInvoiceStatus(),
                        inv.getCustomerName(), inv.getTotalCost(), inv.getTaxRate(),
                        inv.getDeliveryStatus(),inv.getProductsPurchased());
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

    public void noProduct(){
        System.out.print("Product does not exist");
    }
}
