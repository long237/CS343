import Customers.Customer;
import Invoices.InvoiceController;
import Warehouses.WarehouseController;
import Salespeople.SalespersonController;
import Customers.CustomerController;
import java.util.Scanner;

public class WarehouseManage {

    public static void main(String[] args) {
        WarehouseController warehouseController = new WarehouseController();
        SalespersonController sp = new SalespersonController();
        CustomerController cc = new CustomerController();
        //InvoiceController invoiceController = new InvoiceController();

        // Add invoice    1
        // Add product      2
        // Edit Invoices.Invoice     3
        //InvoiceController.Icontroller();

        WarehouseManageUI warehouseManageUI = new WarehouseManageUI();
        warehouseManageUI.PrintMainMenu();
        int user_input = warehouseManageUI.getUserOption();


        //while loop
//        if (user_input == 1) {
//            invoiceController.Icontroller();
//        }

        if (user_input == 2) {
            sp.salespersonController();
        }

        if(user_input == 3) {
            warehouseController.warehouseController();
        }

        if(user_input == 5) {
            cc.customerController();
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
