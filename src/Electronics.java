public class Electronics extends Product {
    private String eleBrand;
    private String warrPeriod;

    public Electronics() {
    }
    public Electronics(String eleBrand) {
        this.eleBrand = eleBrand;
    }
    public Electronics(String eleBrand, String warrPeriod) {
        this.eleBrand = eleBrand;
        this.warrPeriod = warrPeriod;
    }

    public Electronics(String proId, String proName, double price,int quantity, String eleBrand, String warrPeriod) {
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

    public String getWarrPeriod() {
        return warrPeriod;
    }

    public void setWarrPeriod(String warrPeriod) {
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
