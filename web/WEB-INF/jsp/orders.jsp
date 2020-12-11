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
    <c:if test="${cartItems != null}">
        <c:forEach var="item" items="${cartItems}">
            <div class="item-card">
                Name: ${item.key.name} Count: ${item.value}
                <button onclick="location.href='Controller?command=del_from_order&itemId=${item.key.id}'"></button>
            </div>
        </c:forEach>
        <button onclick="location.href='Controller?command=confirm_order'"></button>
    </c:if>
    <c:forEach var="confirmedOrder" items="${orders}">
        <div class="order-card">
            Order ID: ${confirmedOrder.id}
            <button onclick="location.href='Controller?command=get_order_info&orderId=${confirmedOrder.id}'"></button>
        </div>
    </c:forEach>
</main>

<footer>
    <jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</footer>

</body>
</html>