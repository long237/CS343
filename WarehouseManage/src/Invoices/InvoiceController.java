package Invoices;
import Database.Database;
import Products.Product;
import Warehouses.Warehouse;
import Warehouses.WarehouseController;
import Warehouses.WarehouseUI;
import Customers.Customer;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class InvoiceController {

    InvoiceUI invoiceUI = new InvoiceUI();
    Database dataBase = new Database();
    WarehouseUI warehouseUI = new WarehouseUI();

    public void Icontroller () throws IOException {
        InvoiceUI invoiceUI = new InvoiceUI();
        Database dataBase = new Database();
        WarehouseController warehouseCon = new WarehouseController();

        Scanner scanner = new Scanner(System.in);



        int menu_op = 0;
        while (menu_op != -1) {
            ArrayList<Invoice> invoiceList = Database.retrieve_invoices();
            ArrayList<Product> productList = warehouseCon.getAllProducts();

            int largestID = invoiceList.get(invoiceList.size() - 1).getInvoiceId();
            menu_op = invoiceUI.invoiceMenu();            //Display the menu and ask for an option
            System.out.println("menu value: " + menu_op);

            if (menu_op == 1) {         //1. Edit invoices
                //edit invoices
                int in_option = 0;      //Invoice nubmer to be edit
                while (in_option < 1 || in_option > invoiceList.size()) {
                    in_option = invoiceUI.chooseInvoice(invoiceList);
                }
                System.out.println("Invoice to be edit: " + in_option);
                Invoice in_edit = invoiceList.get(in_option - 1);

                int invoice_part = 0;
                while(invoice_part < 1 || invoice_part > 7){
                    invoice_part = invoiceUI.editInvoiceMenu();
                }

                if(invoice_part == 1) {         //1.Change the customername
                    String c_name = this.getcName();
                    in_edit.setmCustomerName(c_name);
                    dataBase.update_invoices(invoiceList);
                }

                else if(invoice_part == 2){     //2.Edit the tax rate
                    double tax_input = this.getTax();
                    in_edit.setmTaxRate(tax_input);
                    dataBase.update_invoices(invoiceList);
                }
                else if (invoice_part == 3){    //3.Delivery status
                    Boolean status = this.getStatus();
                    in_edit.setmDeliveryStatus(status);
                    dataBase.update_invoices(invoiceList);
                }
                else if (invoice_part == 4) {   //4.Delivery Address
                    String address = this.getAddress();
                    in_edit.setmAddress(address);
                    dataBase.update_invoices(invoiceList);
                }
                else if (invoice_part == 5) {   //5.Change date
                    //int[] date_input = this.getDate();
                    LocalDate date_input = this.getDate();
                    in_edit.setmDateOpened(date_input);
                    // in_edit.setmDateOpened(LocalDate.of(date_input[0], date_input[1], date_input[2]));
                    dataBase.update_invoices(invoiceList);
                }
                else if (invoice_part == 6) {   //6. Add invoice
                    addProductPurchased(in_edit, productList);
                    dataBase.update_invoices(invoiceList);
                }
                else if (invoice_part == 7) {
                    Boolean status = this.getStatus();
                    in_edit.setmInvoiceStatus(status);

                    if (!status) {          //Invoice status != delivery status
                        System.out.println("Ok enter a closing date for the invoice: ");
                        int[] closingDate = invoiceUI.changeDateOpened();
                        in_edit.addDiscount(LocalDate.of(closingDate[0], closingDate[1], closingDate[2]));
                    }
                    /**Add delivery charge to the invoice if the user want delivery **/
                    if (in_edit.getDeliveryStatus()) {
                        in_edit.addDeliveryCharge();
                    }
                    dataBase.update_invoices(invoiceList);
                }
            }

            else if (menu_op == 2) {            //2. View Invoice
                int user_op = 0;
                while (user_op != -1) {
                    user_op = getViewInv();
                    if (user_op == 1){
//                        invoiceUI.viewAllInvoices(invoiceList);
                        invoiceUI.viewAllInvoices2(invoiceList);
                    }
                    else if(user_op == 2) {
                        invoiceUI.viewOpenInvoices2(invoiceList);
                    }
                    else if (user_op == 3) {
                        invoiceUI.viewClosedInvoices(invoiceList);
                    }
                }
            }


            else if (menu_op == 3){             //3.Add invoice.
                System.out.println("Add Invoice submenu: ");
                Invoice inputInvoice = addInvoice(largestID, productList);
                invoiceList.add(inputInvoice);
                dataBase.update_invoices(invoiceList);                  //update the database with new invoice
            }


            //view in
        }
    }

    //All invalid value are return -2, use -1 to exit
    public int getViewInv() {
        int user_op = -2;
        while (user_op == -2) {
            user_op = invoiceUI.viewInvMenu();
        }
        return user_op;
    }
    public String getcName(){
        String c_name = invoiceUI.editCustomerName();
        while(c_name.equals("-2")){
            c_name = invoiceUI.editCustomerName();
        }
        return c_name;
    }

    public double getTax(){
        double tax_input = -2;
        while (tax_input == -2 || tax_input < 0){
            tax_input = invoiceUI.editTaxRate();
        }
        return tax_input;
    }

    // TODO: add an error message when the user enter the wrong value
    public Boolean getStatus(){
        String status = invoiceUI.editDeliveryStatus();
        while ( !status.equals("OPEN") && !status.equals("CLOSED")){
            status = invoiceUI.editDeliveryStatus();
            //System.out.println("Invalid input, please try again.");
        }
        return status.equals("OPEN");
    }

//    public Boolean getInvoiceStat(){
//        String status = invoiceUI.getInvoiceStatus();
//        while ( !status.equals("OPEN") && !status.equals("CLOSE")){
//            status = invoiceUI.editDeliveryStatus();
//            //System.out.println("Invalid input, please try again.");
//        }
//        return status.equals("OPEN");
//    }

    public String getAddress(){
        return invoiceUI.editDeliveryAddress();
    }

    public LocalDate getDate() {
        int[] date_input = new int[3];
        date_input[0] = -1;
        while (date_input[0] < 0 || date_input[1] < 0 || date_input[1] > 13 || date_input[2] < 0 || date_input[2] > 31){
            date_input = invoiceUI.changeDateOpened();
        }
        LocalDate date = LocalDate.of(date_input[0], date_input[1], date_input[2]);
        return date;
    }

    public Invoice addInvoice(int largestID, ArrayList<Product> productList) throws IOException {

        ArrayList<Customer> customerList = dataBase.retrieve_Customer();

        String incName = getcName();
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
            taxRate = getTax();
            dataBase.addCustomer(new Customer(incName, taxRate), dataBase.retrieve_Customer());
        }
        boolean deliStat = getStatus();
        String deliAddress = getAddress();
        //int[] dateValue = getDate();
        LocalDate dateOpen = getDate();
        Invoice inputInvoice = new Invoice(largestID + 1, incName, taxRate, deliStat, deliAddress, dateOpen);
        boolean doneAddingProducts = true;

        while (doneAddingProducts) {
            addProductPurchased(inputInvoice, productList);
            doneAddingProducts = invoiceUI.addMoreProducts();
        }
        inputInvoice.calCost();

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

    public void addProductPurchased(Invoice invoice, ArrayList<Product> productList) throws IOException {
        ArrayList<ArrayList<Product>> warehouseList = new ArrayList<ArrayList<Product>>();
        int numWarehouse = dataBase.maxWarehouses();

        int warehouseNumber = 0;
        for (int i = 1; i <= numWarehouse; i++) {
            warehouseList.add(getProducts(i));
        }

        for (int i = 0; i < numWarehouse; i++) {
            //Display warehouse content
            //Todo: Ask keira or Bryan for a function to print out products to choose from
            warehouseUI.printProducts(productList);
        }
        String productName = invoiceUI.getProductName();
        boolean productFound = false;
        while (!productFound) {

            for (int i = 1; i <= numWarehouse && productFound == false; ++i) {
                productFound = findProduct(i, productName);
                warehouseNumber = i;
            }
            if (!productFound) {
                invoiceUI.noProduct();
                productName = invoiceUI.getProductName();
            }
        }
        //if quantiy is more than in stock, give what is in stock then check other products in warehouse

        int user_quant = getQuant();
        int user_want = user_quant;

        boolean isEnough = false;
        double prod_retail = 0.0;
        for (int i = 0; i < warehouseList.size() && isEnough == false; i++) {        //get the warehouse
            for (Product prod : warehouseList.get(i)) { //iterate the product inside the warehouse
                if (prod.getName().equals(productName)) {
                    int prod_stock = prod.getQuantityInStock();
                    prod_retail = prod.getRetailPrice();
                    if (prod_stock >= user_quant) {
                        prod.setQuantityInStock(prod.getQuantityInStock() - user_quant);
                        user_quant = 0;
                        isEnough = true;
                        break;
                    }
                    else if (prod_stock < user_quant) {
                        user_quant = user_quant - prod_stock;
                        prod.setQuantityInStock(0);
                    }
                }
            }
        }
        Product in_Product = new Product(productName, prod_retail, user_want - user_quant);
        invoice.addProductsPurchased(in_Product);
        for (int i = 0; i < warehouseList.size(); i++) {
            dataBase.update_products(i + 1, warehouseList.get(i));
        }
    }

    public int getQuant() {
        int user_quant = -2;
        while (user_quant == -2){
            user_quant = invoiceUI.getProductquant();
        }
        return user_quant;
    }

    public boolean findProduct(int warehouseNumber, String productName) {
        for (Product product : getProducts(warehouseNumber)) {
            if (product.getName().toLowerCase().equals(productName.toLowerCase())) {
               return true;
            }
        }
        return false;
    }


    public ArrayList<Product> getProducts(int warehouseNumber) {
        return dataBase.retrieve_products(warehouseNumber);
    }

    public static void main(String[] args) throws IOException {
        InvoiceController invoiceController = new InvoiceController();
        invoiceController.Icontroller();


      // Invoice invoice1 = new Invoice();
     //  invoiceController.addProductPurchased(invoice1);

        //check product was added
//        System.out.println(invoice1);

    }

}
