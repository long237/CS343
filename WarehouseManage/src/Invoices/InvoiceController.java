package Invoices;
import Database.Database;
import Products.Product;
import Warehouses.WarehouseUI;
import Customers.Customer;
import java.lang.reflect.Array;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Scanner;

public class InvoiceController {

//    private Database db = new Database();
//    private InvoiceUIKEIRA ui = new InvoiceUIKEIRA();
//
//    public ArrayList<Invoice> getInvoices() { return db.retrieve_invoices(); }
//    public int getMaxNumOfInvoices() { return getInvoices().size(); }
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
//        if (getInvoice(invoiceID).getInvoiceId() != -1234) {
//            invoiceExists = true;
//        }
//        return invoiceExists;
//    }
//
//    // kkkkk: returns TRUE if Invoice w/ given invoiceInfo was successfully added to database
//    public boolean addInvoiceK(int invoiceID, String customerName, boolean invoiceStatus, double taxRate,
//                              boolean deliveryStatus, String address, LocalDate dateOpened) {
//        boolean addedInvoice = false;
//        Invoice invoiceToAdd = new Invoice(invoiceID, customerName, invoiceStatus, taxRate,
//                   deliveryStatus, address, dateOpened);
//        if (!findInvoice(invoiceToAdd.getInvoiceId())) {
//            ArrayList<Invoice> invoices = getInvoices();
//            invoices.add(invoiceToAdd);
//            db.update_invoices(invoices);
//            addedInvoice = true;
//        }
//        return addedInvoice;
//    }
//
//    // kkkkk: (returns TRUE if invoiceToRemove was successfully removed from database)
//    public boolean removedInvoice(int invoiceToRemove) {
//        boolean removedInvoice = false;
//        if (findInvoice(invoiceToRemove)) {
//            ArrayList<Invoice> invoices = getInvoices();
//            //System.out.println("REMOVING: " + getProduct(warehouseNumber, productToRemove).getName());
//            invoices.remove(getInvoice(invoiceToRemove)); // TODO: must override equals() for this to work
//            //System.out.println("WAREHOUSE AFTER REMOVING: " + warehouseProducts);
//            db.update_invoices(invoices);
//            removedInvoice = true;
//        }
//        return removedInvoice;
//    }
//
//    public boolean addProductPurchased(int invoiceID, Product newProduct) {
//        boolean addedQuantity = false;
//        ArrayList<Invoice> invoices = getInvoices();
//        for (Invoice invoice : invoices) {
//            if (invoice.getInvoiceId() == invoiceID) {
//                //System.out.println("ADDING newProduct.getName().toUpperCase() + "'s...");
//                invoice.addProductsPurchased(newProduct);
//                //System.out.println(product);
//                db.update_invoices(invoices);
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
        Scanner scanner = new Scanner(System.in);

        int menu_op = 0;
        while (menu_op != -1) {
            ArrayList<Invoice> invoiceList = Database.retrieve_invoices();
            menu_op = invoiceUI.invoiceMenu();            //Display the menu and ask for an option
            System.out.println("menu value: " + menu_op);

            if (menu_op == 1) {         //1. Edit invoices
                //edit invoices
                int in_option = 0;      //Invoice nubmer to be edit
                while (in_option < 1 || in_option > invoiceList.size()){
                    in_option = invoiceUI.chooseInvoice(invoiceList);
                }
                System.out.println("Invoice to be edit: " + in_option);
                Invoice in_edit = invoiceList.get(in_option - 1);

                int invoice_part = 0;
                while(invoice_part < 1 || invoice_part > 7){
                    invoice_part = invoiceUI.editInvoiceMenu();
                }

                if(invoice_part == 1) {         //1.Change the customername
                    String c_name = this.getcName(invoiceUI);
                    in_edit.setmCustomerName(c_name);
                    dataBase.update_invoices(invoiceList);
                }

                else if(invoice_part == 2){     //2.Edit the tax rate
                    double tax_input = this.getTax(invoiceUI);
                    in_edit.setmTaxRate(tax_input);
                    dataBase.update_invoices(invoiceList);
                }
                else if (invoice_part == 3){    //3.Delivery status
                    Boolean status = this.getDeliveryStat(invoiceUI);
                    in_edit.setmDeliveryStatus(status);
                    dataBase.update_invoices(invoiceList);
                }
                else if (invoice_part == 4) {   //4.Delivery Address
                    String address = this.getAddress(invoiceUI);
                    in_edit.setmAddress(address);
                    dataBase.update_invoices(invoiceList);
                }
                else if (invoice_part == 5) {   //5.Change date
                    int[] date_input = this.getDate(invoiceUI);
                    in_edit.setmDateOpened(LocalDate.of(date_input[0], date_input[1], date_input[2]));
                    dataBase.update_invoices(invoiceList);
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
                System.out.println("Add Invoice submenu: ");
                Invoice inputInvoice = addInvoice(invoiceUI,dataBase);
                invoiceList.add(inputInvoice);
                dataBase.update_invoices(invoiceList);                  //update the database with new invoice
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
    }

    public double getTax(InvoiceUI invoiceUI){
        double tax_input = -2;
        while (tax_input == -2 || tax_input < 0){
            tax_input = invoiceUI.editTaxRate();
        }
        return tax_input;
    }

    public Boolean getDeliveryStat(InvoiceUI invoiceUI){
        String status = invoiceUI.editDeliveryStatus();
        while ( !status.equals("OPEN") && !status.equals("CLOSE")){
            status = invoiceUI.editDeliveryStatus();
            System.out.println("Invalid input, please try again.");
        }
        return status.equals("OPEN");
    }

    public String getAddress(InvoiceUI invoiceUI){
        return invoiceUI.editDeliveryAddress();
    }

    public int[] getDate(InvoiceUI invoiceUI) {
        int[] date_input = new int[3];
        date_input[0] = -1;
        while (date_input[0] < 0 || date_input[1] < 0 || date_input[1] > 13 || date_input[2] < 0 || date_input[2] > 31){
            date_input = invoiceUI.changeDateOpened();
        }
        return date_input;
    }

    public Invoice addInvoice(InvoiceUI invoiceUI, Database dataBase){

        ArrayList<Customer> customerList = dataBase.retrieve_Customer();
        String incName = getcName(invoiceUI);
        double taxRate = -1;
        //Check to see if the customer name already exist in the database
        int cIndex = isInCData(incName, customerList);
        //Prompt the user for tax rate if customer not does not already exist in the database
        //If customer tax already exist use the database tax rate
        if (cIndex >= 0) {
            taxRate = customerList.get(cIndex).getmTaxrate();
        }
        //Ask the user for a tax rate if the customer name is not in the Database, add the new customer into database
        else {
            taxRate = getTax(invoiceUI);
            dataBase.add_customer(new Customer(incName, taxRate));
        }
        boolean deliStat = getDeliveryStat(invoiceUI);
        String deliAddress = getAddress(invoiceUI);
        int[] dateValue = getDate(invoiceUI);
        LocalDate dateOpen = LocalDate.of(dateValue[0], dateValue[1], dateValue[2]);
        Invoice inputInvoice = new Invoice(incName, taxRate, deliStat, deliAddress, dateOpen);
        // TODO: change this products place holder into asking the user for products
        inputInvoice.addProductsPurchased(new Product("Hat", 10, 5));
        inputInvoice.addProductsPurchased(new Product("Phone", 250, 1));
        return inputInvoice;
    }

    public int isInCData(String incName, ArrayList<Customer> customerList){
        int index = -1;
        //Check to see if the customer name already exist in the database
        for (int i = 0; i < customerList.size(); i++) {
            String temp_cName = customerList.get(i).getmName();
            if(temp_cName.equals(incName)) {
                index = i;
                return index;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        InvoiceController invoiceController = new InvoiceController();
        invoiceController.Icontroller();
    }

}
