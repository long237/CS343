package Salespeople;

import java.util.ArrayList;
import java.util.Scanner;

public class SalespersonUI {
    Scanner in = new Scanner(System.in);
    public int salespersonMenu() {
        System.out.println("SalesPerson Menu: ");
        System.out.println("Option 1 - Edit salesperson: ");
        System.out.println("Option 2 - View sales people: ");
        System.out.println("Option 3 - Add salesperson: ");
        System.out.println("(Enter -1 to exit)");

        try {
            int choice = in.nextInt();
            if ((choice < 0 && choice != -1)|| choice > 3) {
                throw new Exception();
            }
            in.nextLine();
            return choice;
        } catch (Exception e) {
            in.nextLine();
            System.out.println("Invalid input: Enter the number 1 or 2. (Enter -1 to exit.)");
            return -2;
        }
    }

    public int chooseSalesperson(ArrayList<Salesperson> salesTeam) {
        viewSalesperson(salesTeam);
        int teamSize = salesTeam.size();
        while(true) {
            try {
                System.out.println("What salesperson would you like to edit? ");
                int choice = in.nextInt();
                if ((choice < 0 && choice != -1) || choice > teamSize) {
                    throw new Exception();
                }
                in.nextLine();
                return choice - 1;
            } catch (Exception e) {
                in.nextLine();
                System.out.println("Invalid Input: Enter a value between 1 and " + teamSize + ". (Enter -1 to exit)");
            }
        }
    }

    public int editSalespersonMenu() {

        System.out.println("Option 1 - Edit name: ");
        System.out.println("Option 2 - Edit ID: ");
        System.out.println("Option 3 - Edit Commission Rate: ");
        System.out.println("Option 4 - Edit Total Sales: ");
        System.out.println("(Enter -1 to exit)");
        while (true) {
            try {
                int choice = in.nextInt();
                if ((choice < 0 && choice != -1) || choice > 4) {
                    throw new Exception();
                }
                in.nextLine();
                return choice;
            } catch (Exception e) {
                in.nextLine();
                System.out.println("Invalid Input: Enter a numeric value between 1 and 4. (Enter -1 to exit)");
            }
        }
    }

    public void editSalespersonName(Salesperson salesperson) {
        System.out.println("What is the new name for the salesperson?: ");
        String newName = in.nextLine();
        salesperson.setSalespersonName(newName);
    }

    public boolean editSalespersonID(Salesperson salesperson) {
        int newID;
        System.out.println("What is the new ID for the salesperson?: ");
        try {
            newID = in.nextInt();
            if (newID < 0) {
                throw new Exception();
            }
            in.nextLine();
        } catch (Exception e) {
            in.nextLine();
            System.out.println("Invalid Input: Enter a positive numeric value");
            return false;
        }
        salesperson.setSalespersonID(newID);
        return true;
    }

    public boolean editCommissionRate(Salesperson salesperson) {
        double newCommission;
        System.out.println("What is the new commission rate for the salesperson?: ");
        try {
            newCommission = in.nextDouble();
            if (Double.toString(newCommission).length() > 4 || newCommission < 0) {
                throw new Exception();
            }
            in.nextLine();
        } catch (Exception e) {
            in.nextLine();
            System.out.println("Invalid Input: Enter a correct dollar value");
            return false;
        }
        salesperson.setSalespersonCommission(newCommission);
        return true;

    }

    public boolean editTotalSales(Salesperson salesperson) {
        int newTotalSales;
        System.out.println("What is the new number of total sales for the salesperson?: ");
        try {
            newTotalSales = in.nextInt();
            in.nextLine();
            if (newTotalSales < 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Invalid Input: Enter a positive integer");
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

    public ArrayList<String> addSalesperson() {
        ArrayList<String> returnList = new ArrayList<>();
        int ID;
        double rate;
        int totalSales;
        System.out.println("What is the new salesperson's Name?");
        String name = in.nextLine();
        returnList.add(name);
        System.out.println("What is the new salesperson's ID?");
        while (true) {
            try {
                ID = in.nextInt();
                if (ID < 0) {
                    throw new Exception();
                }
                in.nextLine();
                break;
            } catch (Exception e) {
                in.nextLine();
                System.out.println("Invalid Input: Enter a positive numeric value");
            }
        }
        returnList.add(Integer.toString(ID));
        System.out.println("What is the new salesperson's commission rate?");
        while (true) {
            try {
                rate = in.nextDouble();
                if (rate < 0 || Double.toString(rate).length() > 4) {
                    throw new Exception();
                }
                in.nextLine();
                break;
            } catch (Exception e) {
                in.nextLine();
                System.out.println("Invalid Input: Enter a positive dollar value");
            }
        }
        returnList.add(Double.toString(rate));
        System.out.println("What is the new salesperson's total sales?");
        while (true) {
            try {
                totalSales = in.nextInt();
                if (totalSales < 0) {
                    throw new Exception();
                }
                in.nextLine();
                break;
            } catch (Exception e) {
                in.nextLine();
                System.out.println("Invalid Input: Enter a positive numeric value");
            }
        }
        returnList.add(Integer.toString(totalSales));
        return returnList;
    }

    public void exitValidation() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Press ENTER to return to MAIN MENU: ");
        sc.nextLine();
    }

    public void salespersonAdded(boolean b, Salesperson sp) {
        if (b) {
            System.out.println("Successfully added " + sp.getSalespersonName());
        }
        else {
            System.out.println("Did not add " + sp.getSalespersonName() + ", the ID \"" + sp.getSalespersonID() + "\" already exists");
        }
    }
}
