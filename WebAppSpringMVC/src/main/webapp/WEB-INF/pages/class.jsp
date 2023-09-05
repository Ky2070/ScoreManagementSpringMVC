<%-- 
    Document   : class
    Created on : Sep 2, 2023, 4:19:51 PM
    Author     : nguye
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản Lý Hệ Thống Điểm</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f5f5f5;
            }
            h1 {
                background-color: #333;
                color: #fff;
                padding: 10px;
                margin: 0;
            }
            table {
                border-collapse: collapse;
                width: 80%;
                margin: 20px auto;
            }
            th, td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
        </style>
    </head>
    <body>
        <h1>Danh Sách Lớp</h1>
        <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Mã Lớp</th>
                <th>Ngành</th>
                <th>Giáo viên chủ nhiệm</th>
                <!-- Các cột khác -->
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${classes}" var="classes">
                <tr>
                    <td>${classes.id}</td>
                    <td>${classes.className}</td>
                    <td>${classes.majorId}</td>
                    <td>${classes.teacherId}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
   </body>
</html>
