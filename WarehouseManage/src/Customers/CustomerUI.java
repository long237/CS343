package Customers;

import Salespeople.Salesperson;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerUI {

    public int customerMenu() {

        System.out.println("Customer Menu: ");
        System.out.println("Option 1 - Edit Customer: ");
        System.out.println("Option 2 - View Customers: ");

        try {
            Scanner in = new Scanner(System.in);
            int choice = in.nextInt();
            in.nextLine();
            return choice;
        } catch (Exception e) {
            System.out.println("Invalid input: Enter the number 1 or 2");
            return -2;
        }
    }

    public int chooseCustomer(ArrayList<Customer> customers) {
        Scanner in = new Scanner(System.in);
        System.out.println("What customer would you like to edit? ");
        viewCustomers(customers);

        try {
            int choice = in.nextInt();
            in.nextLine();
            return choice;
        } catch (Exception e) {
            System.out.println("Invalid Input: Enter a numeric value");
            return -2;
        }
    }

    public int editSalespersonMenu() {

        System.out.println("Option 1 - Edit name: ");
        System.out.println("Option 2 - Edit Tax Rate: ");

        Scanner in = new Scanner(System.in);
        try {
            int choice = in.nextInt();
            in.nextLine();
            return choice;
        } catch (Exception e) {
            System.out.println("Invalid Input: Enter a numeric value between 1 and 2");
            return -2;
        }
    }

    public void editSalespersonName(Customer customer) {

        Scanner in = new Scanner(System.in);
        System.out.println("What is the new name for the customer?: ");
        String newName = in.nextLine();
        customer.setmName(newName);
    }

    public void editTaxRate(Customer customer) {

        Scanner in = new Scanner(System.in);
        System.out.println("What is the new tax rate for the customer?: ");
        double newTaxRate = in.nextDouble();
        customer.setmTaxrate(newTaxRate);

    }

    public void viewCustomers(ArrayList<Customer> customers) {
        //format later
        for (int i = 0; i < customers.size(); i++) {
            System.out.println((i+ 1) + " " + customers.get(i));
        }
    }
}
