import java.util.Scanner;

public class WarehouseManageUI {

    public void PrintMainMenu() {
        System.out.print("MAIN MENU: \n" +
                "\t 1. Manage Account \n" +
                "\t 2. Manage Salespersons \n" +
                "\t 3. Manage Warehouses \n" +
                "\t 4. Manage Invoices \n" +
                "\t 5. Manage Customers \n");
    }

    public int getUserOption(){
        int user = 0;
        Scanner scanner = new Scanner(System.in);
        while(user != -1){
            System.out.print("Select a menu option (Enter (-1) to quit): ");
            user = scanner.nextInt();
            scanner.nextLine();
            return user;
        }
        return user;
    }

    public String ChangePasswordUI() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a new password: ");
        return in.nextLine();

    }

}
