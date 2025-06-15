
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <div class="fa-registered-form">
        <div class="main-div">
            <div class="container-fluid" >
                <section class="gradient-custom">
                    <div class="page-wrapper">
                        <div class="row d-flex justify-content-center align-items-center">
                            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                                <div class="card text-white" style="border-radius: 1rem; background-color: #35bf76;">
                                    <div class="card-body p-5">
                                        <div class="mb-md-5 mt-md-4 pb-5 text-center">
                                            <h2 class="fw-bold mb-2 text-uppercase">Login</h2>
                                            <p class="text-white-50 mb-5">Please enter your login and password!</p>
                                            <form action="j_spring_security_check" id="formRegister" method="post">
                                                <div class="form-outline form-white mb-4">
                                                    <label class="form-label" for="fullName">Fullname</label>
                                                    <input type="text" class="form-control" id="fullName" name="fullName" placeholder="Họ và tên">
                                                </div>

                                                <div class="form-outline form-white mb-4">
                                                    <label class="form-label" for="username">Username</label>
                                                    <input type="text" class="form-control" id="username" name="userName" placeholder="Tên đăng nhập">
                                                </div>

                                                <div class="form-outline form-white mb-4">
                                                    <label class="form-label" for="password">Password</label>
                                                    <input type="password" class="form-control" id="password" name="password" placeholder="Mật khẩu ">
                                                </div>

                                                <div class="form-outline form-white mb-4">
                                                    <label class="form-label" for="repeatpass">Repeat your password</label>
                                                    <input type="password" class="form-control" id="repeatpass" name="j_repeatpass" placeholder="Tên đăng nhập">
                                                </div>



                                                <div class="form-check d-flex justify-content-center mb-2">
                                                    <input class="form-check-input me-2" type="checkbox" value=""
                                                           id="form2Example3cg" />
                                                    <label class="form-check-label">
                                                        I agree all statements in <a href="#!" class="text-body"><u
                                                            style="color: white ;">Terms of
                                                        service</u></a>
                                                    </label>
                                                </div>


                                                <button type="button" class="btn btn-primary" id="btnRegister" >Đăng Ký</button>

                                                <div class="d-flex justify-content-center text-center mt-2 pt-1">
                                                    <a href="#!" class="login-extension text-white"><i
                                                            class="fab fa-facebook-f fa-lg"></i></a>
                                                    <a href="#!" class="login-extension text-white"><i
                                                            class="fab fa-twitter fa-lg mx-4 px-2"></i></a>
                                                    <a href="#!" class="login-extension text-white"><i
                                                            class="fab fa-google fa-lg"></i></a>
                                                </div>

                                                <p class="text-center text-muted mt-2 mb-0">Have already an account? <a href="/login"
                                                                                                                        class="fw-bold text-body"><u style="color: white ;">Login here</u></a></p>
                                            </form>

                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
            <%--<script src="./assets/dist/js/boostrap-v5/bootstrap.js"></script>--%>
            <%--<script src="./assets/dist/js/fontawsome-v5/all.js"></script>--%>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function registerNewUser(data) {
        $.ajax({
            type: "POST",
            url: "/api/user/register",
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function (response) {
                alert('Đăng ký thành công');
                window.location.href = '<c:url value="/login"/>';
            },
            error: function (xhr) {
                $('.error-message').remove(); // Xóa thông báo lỗi cũ

                if (xhr.status === 400) {
                    $('#username').after('<span class="error-message" style="color: red">' + xhr.responseText + '</span>');
                } else {
                    alert("Đăng ký thất bại: " + xhr.responseText);
                }
            }
        });
    }

    $('#btnRegister').click(function (e) {
        e.preventDefault();
        $('.error-message').remove();
        var data = $('#formRegister').serializeArray();
        var json = {};
        var isValid = true;

        $.each(data, function (i, field) {
            json[field.name] = field.value.trim();
        });

        if(json['fullName'] === '' || json['fullName'] === null){
            isValid = false;
            $('#fullname').after('<span class="error-message" style="color: red">Vui lòng nhập họ và tên</span>');
        }
        if (json['userName'] === '' || json['userName'] === null) {
            isValid = false;
            $('#username').after('<span class="error-message" style="color: red">Vui lòng nhập tên đăng nhập</span>');
        }
        if (json['password'] === '' || json['password'] === null) {
            isValid = false;
            $('#password').after('<span class="error-message" style="color: red">Vui lòng nhập mật khẩu</span>');
        }
        if (json['j_repeatpass'] === '' || json['j_repeatpass'] === null) {
            isValid = false;
            $('#repeatpass').after('<span class="error-message" style="color: red">Vui lòng nhập lại mật khẩu</span>');
        }
        if (json['password'] !== json['j_repeatpass']) {
            isValid = false;
            $('#repeatpass').after('<span class="error-message" style="color: red">Mật khẩu không trùng khớp</span>');
        }
        if (json['password'].length < 6){
            isValid = false;
            $('#password').after('<span class="error-message" style="color: red">Độ dài mật khẩu ít nhất 6 ký tự</span>');
        }

        if(isValid){
            registerNewUser(json);
        }
        else
        {
            alert('Vui lòng kiểm tra lại thông tin');
        }
    });
</script>
</body>
</html>
