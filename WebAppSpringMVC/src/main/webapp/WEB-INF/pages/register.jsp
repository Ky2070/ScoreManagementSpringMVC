<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.5.0/dist/css/bootstrap.min.css">
        <title>Đăng Ký</title>
    </head>
    <body>
        <div class="container mt-5">
            <h1>Đăng Ký Giảng Viên</h1>
            <c:url value="/register" var="action" />
            <form action="${action}" method="post">
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Mật khẩu</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <div class="mb-3">
                    <label for="confirmPassword" class="form-label">Xác nhận mật khẩu</label>
                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                </div>
                <c:if test="${param.error != null}">
                    <div class="alert alert-danger">Email không hợp lệ hoặc đã tồn tại hoặc mật khẩu không khớp.</div>
                </c:if>
                <button type="submit" class="btn btn-primary">Đăng ký</button>
            </form>
        </div>
        <div id="success-toast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="toast-header">
                <strong class="me-auto">Thành công</strong>
                <small class="text-muted">Bây giờ</small>
                <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
            <div class="toast-body">
                Đăng ký thành công.
            </div>
        </div>

        <!-- Kiểm tra giá trị param.success và hiển thị toast tương ứng -->
        <c:if test="${param.success != null}">
            <script>
                var successToast = new bootstrap.Toast(document.getElementById('success-toast'));
                successToast.show();
            </script>
        </c:if>
    </body>
</html>
