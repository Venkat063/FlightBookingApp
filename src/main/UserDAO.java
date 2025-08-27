package com.flightapp.dao;

import com.flightapp.model.User;
import java.sql.*;

public class UserDAO {
    public boolean register(String name, String email, String password) {
        String sql = "INSERT INTO users(name,email,password_hash,role) VALUES(?,?,?, 'USER')";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, DBUtil.sha256(password));
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace(); return false;
        }
    }

    public User login(String email, String password) {
        String sql = "SELECT id,name,email,role,password_hash FROM users WHERE email=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String hash = rs.getString("password_hash");
                    if (hash != null && hash.equals(DBUtil.sha256(password))) {
                        return new User(rs.getInt("id"), rs.getString("name"),
                                        rs.getString("email"), rs.getString("role"));
                    }
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
}
