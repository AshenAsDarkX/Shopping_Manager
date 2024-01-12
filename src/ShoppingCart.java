import java.io.Serializable;

public class ShoppingCart extends ProductList implements Serializable {
//    Initializing custoer cart
    public ShoppingCart(){
    }

//    Checkimg weather buyers first purchase or not
    private boolean madePurchase = false;

//    Setters
    public void setMadePurchase(boolean madePurchase) {
    this.madePurchase = madePurchase;
}

//    Getters
    public int getItemQuantity(String proId){
        return super.getProObject(proId).getQuantity();
    }

//    Checking for the first purchase
    public boolean hasMadePurchase(){
        return madePurchase;
    }

//    checking for if the user buys same catogary item three times

    public boolean hasMadeThreePurchasesInSameCategory(String category) {
        int count = 0;
        for (Product product : getProList().values()) {
            if (product instanceof Electronics && category.equals("Electronics")) {
                count++;
            } else if (product instanceof Clothes && category.equals("Clothes")) {
                count++;
            }
        }
        return count >= 3;
    }
}
