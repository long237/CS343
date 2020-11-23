import Invoices.InvoiceController;
import Warehouses.WarehouseController;
import Warehouses.WarehouseUI;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Add invoice    1
        // Add product      2
        // Edit Invoices.Invoice     3
        InvoiceController.Icontroller();

        int user_input = 0;
        if (user_input == 1) {
            InvoiceController.Icontroller();
        }
        else if(user_input == 2) {
            WarehouseController.warehouseController();
        }

        // keira: (11/22) ----------------------------------------------------------------------------------------------
        Scanner in = new Scanner(System.in);
        System.out.println("MAIN MENU: \n" +
                           "\t 1. Manage Account \n" +
                           "\t 2. Manage Salespersons \n" +
                           "\t 3. Manage Warehouses \n" +
                           "\t 4. Manage Invoices \n" +
                           "\t 5. Exit");
        int menuOption = in.nextInt();

        if (menuOption == 3) {
            // REVIEW: (cannot call WarehouseUI methods)
            //selectWarehouseNumber();
        }
        // keira: (END of 11/22) ---------------------------------------------------------------------------------------

    }
}
