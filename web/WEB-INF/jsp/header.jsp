<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Mi
  Date: 09.12.2020
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<header>
    Welcome to the Bundesliga Fan Shop!
</header>
    <c:if test="${user != null}">
        <div class="nav">
            <a href="'Controller?command=go_to_catalog'">Catalog</a>
            <a href="'Controller?command=go_to_main'">Orders</a>
        </div>
        <div>
            <button onclick="location.href='Controller?command=signout'">Sign Out!</button>
        </div>
    </c:if>

</body>
</html>