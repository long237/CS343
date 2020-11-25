package Invoices;
import Database.Database;

import java.util.ArrayList;
import java.util.Scanner;

// REVIEW: (not sure if "extends" is really the proper way to get access to WarehouseDB methods, retrieve_products() & update_products())
public class InvoiceController { // extends InvoiceDB

//    InvoiceUI invoiceUI = new InvoiceUI();
//    invoiceUI.printSubMenu();
//    int user = invoiceUI.getOPtion();
//
//    if  ( user == 1) {
//        addINvoice()
//    }

    /*
    // keira: (Control Methods) ----------------------------------------------------------------------------------------
    public ArrayList<Invoice> getInvoices() {
        return retrieve_invoices();
    }
    public void addInvoice(Invoice invoiceToAdd) {
        ArrayList<Invoice> invoices = getInvoices();
        invoices.add(invoiceToAdd);
        update_invoices(invoices);
    }
    public boolean invoiceExists(Invoice invoiceToFind) {
        return getInvoices().contains(invoiceToFind);
    }
    public void removeInvoice(Invoice invoiceToRemove) {
        ArrayList<Invoice> invoices = getInvoices();
        invoices.remove(invoiceToRemove);
        update_invoices(invoices);
    }
    // keira: (END of Control Methods) ---------------------------------------------------------------------------------
    */

    // REVIEW: (What is the purpose of this method? When will it be called by the UI Boundary Object?)
    public static void Icontroller () {
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

        ArrayList<Invoice> i_data = Database.retrieve_invoices();
        Scanner scanner = new Scanner(System.in);

        int menu_op = 0;
        while (menu_op != -1) {
            menu_op = invoiceUI.invoiceMenu();            //Display the menu and ask for an option
            System.out.println("menu value: " + menu_op);

            if (menu_op == 1) {         //Edit invoice submenu
                //edit invoices
                int in_option = 0;      //Invoice nubmer to be edit
                while (in_option < 1 || in_option > i_data.size()){
                    in_option = invoiceUI.chooseInvoice(i_data);
                }
                System.out.println("Invoice to be edit: " + in_option);

                int invoice_part = 0;
                while(invoice_part < 1 || invoice_part > 7){
                    invoice_part = invoiceUI.editInvoiceMenu();
                }

                if(invoice_part == 1) {
                    String c_name = invoiceUI.customerName();
                    Invoice in_edit = i_data.get(in_option - 1);
                    in_edit.setmCustomerName(c_name);
                    dataBase.update_invoices(i_data);
                }
            }

//            else if (menu_op == 2) {
//                invoiceUI.editInvoiceMenu();
//            }


            //view in
        }

    }

    public static void main(String[] args) {
        Icontroller();
    }

}
