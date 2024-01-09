import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductList {
private HashMap<String, Product> proList = new HashMap<>();

    public HashMap<String, Product> getProList() {
        return proList;
    }

    public void setProList(HashMap<String, Product> proList) {
        this.proList = proList;
    }

    public void addProduct(String key, Product product){
        this.proList.put(key, product);
    }


    public void viewProList(){
        for( HashMap.Entry<String, Product> entry : proList.entrySet() ){
            System.out.println( entry.getKey() + " = " + entry.getValue() );
        }
    }

    public void delProduct(String key){
        this.proList.remove(key);
    }
    
    public void getElectronics(){
            Map<String, Product> electronicsMap = new HashMap<>();
            for (Map.Entry<String, Product> entry : this.proList.entrySet()) {
                if (entry.getValue() instanceof Electronics) {
                    electronicsMap.put(entry.getKey(), (Electronics) entry.getValue());
                }
            }
        System.out.println("Electronics:");
        for (Map.Entry<String, Product> entry : electronicsMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getProductName());
        }
        }

    public void getClothes(){
        Map<String, Product> clothesMap = new HashMap<>();
        for (Map.Entry<String, Product> entry : this.proList.entrySet()) {
            if (entry.getValue() instanceof Clothes) {
                clothesMap.put(entry.getKey(), (Clothes) entry.getValue());
            }
        }
        System.out.println("Electronics:");
        for (Map.Entry<String, Product> entry : clothesMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getProductName());
        }
    }
    @Override
    public String toString() {
        return "ProductList" +
                "proList=" + proList;
    }
}
