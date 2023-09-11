<%-- 
    Document   : forum
    Created on : Sep 8, 2023, 6:20:33 AM
    Author     : nguye
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Diễn đàn trao đổi học tập</title>
    </head>
    <body>
        <div class="form-group">
            <label for="subjTeachSelect">Diễn đàn theo môn học</label>
            <select class="form-select" id="subjTeachSelect" onchange="navigateToForum()">
                <option>Chọn môn học</option>
                <option value="all" data-url="/WebAppSpringMVC/forum">Xem tất cả</option>
                <c:forEach items="${subjteachs}" var="subjteach">  
                    <option value="${subjteach.id}" data-url="/WebAppSpringMVC/forumBySubjectTeacher?subjectTeacherId=${subjteach.id}"> 
                        ${subjteach.teacherId} - ${subjteach.subjectId}
                    </option>
                </c:forEach>

            </select>
        </div>
        <script>
            function navigateToForum() {
                var select = document.getElementById("subjTeachSelect");
                var selectedOption = select.options[select.selectedIndex];
                var href = selectedOption.getAttribute("data-url");
                window.location.href = href; // Chuyển hướng đến URL đã lưu trong thuộc tính data-url
            }
        </script>

        <div class="container">
            <h1>Các diễn đàn</h1>
            <div class="row">
                <c:choose>
                    <c:when test="${empty forums}">
                        <div class="col-md-12">
                            <div class="alert alert-primary" role="alert">
                                Hiện tại chưa có bài đăng.
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${forums}" var="forum">
                            <div class="col-md-4">
                                <div class="card">
                                    <div class="card-body">
                                        <h6 class="card-title">${forum.subjectTeacherId}</h6>
                                        <h5 class="card-title">${forum.title} - ${forum.description}</h5>
                                        <p class="card-text">${forum.content}</p>
                                        <a href="#" class="btn btn-primary">Phản hồi</a>
                                        <c:url value="/deleteForum?forumId=${forum.id}" var="action" />
                                    </div>
                                    <form method="post" action="${action}" class="delete-form">
                                        <input type="hidden" name="forumId" value="${forum.id}">
                                        <button type="button" class="btn btn-danger" onclick="confirmDelete('${forum.id}')">Xóa bài đăng</button>
                                    </form>
                                </div>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <!-- Nút "Thêm bài đăng" ngoài modal -->
        <div class="container mt-3">
            <button type="button" class="btn btn-primary" onclick="navigateToAddPost()">Thêm bài đăng</button>
        </div>

        <script>
            // Hàm để chuyển hướng đến trang thêm bài đăng
            function navigateToAddPost() {
                window.location.href = "/WebAppSpringMVC/addForumPage"; // Thay đổi đường dẫn theo trang thêm bài đăng của bạn
            }
        </script>
        <script>
            function confirmDelete(forumId) {
                if (window.confirm("Bạn có chắc chắn muốn xóa bài đăng này không?")) {
                    const form = document.querySelector('.delete-form');
                    form.id = `delete-form-${forumId}`;
                    form.submit();
                }
            }
        </script>
    </body>

</html>
