<%-- 
    Document   : header
    Created on : Aug 24, 2023, 2:38:58 PM
    Author     : vhuunghia
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!-- nav menu -->



<div class="sticky-top row">
    <nav class="navbar navbar-light bg-light sticky-top navbar-expand-sm">

        <div class="container-fluid">
            <div class="header_nav_title">
                <a class="navbar-brand title_nav header_nav_title" href="/">
                    <img src="https://filethpt.hcm.edu.vn//UploadImages/Config/thptphandangluu/logo%20Truong%20THPT%20PDL%20(3).jpg"
                         alt="" width="70" height="54" class="d-inline-block align-text-top">

                    <h1 style="font-size: 1.1vw; color: rgb(19, 3, 80); font-weight: 700; padding-top: 7px; padding-left: 5px;">
                        Trường Đại Học <br>HCM
                    </h1>
                </a>
            </div>

            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                    <c:url value="/" var="action"/>
                    <li class="nav-item ">
                        <a class="nav-link" aria-current="page" href="${action}"><i class="fas fa-home"></i> Trang chủ</a>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="departmentDropdownButton" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="fa-solid fa-shapes"></i> Khoa-Ban
                        </a>
                        <ul class="dropdown-menu bg-light" aria-labelledby="departmentDropdownButton">
                            <c:forEach items="${departments}" var="departments">
                                <li><a class="dropdown-item"  href="major?departmentId=${departments.id}">${departments.departmentName}</a></li>
                                </c:forEach>
                        </ul>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="#"><i class="fas fa-tv"></i> Cựu Sinh Viên</a>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="departmentDropdownButton" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="fa-solid fa-school"></i> Đào tạo
                        </a>
                        <ul class="dropdown-menu bg-light" aria-labelledby="departmentDropdownButton">
                            <c:forEach items="${trainingTypes}" var="trainingTypes">
                                <li><a class="dropdown-item" href="trainingtype?trainingtypeId=${trainingTypes.id}">${trainingTypes.trainingTypeName}</a></li>
                                </c:forEach>
                        </ul>
                    </li>
                    <li>
                      
                        


                        <a class="btn btn-success" href="<c:url value='/register' />">Đăng ký người dùng</a>

                    </li>

            </div>
            <form class="d-flex" action="/">
                <input class="form-control me-2" name="keyword" type="text" placeholder="Search">
                <button class="btn btn-dark" type="submit"><i class="fas fa-search" style="font-size: 20px"></i></button>
            </form>
            </ul>
        </div>
</div>

<div>


</div>


</nav>

</div>



