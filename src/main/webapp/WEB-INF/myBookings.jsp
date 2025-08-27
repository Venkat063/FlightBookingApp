<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*,com.flightapp.model.Booking" %>
<%
 String ctx = request.getContextPath();
 List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
%>
<!doctype html>
<html>
<head>
  <title>My Bookings - FlightBookingApp</title>
  <link rel="stylesheet" href="<%=ctx%>/assets/css/style.css">
</head>
<body>
  <div class="container" style="margin-top:30px;">
    <div class="card">
      <h2>My Bookings</h2>
      <table class="table">
        <thead>
          <tr><th>Booking #</th><th>Flight ID</th><th>Passengers</th><th>Status</th><th>Booked At</th></tr>
        </thead>
        <tbody>
          <% if (bookings == null || bookings.isEmpty()) { %>
            <tr><td colspan="5">No bookings yet.</td></tr>
          <% } else {
               for (Booking b : bookings) { %>
            <tr>
              <td><%=b.getId()%></td>
              <td><%=b.getFlightId()%></td>
              <td><%=b.getPassengers()%></td>
              <td><span class="badge"><%=b.getStatus()%></span></td>
              <td><%=b.getBookingTime()%></td>
            </tr>
          <% } } %>
        </tbody>
      </table>
      <div style="margin-top:10px;"><a href="<%=ctx%>/">← Search more flights</a></div>
    </div>
  </div>
</body>
</html>
