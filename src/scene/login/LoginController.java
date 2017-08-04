package scene.login;

import resources.database.DB;
import resources.database.UserDAO;
import scene.login.entity.User;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;

/**
 * Created by Liu Woon Kit on 5/7/2017.
 */
public class LoginController {
    public boolean verifyUser(String userID, String password) {
        return User.verifyUser(userID, password);
    }
}