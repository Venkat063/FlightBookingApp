package com.flightapp.servlet;

import com.flightapp.dao.FlightDAO;
import com.flightapp.model.Flight;
import com.flightapp.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet("/admin/add-flight")
public class AdminAddFlightServlet extends HttpServlet {
    private final FlightDAO dao = new FlightDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User u = (User) req.getSession().getAttribute("user");
        if (u == null || !"ADMIN".equals(u.getRole())) {
            resp.sendError(403, "Forbidden");
            return;
        }
        req.getRequestDispatcher("/WEB-INF/views/adminAddFlight.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User u = (User) req.getSession().getAttribute("user");
        if (u == null || !"ADMIN".equals(u.getRole())) { resp.sendError(403); return; }

        Flight f = new Flight();
        f.setAirline(req.getParameter("airline"));
        f.setFromCity(req.getParameter("from_city"));
        f.setToCity(req.getParameter("to_city"));
        f.setDepartDate(LocalDate.parse(req.getParameter("depart_date")));
        f.setDepartTime(LocalTime.parse(req.getParameter("depart_time")));
        f.setPrice(Double.parseDouble(req.getParameter("price")));
        f.setSeatsAvailable(Integer.parseInt(req.getParameter("seats")));

        if (dao.addFlight(f)) {
            req.setAttribute("msg", "Flight added!");
        } else {
            req.setAttribute("error", "Failed to add flight");
        }
        req.getRequestDispatcher("/WEB-INF/views/adminAddFlight.jsp").forward(req, resp);
    }
}
