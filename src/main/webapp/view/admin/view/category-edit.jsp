<%--
  Created by IntelliJ IDEA.
  User: Piuta
  Date: 8/9/2020
  Time: 8:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Category</title>
</head>
<body>
<div align="center">
    <h1>CATEGORY EDIT</h1>
    <form action="/admin/category/edit" method="post">
        <table border="1" cellpadding="5">
            <tr>
                <th>ID</th>
                <th>Name</th>
            </tr>
            <tr>
                <td><input type="number" name="id" value="${category.getId()}" readonly/></td>
                <td><input type="text" name="name" value="${category.getName()}" size="45px"></td>
            </tr>
            <tr>
                <td colspan="2"><button type="submit" value="save">Save</button></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
