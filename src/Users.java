import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public class Users implements Serializable { //Serializable for save the object
//    Initializing a Hashmap to store users
    private final HashMap<String, User> users = new HashMap<>();
//    Method for add users to the map

    public void addUser(String key, User user){
        this.users.put(key, user);
    }
    public boolean login(String username,String password) {
        if (users.containsKey(username)) {
            return Objects.equals(password, users.get(username).getPassword());
        }
        else {
            return false;
        }
    }

//Getters

    public User getUser(String username) {
        return users.get(username);
    }

    public boolean keyAvailability(String key){
       return users.containsKey(key);
    }

//    Method used for loading data
    public void copyFrom(Users users) {
        // Clear existing data
        this.users.clear();

        // Copy data from the other Users object
        this.users.putAll(users.users);
    }
//    Method for clear hashmap when copiying another object
    public void clear() {
        users.clear();
    }
}
