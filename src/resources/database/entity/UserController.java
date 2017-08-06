package resources.database.entity;

/**
 * Created by hhf on 8/5/17.
 */
public class UserController {
    public static User getUserInformation(String id){
            return User.getUserInfomation(id);
    }
    public static String[] getAllUser(){return User.getAllUser();}
}
