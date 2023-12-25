import java.lang.reflect.Constructor;

public abstract class Product {

//    Initialize variables
    private String productId;
    private String productName;
    private int numOfAvaItms;
    private double proPrice;
    private int quantity;


//    Default Constructor
public Product(){

    }
//    Constructors
public Product(String proId){
    this.productId = proId;
}
    public Product(String proId, String proName){
        this.productId = proId;
        this.productName = proName;
    }
    public Product(String proId, String proName, int numOfAva){
        this.productId = proId;
        this.productName = proName;
        this.numOfAvaItms = numOfAva;
    }
    public Product(String proId, String proName, double price,int quantity){
        this.productId = proId;
        this.productName = proName;
        this.proPrice = price;
        this.quantity = quantity;
    }

//    Setters
    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setNumOfAvaItms(int numOfAvaItms) {
        this.numOfAvaItms = numOfAvaItms;
    }

    public void setPrice(double price) {
        this.proPrice = price;
    }

//    Getters
    public String getProductId(){
        return productId;
    }
    public String getProductName(){
        return productName;
    }
    public int getNumOfAvaItms(){
        return numOfAvaItms;
    }
    public double getProPrice(){
    return proPrice;
    }

    @Override
    public String toString() {
        return "Product" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", numOfAvaItms=" + numOfAvaItms +
                ", proPrice=" + proPrice;
    }
}
