package Products;

import java.util.HashSet;
import java.util.TreeSet;
import java.util.Set;

public class ProductTester {
    public static void main(String[] args)
    {
        // KEIRA: ( constructors & toString() ) ------------------------------------------------------------------------

        // QUESTION: (Need to replace HashSet w/ TreeSet & implement Comparator
        //  to Sort Products Alphabetically in Future)
        Set<Product> products = new HashSet<Product>();

        Product jelly_patty = new Product("jelly patty", 15.00, 5.00, 50);
        Product kerglooginpfiefer = new Product("kerglooginpfiefer", 10.00, 5.00, 20);
        Product seanut_brittle = new Product("seanut brittle", 4.00, 0.50, 20);
        Product barnacle_chips = new Product("barnacle chips", 1.00, 0.50, 20);

        products.add(jelly_patty);
        products.add(kerglooginpfiefer);
        products.add(seanut_brittle);
        products.add(barnacle_chips);

        System.out.println("\n ...PRODUCTs TABLE... \n");
        System.out.println(getProductsTable(products));

        // KEIRA: ( methods ) ------------------------------------------------------------------------------------------
        System.out.println("\n ...PRODUCT methods at work... \n");
        System.out.println("   " + getProductsTableHeader());

        // KKKKK: 1) A new product is added to the warehouse...
        Product krabby_patty = new Product("krabby patty", 25.00, 10.00, 30);
        System.out.println("1) " + krabby_patty.toString());

        // KKKKK: 2) 5 customers each order 5 krabby_patty's...
        String num = "2) ";
        for (int i = 0; i < 5; i++) {
            krabby_patty.addQuantitySold(5);
            System.out.println(num + krabby_patty.toString());
            num = "   ";
        }

        // KKKKK: 3) When the warehouse is low in stock on krabby_patty's,
        //    the Store Owner will order more krabby_patty's from the Krusty Krab...
        if (krabby_patty.isLowStock()) {
            krabby_patty.addQuantityInStock(30);
        }
        System.out.println("3) " + krabby_patty.toString());

        // KKKKK: 4) 5 customers each order 5 krabby_patty's...
        num = "4) ";
        for (int i = 0; i < 5; i++) {
            krabby_patty.addQuantitySold(5);
            System.out.println(num + krabby_patty.toString());
            num = "   ";
        }
    }

    // KEIRA: ( PRODUCTs TABLE methods ) -------------------------------------------------------------------------------
    public static String getProductsTableHeader() {
        return String.format("%-20s %15s %15s %15s %10s %18s %18s %18s %18s",
                "PRODUCT-NAME", "RETAIL-PRICE", "COST",
                "#-IN-STOCK", "#-SOLD", "TOTAL-SALES",
                "TOTAL-COST", "TOTAL-PROFIT", "TOTAL-PROFIT-%");
    }
    public static String getProductsTable(Set<Product> products) {
        StringBuilder productsTable = new StringBuilder();
        String header = getProductsTableHeader();
        productsTable.append(header);
        productsTable.append("\n");
        for (Product product : products) {
            productsTable.append(product.toString());
            productsTable.append("\n");
        }
        return productsTable.toString();
    }
}
