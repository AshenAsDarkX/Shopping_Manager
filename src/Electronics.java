import java.io.Serializable;

public class Electronics extends Product implements Serializable {
//    Initializing variables
    private String eleBrand;
    private int warrPeriod;

//    Initializing constructors

    public Electronics(String proId, String proName, double price,int quantity, String eleBrand, int warrPeriod) {
        super(proId, proName, price, quantity);
        this.eleBrand = eleBrand;
        this.warrPeriod = warrPeriod;
    }

//    Getteras
    public String getEleBrand() {
        return eleBrand;
    }
    public int getWarrPeriod() {
        return warrPeriod;
    }

//    Setters
    public void setEleBrand(String eleBrand) {
        this.eleBrand = eleBrand;
    }

    public void setWarrPeriod(int warrPeriod) {
        this.warrPeriod = warrPeriod;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Electronics{" +
                "eleBrand='" + eleBrand + '\'' +
                ", warrPeriod='" + warrPeriod + '\'' +
                '}';
    }
}
