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
                        Trường Trung Học Phổ Thông <br>Hồng Hà
                    </h1>
                </a>
            </div>

            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                    <c:url value="/" var="action"/>
                    <li class="nav-item ">
                        <a class="nav-link" aria-current="page" href="${action}"><i class="fas fa-users"></i> Trang chủ</a>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="departmentDropdownButton" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="fa-solid fa-shapes"></i> Khoa-Ban
                        </a>
                        <ul class="dropdown-menu bg-light" aria-labelledby="departmentDropdownButton">
                            <c:forEach items="${departments}" var="departments">
                                <li><a class="dropdown-item" href="#">${departments.departmentName}</a></li>
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
                                <li><a class="dropdown-item" href="#">${trainingTypes.trainingTypeName}</a></li>
                            </c:forEach>
                        </ul>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="#"><i class="fas fa-globe-europe"></i> English</a>
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

    <div class="row">
        <div class="topnav">
            <a class="active" href="#home"><i class="fa-solid fa-house-user"></i> Home</a>
            <a href="/"><i class="fas fa-newspaper"></i> News</a>

        </div>
    </div>
    <!--            
    
    
    
    
    
    <!--ô đăng nhập -->
    <!--{% if current_user.is_authenticated %}
    
        {% else %}-->
    <div class="login_ui">
        <section class="background_login_form" id="bg_login_form">
        </section>
        <div id="main-wrapper" class="container login_form_contain">
            <div class="row justify-content-center login_form">
                <div class="col-xl-10">
                    <div class="card border-0">
                        <div class="card-body p-0">
                            <div class="row no-gutters">
                                <div class="col-lg-6">
                                    <div class="p-5">
                                        <div class="mb-5">
                                            <h3 class="h4 font-weight-bold text-theme">Login</h3>
                                        </div>

                                        <h6 class="h5 mb-0">Welcome back!</h6>
                                        <p class="text-muted mt-2 mb-5">Enter your email address and password to access
                                            admin
                                            panel.</p>

                                        <form method="post" enctype="multipart/form-data" action="/">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">User name</label>
                                                <input type="text" class="form-control" name="username_login" id="exampleInputEmail1">
                                            </div>
                                            <div class="form-group mb-5">
                                                <label for="exampleInputPassword1">Password</label>
                                                <input type="password" class="form-control" name="password_login"  id="exampleInputPassword1">
                                            </div>
                                            <button type="submit" class="btn btn-theme">Login</button>
                                            <a href="#l" class="forgot-link float-right text-primary">Forgot password?</a>
                                        </form>
                                    </div>
                                </div>

                                <div class="col-lg-6 d-none d-lg-inline-block">
                                    <div class="account-block rounded-right">
                                        <div class="overlay rounded-right"></div>
                                        <div class="account-testimonial">
                                            <h4 class="text-white mb-4">Vo Huu Nghia</h4>
                                            <p class="lead text-white">"Best investment i made for a long time. Can only
                                                recommend it for other users."</p>
                                            <p>- Admin User</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <!-- end card-body -->
                    </div>
                    <!-- end card -->


                    <!-- end row -->

                </div>
                <!-- end col -->
            </div>
            <!-- Row -->
        </div>
    </div>



    <!--    {% if err_msg %}
                <div class="alert box_alert" id="box_alert_fail_login">
                    <span class="closebtn">&times;</span>
                    <strong>Cảnh báo</strong> Đăng nhập thất bại do : {{ err_msg }}
                </div>
        {% endif %}
    
     {% endif %}-->

    <script src="{{ url_for('static', filename='js/login.js') }}"></script>

    <script>
var close = document.getElementsByClassName("closebtn");
var i;

for (i = 0; i < close.length; i++) {
    close[i].onclick = function () {
        var div = this.parentElement;
        div.style.opacity = "0";
        setTimeout(function () {
            div.style.display = "none";
        }, 600);
    }
}


setTimeout(() => {
    var b = document.getElementById('box_alert_fail_login');

    if (b != null) {
        console.log(b)
        b.style.display = 'none'
    }

}, 3000)
    </script>
