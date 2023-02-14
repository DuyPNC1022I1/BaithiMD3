<%--
  Created by IntelliJ IDEA.
  User: THIS PC
  Date: 2/14/2023
  Time: 10:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add new product</title>
</head>
<body>
<h1>Create new Student</h1>
<form action="/server?action=create" method="post">
    <label>Name: </label>
    <input type="text" name="name" placeholder="name"><br>
    <label>Email: </label>
    <input type="text" name="email" placeholder="email"><br>
    <label>Birthday: </label>
    <input type="text" name="birthday" placeholder="birthDay"><br>
    <label>Address: </label>
    <input type="text" name="address" placeholder="address"><br>
    <label>PhoneNumber: </label>
    <input type="text" name="phoneNumber" placeholder="phoneNumber"><br>
    <label>Classroom: </label>
    <select name="classroom">
        <c:forEach items="${requestScope['classrooms']}" var="classroom">
            <option>${classroom.getName()}</option>
        </c:forEach>
    </select>
    <br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
