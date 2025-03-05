<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 3/4/2025
  Time: 10:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try{ace.settings.check('main-container' , 'fixed')}catch(e){}
    </script>

    <div id="sidebar" class="sidebar                  responsive">
        <script type="text/javascript">
            try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
        </script>

        <div class="sidebar-shortcuts" id="sidebar-shortcuts">
            <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
                <button class="btn btn-success">
                    <i class="ace-icon fa fa-signal"></i>
                </button>

                <button class="btn btn-info">
                    <i class="ace-icon fa fa-pencil"></i>
                </button>

                <button class="btn btn-warning">
                    <i class="ace-icon fa fa-users"></i>
                </button>

                <button class="btn btn-danger">
                    <i class="ace-icon fa fa-cogs"></i>
                </button>
            </div>

            <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
                <span class="btn btn-success"></span>

                <span class="btn btn-info"></span>

                <span class="btn btn-warning"></span>

                <span class="btn btn-danger"></span>
            </div>
        </div><!-- /.sidebar-shortcuts -->


    </div>

    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">Home</a>
                    </li>
                    <li class="active">Dashboard</li>
                </ul><!-- /.breadcrumb -->

            </div>

            <div class="page-content">

                <div class="page-header">
                    <h1>
                        Thông tin Khách Hàng
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            overview &amp; stats
                        </small>
                    </h1>
                </div><!-- /.page-header -->
                <div class="row">
                    <div class="col-xs-12">
                        <form:form class="form-horizontal" role="form" id="from-edit" modelAttribute="customer" >
                            <div class="form-group">
                                <label class="col-xs-3 control-label">Tên Khách Hàng</label>
                                <div class="col-xs-9" >
                                    <form:input path="name" class="form-control" id="name" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3 control-label">Số điện thoại</label>
                                <div class="col-xs-9" >
                                    <form:input path="phone" class="form-control" id = "phone"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">Email</label>
                                <div class="col-xs-9" >
                                    <form:input path="email" class="form-control" id="email" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">Tên Công Ty</label>
                                <div class="col-xs-9" >
                                    <form:input path="companyName" class="form-control" id ="companyName" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">Nhu cầu</label>
                                <div class="col-xs-9" >
                                    <form:input path="demand" class="form-control" id ="demand" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">Trạng thái xử lý</label>
                                <form:select path="status" class="form-control" id="status">
                                    <option value=""> -- Chọn trạng thái --</option>
                                    <c:forEach var="entry" items="${status}">
                                        <option value="${entry.key}" ${customer.status == entry.key ? 'selected' : ''}>
                                                ${entry.value}
                                        </option>
                                    </c:forEach>
                                </form:select>
                            </div>


                            <!-- Button -->
                            <div class="form-group">
                                <label class="col-xs-3 control-label"></label>
                                <div class="col-xs-9" >
                                    <c:if test="${not empty customer.id}">
                                        <button type="button" class="btn btn-dark" id="btnAddCustomer">Sửa Tòa Nhà</button>
                                    </c:if>
                                    <c:if test="${empty customer.id}">
                                        <button type="button" class="btn btn-primary" id="btnAddCustomer">Thêm Tòa Nhà</button>
                                    </c:if>

                                    <button type="button" class="btn btn-warning" id="btnCancelBuilding">Hủy Thao Tác</button>


                                </div>
                            </div>
                            <form:hidden path="id"/>
                        </form:form>
                    </div>
                </div>



            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->

</div>

<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
    <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
</a>
<script src="assets/js/jquery.2.1.1.min.js"></script>
<script>

    function AddCustomer(data){
        // Kiểm tra id có tồn tại hay không
        const isUpdate = data.id !== undefined && data.id !== null && data.id !== "";
        // Nếu có id thì là update, không có id thì là thêm mới
        const action = isUpdate ? "Sửa" : "Thêm";
        $.ajax({
            url: '/api/admin/customers',
            type: 'POST',
            data: JSON.stringify(data), // Convert từ Object sang JSON
            contentType: 'application/json', // Kiểu dữ liệu gửi đi là JSON
            success: function (response) {
                alert(action + ' tòa nhà thành công');
                window.location.href="<c:url value="/admin/customer-list" />"
                console.log('Success');
            },
            error: function (response) {
                console.log('Fail');
                alert(response.responseJSON);
            }
        })
    }
    $('#btnAddCustomer').click(function (e) {
        e.preventDefault();
        var formData = $('#from-edit').serializeArray(); // Mảng các đổi tượng
        var json = {};
        $.each(formData, function (i, field) {
            json["" + field.name + ""] = field.value;
        });
        console.log("Dữ liệu gửi API:", json);

        var isValid = true;
        if (isValid){
            AddCustomer(json);
        }
        else{
            alert('Vui lòng nhập đầy đủ thông tin');
        }
    })
</script>
</div><!-- /.main-container -->
</body>
</html>
