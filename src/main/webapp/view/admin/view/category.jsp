<%--
  Created by IntelliJ IDEA.
  User: Piuta
  Date: 8/9/2020
  Time: 6:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Category</title>
</head>
<body>
<div align="center">
    <h1>CATEGORY LIST</h1>
    <h3><a href="/home">HOME</a></h3>
    <h3><a href="/admin">Dashboard</a></h3>
    <form action="/admin/category/add" method="post">
        <table border="1" cellpadding="5">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Action</th>
            </tr>
            <tr>
                <td>New Category</td>
                <td><input type="text" name="name" placeholder="Name Category" size="45px"></td>
                <td><button type="submit" value="add-category">Add</button></td>
            </tr>
            <c:forEach var="cate" items="${cateList}">
                <tr>
                    <td>${cate.getId()}</td>
                    <td>${cate.getName()}</td>
                    <td>
                        <a href="/admin/category/edit?id=${cate.getId()}">Edit</a>
                        <a href="/admin/category/delete?id=${cate.getId()}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>
</div>
</body>
</html>
