<%--
  Created by IntelliJ IDEA.
  User: Игорь
  Date: 13.09.2016
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meal List</title>
</head>
<body>
<h2><a href="index.html"> Home</a></h2>
<h1>MealList</h1>
<p>table</p>
<table border="1">
    <tr>
        <th>Date/Time</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>


    <c:forEach items="${meals}" var="meal">
                <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealWithExceed"></jsp:useBean>
        <%-- <%=meal.getDescription%>--%>
        <%--<% String color=meal.isExceed()?"red":"green"; %>--%>

        <%--<c:set var="color" value="${meal.exceed?'red':'green'}"/>--%>
   <%--     <c:set var="color" value="green"/>--%>

<%--        <tr style="color: <%=color%>">--%>
        <tr style="color:${meal.exceed?'red':'green'}">
            <td>${meal.dateTime}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
        </tr>

        <%--            <td><%=meal.getDateTime()%></td>
                    <td><%=meal.getDescription()%></td>
                    <td><%=meal.getCalories()%></td>--%>
    </c:forEach>


</table>

</body>
</html>
