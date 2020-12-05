import Customers.Customer;
import Database.Database;
import Invoices.InvoiceController;
import Warehouses.WarehouseController;
import Salespeople.SalespersonController;
import Customers.CustomerController;

import java.io.IOException;
import java.util.Scanner;

public class WarehouseManage {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        Database dataBase = new Database();
        WarehouseController warehouseController = new WarehouseController();
        SalespersonController sp = new SalespersonController();
        CustomerController customerController = new CustomerController();
        InvoiceController invoiceController = new InvoiceController();
        //Todo: make it possible to return to in change password
        // Add invoice    1
        // Add product      2
        // Edit Invoices.Invoice     3
        //InvoiceController.Icontroller();

        WarehouseManageUI warehouseManageUI = new WarehouseManageUI();

        System.out.println("Enter your password: ");
        String user_attempt = in.nextLine();
        String user_pass = dataBase.retrievePass();
        while (!user_attempt.equals(user_pass)) {
            System.out.println("Incorrect Password: Please try again: ");
            user_attempt = in.nextLine();
        }
        System.out.println("Success!");

        warehouseManageUI.PrintMainMenu();
        int user_input = warehouseManageUI.getUserOption();
        while (user_input != -1) {

            if (user_input == 1) {
                String new_pass = warehouseManageUI.ChangePasswordUI();
                dataBase.updatePass(new_pass);
            }
            else if (user_input == 2){
                sp.salespersonController();
            }
            else if (user_input == 3)
            {
                warehouseController.warehouseController();
            }
            else if (user_input == 4) {
                invoiceController.Icontroller();
            }
            else if (user_input == 5){
                customerController.customerController();
            }
            warehouseManageUI.PrintMainMenu();
            user_input = warehouseManageUI.getUserOption();
        }


//        // keira: (11/22) ----------------------------------------------------------------------------------------------
//        Scanner in = new Scanner(System.in);
//        System.out.println("MAIN MENU: \n" +
//                           "\t 1. Manage Account \n" +
//                           "\t 2. Manage Salespersons \n" +
//                           "\t 3. Manage Warehouses \n" +
//                           "\t 4. Manage Invoices \n" +
//                           "\t 5. Exit");
//        int menuOption = in.nextInt();
//
//        if (menuOption == 3) {
//            selectWarehouseNumber();
//        }
//        // keira: (END of 11/22) ---------------------------------------------------------------------------------------

    }


}
