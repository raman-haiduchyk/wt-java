<%--
  Created by IntelliJ IDEA.
  User: Mi
  Date: 09.12.2020
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>BundesFan | Catalog</title>
</head>
<body>

<c:forEach var="category" items="${categories}">
    <a href="/Controller?command=get_items&categoryId=${category.id}">${category.name}</a>
</c:forEach>
    <a href="/Controller?command=get_items">All Categories</a>

<c:forEach var="item" items="${catalogItems}">
    <div class="product-card">
        ${item.name}
        <img src="${item.imageUrl}">
        <div class="desc">
                ${item.manufacturer}
                <br>
                ${item.team}
                <br>
                ${item.price}
                <br>
                ${item.size}
                <br>
        </div>
        <button onclick="location.href='Controller?command=add_to_order&itemId=${item.id}'">Add to cart</button>
    </div>
</c:forEach>

</body>
</html>
