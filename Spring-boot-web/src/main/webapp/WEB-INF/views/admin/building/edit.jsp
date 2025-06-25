    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 12/25/2024
  Time: 2:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<html>
<head>
    <title>Danh Sách Tòa Nhà</title>
</head>
<body>
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try{ace.settings.check('main-container' , 'fixed')}catch(e){}
    </script>

    <div id="sidebar" class="sidebar responsive" style="border-color: white; background-color: white;">
        <script type="text/javascript">
            try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
        </script>
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
                        Thông tin tòa nhà
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            overview &amp; stats
                        </small>
                    </h1>
                </div><!-- /.page-header -->
                <div class="row">
                    <div class="col-xs-12">
                        <form:form class="form-horizontal" role="form" id="from-edit" modelAttribute="building" >
                            <div class="form-group">
                                <label class="col-xs-3 control-label">Tên tòa nhà</label>
                                <div class="col-xs-9" >
<%--                                    <input type="text" class="form-control" name="name">--%>
                                    <form:input path="name" class="form-control" id="name" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">Quận</label>
                                <div class="col-xs-2" >
                                    <form:select path="district" class="form-control" id="district">
                                        <option value=""> -- Chọn Quận --</option>
                                        <form:options items="${districts}" ></form:options>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">Phường</label>
                                <div class="col-xs-9" >
<%--                                    <input type="text" class="form-control" name="ward">--%>
                                    <form:input path="ward" class="form-control" id = "ward"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">Đường</label>
                                <div class="col-xs-9" >
<%--                                    <input type="text" class="form-control" name="street">--%>
                                    <form:input path="street" class="form-control" id="street" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">Kết cấu</label>
                                <div class="col-xs-9" >
<%--                                    <input type="text" class="form-control" name="structure">--%>
                                    <form:input path="structure" class="form-control" id ="structure" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">Số tầng hầm</label>
                                <div class="col-xs-9" >
<%--                                    <input type="number" class="form-control" name="numberOfBasement">--%>
                                    <form:input path="numberOfBasement" class="form-control" id ="numberOfBasement" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">Diện tích sàn</label>
                                <div class="col-xs-9" >
<%--                                    <input type="number" class="form-control" name="floorArea">--%>
                                    <form:input path="floorArea" class="form-control" id ="floorArea" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">Hướng</label>
                                <div class="col-xs-9" >
<%--                                    <input type="text" class="form-control" name="direction">--%>
                                    <form:input path="direction" class="form-control" id ="direction" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">Hạng</label>
                                <div class="col-xs-9" >
<%--                                    <input type="text" class="form-control" name="level">--%>
                                    <form:input path="level" class="form-control" id ="level" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">Diện tích thuê</label>
                                <div class="col-xs-9" >
<%--                                    <input type="text" class="form-control" name="rentArea">--%>
                                    <form:input path="rentArea" class="form-control" id="rentArea"  />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">Giá thuê</label>
                                <div class="col-xs-9" >
<%--                                    <input type="number" class="form-control" name="rentPrice">--%>
                                    <form:input path="rentPrice" class="form-control" id="rentPrice"  />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">Mô tả giá</label>
                                <div class="col-xs-9" >
<%--                                    <input type="text" class="form-control" name="rentPriceDescription">--%>
                                    <form:input path="rentPriceDescription" class="form-control" id="rentPriceDescription" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">Phí dịch vụ</label>
                                <div class="col-xs-9" >
<%--                                    <input type="text" class="form-control" name="serviceFee">--%>
                                    <form:input path="serviceFee" class="form-control" id="serviceFee"  />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">Phí ô tô</label>
                                <div class="col-xs-9" >
<%--                                    <input type="text" class="form-control" name="carFee">--%>
                                    <form:input path="carFee" class="form-control" id="carFee" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">Phí mô tô</label>
                                <div class="col-xs-9" >
<%--                                    <input type="text" class="form-control" name="motoFee">--%>
                                    <form:input path="motoFee" class="form-control" id="motoFee" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">Phí ngoài giờ</label>
                                <div class="col-xs-9" >
<%--                                    <input type="text" class="form-control" name="overtimeFee">--%>
                                    <form:input path="overtimeFee" class="form-control" id="overtimeFee" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">Tiền điện</label>
                                <div class="col-xs-9" >
<%--                                    <input type="text" class="form-control" name="electricityFee">--%>
                                    <form:input path="electricityFee" class="form-control" id="electricityFee" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">Tiền nước</label>
                                <div class="col-xs-9" >
<%--                                    <input type="text" class="form-control" name="waterFee">--%>
                                    <form:input path="waterFee" class="form-control" id="waterFee" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">Đặt cọc</label>
                                <div class="col-xs-9" >
<%--                                    <input type="text" class="form-control" name="deposit">--%>
                                    <form:input path="deposit" class="form-control" id="deposit" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">Thanh toán</label>
                                <div class="col-xs-9" >
<%--                                    <input type="text" class="form-control" name="payment">--%>
                                    <form:input path="payment" class="form-control" id="payment" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">Thời hạn thuê</label>
                                <div class="col-xs-9" >
<%--                                    <input type="text" class="form-control" name="rentTime">--%>
                                    <form:input path="rentTime" class="form-control" id="rentTime" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">Thời gian trang thí</label>
                                <div class="col-xs-9" >
<%--                                    <input type="text" class="form-control" name="decorationTime">--%>
                                    <form:input path="decorationTime" class="form-control"  id="decorationTime" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">Tên quản lý</label>
                                <div class="col-xs-9" >
<%--                                    <input type="text" class="form-control" name="managerName">--%>
                                    <form:input path="managerName" class="form-control" id="managerName" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">SĐT Quản Lý</label>
                                <div class="col-xs-9" >
<%--                                    <input type="number" class="form-control" name="managerPhone">--%>
                                    <form:input path="managerPhone" class="form-control" id="managerPhone" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">Loại tòa nhà</label>
                                <div class="col-xs-9" >
                                    <form:checkboxes path="typeCode" items="${typeCodes}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">Phí môi giới</label>
                                <div class="col-xs-9" >
<%--                                    <input type="number" class="form-control" name="brokerageFee">--%>
                                    <form:input path="brokerageFee" class="form-control" id="brokerageFee"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">Ghi chú</label>
                                <div class="col-xs-9" >
<%--                                    <input type="text" class="form-control" name="note">--%>
                                    <form:input path="note" class="form-control" id="note" />
                                </div>
                            </div>
<%--                            <div class="form-group">--%>
<%--                                <label class="col-xs-3 control-label">Hình đại diện</label>--%>

<%--                                <div class="col-xs-9">--%>
<%--                                    <input class="col-xs-3 no-padding-right" type="file" id="uploadImage"/>--%>
<%--                                    <c:if test="${not empty building.avatar}">--%>
<%--                                        <c:set var="imagePath" value="/repository${building.avatar}"/>--%>
<%--                                        <img src="${imagePath}" id="viewImage" width="300px" height="300px" style="margin-top: 50px">--%>
<%--                                    </c:if>--%>
<%--                                    <c:if test="${empty building.avatar}">--%>
<%--                                        <img src="/admin/image/default.png" id="viewImage" width="300px" height="300px">--%>
<%--                                    </c:if>--%>
<%--                                </div>--%>

<%--                            </div>--%>
                            <div class="form-group">
                                <label class="col-xs-3 control-label">Hình đại diện</label>
                                <div class="col-xs-9">
                                    <input type="file" id="uploadImage" style="width: auto;" />
                                    <div style="margin-top: 15px;">
                                        <c:if test="${not empty building.avatar}">
                                            <c:set var="imagePath" value="/repository${building.avatar}" />
                                            <img src="${imagePath}" id="viewImage" class="img-responsive" style="max-width: 300px; height: auto; margin-top: 15px;">
                                        </c:if>
                                        <c:if test="${empty building.avatar}">
                                            <img src="/admin/image/default.png" id="viewImage" class="img-responsive" style="max-width: 300px; height: auto; margin-top: 15px;">
                                        </c:if>
                                    </div>
                                </div>
                            </div>


                            <!-- Button -->
                            <div class="form-group">
                                <label class="col-xs-3 control-label"></label>
                                <div class="col-xs-9" >
                                    <c:if test="${not empty building.id}">
                                        <button type="button" class="btn btn-dark" id="btnAddBuilding">Sửa Tòa Nhà</button>
                                    </c:if>
                                    <c:if test="${empty building.id}">
                                        <button type="button" class="btn btn-primary" id="btnAddBuilding">Thêm Tòa Nhà</button>
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
</div><!-- /.main-container -->

<!--[if !IE]> -->
<script src="assets/js/jquery.2.1.1.min.js"></script>

<!-- <![endif]-->

<script>

    var imageBase64 = '';
    var imageName = '';
    $('#uploadImage').change(function (event) {
        var reader = new FileReader();
        var file = $(this)[0].files[0];
        reader.onload = function(e){
            imageBase64 = e.target.result;
            imageName = file.name; // ten hinh khong dau, khoang cach. vd: a-b-c
        };
        reader.readAsDataURL(file);
        openImage(this, "viewImage");
    });

    function openImage(input, imageView) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' +imageView).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    $('#btnAddBuilding').click(function (e) {
            e.preventDefault();
            // Đổ dữ liệu từ form vào mảng
            var formData = $('#from-edit').serializeArray(); // Mảng các đổi tượng
            var json = {};
            var typeCode = [];
            var isValid = true;
            $.each(formData, function (i, field) {
                if(field.name != "typeCode"){
                    json["" + field.name + ""] = field.value;
                }
                else{
                    typeCode.push(field.value);
                }

            });


            json["typeCode"] = typeCode;
        // Bổ sung dữ liệu ảnh
        if (imageBase64) {
            json["imageBase64"] = imageBase64; // Nội dung ảnh
            json["imageName"] = imageName;     // Tên ảnh
        }
            // Thử Nghiệm kiem tra từng trường
            if (json['name'] == '' || json['name'] == null) {
                $('#name').after('<span class="error-message" style="color: red">Vui lòng nhập tên tòa nhà</span>');
                isValid = false;
            }
            if (json['district'] == '' || json['district'] == null) {
                $('#district').after('<span class="error-message" style="color: red">Vui lòng chọn quận</span>');
                isValid = false;
            }
            if (json['ward'] == '' || json['ward'] == null) {
                $('#ward').after('<span class="error-message" style="color: red">Vui lòng nhập phường</span>');
                isValid = false;
            }
            if (json['rentPrice'] == '' || isNaN(json['rentPrice']) || parseFloat(json['rentPrice']) <= 0) {
                $('#rentPrice').after('<span class="error-message" style="color: red">Vui lòng nhập giá thuê hợp lệ</span>');
                isValid = false;
            }
            if (json['brokerageFee'] == '' || isNaN(json['brokerageFee']) || parseFloat(json['brokerageFee']) <= 0) {
                $('#brokerageFee').after('<span class="error-message" style="color: red">Vui lòng nhập phí môi giới hợp lệ</span>');
                isValid = false;
            }
            if (json['floorArea'] == '' || isNaN(json['floorArea']) || parseFloat(json['floorArea']) <= 0) {
                $('#floorArea').after('<span class="error-message" style="color: red">Vui lòng nhập diện tích hợp lệ</span>');
                isValid = false;
            }
            if (json['serviceFee'] == '' || isNaN(json['serviceFee']) || parseFloat(json['serviceFee']) <= 0) {
                $('#serviceFee').after('<span class="error-message" style="color: red">Vui lòng nhập phí dịch vụ hợp lệ</span>');
                isValid = false;
            }
            if (json['carFee'] == '' || isNaN(json['carFee'])) {
                $('#carFee').after('<span class="error-message" style="color: red">Vui lòng nhập phí ô tô hợp lệ</span>');
                isValid = false;
            }
            if (json['motoFee'] == '' || isNaN(json['motoFee'])) {
                $('#motoFee').after('<span class="error-message" style="color: red">Vui lòng nhập phí mô tô hợp lệ</span>');
                isValid = false;
            }
            if (json['overtimeFee'] == '' || isNaN(json['overtimeFee'])) {
                $('#overtimeFee').after('<span class="error-message" style="color: red">Vui lòng nhập phí ngoài giờ hợp lệ</span>');
                isValid = false;
            }
            if (json['electricityFee'] == '' || isNaN(json['electricityFee'])) {
                $('#electricityFee').after('<span class="error-message" style="color: red">Vui lòng nhập tiền điện hợp lệ</span>');
                isValid = false;
            }
            if (json['waterFee'] == '' || isNaN(json['waterFee'])) {
                $('#waterFee').after('<span class="error-message" style="color: red">Vui lòng nhập tiền nước hợp lệ</span>');
                isValid = false;
            }
            if (json['deposit'] == '' || isNaN(json['deposit'])) {
                $('#deposit').after('<span class="error-message" style="color: red">Vui lòng nhập tiền đặt cọc hợp lệ</span>');
                isValid = false;
            }
            if (json['rentTime'] == '' || isNaN(json['rentTime']) ) {
                $('#rentTime').after('<span class="error-message" style="color: red">Vui lòng nhập thời hạn thuê hợp lệ</span>');
                isValid = false;
            }
            if (json['decorationTime'] == '' || isNaN(json['decorationTime'])) {
                $('#decorationTime').after('<span class="error-message" style="color: red">Vui lòng nhập thời gian trang trí hợp lệ</span>');
                isValid = false;
            }
            if (isValid){
                AddBuilding(json);
            }
            else{
                alert('Vui lòng nhập đầy đủ thông tin');
            }
        })

        function AddBuilding(data){
            // Kiểm tra id có tồn tại hay không
            const isUpdate = data.id !== undefined && data.id !== null && data.id !== "";
            // Nếu có id thì là update, không có id thì là thêm mới
            const action = isUpdate ? "Sửa" : "Thêm";
            $.ajax({
                url: '/api/building',
                type: 'POST',
                data: JSON.stringify(data), // Convert từ Object sang JSON
                contentType: 'application/json', // Kiểu dữ liệu gửi đi là JSON
                success: function (response) {
                    alert(action + ' tòa nhà thành công');
                    window.location.href="<c:url value="/admin/building-list" />"
                    console.log('Success');
                },
                error: function (response) {
                    console.log('Fail');
                    alert(response.responseJSON);
                }
            })
        }

        // Hủy thao tác
        $('#btnCancelBuilding').click(function (e) {
            e.preventDefault();
            window.location.href="<c:url value="/admin/building-list" />"
        })



</script>
</body>
</html>
