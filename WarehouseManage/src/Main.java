import Warehouses.WarehouseUI;
import java.util.Scanner;

// REVIEW: (can only "extend" 1 class BUT can "implement" 1+ classes
//  Need to make interfaces?)
public class Main extends WarehouseUI {

    public static void main(String[] args) {
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
            selectWarehouseNumber();
        }

    }
}
