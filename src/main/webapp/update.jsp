<%--
  Created by IntelliJ IDEA.
  User: THIS PC
  Date: 2/14/2023
  Time: 10:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: THIS PC
  Date: 2/9/2023
  Time: 4:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Update information</title>
</head>
<body>
<h1>Form update information</h1>
<form action="/server?action=update&id=${student.getId()}" method="post">
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
    <input type="submit" value="Submit">
</form>
<p>
    <a href="/admin">
        <button>Back to home page</button>
    </a>
</p>
</body>
</html>

