<%--
  Created by IntelliJ IDEA.
  User: THIS PC
  Date: 2/14/2023
  Time: 1:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>ResultSearch</title>
</head>
<body>
<c:choose>
    <c:when test="${requestScope['flag'] == false}">
        <p>Không tìm thấy sản phẩm có tên vừa nhập!</p>
    </c:when>
    <c:when test='${requestScope["flag"] == true}'>
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
            <c:forEach items="${requestScope['studentByName']}" var="student">
                <tr>
                    <td>${student.getId()}</td>
                    <td>${student.getName()}</td>
                    <td>${student.getBirthday()}</td>
                    <td>${student.getAddress()}</td>
                    <td>${student.getPhoneNumber()}</td>
                    <td>${student.getEmail()}</td>
                    <td>${student.getClassroom().getName()}</td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
</c:choose>
</body>
</html>
