package Salespeople;
import Database.Database;
import java.util.ArrayList;
public class SalespersonController {
    Database db = new Database();
    SalespersonUI ui = new SalespersonUI();
    public void salespersonController() {
        int firstChoice = 0;
        while (firstChoice != -1) {
            firstChoice = ui.salespersonMenu();
            if (firstChoice == 1) {
                ArrayList<Salesperson> salespersonList = new ArrayList<>(db.retrieve_salesPerson());
                int personNumber = ui.chooseSalesperson(salespersonList);
                int editChoice = ui.editSalespersonMenu();

                //name
                if (editChoice == 1) {
                    ui.editSalespersonName(salespersonList.get(personNumber));
                    db.update_Saleperson(salespersonList);
                }

                //ID
                else if (editChoice == 2) {
                    boolean flag = false;
                    while (!flag) {
                        flag = ui.editSalespersonID(salespersonList.get(personNumber));
                        db.update_Saleperson(salespersonList);
                    }
                }

                //Commission Rate
                else if (editChoice == 3) {
                    boolean flag = false;
                    while (!flag) {
                        flag = ui.editCommissionRate(salespersonList.get(personNumber));
                        db.update_Saleperson(salespersonList);
                    }
                }

                //Total Sales
                else if (editChoice == 4) {
                    boolean flag = false;
                    while (!flag) {
                        flag = ui.editTotalSales(salespersonList.get(personNumber));
                        db.update_Saleperson(salespersonList);
                    }
                }

            }
            else if (firstChoice == 2) {
                ui.viewSalesperson(db.retrieve_salesPerson());
                ui.exitValidation();
            }
            else if (firstChoice == 3) {
                ArrayList<Salesperson> salespersonList = new ArrayList<>(db.retrieve_salesPerson());
                ArrayList<String> inputsToAdd = ui.addSalesperson();
                Salesperson sp = new Salesperson(inputsToAdd.get(0), Integer.parseInt(inputsToAdd.get(1)),
                        Double.parseDouble(inputsToAdd.get(2)), Integer.parseInt(inputsToAdd.get(3)));
                if (!db.check_ID_Exists(sp)) {
                    db.add_Salesperson(sp, salespersonList);
                    ui.salespersonAdded(true, sp);
                }
                else {
                    ui.salespersonAdded(false, sp);
                }
            }
        }
    }
}
