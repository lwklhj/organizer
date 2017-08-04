package resources.database;

import scene.login.entity.User;

/**
 * Created by Liu Woon Kit on 5/7/2017.
 */
public class UserDAO {
    // This class stores user object
    private static User user;

    public UserDAO() {
    }

    public UserDAO(User user) {
    this.user = user;
}

    public static User getUser() {
        return user;
    }

    public void logout() {
        user = null;
    }
}