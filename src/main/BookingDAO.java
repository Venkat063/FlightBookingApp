package com.flightapp.dao;

import com.flightapp.model.Booking;
import com.flightapp.model.Flight;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    public boolean createBooking(int userId, int flightId, int passengers) {
        String insert = "INSERT INTO bookings(user_id, flight_id, passengers, status) VALUES(?,?,?, 'CONFIRMED')";
        try (Connection con = DBUtil.getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement ps = con.prepareStatement(insert)) {
                // Decrement seats first
                FlightDAO fdao = new FlightDAO();
                if (!fdao.decrementSeats(flightId, passengers)) {
                    con.rollback();
                    return false;
                }
                ps.setInt(1, userId);
                ps.setInt(2, flightId);
                ps.setInt(3, passengers);
                int ok = ps.executeUpdate();
                con.commit();
                return ok == 1;
            } catch (SQLException ex) {
                con.rollback();
                throw ex;
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public List<Booking> findByUser(int userId) {
        String sql = "SELECT * FROM bookings WHERE user_id=? ORDER BY booking_time DESC";
        List<Booking> list = new ArrayList<>();
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    private Booking map(ResultSet rs) throws SQLException {
        Booking b = new Booking();
        b.setId(rs.getInt("id"));
        b.setUserId(rs.getInt("user_id"));
        b.setFlightId(rs.getInt("flight_id"));
        b.setPassengers(rs.getInt("passengers"));
        b.setStatus(rs.getString("status"));
        Timestamp ts = rs.getTimestamp("booking_time");
        b.setBookingTime(ts != null ? ts.toLocalDateTime() : LocalDateTime.now());
        return b;
    }
}
