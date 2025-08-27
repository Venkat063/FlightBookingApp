<%@ page contentType="text/html;charset=UTF-8" %>
<%
 String ctx = request.getContextPath();
 String flash = (String) request.getAttribute("flash");
 String err = (String) request.getAttribute("error");
%>
<!doctype html>
<html>
<head>
  <title>Login - FlightBookingApp</title>
  <link rel="stylesheet" href="<%=ctx%>/assets/css/style.css">
</head>
<body>
  <div class="container" style="margin-top:60px; max-width:560px;">
    <div class="card">
      <h2>Login</h2>
      <% if (flash != null) { %><div class="alert ok"><%=flash%></div><% } %>
      <% if (err != null) { %><div class="alert error"><%=err%></div><% } %>
      <form method="post" action="<%=ctx%>/login" class="grid">
        <div>
          <label>Email</label>
          <input class="input" type="email" name="email" required>
        </div>
        <div>
          <label>Password</label>
          <input class="input" type="password" name="password" required>
        </div>
        <div>
          <button class="btn">Sign In</button>
          <a href="<%=ctx%>/" style="margin-left:10px;">← Back</a>
        </div>
      </form>
    </div>
  </div>
</body>
</html>
