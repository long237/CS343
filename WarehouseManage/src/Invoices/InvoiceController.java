package Invoices;
import Database.Database;
import Products.Product;
import Warehouses.WarehouseUI;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Scanner;

public class InvoiceController {

    private Database db = new Database();
    private InvoiceUIKEIRA ui = new InvoiceUIKEIRA();

    public ArrayList<Invoice> getInvoices() { return db.retrieve_invoices(); }
    public int getMaxNumOfInvoices() { return getInvoices().size(); }

    public Invoice getInvoice(int invoiceID) {
        Invoice invoiceToReturn = new Invoice(); // invoiceToReturn.getInvoiceId() => -1234
        for (Invoice invoice : getInvoices()) {
            if (invoice.getInvoiceId() == invoiceID) {
                invoiceToReturn = invoice;
            }
        }
        return invoiceToReturn;
    }

    // kkkkk: returns TRUE if Invoice w/ given invoiceID exists in getInvoices()
    public boolean findInvoice(int invoiceID) {
        boolean invoiceExists = false;
        if (getInvoice(invoiceID).getInvoiceId() != -1234) {
            invoiceExists = true;
        }
        return invoiceExists;
    }

    // kkkkk: returns TRUE if Invoice w/ given invoiceInfo was successfully added to database
    public boolean addInvoiceK(int invoiceID, String customerName, boolean invoiceStatus, double taxRate,
                              boolean deliveryStatus, String address, LocalDate dateOpened) {
        boolean addedInvoice = false;
        Invoice invoiceToAdd = new Invoice(invoiceID, customerName, invoiceStatus, taxRate,
                   deliveryStatus, address, dateOpened);
        if (!findInvoice(invoiceToAdd.getInvoiceId())) {
            ArrayList<Invoice> invoices = getInvoices();
            invoices.add(invoiceToAdd);
            db.update_invoices(invoices);
            addedInvoice = true;
        }
        return addedInvoice;
    }

    // kkkkk: (returns TRUE if invoiceToRemove was successfully removed from database)
    public boolean removedInvoice(int invoiceToRemove) {
        boolean removedInvoice = false;
        if (findInvoice(invoiceToRemove)) {
            ArrayList<Invoice> invoices = getInvoices();
            //System.out.println("REMOVING: " + getProduct(warehouseNumber, productToRemove).getName());
            invoices.remove(getInvoice(invoiceToRemove)); // TODO: must override equals() for this to work
            //System.out.println("WAREHOUSE AFTER REMOVING: " + warehouseProducts);
            db.update_invoices(invoices);
            removedInvoice = true;
        }
        return removedInvoice;
    }

    public boolean addProductPurchased(int invoiceID, Product newProduct) {
        boolean addedQuantity = false;
        ArrayList<Invoice> invoices = getInvoices();
        for (Invoice invoice : invoices) {
            if (invoice.getInvoiceId() == invoiceID) {
                //System.out.println("ADDING newProduct.getName().toUpperCase() + "'s...");
                invoice.addProductsPurchased(newProduct);
                //System.out.println(product);
                db.update_invoices(invoices);
                addedQuantity = true;
            }
        }
        return addedQuantity;
    }

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
                    String c_name = this.getcName(invoiceUI);
                    in_edit.setmCustomerName(c_name);
                    dataBase.update_invoices(i_data);
                }

                else if(invoice_part == 2){     //2.Edit the tax rate
                    double tax_input = this.getTax(invoiceUI);
                    in_edit.setmTaxRate(tax_input);
                    dataBase.update_invoices(i_data);
                }
                else if (invoice_part == 3){    //3.Delivery status
                    Boolean status = this.getDeliveryStat(invoiceUI);
                    in_edit.setmDeliveryStatus(status);
                    dataBase.update_invoices(i_data);
                }
                else if (invoice_part == 4) {   //4.Delivery Address
                    String address = this.getAddress(invoiceUI);
                    in_edit.setmAddress(address);
                    dataBase.update_invoices(i_data);
                }
                else if (invoice_part == 5) {   //5.Change date
                    int[] date_input = this.getDate(invoiceUI);
                    in_edit.setmDateOpened(LocalDate.of(date_input[0], date_input[1], date_input[2]));
                    dataBase.update_invoices(i_data);
                }
                else if (invoice_part == 6) {
                    System.out.println("Add product to be continue on Nov 27th");
                }
                else if (invoice_part == 7) {
                    System.out.println("Remove product to be continue on Nov 27th");
                }
            }

            else if (menu_op == 2) {            //2. View Invoice
                invoiceUI.editInvoiceMenu();
            }

            else if (menu_op == 3){             //3.Add invoice.
                ArrayList<String> user_input = invoiceUI.addInvoiceMenu();

            }


            //view in
        }
    }
    public String getcName(InvoiceUI invoiceUI){
        String c_name = invoiceUI.editCustomerName();
        while(c_name.equals("-2")){
            c_name = invoiceUI.editCustomerName();
        }
        return c_name;
        //in_edit.setmCustomerName(c_name);
    }

    public double getTax(InvoiceUI invoiceUI){
        double tax_input = -2;
        while (tax_input == -2 || tax_input < 0){
            tax_input = invoiceUI.editTaxRate();
        }
        return tax_input;
        //in_edit.setmTaxRate(tax_input);
    }

    public Boolean getDeliveryStat(InvoiceUI invoiceUI){
        String status = invoiceUI.editDeliveryStatus();
        while ( !status.equals("OPEN") && !status.equals("CLOSE")){
            status = invoiceUI.editDeliveryStatus();
            System.out.println("Invalid input, please try again.");
        }
        return status.equals("OPEN");
        //in_edit.setmDeliveryStatus(status.equals("OPEN"));
    }

    public String getAddress(InvoiceUI invoiceUI){
        String address = invoiceUI.editDeliveryAddress();
        return address;
        //in_edit.setmAddress(address);
    }

    public int[] getDate(InvoiceUI invoiceUI) {
        int[] date_input = new int[3];
        date_input[0] = -1;
        while (date_input[0] < 0 || date_input[1] < 0 || date_input[1] > 13 || date_input[2] < 0 || date_input[2] > 31){
            date_input = invoiceUI.changeDateOpened();
        }
        return date_input;
        //in_edit.setmDateOpened(LocalDate.of(date_input[0], date_input[1], date_input[2]));
    }

    public void addInvoice(Invoice UI){

    }

    public static void main(String[] args) {
        InvoiceController invoiceController = new InvoiceController();
        invoiceController.Icontroller();
    }

}
