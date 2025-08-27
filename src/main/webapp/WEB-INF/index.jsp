<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.flightapp.model.User" %>
<%
  User current = (User) session.getAttribute("user");
  String ctx = request.getContextPath();
%>
<!doctype html>
<html>
<head>
  <title>FlightBookingApp</title>
  <link rel="stylesheet" href="<%=ctx%>/assets/css/style.css">
</head>
<body>
  <header class="header">
    <div class="container nav">
      <div><strong>✈️ FlightBookingApp</strong></div>
      <div>
        <% if (current == null) { %>
          <a href="<%=ctx%>/login" class="btn">Login</a>
          <a href="<%=ctx%>/register" class="btn btn-outline" style="margin-left:8px;">Register</a>
        <% } else { %>
          <span class="badge">Hello, <%=current.getName()%></span>
          <a href="<%=ctx%>/my-bookings" style="margin-left:10px;">My Bookings</a>
          <% if ("ADMIN".equals(current.getRole())) { %>
            <a href="<%=ctx%>/admin/add-flight" style="margin-left:10px;">Add Flight</a>
          <% } %>
          <a href="<%=ctx%>/logout" class="btn btn-outline" style="margin-left:10px;">Logout</a>
        <% } %>
      </div>
    </div>
  </header>

  <main class="container" style="margin-top:20px;">
    <div class="card">
      <h2>Find Flights</h2>
      <form action="<%=ctx%>/search" method="post" class="grid grid-3">
        <div>
          <label>From</label>
          <input class="input" name="from" placeholder="Hyderabad" required>
        </div>
        <div>
          <label>To</label>
          <input class="input" name="to" placeholder="Mumbai" required>
        </div>
        <div>
          <label>Departure Date</label>
          <input class="input" type="date" name="date" required>
        </div>
        <div style="grid-column: 1/-1;">
          <button class="btn">Search</button>
        </div>
      </form>
    </div>
  </main>

  <footer>
    <div class="container">© <%=java.time.Year.now()%> FlightBookingApp</div>
  </footer>
</body>
</html>
