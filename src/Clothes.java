import java.util.HashMap;
import java.util.Map;

public class Clothes extends Product {
    private String size;
    private String color;

    public Clothes(String proId, String proName, int numOfAva, double price, String size) {
        this.size = size;
    }

    public Clothes(String proId, String proName, double price,int quantity, String size, String color) {
        super(proId, proName, price, quantity);
        this.size = size;
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
