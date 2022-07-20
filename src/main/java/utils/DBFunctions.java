package utils;

import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ArrayList;

public class DBFunctions {
    public static HashMap<String, String> singleKeyValueToJson(String key, String value) {
        HashMap<String, String> single = new HashMap<>();
        single.put(key, value);
        return single;
    }

    public static void storeUser(String first_name, String last_name, String email, String tlf, String superuser, String password1) throws Exception {
        PreparedStatement ps;
        Connection db;
        String salt = SecureUtils.makeSalt();
        String hash = SecureUtils.makePassword(password1, salt);
        db = DBUtils.getINSTANCE().getConnection();
        String query = "INSERT INTO user (first_name, last_name, email, tlf, password, is_superuser, salt) values (?,?,?,?,?,?,?)";
        ps = db.prepareStatement(query);

        ps.setString(1, first_name);
        ps.setString(2, last_name);
        ps.setString(3, email);
        ps.setString(4, tlf);
        ps.setString(5, hash);
        ps.setBoolean(6, superuser.equals("on"));
        ps.setString(7, salt);


        ps.execute();
    }

    public static User getUser(String email) {
        Connection db = null;
        PreparedStatement ps;
        User user = null;
        try {
            db = DBUtils.getINSTANCE().getConnection();
            String query1 = "select * from user where email=?; ";
            ps = db.prepareStatement(query1);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setIs_superuser(rs.getBoolean("is_superuser"));
                user.setEmail(rs.getString("email"));
            }
            return user;

        } catch (Exception e) {
            e.printStackTrace();

            return user;
        }
    }
}
