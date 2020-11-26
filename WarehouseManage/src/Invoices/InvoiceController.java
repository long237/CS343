package Invoices;
import Database.Database;
import Products.Product;
import Warehouses.WarehouseUI;

import java.util.ArrayList;
import java.util.Scanner;

public class InvoiceController {

//    private Database db = new Database();
//    private InvoiceUIKEIRA ui = new InvoiceUIKEIRA();
//
//    public ArrayList<Invoice> getInvoices() { return db.retrieve_invoices(); }
//    public int getMaxNumOfWarehouses() { return db.maxWarehouses(); }
//
//    public Invoice getInvoice(int invoiceID) {
//        Invoice invoiceToReturn = new Invoice(); // invoiceToReturn.getInvoiceId() => -1234
//        for (Invoice invoice : getInvoices()) {
//            if (invoice.getInvoiceId() == invoiceID) {
//                invoiceToReturn = invoice;
//            }
//        }
//        return invoiceToReturn;
//    }
//
//    // kkkkk: returns TRUE if Invoice w/ given invoiceID exists in getInvoices()
//    public boolean findInvoice(int invoiceID) {
//        boolean invoiceExists = false;
//        if (!getInvoice(invoiceID).getInvoiceId() = -1234) {
//            invoiceExists = true;
//        }
//        return invoiceExists;
//    }
//
//    // kkkkk: returns TRUE if Invoice w/ given invoiceInfo was successfully added to database
//    public boolean addInvoice(String invoiceID, String customerName, String invoiceStatus, double taxRate,
//                              boolean deliveryStatus, String address) {
//        boolean addedProduct = false;
//        Invoice invoiceToAdd = new Invoice(invoiceID, customerName, InvoiceStatus, TaxRate,
//                   deliveryStatus, address, dateOpened);
//        if (!findProduct(warehouseNumber, productToAdd.getName())) {
//            ArrayList<Product> warehouseProducts = getProducts(warehouseNumber);
//            warehouseProducts.add(productToAdd);
//            db.update_products(warehouseNumber, warehouseProducts);
//            addedProduct = true;
//        }
//        return addedProduct;
//    }
//
//    // kkkkk: (returns TRUE if productToRemove was successfully removed from database)
//    public boolean removeProduct(int warehouseNumber, String productToRemove) {
//        boolean removedProduct = false;
//        if (findProduct(warehouseNumber, productToRemove)) {
//            ArrayList<Product> warehouseProducts = getProducts(warehouseNumber);
//            //System.out.println("REMOVING: " + getProduct(warehouseNumber, productToRemove).getName());
//            warehouseProducts.remove(getProduct(warehouseNumber, productToRemove));
//            //System.out.println("WAREHOUSE AFTER REMOVING: " + warehouseProducts);
//            db.update_products(warehouseNumber, warehouseProducts);
//            removedProduct = true;
//        }
//        return removedProduct;
//    }
//
//    public boolean addProductQuantity(int warehouseNumber, String productName, int quantityToAdd) {
//        boolean addedQuantity = false;
//        ArrayList<Product> warehouseProducts = getProducts(warehouseNumber);
//        for (Product product : warehouseProducts) {
//            if (product.getName().equals(productName)) {
//                //System.out.println("ADDING " + quantityToAdd + " " + productName.toUpperCase() + "'s...");
//                product.addQuantityInStock(quantityToAdd);
//                //System.out.println(product);
//                db.update_products(warehouseNumber, warehouseProducts);
//                addedQuantity = true;
//            }
//        }
//        return addedQuantity;
//    }

    public void Icontroller () {
        //ArrayList<Invoices.Invoice> i_data = retrieve_invoices();
        //[..., ...., , ...]
        //add invoice 1
        //save_invoice
        // edit invoice 3       i_data[3]
        // update_invoices

        // while not -1 keep running

        // REVIEW: (Controller "controls" the data, UI Boundary Object should be the only thing that the user accesses/interacts with
        //  Shouldn't UI & Controller be separate from each other to minimize coupling.)

        InvoiceUI invoiceUI = new InvoiceUI();
        Database dataBase = new Database();
//        invoiceUI.Menu();
//        invoiceUI.customerName();


        Scanner scanner = new Scanner(System.in);

        int menu_op = 0;
        while (menu_op != -1) {
            ArrayList<Invoice> i_data = Database.retrieve_invoices();
            menu_op = invoiceUI.invoiceMenu();            //Display the menu and ask for an option
            System.out.println("menu value: " + menu_op);

            if (menu_op == 1) {         //1. Edit invoices
                //edit invoices
                int in_option = 0;      //Invoice nubmer to be edit
                while (in_option < 1 || in_option > i_data.size()){
                    in_option = invoiceUI.chooseInvoice(i_data);
                }
                System.out.println("Invoice to be edit: " + in_option);
                Invoice in_edit = i_data.get(in_option - 1);

                int invoice_part = 0;
                while(invoice_part < 1 || invoice_part > 7){
                    invoice_part = invoiceUI.editInvoiceMenu();
                }

                if(invoice_part == 1) {         //1.Change the customername
                    this.editcName(invoiceUI, in_edit);
                    dataBase.update_invoices(i_data);
                }

                else if(invoice_part == 2){     //2.Edit the tax rate
                    this.editTax(invoiceUI, in_edit);
                    dataBase.update_invoices(i_data);
                }
                else if (invoice_part == 3){    //3.Delivery status
                    this.editDelivery(invoiceUI, in_edit);
                    dataBase.update_invoices(i_data);
                }
            }

            else if (menu_op == 2) {            //2. View Invoice
                invoiceUI.editInvoiceMenu();
            }

            else if (menu_op == 3){

            }


            //view in
        }
    }
    public void editcName(InvoiceUI invoiceUI, Invoice in_edit){
        String c_name = invoiceUI.editCustomerName();
        while(c_name.equals("-2")){
            c_name = invoiceUI.editCustomerName();
        }
        in_edit.setmCustomerName(c_name);
        //dataBase.update_invoices(i_data);
    }

    public void editTax(InvoiceUI invoiceUI, Invoice in_edit){
        double tax_input = -2;
        while (tax_input == -2 || tax_input < 0){
            tax_input = invoiceUI.editTaxRate();
        }
        in_edit.setmTaxRate(tax_input);
    }

    public void editDelivery(InvoiceUI invoiceUI, Invoice in_edit){
        String status = invoiceUI.editDeliveryStatus();
        while ( !status.equals("OPEN") && !status.equals("CLOSE")){
            status = invoiceUI.editDeliveryStatus();
        }
        in_edit.setmDeliveryStatus(status.equals("OPEN"));
    }

    public static void main(String[] args) {
        InvoiceController invoiceController = new InvoiceController();
        invoiceController.Icontroller();
    }

}
