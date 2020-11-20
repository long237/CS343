package Warehouses;
import Products.Product;
import java.util.HashSet;

public class Warehouse {
    private int mWarehouseNumber;
    private HashSet<Product> mWarehouseProducts;

    public int getWarehouseNumber() { return mWarehouseNumber; }

    public void setWarehouseNumber(int mWarehouseNumber) { this.mWarehouseNumber = mWarehouseNumber; }

    public HashSet<Product> getProducts() { return mWarehouseProducts; }

    public void addWarehouseProducts(Product p) {
        mWarehouseProducts.add(p);
    }
}
