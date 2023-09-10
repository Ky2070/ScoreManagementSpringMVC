<%-- 
    Document   : header
    Created on : Aug 24, 2023, 2:38:58 PM
    Author     : vhuunghia
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="row">
    <div id="carouselExampleDark" class="carousel carousel-dark slide" data-bs-ride="carousel">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="0" class="active"
                    aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="1"
                    aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="2"
                    aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
            <div class="carousel-item active" data-bs-interval="10000">
                <img src="https://jes.edu.vn/wp-content/uploads/2021/06/Cac-truong-thpt-dan-lap-o-ha-noi.jpg"
                     class="d-block w-100" alt="...">
                <div class="carousel-caption d-none d-md-block">
                    <h5>First slide label</h5>
                    <p>Some representative placeholder content for the first slide.</p>
                </div>
            </div>
            <div class="carousel-item" data-bs-interval="2000">
                <img src="https://toplist.vn/images/800px/truong-thpt-chuyen-hung-vuong-679866.jpg"
                     class="d-block w-100" alt="...">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Second slide label</h5>
                    <p>Some representative placeholder content for the second slide.</p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="https://images6.alphacoders.com/113/1136461.jpg" class="d-block w-100" alt="...">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Third slide label</h5>
                    <p>Some representative placeholder content for the third slide.</p>
                </div>
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
</div>
<!--  -->


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

                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="#"><i class="fas fa-globe-europe"></i> Đăng nhập</a>
                    </li>
                </ul>
            </div>
        </div>

        <div>

            <form class="d-flex" action="/">
                <input class="form-control me-2" name="keyword" type="text" placeholder="Search">
                <button class="btn btn-dark" type="submit"><i class="fas fa-search" style="font-size: 20px"></i></button>
            </form>
        </div>
    </nav>

</div>
    

    
