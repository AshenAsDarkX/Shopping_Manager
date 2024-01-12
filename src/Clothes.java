import java.io.Serializable;

public class Clothes extends Product implements Serializable {
//    Initializing variables
    private String size;
    private String color;


//    Initializing constructor
    public Clothes(String proId, String proName, double price,int quantity, String size, String color) {
        super(proId, proName, price, quantity);
        this.size = size;
        this.color = color;
    }
//    Getters

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

//    Setters
    public void setSize(String size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
