import java.util.Scanner;

public class MainUI {

    public void PrintMainMenu() {
        System.out.println("MAIN MENU: \n" +
                "\t 1. Manage Account \n" +
                "\t 2. Manage Salespersons \n" +
                "\t 3. Manage Warehouses \n" +
                "\t 4. Manage Invoices \n" +
                "\t 5. Exit");
    }

    public int getUserOption(){
        int user = -1;
        Scanner scanner = new Scanner(System.in);
        while(user != -1){
            System.out.println("Input an option: ");
            user = scanner.nextInt();
        }
        return user;
    }

}
