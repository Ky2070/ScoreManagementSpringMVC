<%-- 
    Document   : department
    Created on : Sep 3, 2023, 10:39:03 AM
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
        <c:url value="/departments" var="action"/>
        <h1>Danh Sách Khoa</h1>
        <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Tên Khoa</th>
                <!-- Các cột khác -->
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${departments}" var="departments">
                <tr>
                    <td>${departments.id}</td>
                    <td>${departments.departmentName}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
   </body>
</html>

