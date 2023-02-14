<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Display</title>
</head>
<body>
<h1> Show List Student</h1>
<a href="/server?action=showCreate"><button>Add</button></a>
<form action="/server?action=searchByName" method="post">
    <label>SearchByName</label>
    <input type="text" name="searchByName">
    <input type="submit">
</form>
<table border="2">
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Birthday</td>
        <td>Address</td>
        <td>PhoneNumber</td>
        <td>Email</td>
        <td>Classroom</td>
        <td colspan="2">Action</td>
    </tr>
    <c:forEach items="${requestScope['students']}" var="student">
        <tr>
            <td>${student.getId()}</td>
            <td>${student.getName()}</td>
            <td>${student.getBirthday()}</td>
            <td>${student.getAddress()}</td>
            <td>${student.getPhoneNumber()}</td>
            <td>${student.getEmail()}</td>
            <td>${student.getClassroom().getName()}</td>
            <td><a href="server?action=showUpdate&id=${student.getId()}">
                <button>Edit</button>
            </a></td>
            <td><a href="server?action=delete&id=${student.getId()}">
                <button>Delete</button>
            </a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

