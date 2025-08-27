package com.flightapp.servlet;

import com.flightapp.dao.BookingDAO;
import com.flightapp.model.Booking;
import com.flightapp.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/my-bookings")
public class MyBookingsServlet extends HttpServlet {
    private final BookingDAO bookingDAO = new BookingDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User u = (User) req.getSession().getAttribute("user");
        if (u == null) { resp.sendRedirect(req.getContextPath() + "/login"); return; }
        List<Booking> list = bookingDAO.findByUser(u.getId());
        req.setAttribute("bookings", list);
        req.getRequestDispatcher("/WEB-INF/views/myBookings.jsp").forward(req, resp);
    }
}
