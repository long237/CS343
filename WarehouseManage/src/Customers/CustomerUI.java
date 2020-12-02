package Customers;

import Salespeople.Salesperson;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerUI {
    Scanner in = new Scanner(System.in);
    public int customerMenu() {

        System.out.println("Customer Menu: ");
        System.out.println("Option 1 - Edit Customer: ");
        System.out.println("Option 2 - View Customers: ");
        System.out.println("Option 3 - Add Customer: ");
        System.out.println("Enter -1 to exit");

        try {
            int choice = in.nextInt();
            in.nextLine();
            return choice;
        } catch (Exception e) {
            System.out.println("Invalid input: Enter the number 1 or 2");
            return -2;
        }
    }

    public int chooseCustomer(ArrayList<Customer> customers) {
        viewCustomers(customers);
        int customerSize = customers.size();
        while (true) {
            try {
                System.out.println("What customer would you like to edit? (Enter -1 to exit)");
                int choice = in.nextInt();
                if ((choice <= 0 && choice != -1) || choice > customerSize) {
                    throw new Exception();
                }
                in.nextLine();
                return choice;
            } catch (Exception e) {
                in.nextLine();
                System.out.println("Invalid Input: Enter a value between 1 and " + customerSize + ". (Enter -1 to exit)");
            }
        }
    }

    public int editCustomerMenu() {

        System.out.println("Option 1 - Edit name: ");
        System.out.println("Option 2 - Edit Tax Rate: ");
        System.out.println("(Enter -1 to exit)");

        while (true) {
            try {
                int choice = in.nextInt();
                if ((choice <= 0 && choice != -1) || choice > 2) {
                    throw new Exception();
                }
                in.nextLine();
                return choice;
            } catch (Exception e) {
                in.nextLine();
                System.out.println("Invalid Input: Enter a numeric value between 1 and 2. (Enter -1 to exit)");
            }
        }
    }

    public void editCustomerName(Customer customer) {
        System.out.println("What is the new name for the customer?: ");
        String newName = in.nextLine();
        customer.setmName(newName);
    }

    public void editTaxRate(Customer customer) {
        double newTaxRate;
        System.out.println("What is the new tax rate for the customer?: ");
        while (true) {
            try {
                newTaxRate = in.nextDouble();
                DecimalFormat df = new DecimalFormat("#.00");
                newTaxRate = Double.parseDouble(df.format(newTaxRate));
                if (newTaxRate < 0 && newTaxRate != -1) {
                    throw new Exception();
                }
                in.nextLine();
                break;
            } catch (Exception e) {
                in.nextLine();
                System.out.println("Invalid input. Please enter a positive dollar value. (Enter -1 to exit)");
            }
        }
        if (newTaxRate != -1) {
            customer.setmTaxrate(newTaxRate);
        }
    }

    public void viewCustomers(ArrayList<Customer> customers) {
        //format later
        for (int i = 0; i < customers.size(); i++) {
            System.out.println((i+ 1) + " " + customers.get(i));
        }
    }

    public ArrayList<String> addCustomer() {
        ArrayList<String> returnList = new ArrayList<>();
        String nameToAdd;
        double rateToAdd;
        System.out.println("What is the customer's name? (Enter -1 to exit)");
        nameToAdd = in.nextLine();
        if (nameToAdd.equals("-1")) {
            returnList.clear();
            return returnList;
        }
        returnList.add(nameToAdd);
        System.out.println("What is the customer's tax rate? (Enter -1 to exit)");
        while (true) {
            try {
                rateToAdd = in.nextDouble();
                DecimalFormat df = new DecimalFormat("#.00");
                rateToAdd = Double.parseDouble(df.format(rateToAdd));
                if ((rateToAdd < 0 && rateToAdd != -1)) {
                    throw new Exception();
                }
                in.nextLine();
                if (rateToAdd == -1) {
                    returnList.clear();
                    return returnList;
                }
                returnList.add(Double.toString(rateToAdd));
                break;
            } catch (Exception e) {
                in.nextLine();
                System.out.println("Invalid input. Please enter a positive value.");
            }
        }
        return returnList;
    }

    public void addSuccessMessage(Customer c) {
        System.out.println("Customer \"" + c.getmName() + "\" added with tax rate of " + c.getmTaxrate() + "%.");
    }

    public void exitValidation() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Press ENTER to return to MAIN MENU: ");
        sc.nextLine();
    }
}
