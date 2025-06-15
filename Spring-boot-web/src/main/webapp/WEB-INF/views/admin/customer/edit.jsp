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
    <title>Khách Hàng</title>
</head>

<body>
<div class="main-container" id="main-container">

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
<%--            đặt chiều cao full--%>

            <div class="page-content ">

                <div class="page-header">
                    <h1>
                        Thông tin Khách Hàng
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            overview &amp; stats
                        </small>
                    </h1>
                </div><!-- /.page-header -->
                <div class="row ">
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
                                        <button type="button" class="btn btn-dark" id="btnAddCustomer">Sửa Khách Hàng</button>
                                    </c:if>
                                    <c:if test="${empty customer.id}">
                                        <button type="button" class="btn btn-primary" id="btnAddCustomer">Thêm Khách Hàng</button>
                                    </c:if>

                                    <button type="button" class="btn btn-warning" id="btnCancel">Hủy Thao Tác</button>


                                </div>
                            </div>
                            <form:hidden path="id"/>
                            <c:if test="${not empty customer.staffIds}">
                                <c:forEach var="id" items="${customer.staffIds}">
                                    <input type="hidden" name="staffIds" value="${id}" />
                                </c:forEach>
                            </c:if>
                        </form:form>
                    </div>
                </div>
<%--            Fix lech mau--%>


                <c:if test="${not empty customer.id}">
                    <div class="col-xs-12">
                        <h2 class="smaller lighter blue">
                            Giao dịch khách hàng
                        </h2>
                    </div>

                    <!-- Bảng dành cho CSKH -->
                    <div class="col-xs-12">
                        <h3 class="smaller lighter blue">
                            Chăm sóc khách hàng (CSKH)
                            <button class="btn btn-md btn-success pull-right" title="Thêm giao dịch"
                                    onclick="addTransaction('CSKH', ${customer.id})">
                                <i class="ace-icon glyphicon glyphicon-plus smaller-80"></i> Thêm giao dịch
                            </button>
                        </h3>
                        <div class="hr hr-16 "></div>
                    </div>
                    <div class="col-xs-12">
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr>
                                <th class="text-center">NGÀY TẠO</th>
                                <th class="text-center">NGƯỜI TẠO</th>
                                <th class="text-center">NGÀY SỬA</th>
                                <th class="text-center">NGƯỜI SỬA</th>
                                <th class="text-center">CHI TIẾT GIAO DỊCH</th>
                                <th class="text-center">THAO TÁC</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:choose>
                                <c:when test="${not empty transactions}">
                                    <c:forEach var="transactionData" items="${transactions}">
                                        <c:if test="${transactionData.code == 'CSKH'}">
                                            <tr>
                                                <td>${transactionData.createdDate}</td>
                                                <td>${transactionData.createdBy}</td>
                                                <td>${transactionData.modifiedDate}</td>
                                                <td>${transactionData.modifiedBy}</td>
                                                <td>${transactionData.note}</td>
                                                <td class="text-center">
                                                    <button class="btn btn-info btn-sm"
                                                            onclick="editTransaction(${transactionData.id}, ${customer.id}, '${transactionData.code}')"
                                                            data-note="${transactionData.note}">
                                                        <i class="fa fa-pencil"></i> Chỉnh Sửa
                                                    </button>
                                                    <button class="btn btn-danger btn-sm" onclick="deleteTransaction(${transactionData.id})">
                                                        <i class="fa fa-trash"></i> Xóa
                                                    </button>
                                                </td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td colspan="6" class="text-center">Chưa có giao dịch nào</td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                            </tbody>
                        </table>
                    </div>

                    <!-- Bảng dành cho DDX -->
                    <div class="col-xs-12">
                        <h3 class="smaller lighter blue">
                            Dẫn đi xem nhà (DDX)
                            <button class="btn btn-md btn-success pull-right" title="Thêm giao dịch"
                                    onclick="addTransaction('DDX', ${customer.id})">
                                <i class="ace-icon glyphicon glyphicon-plus smaller-80"></i> Thêm giao dịch
                            </button>
                        </h3>
                        <div class="hr hr-16 "></div>
                    </div>
                    <div class="col-xs-12">
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr>
                                <th class="text-center">NGÀY TẠO</th>
                                <th class="text-center">NGƯỜI TẠO</th>
                                <th class="text-center">NGÀY SỬA</th>
                                <th class="text-center">NGƯỜI SỬA</th>
                                <th class="text-center">CHI TIẾT GIAO DỊCH</th>
                                <th class="text-center">THAO TÁC</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:choose>
                                <c:when test="${not empty transactions}">
                                    <c:forEach var="transactionData" items="${transactions}">
                                        <c:if test="${transactionData.code == 'DDX'}">
                                            <tr>
                                                <td>${transactionData.createdDate}</td>
                                                <td>${transactionData.createdBy}</td>
                                                <td>${transactionData.modifiedDate}</td>
                                                <td>${transactionData.modifiedBy}</td>
                                                <td>${transactionData.note}</td>
                                                <td class="text-center">
                                                <button class="btn btn-info btn-sm"
                                                        onclick="editTransaction(${transactionData.id}, ${customer.id}, '${transactionData.code}')"
                                                        data-note="${transactionData.note}">
                                                    <i class="fa fa-pencil"></i> Chỉnh Sửa
                                                </button>
                                                    <button class="btn btn-danger btn-sm" onclick="deleteTransaction(${transactionData.id})">
                                                        <i class="fa fa-trash"></i> Xóa
                                                    </button>
                                                </td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td colspan="6" class="text-center">Chưa có giao dịch nào</td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                            </tbody>
                        </table>
                    </div>

                </c:if>


            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->
    <!-- Modal nhập thông tin giao dịch -->
    <div class="modal fade" id="transactionModal" tabindex="-1" role="dialog" aria-labelledby="transactionModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <!-- Tiêu đề Modal -->
                <div class="modal-header">
                    <h5 class="modal-title">Nhập thông tin giao dịch</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>

                <!-- Nội dung Modal -->
                <div class="modal-body">
                    <form id="transactionForm">
                        <!-- Input ẩn lưu ID giao dịch -->
                        <input type="hidden" id="transactionId">
                        <input type="hidden" id="code">
                        <input type="hidden" id="customerId">

                        <div class="form-group">
                            <label for="transactionNote">Chi tiết giao dịch</label>
                            <input type="text" class="form-control" id="transactionNote" placeholder="Nhập nội dung giao dịch">
                        </div>
                    </form>
                </div>

                <!-- Footer Modal -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="confirmTransaction()">Xác nhận</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                </div>
            </div>
        </div>
    </div>

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
                alert(action + ' khách hàng thành công');
                window.location.href="<c:url value="/admin/customer-list" />"
                console.log('Success');
            },
            error: function (xhr) {
                console.log('Fail');
                $('.error-message').remove(); // Xóa toàn bộ thông báo lỗi trước khi hiển thị lỗi mới

                if (xhr.status === 400) {
                    var errors = xhr.responseJSON; // Lấy danh sách lỗi từ response JSON
                    console.log(errors);

                    // Lặp qua từng lỗi và hiển thị ngay bên dưới input tương ứng
                    Object.keys(errors).forEach(function (field) {
                        var errorMessage = errors[field]; // Lấy lỗi của field
                        var inputField = $("#" + field); // Tìm input theo ID (phải trùng với field của DTO)

                        if (inputField.length) {
                            inputField.after('<span class="error-message" style="color: red">' + errorMessage + '</span>');
                        }
                    });
                } else {
                    alert('Có lỗi xảy ra, vui lòng thử lại sau.');
                }
            }
        })
    }
    $('#btnAddCustomer').click(function (e) {
        e.preventDefault();

        var formData = $('#from-edit').serializeArray(); // Lấy toàn bộ input trong form
        var json = {};

        // Xử lý từng trường trong form
        $.each(formData, function (i, field) {
            if (field.name === "staffIds") {
                if (!json["staffIds"]) json["staffIds"] = [];
                json["staffIds"].push(parseInt(field.value)); // ép kiểu sang số
            } else {
                json[field.name] = field.value;
            }
        });

        console.log("Dữ liệu gửi API:", json); //  debug kiểm tra dữ liệu đúng chưa

        var isValid = true;

        if (isValid) {
            AddCustomer(json);
        } else {
            alert('Vui lòng nhập đầy đủ thông tin');
        }
    });

    $('#btnCancel').click(function (e){
        e.preventDefault();
        window.location.href="<c:url value="/admin/customer-list" />"
    })

    // Mở modal để thêm mới giao dịch
    function addTransaction(transactionCode, customerId) {
        $('#transactionId').val(""); // Để trống ID để tạo mới
        $('#code').val(transactionCode);
        $('#customerId').val(customerId);
        $('#transactionNote').val(""); // Xóa nội dung cũ

        // Hiển thị modal
        $('#transactionModal').modal('show');
    }

    function editTransaction(transactionId, customerId, transactionCode) {
        let note = event.currentTarget.getAttribute("data-note"); // Lấy dữ liệu note từ button
        console.log("Debug Note:", note); // Debug xem có lấy được dữ liệu không

        $('#transactionId').val(transactionId);
        $('#customerId').val(customerId);
        $('#code').val(transactionCode);
        $('#transactionNote').val(note); // Hiển thị note lên modal

        $('#transactionModal').modal('show'); // Hiển thị modal
    }

    // Xác nhận (thêm mới hoặc cập nhật giao dịch)
    function confirmTransaction() {
        var transactionData = {
            id: $('#transactionId').val() || null, // Nếu ID rỗng thì là thêm mới
            code: $('#code').val(),
            customerId: $('#customerId').val(),
            note: $('#transactionNote').val()
        };

        $.ajax({
            url: '/api/admin/transactions',
            type: 'POST',
            data: JSON.stringify(transactionData),
            contentType: 'application/json',
            success: function (response) {
                alert("Lưu giao dịch thành công!");
                location.reload(); // Refresh lại trang để cập nhật giao diện

            },
            error: function () {
                alert("Có lỗi xảy ra, vui lòng thử lại!");
            }
        });

        // Đóng modal sau khi gửi request
        $('#transactionModal').modal('hide');
    }

    // Xóa giao dịch
    function deleteTransaction(transactionId) {
        if (confirm("Bạn có chắc chắn muốn xóa giao dịch này không?")) {
            $.ajax({
                url: '/api/admin/transactions/' + transactionId,
                type: 'DELETE',
                success: function (response) {
                    alert("Xóa giao dịch thành công!");
                    location.reload(); // Refresh lại trang để cập nhật giao diện
                },
                error: function () {
                    alert("Có lỗi xảy ra, vui lòng thử lại!");
                }
            });
        }
    }



</script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        const sidebar = document.getElementById('sidebar');
        if (sidebar) {
            sidebar.style.backgroundColor = '#f2f2f2';
        }
    });
</script>

</div><!-- /.main-container -->
</body>
</html>
