public class Cart {
    private Product[] productInCart;
    private int[] amountOfEach;
    private int currentPrice;

    // Cart const
    public Cart() {
        currentPrice = 0;
        amountOfEach = new int[0];
        productInCart = new Product[0];
    }

    // the function added new product to the cart
    public void addProduct (Product p, int amount){
        Product[] newP = new Product[productInCart.length + 1];
        int[] newAmountOfEach = new int[amountOfEach.length + 1];
        for (int i = 0; i < productInCart.length; i++) {
            newP[i] = productInCart[i];
            newAmountOfEach[i] = amountOfEach[i];
        }
        newAmountOfEach[newAmountOfEach.length-1] = amount;
        newP[newP.length-1] = p;
        this.productInCart = newP;
        this.amountOfEach = newAmountOfEach;
        this.currentPrice = this.currentPrice + (amount*p.getPrice());
    }
    // the function print all items in the cart
    public void printCart(){
        for (int i = 0; i < productInCart.length; i++) {
            System.out.println(productInCart[i]+" "+amountOfEach[i]*productInCart[i].getPrice());
        }
    }
    // the function return the price
    public int getCurrentPrice() {
        return currentPrice;
    }
}