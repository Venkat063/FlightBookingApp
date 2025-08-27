package com.flightapp.dao;

import com.flightapp.model.Flight;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class FlightDAO {
    public List<Flight> search(String from, String to, LocalDate date) {
        String sql = "SELECT * FROM flights WHERE from_city=? AND to_city=? AND depart_date=? AND seats_available > 0 ORDER BY depart_time";
        List<Flight> list = new ArrayList<>();
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, from);
            ps.setString(2, to);
            ps.setDate(3, Date.valueOf(date));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(map(rs));
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public Flight findById(int id) {
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM flights WHERE id=?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public boolean decrementSeats(int flightId, int count) {
        String sql = "UPDATE flights SET seats_available = seats_available - ? WHERE id=? AND seats_available >= ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, count);
            ps.setInt(2, flightId);
            ps.setInt(3, count);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean addFlight(Flight f) {
        String sql = "INSERT INTO flights(airline,from_city,to_city,depart_date,depart_time,price,seats_available) VALUES(?,?,?,?,?,?,?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, f.getAirline());
            ps.setString(2, f.getFromCity());
            ps.setString(3, f.getToCity());
            ps.setDate(4, Date.valueOf(f.getDepartDate()));
            ps.setTime(5, Time.valueOf(f.getDepartTime()));
            ps.setDouble(6, f.getPrice());
            ps.setInt(7, f.getSeatsAvailable());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    private Flight map(ResultSet rs) throws SQLException {
        Flight f = new Flight();
        f.setId(rs.getInt("id"));
        f.setAirline(rs.getString("airline"));
        f.setFromCity(rs.getString("from_city"));
        f.setToCity(rs.getString("to_city"));
        f.setDepartDate(rs.getDate("depart_date").toLocalDate());
        f.setDepartTime(rs.getTime("depart_time").toLocalTime());
        f.setPrice(rs.getDouble("price"));
        f.setSeatsAvailable(rs.getInt("seats_available"));
        return f;
    }
}
