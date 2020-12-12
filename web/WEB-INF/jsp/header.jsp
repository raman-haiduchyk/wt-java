<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<header>
    Welcome to the Bundesliga Fan Shop!
</header>
    <c:if test="${user != null}">
        <div class="nav">
            <a href="'Controller?command=get_items'">Catalog</a>
            <a href="'Controller?command=go_to_main'">Main</a>
        </div>
        <div>
            <button onclick="location.href='Controller?command=signout'">Sign Out!</button>
        </div>
    </c:if>

</body>
</html>