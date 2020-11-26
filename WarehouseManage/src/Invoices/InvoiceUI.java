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
        System.out.println("Option 3 - Add Invoice");
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
        System.out.println("What is the new delivery status: type OPEN or CLOSED");
        String newDeliveryStatus = in.nextLine();

//        if (newDeliveryStatus == "OPEN") {
//            invoice.setmDeliveryStatus(true);
//        } else {
//            invoice.setmDeliveryStatus(false);
//        }
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
        for (int i = 0; i < invoices.size(); i++) {
            int num = i + 1;
            System.out.println(num + " " + invoices.get(i));
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

    public ArrayList<String> addInvoiceMenu() {
        Scanner in = new Scanner(System.in);
        ArrayList<String> outputList = new ArrayList<>();
        outputList.add("Customer Name: ");
        outputList.add("Products Purchased: ");
        outputList.add("Tax Rate: ");
        outputList.add("Total: ");
        outputList.add("Delivery: ");
        outputList.add("Delivery Address: ");
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < 7; i++){
            if (i < 6) {
                System.out.println("\tEnter the invoice's " + outputList.get(i));
                System.out.println("\t(Enter (-1) to ABORT)");
            }
            else {
                System.out.println("CONTINUE ADDING INVOICES? (Enter (-1) to EXIT): ");
            }
            System.out.print("\t");
            String input = in.nextLine();
            if (input.equals("-1") && i != 6) {
                temp.clear();
                temp.add("-1");
                return temp;
            }
            if (i == 1) {
                try {
                    Integer.parseInt(input);
                }
                catch(Exception e) {
                    System.out.println("Invalid input, please try again: ");
                }
            }
            if (i > 1 && i != 6) {
                try {
                    Double.parseDouble(input);
                } catch (Exception e) {
                    System.out.println("Invalid input, please try again: ");
                }
            }
            temp.add(input);
        }
        return temp;
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
