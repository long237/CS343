package Invoices;
import java.util.ArrayList;

// REVIEW: (not sure if "extends" is really the proper way to get access to WarehouseDB methods, retrieve_products() & update_products())
public class InvoiceController {

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
        invoiceUI.Menu();
        invoiceUI.customerName();
    }

    public static void createInvoice() {

    }
}
