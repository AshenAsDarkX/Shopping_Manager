import java.util.HashMap;
import java.util.Objects;

public class Users {
    private HashMap<String, User> users = new HashMap<>();

    public void addUser(String key, User user){
        this.users.put(key, user);
    }
    public void viewUserList(){
        for( HashMap.Entry<String, User> entry : users.entrySet() ){
            System.out.println( entry.getKey() + " = " + entry.getValue() );
        }
    }
    public boolean login(String username,String password) {
        if (users.containsKey(username)) {
            return Objects.equals(password, users.get(username).getPassword());
        }
        else {
            return false;
        }
    }
}
