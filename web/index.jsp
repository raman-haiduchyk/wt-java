<%--
  Created by IntelliJ IDEA.
  User: Mi
  Date: 07.12.2020
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>BundesFan | Main</title>
  </head>
  <body>

  <header>
    <jsp:include page="WEB-INF/jsp/header.jsp"/>
  </header>


  <main>

    <c:if test="${param.error == 'error'}">
      <h2>Oops. Something went wrong while signing in. Try again.</h2>
    </c:if>

    <c:choose>
      <c:when test="${user != null}">
        <jsp:include page="WEB-INF/jsp/orders.jsp"/>
      </c:when>
      <c:otherwise>
        <jsp:include page="WEB-INF/jsp/login.jsp"/>
      </c:otherwise>
    </c:choose>
  </main>

  <footer>
    <jsp:include page="WEB-INF/jsp/footer.jsp"/>
  </footer>

  </body>
</html>
