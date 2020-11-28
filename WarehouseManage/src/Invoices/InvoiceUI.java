package Invoices;
import Products.Product;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;


public class InvoiceUI {
    Scanner in = new Scanner(System.in);

    public void printInvoices(ArrayList<Invoice> invoices) {
        System.out.println("\n INVOICES: ");
        System.out.println(getInvoicesTable(invoices));
    }

    public String getInvoicesTableHeader() { // TODO: EDIT INVOICE HEADER & Invoice's toString()
        return " " + String.format("%-20s %15s %15s %15s %10s %18s %18s %18s %18s",
                "PRODUCT-NAME", "#-IN-STOCK", "COST", "RETAIL-PRICE", "#-SOLD", "TOTAL-SALES",
                "TOTAL-COST", "TOTAL-PROFIT", "TOTAL-PROFIT-%");
    }
    public String getInvoicesTable(ArrayList<Invoice> invoices) {
        StringBuilder invoicesTable = new StringBuilder();
        String header = getInvoicesTableHeader();
        invoicesTable.append(header);
        invoicesTable.append("\n");
        for (Invoice invoice : invoices) {
            invoicesTable.append(invoice.toString());
            invoicesTable.append("\n");
        }
        return invoicesTable.toString();
    }

    public int selectInvoiceNumber(int flag, int maxNumOfInvoices) {
        if (flag == 1) {
            System.out.println("That invoice does not exist, please try again: ");
        }
        int input;
        System.out.println("\nVIEW/EDIT INVOICES:");
        for (int i = 1; i <= maxNumOfInvoices; i++) {
            System.out.println("\t " + i + ". Invoice " + i); // TODO: PRINT INVOICE DATA HERE
        }
        System.out.print("Enter an Invoice # (Enter (-1) to exit): ");
        try {
            input = in.nextInt();
            in.nextLine();
            return (input);
        } catch (Exception e) {
            return -2;
        }
    }

    public int selectMenuOption() {
        int input;
        System.out.println("MANAGE INVOICES: \n" +
                "\t 1. Create Invoices. \n" +
                "\t 2. View/Edit Invoices. \n");
        try {
            input = in.nextInt();
            in.nextLine();
            return (input);
        } catch (Exception e) {
            System.out.println("Invalid input, please try again.");
            return -2;
        }
    }

    //addProductMenu returns an ArrayList so that we can return more than one input at a time
    public ArrayList<String> addInvoiceMenu() {
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

    // returns an array list of an array list containing info for each product to add
    public ArrayList<ArrayList<String>> selectAddInvoice() {

        boolean contLoop = true;

        ArrayList<ArrayList<String>> invoicesInfo = new ArrayList<ArrayList<String>>();
        System.out.println("ADDING INVOICE(s) ... ");
        while (contLoop) {

            ArrayList<String> invoiceMenuOption = addInvoiceMenu();
            boolean add = true;
            for (int i = 0; i < invoiceMenuOption.size(); i++) {
                if (invoiceMenuOption.get(i).equals("-1") && i != 6) {
                    add = false;
                }
                if (invoiceMenuOption.get(i).equals("-1")){
                    contLoop = false;
                }
            }
            if (add) {
                invoicesInfo.add(invoiceMenuOption);
            }

        }
        return invoicesInfo;
    }

    public ArrayList<String> removeInvoiceMenu() {
        ArrayList<String> invoicesToRemove = new ArrayList<>();
        boolean flag = true;
        System.out.println("REMOVING INVOICE(s) ... ");
        while(flag) {
            try {
                System.out.println("\tEnter the Invoice # you would like to remove: ");
                System.out.print("\t");
                invoicesToRemove.add(in.nextLine());

                // Continue?
                System.out.print("CONTINUE REMOVING INVOICE(s)? (Enter (-1) to EXIT): ");
                String enterInvoices = in.nextLine();
                if (enterInvoices.equals("-1") || enterInvoices.equals("N")){
                    flag = false;
                }
            } catch (Exception e) {
                System.out.println("Invalid input, please try again.");
            }
        }
        return invoicesToRemove;
    }

//    public HashMap<String, Integer> addQuantityMenu() {
//        HashMap<String, Integer> temp = new HashMap<>();
//        boolean flag = true;
//        System.out.println("ADDING QUANTITY to PRODUCT(s) ... ");
//        while(flag) {
//            try {
//                System.out.println("Enter a product to add a quantity to: ");
//                String productName = in.nextLine();
//                System.out.println("Enter a quantity to add: ");
//                int quantityToAdd = in.nextInt();
//                in.nextLine();
//                temp.put(productName, quantityToAdd);
//
//                // Continue?
//                System.out.print("CONTINUE ADDING QUANTITY to PRODUCT(s)? (Enter (-1) to EXIT): ");
//                String enterProducts = in.nextLine();
//                if (enterProducts.equals("-1") || enterProducts.equals("N")){
//                    flag = false;
//                }
//            } catch (Exception e) {
//                System.out.println("Invalid input, please try again.");
//            }
//        }
//        return temp;
//    }

//    public void selectAddQuantity() {}
//    public void selectDecrProfitPercent() {}
//    public void selectLowInStock() {}
//    public void selectQuantityInStock() {}

    public void exitValidation() {
        System.out.print("Press ENTER to return to MAIN MENU: ");
        String input = in.nextLine();
    }
}
