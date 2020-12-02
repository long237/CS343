package Products;
import java.util.Comparator;
public class ProfitPercentComparator implements Comparator<Product> {
    /*
    0: if (x==y)
    -1: if (x < y)
    1: if (x > y)
    */
    @Override
    public int compare(Product p1, Product p2) { return Double.compare (p2.getTotalProfitPercent(), p1.getTotalProfitPercent()); }
}
