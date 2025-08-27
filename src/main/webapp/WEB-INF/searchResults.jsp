<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*,com.flightapp.model.Flight" %>
<%
 String ctx = request.getContextPath();
 List<Flight> flights = (List<Flight>) request.getAttribute("flights");
%>
<!doctype html>
<html>
<head>
  <title>Search Results - FlightBookingApp</title>
  <link rel="stylesheet" href="<%=ctx%>/assets/css/style.css">
</head>
<body>
  <div class="container" style="margin-top:30px;">
    <div class="card">
      <h2>Available Flights</h2>
      <a href="<%=ctx%>/" style="float:right; margin-top:-32px;">New Search</a>
      <table class="table">
        <thead>
          <tr>
            <th>Airline</th><th>From → To</th><th>Date</th><th>Time</th><th>Price</th><th>Seats</th><th></th>
          </tr>
        </thead>
        <tbody>
        <% if (flights == null || flights.isEmpty()) { %>
          <tr><td colspan="7">No flights found.</td></tr>
        <% } else { 
           for (Flight f : flights) { %>
          <tr>
            <td><%=f.getAirline()%></td>
            <td><%=f.getFromCity()%> → <%=f.getToCity()%></td>
            <td><%=f.getDepartDate()%></td>
            <td><%=f.getDepartTime()%></td>
            <td>₹ <%=String.format("%.2f", f.getPrice())%></td>
            <td><%=f.getSeatsAvailable()%></td>
            <td>
              <a class="btn" href="<%=ctx%>/book?flightId=<%=f.getId()%>">Book</a>
            </td>
          </tr>
        <% } } %>
        </tbody>
      </table>
    </div>
  </div>
</body>
</html>
