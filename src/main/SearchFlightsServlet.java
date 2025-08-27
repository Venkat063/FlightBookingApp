package com.flightapp.servlet;

import com.flightapp.dao.FlightDAO;
import com.flightapp.model.Flight;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/search")
public class SearchFlightsServlet extends HttpServlet {
    private final FlightDAO flightDAO = new FlightDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String from = req.getParameter("from");
        String to = req.getParameter("to");
        String date = req.getParameter("date");
        List<Flight> results = flightDAO.search(from, to, LocalDate.parse(date));
        req.setAttribute("flights", results);
        req.getRequestDispatcher("/WEB-INF/views/searchResults.jsp").forward(req, resp);
    }
}
