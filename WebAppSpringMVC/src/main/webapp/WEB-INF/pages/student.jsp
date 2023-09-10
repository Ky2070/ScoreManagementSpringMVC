<%-- 
    Document   : student
    Created on : Sep 6, 2023, 7:48:18 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý hệ thống điểm</title>
    </head>
    <body>
         <h1>Danh Sách Sinh Viên</h1>
        <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Mã Sinh Viên</th>
                <th>Họ</th>
                <th>Tên</th>
                <th>Email</th>
                <th>Số điện thoại</th>
                <<th>Lớp</th>
                <!-- Các cột khác -->
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${students}" var="student">
                <tr>
                    <td>${student.id}</td>
                    <td>${student.studentCode}</td>
                    <td>${student.firstName}</td>
                    <td>${student.lastName}</td>
                    <td>${student.email}</td>
                    <td>${student.phone}</td>
                    <td>${student.classId}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    </body>
</html>
