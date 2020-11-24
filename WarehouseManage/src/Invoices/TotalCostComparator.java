package Invoices;

import java.util.Comparator;

public class TotalCostComparator implements Comparator<Invoice> {

    public int compare(Invoice inv1, Invoice inv2) {
        return Double.compare(inv2.getTotalCost(),  inv1.getTotalCost());
    }
}
