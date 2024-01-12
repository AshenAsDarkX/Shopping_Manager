import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public abstract class ProductList implements Serializable {
//    Initializing hashmap for store product
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


//    Getters
    public List<String[]> getProductDetailsList() {
        List<String[]> productDetailsList = new ArrayList<>();

        for (Map.Entry<String, Product> entry : this.proList.entrySet()) {
            String productId = entry.getKey();
            String productName = entry.getValue().getProductName();
            int quantity = entry.getValue().getQuantity();
            double price = entry.getValue().getProPrice();
            String type = (entry.getValue() instanceof Electronics) ? "Electronics" : "Clothes";

            String[] rowData = {productId, productName, String.valueOf(quantity), String.valueOf(price), type};
            productDetailsList.add(rowData);
        }

        return productDetailsList;
    }
    public void getElectronics(){
        Map<String, Product> electronicsMap = new HashMap<>();
        for (Map.Entry<String, Product> entry : this.proList.entrySet()) {
            if (entry.getValue() instanceof Electronics) {
                electronicsMap.put(entry.getKey(), entry.getValue());
        }
    }
    System.out.println("Electronics:");
    for (Map.Entry<String, Product> entry : electronicsMap.entrySet()) {
        System.out.println(entry.getKey() + ": " + entry.getValue().getProductName());
    }
}


    public Product getProObject(String objKey){
        return proList.get(objKey);
    }
    public void getClothes(){
        Map<String, Product> clothesMap = new HashMap<>();
        for (Map.Entry<String, Product> entry : this.proList.entrySet()) {
            if (entry.getValue() instanceof Clothes) {
                clothesMap.put(entry.getKey(), entry.getValue());
            }
        }
        System.out.println("Electronics:");
        for (Map.Entry<String, Product> entry : clothesMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getProductName());
        }
    }
//    Method for view products
    public void viewProList() {
        System.out.printf("%5s | %15s | %15s | %15s | %15s\n", "Product Id", "Product name", "Quantity", "Price", "Type");

        // Create a sorted clone of proList based on product name
        Map<String, Product> sortedList = proList.entrySet()
                .stream()
                .sorted(Comparator.comparing(entry -> entry.getValue().getProductId()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        HashMap::new
                ));

        // Print the sorted list
        for (Map.Entry<String, Product> entry : sortedList.entrySet()) {
            if (entry.getValue() instanceof Electronics) {
                System.out.printf("%5s | %15s | %15s | %15s | %15s\n", entry.getKey(), entry.getValue().getProductName(), entry.getValue().getQuantity(), entry.getValue().getProPrice(), "Electronics");
            }
            if (entry.getValue() instanceof Clothes) {
                System.out.printf("%5s | %15s | %15s | %15s | %15s\n", entry.getKey(), entry.getValue().getProductName(), entry.getValue().getQuantity(), entry.getValue().getProPrice(), "Clothes");
            }
        }
    }

//    Method for deleting product
    public void delProduct(String key){
        System.out.println(this.proList.get(key).getProductName()+" is removed!");
        this.proList.remove(key);
    }


//    Method for get the mapsize
    public int mapSize(){
        return proList.size();
    }
    public boolean keyAvailability(String key){
        return !proList.containsKey(key);
    }



//    Method for Create empty list
    public void copyFrom(Stock stock){
        this.proList.clear();
        this.proList.putAll(stock.getProList());
    }
    public void clear() {
        proList.clear();
    }


    @Override
    public String toString() {
        return "ProductList" +
                "proList=" + proList;
    }
}
