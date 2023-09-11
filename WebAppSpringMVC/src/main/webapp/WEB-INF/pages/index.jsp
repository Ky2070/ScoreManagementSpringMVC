<%-- 
    Document   : index
    Created on : Jul 13, 2023, 4:12:54 PM
    Author     : Kiet
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <div class="row">
    <div class="topnav">
                <!-- Người dùng đã đăng nhập -->
                <a class="nav-link" href="<c:url value='/forum' />"><i class="fas fa-newspaper"></i> Diễn đàn</a>
    </div>
</div>
        <!-- Content -->
        <div class="container mt-4">
            <!-- Tin tức -->
            <div class="row">
                <div class="col-md-4">
                    <div class="card mb-4">
                        <div class="card-body">
                            <h5 class="card-title">Trường Đại học TP. Hồ Chí Minh đăng cai tổ chức Cuộc thi Tài Năng Anh ngữ lần 2 năm 2023 – Chủ đề “Em yêu Thành phố của em”</h5>
                            <p class="card-text">Sáng ngày 27/8/2023, tại Hội trường 602 cơ sở Võ Văn Tần, Trường Đại Học Mở TP. Hồ Chí Minh đăng cai  tổ chức cuộc thi Tài năng Anh ngữ lần 2 năm 2023 phối hợp cùng với Hội đồng Đội TP. Hồ Chí Minh và Trung tâm Phát triển Khoa học và Công nghệ Trẻ, Hệ thống Anh văn Hội Việt Mỹ VUS.

                                Cuộc thi nhằm  thúc đẩy phong trào thi đua học tập và kỹ năng tự rèn luyện tiếng Anh, nâng cao khả năng sử dụng ngoại ngữ, đặc biệt là kỹ năng giao tiếp và kỹ năng thuyết trình dành cho học sinh các cấp tại Thành phố Hồ Chí Minh....</p>
                            <a href="#" class="btn btn-primary">Xem thêm</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card mb-4">
                        <div class="card-body">
                            <h5 class="card-title">Hội thảo khoa học “The International Conference on Accounting, Economics, Finance, and Management: A Sustainability Development Perspective (ICAAFM 2023)</h5>
                            <p class="card-text">Sáng ngày 25/8/2023 tại Thành phố Nha Trang, Trường Đại học Mở Thành phố Hồ Chí Minh phối hợp cùng Trường Đại học Nha Trang tổ chức Hội thảo khoa học quốc tế với chủ đề: “The International Conference on Accounting, Economics, Finance, and Management: A Sustainability Development Perspective (ICAAFM 2023)”. Hội thảo được tổ chức với hình thức trực tiếp và trực tuyến....</p>
                            <a href="#" class="btn btn-primary">Xem thêm</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card mb-4">
                        <div class="card-body">
                            <h5 class="card-title">Thông báo tuyển sinh Đại học Chính quy năm 2022</h5>
                            <p class="card-text">Căn cứ Thông tư số 07/VBHN-BGDĐT ngày 20/12/2021 ban hành Quy chế tuyển sinh trình độ đại học; tuyển sinh trình độ cao đẳng ngành Giáo dục mầm non, hợp nhất Thông tư số 09/2020/TT-BGDĐT ngày 07 tháng 5 năm 2020 của Bộ trưởng Bộ GD&ĐT và Thông tư số 16/2021/TT-BGDĐT về sửa đổi, bổ sung Thông tư số 09/2020 của Bộ trưởng Bộ GDĐT ngày 01/6/2021,...</p>
                            <a href="#" class="btn btn-primary">Xem thêm</a>
                        </div>
                    </div>
                </div>
            </div>
<!--            <div class="accordion" id="myAccordion">
                <div class="accordion-item">
                    <h2 class="accordion-header" id="section1Header">
                        <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#section1Content" aria-expanded="true" aria-controls="section1Content">
                            Section 1
                        </button>
                    </h2>
                    <div id="section1Content" class="accordion-collapse collapse show" aria-labelledby="section1Header">
                        <div class="accordion-body">
                            Content for section 1.
                        </div>
                    </div>
                </div>
                <div class="accordion-item">
                     Add more accordion sections here 
                </div>
            </div>
            <ul class="nav nav-tabs" id="myTabs" role="tablist">
                <li class="nav-item" role="presentation">
                    <a class="nav-link active" id="tab1-tab" data-bs-toggle="tab" href="#tab1" role="tab" aria-controls="tab1" aria-selected="true">Tab 1</a>
                </li>
                <li class="nav-item" role="presentation">
                    <a class="nav-link" id="tab2-tab" data-bs-toggle="tab" href="#tab2" role="tab" aria-controls="tab2" aria-selected="false">Tab 2</a>
                </li>
                 Add more tabs here 
            </ul>
            <div class="tab-content" id="myTabsContent">
                <div class="tab-pane fade show active" id="tab1" role="tabpanel" aria-labelledby="tab1-tab">
                    Content for Tab 1.
                </div>
                <div class="tab-pane fade" id="tab2" role="tabpanel" aria-labelledby="tab2-tab">
                    Content for Tab 2.
                </div>
                 Add more tab content here 
            </div>-->
        </div>
        <div class="container">
            <h2>Tin Tức</h2>
            <div class="row">
                <div class="col-md-4">
                    <div class="news-item">

                        <h3>THÔNG BÁO TUYỂN SINH NGHIÊN CỨU SINH NĂM 2023 – ĐỢT 2</h3>
                        <p>Thời gian đào tạo tiêu chuẩn của trình độ tiến sĩ là 03 năm. Nghiên cứu sinh được phép hoàn thành chương trình đào tạo sớm hơn so với kế hoạch học tập, nghiên cứu toàn khóa không quá 01 năm (12 tháng), hoặc chậm hơn...</p>
                        <a href="#" class="btn btn-primary">Xem thêm</a>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="news-item">

                        <h3>Tuyển sinh Thạc sĩ MBA liên kết với Đại học Fresenius, Đức – Giai đoạn...</h3>
                        <p>LIÊN KẾT VỚI ĐẠI HỌC FRESENIUS (ĐỨC)
                            GIAI ĐOẠN 1 – ĐỢT 2 NĂM 2023</p>
                        <a href="#" class="btn btn-primary">Xem thêm</a>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="news-item">

                        <h3>Tuyển sinh đại học chính quy năm 2023</h3>
                        <p>...</p>
                        <a href="#" class="btn btn-primary">Xem thêm</a>
                    </div>
                </div>
                <!-- Thêm các tin tức giả lập khác tương tự -->
            </div>
        </div>
    </body>
</html>
