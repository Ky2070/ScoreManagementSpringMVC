<%-- 
    Document   : login
    Created on : Sep 6, 2023, 10:54:33 AM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng nhập</title>
    </head>
    <body>


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
                                                <h4 class="text-dark mb-4">Spring Security</h4>
                                                <p class="lead text-dark">"Best investment i made for a long time. Can only
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
    </body>
</html>
