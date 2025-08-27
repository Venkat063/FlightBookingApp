<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.flightapp.model.Flight,com.flightapp.model.User" %>
<%
 String ctx = request.getContextPath();
 User current = (User) session.getAttribute("user");
 if (current == null) { response.sendRedirect(ctx + "/login"); return; }
 Flight f = (Flight) request.getAttribute("flight");
 String err = (String) request.getAttribute("error");
%>
<!doctype html>
<html>
<head>
  <title>Book Flight - FlightBookingApp</title>
  <link rel="stylesheet" href="<%=ctx%>/assets/css/style.css">
</head>
<body>
<div class="container" style="margin-top:30px; max-width:720px;">
  <div class="card">
    <h2>Book Flight</h2>
    <% if (err != null) { %><div class="alert error"><%=err%></div><% } %>
    <p><strong><%=f.getAirline()%></strong> — <%=f.getFromCity()%> → <%=f.getToCity()%> |
       <%=f.getDepartDate()%> @ <%=f.getDepartTime()%> | Seats: <%=f.getSeatsAvailable()%> | ₹ <%=String.format("%.2f", f.getPrice())%>
    </p>
    <form method="post" action="<%=ctx%>/book" class="grid grid-2">
      <input type="hidden" name="flightId" value="<%=f.getId()%>">
      <div>
        <label>Passengers</label>
        <input class="input" type="number" name="passengers" min="1" max="<%=f.getSeatsAvailable()%>" value="1" required>
      </div>
      <div style="align-self:end;">
        <button class="btn">Confirm Booking</button>
        <a href="<%=ctx%>/search" style="margin-left:10px;">Back</a>
      </div>
    </form>
  </div>
</div>
</body>
</html>
