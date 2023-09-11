<%-- 
    Document   : major
    Created on : Sep 4, 2023, 9:55:32 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Quản lý hệ thống điểm</title>
</head>
<body>
    <div class="container">
        <h1>Danh sách Ngành</h1>
        <div class="row">
            <c:forEach items="${majors}" var="majors">
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">${majors.majorName} - ${majors.trainingTypeId}</h5>
                            <p class="card-text">Description or details about the major.</p>
                            <a href="classes?majorId=${majors.id}" class="btn btn-primary">Chi tiết</a>
                            <button type="button" class="btn btn-danger">Xóa ngành</button>
                            <button type="button" class="btn btn-primary">Cập nhật</button>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
