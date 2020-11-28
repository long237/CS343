package Invoices;

import java.util.Comparator;

public class OrderDateComparator implements Comparator<Invoice> {
    public int compare(Invoice inv1, Invoice inv2) {
        return inv1.getDateOpened().compareTo(inv2.getDateOpened());
    }
}
