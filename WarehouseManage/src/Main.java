import Invoices.InvoiceController;
import Warehouses.WarehouseController;

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
            WarehouseController.Wcontroller();
        }
    }
}
