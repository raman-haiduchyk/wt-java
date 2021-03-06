<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>BundesFan | Cart</title>
</head>
<body>

<header>
    <jsp:include page="header.jsp"/>
</header>



<main>
    <c:if test="${param.error == 'confirmOrder'}">
        <h2>Oops. Something went wrong while confirming. Try again.</h2>
    </c:if>

    <c:if test="${param.confirm == 'ok'}">
        <h2>Order confirmed.</h2>
    </c:if>

    <c:if test="${cartItems != null}">
        <c:forEach var="item" items="${cartItems}">
            <div class="item-card">
                Name: ${item.key.name} Count: ${item.value}
                <button onclick="location.href='Controller?command=del_from_order&itemId=${item.key.id}'">Delete</button>
            </div>
        </c:forEach>
        <button onclick="location.href='Controller?command=confirm_order'">Confirm</button>
    </c:if>
    <c:forEach var="confirmedOrder" items="${orders}">
        <div class="order-card">
            Order ID: ${confirmedOrder.id}
            <button onclick="location.href='Controller?command=get_order_info&orderId=${confirmedOrder.id}'">More</button>
        </div>
    </c:forEach>
</main>

<footer>
    <jsp:include page="footer.jsp"/>
</footer>

</body>
</html>