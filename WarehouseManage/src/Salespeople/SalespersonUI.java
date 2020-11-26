package Salespeople;

import java.util.ArrayList;
import java.util.Scanner;

public class SalespersonUI {

    public int salespersonMenu() {

        System.out.println("SalesPerson Menu: ");
        System.out.println("Option 1 - Edit salesperson: ");
        System.out.println("Option 2 - View sales people: ");
        System.out.println("(Enter -1 to exit)");

        try {
            Scanner in = new Scanner(System.in);
            int choice = in.nextInt();
            in.nextLine();
            return choice;
        } catch (Exception e) {
            System.out.println("Invalid input: Enter the number 1 or 2. (Enter -1 to exit.)");
            return -2;
        }
    }

    public int chooseSalesperson(ArrayList<Salesperson> salesTeam) {
        Scanner in = new Scanner(System.in);
        System.out.println("What salesperson would you like to edit? ");
        viewSalesperson(salesTeam);

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
        System.out.println("Option 2 - Edit ID: ");
        System.out.println("Option 3 - Edit Commission Rate: ");
        System.out.println("Option 4 - Edit Total Sales: ");
        System.out.println("(Enter -1 to exit)");

        Scanner in = new Scanner(System.in);
        try {
            int choice = in.nextInt();
            in.nextLine();
            return choice;
        } catch (Exception e) {
            System.out.println("Invalid Input: Enter a numeric value between 1 and 4");
            return -2;
        }
    }

    public void editSalespersonName(Salesperson salesperson) {

        Scanner in = new Scanner(System.in);
        System.out.println("What is the new name for the salesperson?: ");
        String newName = in.nextLine();
        salesperson.setSalespersonName(newName);
    }

    public boolean editSalespersonID(Salesperson salesperson) {
        int newID;
        Scanner in = new Scanner(System.in);
        System.out.println("What is the new ID for the salesperson?: ");
        try {
            newID = in.nextInt();
            in.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid Input: Enter a numeric value");
            return false;
        }
        salesperson.setSalespersonID(newID);
        return true;
    }

    public boolean editCommissionRate(Salesperson salesperson) {
        double newCommission;
        Scanner in = new Scanner(System.in);
        System.out.println("What is the new commission rate for the salesperson?: ");
        try {
            newCommission = in.nextDouble();
            in.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid Input: Enter a numeric value");
            return false;
        }
        salesperson.setSalespersonCommission(newCommission);
        return true;

    }

    public boolean editTotalSales(Salesperson salesperson) {
        int newTotalSales;
        Scanner in = new Scanner(System.in);
        System.out.println("What is the new number of total sales for the salesperson?: ");
        try {
            newTotalSales = in.nextInt();
            in.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid Input: Enter a numeric value");
            return false;
        }
        salesperson.setTotalSales(newTotalSales);
        return true;
    }

    public void viewSalesperson(ArrayList<Salesperson> salesTeam) {
        //format later
        for (int i = 0; i < salesTeam.size(); i++) {
            System.out.println((i+ 1) + " " + salesTeam.get(i));
        }
    }

    public void exitValidation() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Press ENTER to return to MAIN MENU: ");
        sc.nextLine();
    }
}
