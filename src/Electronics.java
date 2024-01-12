import java.io.Serializable;

public class Electronics extends Product implements Serializable {
    private String eleBrand;
    private int warrPeriod;

    public Electronics() {
    }
    public Electronics(String eleBrand) {
        this.eleBrand = eleBrand;
    }
    public Electronics(String eleBrand, int warrPeriod) {
        this.eleBrand = eleBrand;
        this.warrPeriod = warrPeriod;
    }

    public Electronics(String proId, String proName, double price,int quantity, String eleBrand, int warrPeriod) {
        super(proId, proName, price, quantity);
        this.eleBrand = eleBrand;
        this.warrPeriod = warrPeriod;
    }

    public String getEleBrand() {
        return eleBrand;
    }

    public void setEleBrand(String eleBrand) {
        this.eleBrand = eleBrand;
    }

    public int getWarrPeriod() {
        return warrPeriod;
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
