<%-- 
    Document   : addForum
    Created on : Sep 10, 2023, 9:37:56 AM
    Author     : nguye
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Diễn đàn trao đổi học tập</title>
</head>
<body>
    <h1 class="text-center text-info mt-1">THÊM BÀI ĐĂNG</h1>
<c:url value="/addForum" var="action" />
<form:form modelAttribute="forum" method="post" action="${action}">
    <input type="hidden" id="id" name="id" path="id" />
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="title" id="title" placeholder="Tiêu đề" name="title" />
        <label for="title">Tiêu đề</label>
        <form:errors path="title" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating">
        <form:textarea class="form-control" id="description" name="description" path="description" placeholder="Mô tả"></form:textarea>
        <label for="description">Mô tả</label>
        <form:errors path="description" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:textarea class="form-control" id="content" name="content" path="content" placeholder="Nội dung"></form:textarea>
        <label for="content">Nội dung</label>
        <form:errors path="content" element="div" cssClass="text-danger" />
    </div>
 
    <!--<input type="hidden" id="subjectTeacherId" name="subjectTeacherId" path="subjectTeacherId" />-->
      <label for="subjTeachSelect">Chọn giáo viên - môn học</label>
  <div class="form-floating">
    <select class="form-select" id="subjTeachSelect" name="subjectTeacherId.id">
        <option>Chọn giáo viên - môn học</option>
        <c:forEach items="${subjteachs}" var="subjteach">
            <option value="${subjteach.id}">
                ${subjteach.teacherId} - ${subjteach.subjectId}
            </option>
        </c:forEach>
    </select>
</div>
</div>
    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info">Thêm bài đăng</button>
    </div>
</form:form>
</body>
</html>

