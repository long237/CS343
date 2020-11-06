
// XXXXX: (fix ASAP!)                (#ff0070)
// KEIRA: (main headers)             (#ff0070)
// kkkkk: (sub-headers)              (#fcb9c5)
// KKKKK: (sub sub-headers)          (#b97474)
//
// TODO: (incomplete)                (#ccff00)
// QUESTION: (wtf did i just do)    (#00b9ff)

public class Product {

    private String mName;
    private int mQuantityAvailable;
    private double mPrice;
    private int mAmountSold;

    public Product() {
        this.mName = "john doe";
        this.mQuantityAvailable = 12;
        this.mPrice = 0.0;
        this.mAmountSold = 1234;
    }

    public Product(String mName, int mQuantityAvailable,
                   double mPrice, int mAmountSold) {
        this.mName = mName;
        this.mQuantityAvailable = mQuantityAvailable;
        this.mPrice = mPrice;
        this.mAmountSold = mAmountSold;
    }

    public String getName() {
        return mName;
    }
    public void setName(String name) { mName = name;  }
    public int getQuantityAvailable() { return mQuantityAvailable; }
    public void setQuantityAvailable(int quantityAvailable) { mQuantityAvailable = quantityAvailable; }
    public double getPrice() { return mPrice; }
    public void setPrice(double price) { mPrice = price; }
    public int getAmountSold() { return mAmountSold; }
    public void setAmountSold(int amountSold) {
        mAmountSold = amountSold;
    }

    public double calculateTotalSales() {
        double totalSales = 0.0;
        return totalSales;
    }
    public double calculateTotalCost() {
        double totalCost = 0.0;
        return totalCost;
    }
    public double calculateTotalProfit() {
        double totalProfit = 0.0;
        return totalProfit;
    }
    public double calculateProfitPercent() {
        double profitPercent = 0.0;
        return profitPercent;
    }
}
