import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String password;

    private final ShoppingCart cart = new ShoppingCart();

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getItemQuantity(String proId){
        return cart.getItemQuantity(proId);
    }
}
