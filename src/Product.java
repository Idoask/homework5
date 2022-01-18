public class Product {
    private String name;
    private int price;
    private int discount;
    private boolean isInInventory;

    // product const
    public Product(String name, int price, int discount) {
        this.name = name;
        this.price = price;
        this.isInInventory = true;
        this.discount = discount;
    }


    @Override
    public String toString() {
        return "product name: "+name;
    }

    // getter for price
    public int getPrice() {
        return price;
    }

    // getter for name
    public String getName() {
        return name;
    }

    // getter for is the item in inventory
    public boolean isInInventory() {
        return isInInventory;
    }

    // setter for item in inventory
    public void setInInventory(boolean inInventory) {
        isInInventory = inInventory;
    }
}
