<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table border="1">
    <tr>
        <td>Name</td>
        <td>Email</td>
        <td>Address</td>
        <td>Delete</td>
        <td>Edit</td>
    </tr>
    <c:forEach items="${customers}" var="customer">
        <tr>
            <td><a href="${pageContext.request.contextPath}/customers?action=view&id=${customer.id}">${customer.name}</a></td>
            <td>${customer.email}</td>
            <td>${customer.address}</td>
            <td><a href="${pageContext.request.contextPath}/customers?action=edit&id=${customer.id}">edit</a></td>
            <td><a href="${pageContext.request.contextPath}/customers?action=delete&id=${customer.id}">delete</a></td>
        </tr>
    </c:forEach>
</table>