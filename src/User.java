import java.io.Serializable;

public class User implements Serializable {
//    Initializing variables
    private String userName;
    private String password;

    private final ShoppingCart cart = new ShoppingCart();

//    Constructors
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }


//Getters
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
    public int getItemQuantity(String proId){
        return cart.getItemQuantity(proId);
    }

//Setters
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
