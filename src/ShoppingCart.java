import java.io.Serializable;

public class ShoppingCart extends ProductList implements Serializable {
    public ShoppingCart(){
    }

    private boolean madePurchase = false;
    public int getItemQuantity(String proId){
        return super.getProObject(proId).getQuantity();
    }

    public boolean hasMadePurchase(){
        return madePurchase;
    }
    public void setMadePurchase(boolean madePurchase) {
        this.madePurchase = madePurchase;
    }


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
