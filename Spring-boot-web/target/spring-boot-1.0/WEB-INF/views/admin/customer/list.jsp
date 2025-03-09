<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="main-content" id="main-container">
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
                        Danh Sách Khách Hàng
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            overview &amp; stats
                        </small>
                    </h1>
                </div><!-- /.page-header -->
                <div class="row">
                    <div class="widget-box" style="font-family: 'Times New Roman', Times, serif;">
                        <div class="widget-header">
                            <h4 class="widget-title">Masked Input</h4>

                            <span class="widget-toolbar">
                                            <a href="#" data-action="settings">
                                                <i class="ace-icon fa fa-cog"></i>
                                            </a>

                                            <a href="#" data-action="reload">
                                                <i class="ace-icon fa fa-refresh"></i>
                                            </a>

                                            <a href="#" data-action="collapse">
                                                <i class="ace-icon fa fa-chevron-up"></i>
                                            </a>

                                            <a href="#" data-action="close">
                                                <i class="ace-icon fa fa-times"></i>
                                            </a>
                                        </span>
                        </div>

                        <div class="widget-body" style="display: block;">
                            <div class="widget-main">
                                <form:form id="listForm" action="/admin/customer-list" method="get" modelAttribute="modelSearch">
                                    <div class="row">
                                        <!-- Hàng 1 -->
                                        <div class="col-xs-12">
                                            <div class="col-xs-6">
                                                <label>
                                                    Tên Khách Hàng
                                                </label>
                                                    <%--                                                <input type="text" class="form-control" name="name" value="${modelSearch.name}">--%>
                                                <form:input path="fullName" class="form-control" />
                                            </div>
                                            <div class="col-xs-6">
                                                <label>
                                                    Số Điện Thoại
                                                </label>
                                                    <%--                                                <input type="number" class="form-control" name="floorArea" value="${modelSearch.floorArea}">--%>
                                                <form:input path="phone" class="form-control" />
                                            </div>
                                        </div>
                                        <!-- Hàng 2 -->
                                        <div class="col-xs-12">
                                            <div class="col-xs-2">
                                                <label>
                                                    Tình Trạng
                                                </label>
                                                <form:select path="status" class="form-control">
                                                    <option value=""> -- Chọn Trạng Thái --</option>
                                                    <form:options items="${status}" ></form:options>
                                                </form:select>
                                                    <%--                                                <select class="form-control" name="district" >--%>
                                                    <%--                                                    <option value="">-- Chọn Quận --</option>--%>
                                                    <%--                                                    <option value="QUAN_1">Quận 1</option>--%>
                                                    <%--                                                    <option value="QUAN_2">Quận 2</option>--%>
                                                    <%--                                                    <option value="QUAN_3">Quận 3</option>--%>
                                                    <%--                                                    <option value="QUAN_4">Quận 4</option>--%>
                                                    <%--                                                </select>--%>
                                            </div>
                                            <div class="col-xs-5">
                                                <label>
                                                    Email
                                                </label>
                                                    <%--                                                <input type="text" class="form-control" name="ward" value="${modelSearch.ward}">--%>
                                                <form:input path="email" class="form-control" />
                                            </div>
                                        </div>

                                        <!-- Hàng 3 -->
<%--                                        <div class="col-xs-12">--%>
<%--&lt;%&ndash;                                            <div class="col-xs-4">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                <label>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                    Số tầng hầm&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                </label>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                    &lt;%&ndash;                                                <input type="number" class="form-control" name="numberOfBasement" value="${modelSearch.numberOfBasement}">&ndash;%&gt;&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                <form:input path="numberOfBasement" class="form-control" />&ndash;%&gt;--%>
<%--&lt;%&ndash;                                            </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                            <div class="col-xs-4">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                <label>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                    Hướng&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                </label>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                    &lt;%&ndash;                                                <input type="text" class="form-control" name="direction" value="${modelSearch.direction}">&ndash;%&gt;&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                <form:input path="direction" class="form-control" />&ndash;%&gt;--%>
<%--&lt;%&ndash;                                            </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                            <div class="col-xs-4">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                <label>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                    Hạng&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                </label>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                    &lt;%&ndash;                                                <input type="text" class="form-control" name="level" value="${modelSearch.level}">&ndash;%&gt;&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                <form:input path="level" class="form-control" />&ndash;%&gt;--%>
<%--&lt;%&ndash;                                            </div>&ndash;%&gt;--%>
<%--                                        </div>--%>

                                        <!-- Hàng 4 -->
<%--                                        <div class="col-xs-12">--%>
<%--                                            <div class="col-xs-3">--%>
<%--                                                <label>--%>
<%--                                                    Diện tích từ--%>
<%--                                                </label>--%>
<%--                                                    &lt;%&ndash;                                                <input type="number" class="form-control" name="AreaFrom" value="${modelSearch.areaFrom}">&ndash;%&gt;--%>
<%--                                                <form:input path="areaFrom" class="form-control" />--%>
<%--                                            </div>--%>
<%--                                            <div class="col-xs-3">--%>
<%--                                                <label>--%>
<%--                                                    Diện tích đến--%>
<%--                                                </label>--%>
<%--                                                    &lt;%&ndash;                                                <input type="number" class="form-control" name="AreaTo" value="${modelSearch.areaTo}">&ndash;%&gt;--%>
<%--                                                <form:input path="areaTo" class="form-control" />--%>
<%--                                            </div>--%>
<%--                                            <div class="col-xs-3">--%>
<%--                                                <label>--%>
<%--                                                    Giá thuê từ--%>
<%--                                                </label>--%>
<%--                                                    &lt;%&ndash;                                                <input type="number" class="form-control" name="rentPriceFrom" value="${modelSearch.rentPriceFrom}">&ndash;%&gt;--%>
<%--                                                <form:input path="rentPriceFrom" class="form-control" />--%>
<%--                                            </div>--%>
<%--                                            <div class="col-xs-3">--%>
<%--                                                <label>--%>
<%--                                                    Giá thuê đến--%>
<%--                                                </label>--%>
<%--                                                    &lt;%&ndash;                                                <input type="number" class="form-control" name="rentPriceTo" value="${modelSearch.rentPriceTo}">&ndash;%&gt;--%>
<%--                                                <form:input path="rentPriceTo" class="form-control" />--%>
<%--                                            </div>--%>
<%--                                        </div>--%>

                                        <!-- Hàng 5 -->
<%--                                        <div class="col-xs-12">--%>

<%--                                            <div class="col-xs-5">--%>
<%--                                                <label>--%>
<%--                                                    Tên Quản Lý--%>
<%--                                                </label>--%>
<%--                                                    &lt;%&ndash;                                                <input type="text" class="form-control" name="managerName" value="${modelSearch.managerName}">&ndash;%&gt;--%>
<%--                                                <form:input path="managerName" class="form-control" />--%>
<%--                                            </div>--%>
<%--                                            <div class="col-xs-5">--%>
<%--                                                <label>--%>
<%--                                                    SĐT Quản Lý--%>
<%--                                                </label>--%>
<%--                                                    &lt;%&ndash;                                                <input type="text" class="form-control" name="managerPhoneNumber" value="${modelSearch.managerPhone}">&ndash;%&gt;--%>
<%--                                                <form:input path="managerPhone" class="form-control" />--%>
<%--                                            </div>--%>
<%--                                            <security:authorize access="hasRole('MANAGER')">--%>
<%--                                                <div class="col-xs-2">--%>
<%--                                                    <label>--%>
<%--                                                        Chọn Nhân Viên--%>
<%--                                                    </label>--%>
<%--                                                    <form:select path="staffId" class="form-control">--%>
<%--                                                        <option value=""> -- Chọn Nhân Viên --</option>--%>
<%--                                                        <form:options items="${staffMap}" ></form:options>--%>
<%--                                                    </form:select>--%>
<%--                                                </div>--%>
<%--                                            </security:authorize>--%>

<%--                                        </div>--%>

                                        <!-- Hàng 6 -->
<%--                                        <div class="col-xs-12">--%>

<%--                                            <div class="col-xs-6">--%>
<%--                                                    &lt;%&ndash;                                                <label class="checkbox-inline">&ndash;%&gt;--%>
<%--                                                    &lt;%&ndash;                                                    <input type="checkbox" name="typeCode" value="noi-that"> Nội thất&ndash;%&gt;--%>
<%--                                                    &lt;%&ndash;                                                </label>&ndash;%&gt;--%>
<%--                                                    &lt;%&ndash;                                                <label  class="checkbox-inline">&ndash;%&gt;--%>
<%--                                                    &lt;%&ndash;                                                    <input type="checkbox"  name="typeCode" value="tang-tret" > Tầng trệt&ndash;%&gt;--%>
<%--                                                    &lt;%&ndash;                                                </label>&ndash;%&gt;--%>
<%--                                                    &lt;%&ndash;                                                <label  class="checkbox-inline">&ndash;%&gt;--%>
<%--                                                    &lt;%&ndash;                                                    <input type="checkbox"  name="typeCode" value="nguyen-can"> Nguyên Căn&ndash;%&gt;--%>
<%--                                                    &lt;%&ndash;                                                </label>&ndash;%&gt;--%>
<%--                                                <form:checkboxes path="typeCode" items="${typeCodes}"/>--%>
<%--                                            </div>--%>
<%--                                        </div>--%>

                                        <!-- Hàng 7 -->
                                        <div class="col-xs-12">

                                            <div class="col-xs-6">

                                                <button class="btn btn-primary" id="btnSearch">
                                                    <i class="ace-icon glyphicon glyphicon-search"></i>
                                                    Tìm Kiếm</button>
                                            </div>
                                        </div>

                                    </div>
                                </form:form>

                            </div>
                        </div>

                        <div class="pull-right" >
                            <a href="/admin/customer-edit">
                                <button class="btn btn-app btn-primary btn-sm" title="Thêm Khách Hàng">
                                    <i class="ace-icon fa fa-home"></i>
                                </button>
                            </a>
                            <security:authorize access="hasRole('MANAGER')">
                                <button class="btn btn-app btn-danger btn-sm" title="Xóa khách hàng" id="btn-deleteCustomer">
                                    <i class="ace-icon fa fa-trash-o bigger-200"></i>
                                </button>
                            </security:authorize>
                        </div>


                    </div>
                </div>

                <div class="hr hr-18 dotted hr-double"></div>

                <!-- Table -->
                <div class="row">
                    <div class="col-xs-12">
                        <!-- Hiển thị danh sách tòa nhà -->
                        <display:table name="${modelSearch.listResult}" cellspacing="0" cellpadding="0"
                                       requestURI="/admin/customer-list" partialList="true" sort="external"
                                       size="${modelSearch.totalItems}" defaultsort="2" defaultorder="ascending"
                                       id="customer-list" pagesize="${modelSearch.maxPageItems}"
                                       export="false"
                                       class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                       style="margin: 3em 0 1.5em;"
                                       uid="customer">

                            <!-- Cột hiển thị checkbox -->
                            <display:column title="Chọn" class="center">
                                <input type="checkbox" value="${customer.id}">
                            </display:column>

                            <!-- Các cột hiển thị thông tin -->
                            <display:column property="name" title="Tên khách hàng" />
                            <display:column property="phone" title="Di động" />
                            <display:column property="email" title="Email" />
                            <display:column property="demand" title="Nhu cầu" />
                            <display:column property="createdBy" title="Người thêm" />
                            <display:column property="createdDate" title="Ngày thêm" />
                            <display:column property="status" title="Tình trạng" />
                            <!-- Cột hành động -->
                            <display:column title="Thao tác">
                                <div class="hidden-sm hidden-xs btn-group">
                                    <security:authorize access="hasRole('MANAGER')">
                                        <button class="btn btn-xs btn-success" onclick="assignmentCustomer(${customer.id})" title="Giao Khách Hàng">
                                            <i class="ace-icon fa fa-check bigger-120"></i>
                                        </button>
                                    </security:authorize>
                                    <a class="btn btn-xs btn-info" href="/admin/customer-edit-${customer.id}">
                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                                    </a>
                                    <security:authorize access="hasRole('MANAGER')">
                                        <button class="btn btn-xs btn-danger" onclick="deleteCustomer(${customer.id})" title="Xóa Khách Hàng">
                                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                        </button>
                                    </security:authorize>
                                </div>
                            </display:column>
                        </display:table>
                    </div>

                </div>
            </div>
            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->


    <!-- Modal Fade -->
    <div class="modal" id="assignmentCustomerModal" style="font-family: 'Times New Roman', Times, serif;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Danh Sách Nhân Viên</h5>
                </div>
                <div class="modal-body">
                    <table id="staff-list" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th class="center">
                                <label class="pos-rel">
                                    <p class="center">Chọn</p>
                                    <span class="lbl"></span>
                                </label>
                            </th>
                            <th>Họ Tên nhân viên</th>
                        </tr>
                        </thead>

                        <tbody>
                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" id="btn-aggsingmentCustomer">Giao Khách Hàng</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" id="customerId" value="">
</div><!-- /.main-container -->
<!--[if !IE]> -->
<script src="assets/js/jquery.2.1.1.min.js"></script>
<!-- <![endif]-->

<script>
    // Ajax Search Building
    $('#btnSearch').click(function(e) {
        e.preventDefault();
        $('#listForm').submit();
    });

    // Ajax Delete Customer
    function deleteCustomer(data){
        $.ajax({
            url: '/api/customers/' + data,
            type: 'DELETE',
            // data: JSON.stringify(data), // Convert từ Object sang JSON
            // contentType: 'application/json', // Kiểu dữ liệu gửi đi là JSON
            dataType: "JSON",
            success: function (response) {
                alert(response.message);
                console.log('Success');
                window.location.href="<c:url value="/admin/customer-list" />"
            },
            error: function (response) {
                console.log('Fail');
                alert(response.message);
                alert('Xóa tòa nhà thất bại');
            }
        })
    }

    $('#btn-deleteCustomer').click(function (e){
        e.preventDefault();
        var data = {};
        var ids = $('#customer').find('tbody input[type="checkbox"]:checked').map(function() {
            return $(this).val();
        }).get();
        data['ids'] = ids;
        console.log(data);
        if (data['ids'].length > 0){
            deleteCustomer(data['ids']);
        }
        else
        {
            alert('Chưa chọn khách hàng cần xóa');
        }

    })

    function assignmentCustomer(id) {
        console.log('Hello Nguyên');
        $('#customerId').val(id);
        $('#assignmentCustomerModal').modal();
        loadStaff(id);
    }
    function loadStaff(customerId) {
        $('#customerId').val(customerId); // Gán ID tòa nhà vào input ẩn

        $.ajax({
            url: "/api/customers/" + customerId + "/staffs",
            type: 'GET',
            dataType: 'JSON',
            success: function (response) {
                console.log("Response from API:", response); // Kiểm tra dữ liệu trả về

                var row = '';
                response.forEach(function (item) {
                    row += '<tr>';
                    row += '<td class="center">';
                    row += '<label class="pos-rel">';
                    row += '<input type="checkbox" class="ace" value="' + item.staffId + '" ' + (item.checked === 'checked' ? 'checked' : '') + '>';
                    row += '<span class="lbl"></span>';
                    row += '</label>';
                    row += '</td>';
                    row += '<td class="center">' + item.fullName + '</td>';
                    row += '</tr>';
                });


                $('#staff-list tbody').html(row); // Thêm nội dung vào bảng
            },
            error: function () {
                alert('Không thể tải danh sách nhân viên. Vui lòng thử lại.');
            }
        });
    }
    // Giao khách hàng cho nhân viên
    $('#btn-aggsingmentCustomer').click(function(e) {
        e.preventDefault();
        var json = {};
        json['customerId'] = $('#customerId').val();
        var staffIds = $('#staff-list').find('tbody input[type="checkbox"]:checked').map(function() {
            return $(this).val();
        }).get();
        json['staffIds'] = staffIds;
        console.log(json);
        if (json['customerId'].length > 0) {
            updateAssingment(json);
        }
        else {
            alert('Customer ID is required');
        }
    });

    // Ajax Assignment Customer
    function updateAssingment(data){
        $.ajax({
            url: '/api/assingments/customer',
            type: 'POST',
            data: JSON.stringify(data), // Convert từ Object sang JSON
            contentType: 'application/json', // Kiểu dữ liệu gửi đi là JSON
            dataType: "JSON",
            success: function (response) {
                alert('Assingment Success');
                location.reload();

            },
            error: function (response) {
                console.log('Fail');
                alert(response.message);
                alert('Assingment Fail');
            }
        })
    }

</script>
</body>
</html>
