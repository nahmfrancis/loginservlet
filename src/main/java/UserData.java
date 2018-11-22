import java.util.HashMap;
import java.util.Map;

public class UserData {
    Map<String, UserEntity> users = new HashMap<>();

    public UserData() {
        users.put("francis", new UserEntity("francis", "123"));
        users.put("festus", new UserEntity("festus", "123"));
    }

    public Map<String, UserEntity> getUsers() {
        return users;
    }

}
