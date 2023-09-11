<%-- 
    Document   : base
    Created on : Aug 24, 2023, 2:33:53 PM
    Author     : vhuunghia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <tiles:insertAttribute name="title"/>
        </title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
        <!-- Thư viện jQuery từ nguồn chính thống -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.8.0/html2pdf.bundle.js" ></script>
        
        <link href="<c:url value="/WEB-INF/static/css/base.css"  />" rel="stylesheet"/>
        <link href="<c:url value="/WEB-INF/static/css/login.css"  />" rel="stylesheet"/>
        <link href="<c:url value="/WEB-INF/static/css/register.css"  />" rel="stylesheet"/>
        <link href="<c:url value="/WEB-INF/static/css/style.css"  />" rel="stylesheet"/>

    </head>
    <body>
        <!--HEADER -->
        <tiles:insertAttribute name="header"/>
         <div class="container">
              <tiles:insertAttribute name="content"/> 
        </div>
        <!--FOOTER -->
        <tiles:insertAttribute name="footer"/>
    </div>
</body>
</html>
