<%@ page contentType="text/html;charset=UTF-8" %>
<%
 String ctx = request.getContextPath();
 String err = (String) request.getAttribute("error");
%>
<!doctype html>
<html>
<head>
  <title>Register - FlightBookingApp</title>
  <link rel="stylesheet" href="<%=ctx%>/assets/css/style.css">
</head>
<body>
  <div class="container" style="margin-top:60px; max-width:560px;">
    <div class="card">
      <h2>Create Account</h2>
      <% if (err != null) { %><div class="alert error"><%=err%></div><% } %>
      <form method="post" action="<%=ctx%>/register" class="grid">
        <div>
          <label>Name</label>
          <input class="input" name="name" required>
        </div>
        <div>
          <label>Email</label>
          <input class="input" type="email" name="email" required>
        </div>
        <div>
          <label>Password</label>
          <input class="input" type="password" name="password" required minlength="6">
        </div>
        <div>
          <button class="btn">Create Account</button>
          <a href="<%=ctx%>/login" style="margin-left:10px;">Have an account?</a>
        </div>
      </form>
    </div>
  </div>
</body>
</html>
