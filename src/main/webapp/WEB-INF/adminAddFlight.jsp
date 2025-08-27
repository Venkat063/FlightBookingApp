<%@ page contentType="text/html;charset=UTF-8" %>
<%
 String ctx = request.getContextPath();
 String msg = (String) request.getAttribute("msg");
 String err = (String) request.getAttribute("error");
%>
<!doctype html>
<html>
<head>
  <title>Add Flight (Admin) - FlightBookingApp</title>
  <link rel="stylesheet" href="<%=ctx%>/assets/css/style.css">
</head>
<body>
  <div class="container" style="margin-top:30px; max-width:800px;">
    <div class="card">
      <h2>Add New Flight</h2>
      <% if (msg != null) { %><div class="alert ok"><%=msg%></div><% } %>
      <% if (err != null) { %><div class="alert error"><%=err%></div><% } %>
      <form method="post" action="<%=ctx%>/admin/add-flight" class="grid grid-3">
        <div><label>Airline</label><input class="input" name="airline" required></div>
        <div><label>From City</label><input class="input" name="from_city" required></div>
        <div><label>To City</label><input class="input" name="to_city" required></div>
        <div><label>Depart Date</label><input class="input" type="date" name="depart_date" required></div>
        <div><label>Depart Time</label><input class="input" type="time" name="depart_time" required></div>
        <div><label>Price (₹)</label><input class="input" type="number" step="0.01" name="price" required></div>
        <div><label>Seats</label><input class="input" type="number" name="seats" min="1" required></div>
        <div style="grid-column:1/-1;">
          <button class="btn">Add Flight</button>
          <a href="<%=ctx%>/" style="margin-left:10px;">Back</a>
        </div>
      </form>
    </div>
  </div>
</body>
</html>
