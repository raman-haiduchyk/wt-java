<%--
  Created by IntelliJ IDEA.
  User: Mi
  Date: 09.12.2020
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <h3>Sign In:</h3>
    <form action="Controller" method="post">
        <input type="hidden" name="command" value="signin"/>

        <br/>
        <label for="login">Login:</label>
        <input type="text" id="login" name="login"/>

        <br/>
        <label for="password">Password</label>
        <input type="password" id="password" name="password"/>

        <br/>
        <input type="submit" value="Sign In"/>
    </form>

    <div>
        <h3>Go to registration page if you don't have account!</h3>
        <button onclick="location.href='Controller?command=go_to_registration'">Sign Up</button>
    </div>

</body>
</html>
