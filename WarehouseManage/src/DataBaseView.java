import Products.Product;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DataBaseView {

    public static void Save_product(ArrayList<Product> list){
        //Product 1         price
        //Prduct 2          price       ...
    }

    public static ArrayList<Product> read_product () {
        ArrayList<Product> pro_list = new ArrayList<Product>();
        //make objects products
        //add to arraylist
        //return arraylist
        return pro_list;
    }

    public static void save_invoice(ArrayList<Invoice> list) {
        // invoice 1 ...
        // incoice 2....

    }

    public static ArrayList<Invoice> read_invoice() {
        ArrayList<Invoice> invoice_list = new ArrayList<Invoice>();
        // invoice 1  index 0
        // invoice 2   index 1
        // invoice 3  index 2

        // recreates invoices
        // recreates products
        // add products objects to Hashset in each invoice
        // add all invoices ot arraylist
        // return arraylist
        return invoice_list;
    }
}
