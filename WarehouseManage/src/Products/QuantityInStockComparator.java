package Products;
import java.util.Comparator;

public class QuantityInStockComparator implements Comparator<Product> {

    /*
    0: if (x==y)
    -1: if (x < y)
    1: if (x > y)
    */
    @Override
    public int compare(Product product_one, Product product_two) {
        return Integer.compare(product_one.getQuantityInStock(), product_two.getQuantityInStock());
    }

}
