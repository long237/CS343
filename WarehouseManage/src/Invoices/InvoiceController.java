package Invoices;
import Database.Database;
import Products.Product;
import Products.ProfitPercentComparator;
import Products.QuantityInStockComparator;
import Warehouses.WarehouseUI;
import java.time.LocalDate;

import java.util.*;

public class InvoiceController {

    private Database db = new Database();
    private InvoiceUI ui = new InvoiceUI();

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
    public boolean addInvoice(int invoiceID, String customerName, boolean invoiceStatus, double taxRate,
                              boolean deliveryStatus, String address, LocalDate dateOpened) {
        boolean addedInvoice = false;
        Invoice invoiceToAdd = new Invoice(); // invoiceID, customerName, invoiceStatus, taxRate, deliveryStatus, address, dateOpened
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

    public void invoiceController() {
        int menuOption = 0;
        int main = 0;
        ArrayList<ArrayList<String>> invoicesToAdd = new ArrayList<>();

        while (menuOption != -1) { // User enters "-1" to exit / go back to "Main Menu: " Window.

            ArrayList<Invoice> invoicesToDisplay = getInvoices();
            ui.printInvoices(invoicesToDisplay);

            // kkkkk: ("MANAGE INVOICES: " Window)
            menuOption = ui.selectMenuOption();

            // kkkkk: (1. Create Invoices.)
            if (menuOption == 1) {
                // KKKKK: ("ADDING INVOICE(s) ... " Window)
                invoicesToAdd = ui.selectAddInvoice();
                for (ArrayList<String> invoiceInfo : invoicesToAdd) {
                    if (Integer.parseInt(invoiceInfo.get(1)) < 0) { // TODO: fix these (.get(index)) index doesn not match
                        break;
                    }
                    if (Double.parseDouble(invoiceInfo.get(2)) < 0 || Double.parseDouble(invoiceInfo.get(3)) < 0) {
                        break;
                    }
                    // TODO: let the user know if was unable to add invoiceInfo[0] (invoiceToAdd's name) & remprompt user for input.
                    addInvoice(Integer.parseInt(invoiceInfo.get(0)), invoiceInfo.get(1), Boolean.parseBoolean(invoiceInfo.get(2)), Double.parseDouble(invoiceInfo.get(3)),
                    Boolean.parseBoolean(invoiceInfo.get(4)), invoiceInfo.get(5), LocalDate.parse(invoiceInfo.get(6)));
                }
            }
            // kkkkk: (2. Remove Products.)
            else if (menuOption == 2) {
                // KKKKK: ("REMOVING PRODUCT(s) FROM WAREHOUSE 1 ..." Window)
                ArrayList<String> productsToRemove = ui.removeInvoiceMenu();
                // TODO: let the user know if was unable to remove productToRemove & remprompt user for input.
                for (String productToRemove : productsToRemove) {
                    //removeProduct(warehouseNumber, productToRemove);
                }
            }
            // kkkkk: 3. Add Product Quantity.
            else if (menuOption == 3) {
//                // KKKKK: "ADD PRODUCT QUANTITY: " Window
//                HashMap<String, Integer> productsToAddQuantityTo = ui.add
//                for (Map.Entry<String, Integer> product : productsToAddQuantityTo.entrySet()) {
//                    // TODO: let user know if productName or productQuantity is invalid / unable to add productQuantity & remprompt user for input.
//                    addProductQuantity(warehouseNumber, product.getKey(), product.getValue());
//                }
            }
            // kkkkk: (4. View Products By Decreasing Profit Percent.)
//            else if (menuOption == 4) {
//                Collections.sort(productsToDisplay, new ProfitPercentComparator());
//                ui.printWarehouseProducts(warehouseNumber, productsToDisplay);
//                ui.exitValidation();
//            }
//            // kkkkk: (5. View Low-In-Stock products.)
//            else if (menuOption == 5) {
//                ArrayList<Product> temp = new ArrayList<>();
//                for (Product p : productsToDisplay) {
//                    if (p.isLowStock()) {
//                        temp.add(p);
//                    }
//                }
//                ui.printWarehouseProducts(warehouseNumber, temp);
//                ui.exitValidation();
//            }
//            // kkkkk: (6. View Products By Increasing Quantity-In-Stock.)
//            else if (menuOption == 6) {
//                Collections.sort(productsToDisplay, new QuantityInStockComparator());
//                ui.printWarehouseProducts(warehouseNumber, productsToDisplay);
//                ui.exitValidation();
//            }
        }
    }

//    public void editcName(InvoiceUI invoiceUI, Invoice in_edit){
//        String c_name = invoiceUI.editCustomerName();
//        while(c_name.equals("-2")){
//            c_name = invoiceUI.editCustomerName();
//        }
//        in_edit.setmCustomerName(c_name);
//        //dataBase.update_invoices(i_data);
//    }
//
//    public void editTax(InvoiceUI invoiceUI, Invoice in_edit){
//        double tax_input = -2;
//        while (tax_input == -2 || tax_input < 0){
//            tax_input = invoiceUI.editTaxRate();
//        }
//        in_edit.setmTaxRate(tax_input);
//    }
//
//    public void editDelivery(InvoiceUI invoiceUI, Invoice in_edit){
//        String status = invoiceUI.editDeliveryStatus();
//        while ( !status.equals("OPEN") && !status.equals("CLOSE")){
//            status = invoiceUI.editDeliveryStatus();
//        }
//        in_edit.setmDeliveryStatus(status.equals("OPEN"));
//    }
//
//    public void editAddress(InvoiceUI invoiceUI, Invoice in_edit){
//        String address = invoiceUI.editDeliveryAddress();
//        in_edit.setmAddress(address);
//    }
//
//    public void editDate(InvoiceUI invoiceUI, Invoice in_edit) {
//        int[] date_input = invoiceUI.changeDateOpened();
//        while (date_input[0] < 0 && date_input[1] < 0 && date_input[2] < 0){
//            date_input = invoiceUI.changeDateOpened();
//        }
//        in_edit.setmDateOpened(LocalDate.of(date_input[0], date_input[1], date_input[2]));
//    }
//
//    public static void main(String[] args) {
//        InvoiceController invoiceController = new InvoiceController();
//        invoiceController.Icontroller();
//    }

}
