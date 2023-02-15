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
    <input type="text" name="name" placeholder="${student.getName()}"><br><br>
    <label>Email: </label>
    <input type="text" name="email" placeholder="${student.getEmail()}"><br><br>
    <label>Birthday: </label>
    <input type="text" name="birthday" placeholder="${student.getBirthday()}"><br><br>
    <label>Address: </label>
    <input type="text" name="address" placeholder="${student.getAddress()}"><br><br>
    <label>PhoneNumber: </label>
    <input type="text" name="phoneNumber" placeholder="${student.getPhoneNumber()}"><br><br>
    <label>Classroom: </label>
    <select name="classroom">
        <c:forEach items="${requestScope['classrooms']}" var="classroom">
            <option>${classroom.getName()}</option>
        </c:forEach>
    </select>
    <input type="submit" value="Submit">
</form>
<p>
    <a href="/server">
        <button>Back to home page</button>
    </a>
</p>
</body>
</html>

