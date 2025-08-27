package com.flightapp.servlet;

import com.flightapp.dao.BookingDAO;
import com.flightapp.dao.FlightDAO;
import com.flightapp.model.Flight;
import com.flightapp.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/book")
public class BookFlightServlet extends HttpServlet {
    private final FlightDAO flightDAO = new FlightDAO();
    private final BookingDAO bookingDAO = new BookingDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("flightId");
        if (idStr == null) { resp.sendRedirect(req.getContextPath() + "/"); return; }
        int flightId = Integer.parseInt(idStr);
        Flight f = flightDAO.findById(flightId);
        if (f == null) { resp.sendRedirect(req.getContextPath() + "/"); return; }
        req.setAttribute("flight", f);
        req.getRequestDispatcher("/WEB-INF/views/booking.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User u = (User) req.getSession().getAttribute("user");
        if (u == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        int flightId = Integer.parseInt(req.getParameter("flightId"));
        int passengers = Integer.parseInt(req.getParameter("passengers"));
        boolean ok = bookingDAO.createBooking(u.getId(), flightId, passengers);
        if (ok) {
            resp.sendRedirect(req.getContextPath() + "/my-bookings");
        } else {
            req.setAttribute("error", "Booking failed (insufficient seats?)");
            doGet(req, resp);
        }
    }
}
