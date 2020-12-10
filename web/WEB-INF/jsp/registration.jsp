<%--
  Created by IntelliJ IDEA.
  User: Mi
  Date: 09.12.2020
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BundesFan | Registration</title>
</head>
<body>


<header>
    <jsp:include page="header.jsp"/>
</header>

<main>

    <c:if test="${param.error == 'error'}">
        <h2>Oops. Something went wrong while signing up. Try again.</h2>
    </c:if>
    <c:if test="${param.error == 'unique'}">
        <h2>Oops. User with this login already exists. Try again.</h2>
    </c:if>

    <div>
        <form action="Controller" method="post">
            <input type="hidden" name="command" value="registration"/>

            <br/>
            <label for="login">Login:</label>
            <input type="text" id="login" name="login"/>

            <br/>
            <label for="address">Email</label>
            <input type="text" id="address" name="address"/>

            <br/>
            <label for="phone">Email</label>
            <input type="text" id="phone" name="phone"/>

            <br/>
            <label for="email">Email</label>
            <input type="text" id="email" name="email"/>

            <br/>
            <label for="firstname">Name</label>
            <input type="text" id="firstname" name="firstname"/>

            <br/>
            <label for="lastname">Lastname</label>
            <input type="text" id="lastname" name="lastname"/>

            <label for="favteam">Choose a favourite team:</label>
            <select id="favteam" name="favteam">
                <option value="FC Bayern München">Volvo</option>
                <option value="Borussia Dortmund">Saab</option>
                <option value="RB Leipzig">Fiat</option>
                <option value="Borussia Mönchengladbach">Audi</option>
                <option value="Bayer 04 Leverkusen">Volvo</option>
                <option value="TSG Hoffenheim">Saab</option>
                <option value="VfL Wolfsburg">Fiat</option>
                <option value="Sport-Club Freiburg">Audi</option>
                <option value="Eintracht Frankfurt">Volvo</option>
                <option value="Hertha Berlin">Saab</option>
                <option value="1. FC Union Berlin">Fiat</option>
                <option value="FC Schalke 04">Audi</option>
                <option value="1. FSV Mainz 05">Volvo</option>
                <option value="1. FC Köln">Saab</option>
                <option value="FC Augsburg">Fiat</option>
                <option value="SV Werder Bremen">Audi</option>
                <option value="DSC Arminia Bielefeld">Fiat</option>
                <option value="VfB Stuttgart">Audi</option>
            </select>

            <br/>
            <label for="password">Password</label>
            <input type="password" id="password" name="password"/>

            <br/>
            <input type="submit" value="Sign Up"/>
        </form>
    </div>

    <div>
        <h3>Go to login page if you already have account!</h3>
        <button onclick="location.href='Controller?command=go_to_login'">Sign Up</button>
    </div>
</main>


<footer>
    <jsp:include page="footer.jsp"/>
</footer>

</body>
</html>
