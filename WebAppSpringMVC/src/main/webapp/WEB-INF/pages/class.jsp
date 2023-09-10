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
        <!-- Kiểm tra nếu danh sách lớp rỗng -->
        <c:if test="${empty classes}">
                <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
                <symbol id="check-circle-fill" fill="currentColor" viewBox="0 0 16 16">
                    <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                </symbol>
                <div class="alert alert-primary d-flex align-items-center" role="alert">
                    <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Info:"><use xlink:href="#info-fill"/></svg>
                    <div>
                        Hiện tại ngành này chưa có lớp
                    </div>
                </div>
                
        </c:if>
        <!-- Nếu danh sách lớp không rỗng, hiển thị bảng danh sách lớp -->
        <c:if test="${not empty classes}">
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
        </c:if> 
    </body>
</html>
