<%-- 
    Document   : subjTeach
    Created on : Sep 9, 2023, 8:55:53 AM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hệ thống quản lý</title>
    </head>
    <body>
        <h1>Danh Sách Sinh Viên</h1>
        <table>
        <thead>
            <tr>
                <th>GV</th>
                <th>Môn học</th>
                
                <!-- Các cột khác -->
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${subjteachs}" var="subjteachs">
                <tr>
                    <td>${subjteachs.teacherId}</td>
                    <td>${subjteachs.subjectId}</td>
                   
                </tr>
            </c:forEach>
        </tbody>
    </table>
    </body>
</html>
