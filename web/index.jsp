<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>BundesFan | SignIn</title>
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
        <c:redirect url="Controller?command=get_user_cart"/>
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
