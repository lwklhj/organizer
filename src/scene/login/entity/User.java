package scene.login.entity;

import resources.database.DB;
import resources.database.UserDAO;

import javax.sql.rowset.CachedRowSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.GregorianCalendar;

/**
 * Created by Liu Woon Kit on 12/6/2017.
 */
public class User {
    private String userID, name;
    private GregorianCalendar birthDate;
    private String email;
    private int hpNumber;
    private char gender;

    public User() {

    }

    public User(String userID, String name, Date birthDate, String email, int hpNumber, char gender) {
        this.userID = userID;
        this.name = name;
        this.birthDate = new GregorianCalendar();
        this.birthDate.setTime(birthDate);
        this.email = email;
        this.hpNumber = hpNumber;
        this.gender = gender;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GregorianCalendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(GregorianCalendar birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getHpNumber() {
        return hpNumber;
    }

    public void setHpNumber(int hpNumber) {
        this.hpNumber = hpNumber;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public static boolean verifyUser(String userID, String password) {
        CachedRowSet rs = DB.read("SELECT userID, name, birthDate, email, hpNumber, gender FROM User WHERE userID='" + userID + "' && password='" + password + "'");

        if(rs.size() == 0)
            return false;

        try {
            if(rs.next()) {
                new UserDAO(new User(rs.getString("userID"), rs.getString("name"), rs.getDate("birthDate"), rs.getString("email"), rs.getInt("hpNumber"), (rs.getString("gender")).charAt(0)));
            }
        } catch (SQLException e) {e.printStackTrace();}

        return true;
    }
}