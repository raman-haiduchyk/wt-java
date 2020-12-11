<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>BundesFan | Main</title>
</head>
<body>

<header>
    <jsp:include page="/WEB-INF/jsp/header.jsp"/>
</header>

<main>
    <c:if test="${orderInfo != null}">
        <c:forEach var="item" items="${orderItems}">
            <div class="item-card">
                Name: ${item.key.name} Price ${item.key.price} Count: ${item.value}
            </div>
        </c:forEach>
        <div class="order-info">
            Date: ${orderOnfo.orerDate} Status: ${orderOnfo.state}
        </div>
    </c:if>
</main>

<footer>
    <jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</footer>

</body>
</html>
