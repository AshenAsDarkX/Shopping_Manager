import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public class Users implements Serializable {
    private final HashMap<String, User> users = new HashMap<>();

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



    public User getUser(String username) {
        return users.get(username);
    }

    public boolean keyAvailability(String key){
       return users.containsKey(key);
    }

    public void copyFrom(Users users) {
        // Clear existing data
        this.users.clear();

        // Copy data from the other Users object
        this.users.putAll(users.users);
    }
    public void clear() {
        users.clear();
    }
}
