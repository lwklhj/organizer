package resources.database;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

class test{
    public static void main(String[] args) {
        DB db = new DB();
        CachedRowSet cachedRowSet = db.read("SELECT * FROM User WHERE userID = '123456A' ");
        try {
            while(cachedRowSet.next()) {
                System.out.print(cachedRowSet.getString("password") + " " + cachedRowSet.getString("name") + " " + cachedRowSet.getInt("hpNumber"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}