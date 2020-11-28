package Customers;
import Database.Database;
import java.util.ArrayList;

public class CustomerController {
    CustomerUI ui = new CustomerUI();
    Database db = new Database();
    public void customerController() {
        int firstMenuChoice = 0;
        while (firstMenuChoice != -1) {
            ArrayList<Customer> customers = new ArrayList<>(db.retrieve_Customer());
            firstMenuChoice = ui.customerMenu();
            if (firstMenuChoice == 1) {
                int editChoice = 0;
                while (editChoice != -1) {
                    int whichCustomer = ui.chooseCustomer(customers) - 1;
                    if (whichCustomer != -2) {
                        editChoice = ui.editCustomerMenu();
                        if (editChoice == 1) {
                            ui.editCustomerName(customers.get(whichCustomer));
                        } else if (editChoice == 2) {
                            ui.editTaxRate(customers.get(whichCustomer));
                        }
                    }
                    else {
                        editChoice = -1;
                    }
                }
            }
            else if (firstMenuChoice == 2) {
                ui.viewCustomers(customers);
                ui.exitValidation();
            }
            else if (firstMenuChoice == 3) {
                ArrayList<String> newCustomer = new ArrayList<>(ui.addCustomer());
                if (!newCustomer.isEmpty()) {
                    Customer c = new Customer(newCustomer.get(0), Double.parseDouble(newCustomer.get(1)));
                    db.addCustomer(c, customers);
                    ui.addSuccessMessage(c);
                }
            }
        }
    }
}
