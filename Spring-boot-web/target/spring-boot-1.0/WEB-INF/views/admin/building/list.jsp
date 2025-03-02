<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 12/25/2024
  Time: 2:06 PM
  To change this template use File | Settings | File Templates.
--%>
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
                        Danh Sách Tòa Nhà
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
                                    <form:form id="listForm" action="/admin/building-list" method="get" modelAttribute="modelSearch">
                                        <div class="row">
                                            <!-- Hàng 1 -->
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                    <label>
                                                        Tên tòa nhà
                                                    </label>
    <%--                                                <input type="text" class="form-control" name="name" value="${modelSearch.name}">--%>
                                                    <form:input path="name" class="form-control" />
                                                </div>
                                                <div class="col-xs-6">
                                                    <label>
                                                        Diện tích sàn
                                                    </label>
    <%--                                                <input type="number" class="form-control" name="floorArea" value="${modelSearch.floorArea}">--%>
                                                    <form:input path="floorArea" class="form-control" />
                                                </div>
                                            </div>
                                            <!-- Hàng 2 -->
                                            <div class="col-xs-12">
                                                <div class="col-xs-2">
                                                    <label>
                                                        Quận
                                                    </label>
                                                    <form:select path="district" class="form-control">
                                                        <option value=""> -- Chọn Quận --</option>
                                                        <form:options items="${districts}" ></form:options>
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
                                                        Phường
                                                    </label>
    <%--                                                <input type="text" class="form-control" name="ward" value="${modelSearch.ward}">--%>
                                                    <form:input path="ward" class="form-control" />
                                                </div>
                                                <div class="col-xs-5">
                                                    <label>
                                                        Đường
                                                    </label>
    <%--                                                <input type="text" class="form-control" name="street" value="${modelSearch.street}">--%>
                                                    <form:input path="street" class="form-control" />
                                                </div>
                                            </div>

                                            <!-- Hàng 3 -->
                                            <div class="col-xs-12">
                                                <div class="col-xs-4">
                                                    <label>
                                                        Số tầng hầm
                                                    </label>
    <%--                                                <input type="number" class="form-control" name="numberOfBasement" value="${modelSearch.numberOfBasement}">--%>
                                                    <form:input path="numberOfBasement" class="form-control" />
                                                </div>
                                                <div class="col-xs-4">
                                                    <label>
                                                        Hướng
                                                    </label>
    <%--                                                <input type="text" class="form-control" name="direction" value="${modelSearch.direction}">--%>
                                                    <form:input path="direction" class="form-control" />
                                                </div>
                                                <div class="col-xs-4">
                                                    <label>
                                                        Hạng
                                                    </label>
    <%--                                                <input type="text" class="form-control" name="level" value="${modelSearch.level}">--%>
                                                    <form:input path="level" class="form-control" />
                                                </div>
                                            </div>

                                            <!-- Hàng 4 -->
                                            <div class="col-xs-12">
                                                <div class="col-xs-3">
                                                    <label>
                                                        Diện tích từ
                                                    </label>
    <%--                                                <input type="number" class="form-control" name="AreaFrom" value="${modelSearch.areaFrom}">--%>
                                                    <form:input path="areaFrom" class="form-control" />
                                                </div>
                                                <div class="col-xs-3">
                                                    <label>
                                                        Diện tích đến
                                                    </label>
    <%--                                                <input type="number" class="form-control" name="AreaTo" value="${modelSearch.areaTo}">--%>
                                                    <form:input path="areaTo" class="form-control" />
                                                </div>
                                                <div class="col-xs-3">
                                                    <label>
                                                        Giá thuê từ
                                                    </label>
    <%--                                                <input type="number" class="form-control" name="rentPriceFrom" value="${modelSearch.rentPriceFrom}">--%>
                                                    <form:input path="rentPriceFrom" class="form-control" />
                                                </div>
                                                <div class="col-xs-3">
                                                    <label>
                                                        Giá thuê đến
                                                    </label>
    <%--                                                <input type="number" class="form-control" name="rentPriceTo" value="${modelSearch.rentPriceTo}">--%>
                                                    <form:input path="rentPriceTo" class="form-control" />
                                                </div>
                                            </div>

                                            <!-- Hàng 5 -->
                                            <div class="col-xs-12">

                                                <div class="col-xs-5">
                                                    <label>
                                                        Tên Quản Lý
                                                    </label>
    <%--                                                <input type="text" class="form-control" name="managerName" value="${modelSearch.managerName}">--%>
                                                    <form:input path="managerName" class="form-control" />
                                                </div>
                                                <div class="col-xs-5">
                                                    <label>
                                                        SĐT Quản Lý
                                                    </label>
    <%--                                                <input type="text" class="form-control" name="managerPhoneNumber" value="${modelSearch.managerPhone}">--%>
                                                    <form:input path="managerPhone" class="form-control" />
                                                </div>
                                                <security:authorize access="hasRole('MANAGER')">
                                                    <div class="col-xs-2">
                                                        <label>
                                                            Chọn Nhân Viên
                                                        </label>
                                                        <form:select path="staffId" class="form-control">
                                                            <option value=""> -- Chọn Nhân Viên --</option>
                                                            <form:options items="${staffMap}" ></form:options>
                                                        </form:select>
                                                    </div>
                                                </security:authorize>

                                            </div>

                                            <!-- Hàng 6 -->
                                            <div class="col-xs-12">

                                                <div class="col-xs-6">
    <%--                                                <label class="checkbox-inline">--%>
    <%--                                                    <input type="checkbox" name="typeCode" value="noi-that"> Nội thất--%>
    <%--                                                </label>--%>
    <%--                                                <label  class="checkbox-inline">--%>
    <%--                                                    <input type="checkbox"  name="typeCode" value="tang-tret" > Tầng trệt--%>
    <%--                                                </label>--%>
    <%--                                                <label  class="checkbox-inline">--%>
    <%--                                                    <input type="checkbox"  name="typeCode" value="nguyen-can"> Nguyên Căn--%>
    <%--                                                </label>--%>
                                                    <form:checkboxes path="typeCode" items="${typeCodes}"/>
                                                </div>
                                            </div>

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
                                   <a href="/admin/building-edit">
                                       <button class="btn btn-app btn-primary btn-sm" title="Thêm toà nhà">
                                           <i class="ace-icon fa fa-home"></i>
                                       </button>
                                   </a>
                                   <security:authorize access="hasRole('MANAGER')">
                                       <button class="btn btn-app btn-danger btn-sm" title="Xóa toà nhà" id="btn-deleteBuilding">
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
                                       requestURI="/admin/building-list" partialList="true" sort="external"
                                       size="${modelSearch.totalItems}" defaultsort="2" defaultorder="ascending"
                                       id="building-list" pagesize="${modelSearch.maxPageItems}"
                                       export="false"
                                       class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                       style="margin: 3em 0 1.5em;"
                                       uid="building">

                            <!-- Cột hiển thị checkbox -->
                            <display:column title="Chọn" class="center">
                                <input type="checkbox" value="${building.id}">
                            </display:column>

                            <!-- Các cột hiển thị thông tin -->
                            <display:column property="name" title="Tên tòa nhà" />
                            <display:column property="address" title="Địa chỉ" />
                            <display:column property="numberOfBasement" title="Số tầng hầm" />
                            <display:column property="managerName" title="Tên quản lý" />
                            <display:column property="managerPhone" title="SĐT quản lý" />
                            <display:column property="floorArea" title="Diện tích sàn" />
                            <display:column property="rentArea" title="Diện tích thuê" />
                            <display:column property="structure" title="Diện trích trống" />
                            <display:column property="rentPrice" title="Giá thuê" />
                            <display:column property="serviceFee" title="Phí dịch vụ" />
                            <display:column property="brokerageFee" title="Phí môi giới" />

                            <!-- Cột hành động -->
                            <display:column title="Hành động">
                                <div class="hidden-sm hidden-xs btn-group">
                                   <security:authorize access="hasRole('MANAGER')">
                                       <button class="btn btn-xs btn-success" onclick="assignmentBuilding(${building.id})" title="Giao tòa nhà">
                                           <i class="ace-icon fa fa-check bigger-120"></i>
                                       </button>
                                   </security:authorize>
                                    <a class="btn btn-xs btn-info" href="/admin/building-edit-${building.id}">
                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                                    </a>
                                    <security:authorize access="hasRole('MANAGER')">
                                        <button class="btn btn-xs btn-danger" onclick="deleteBuilding(${building.id})" title="Xóa tòa nhà">
                                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                        </button>
                                    </security:authorize>
                                </div>
                            </display:column>
                        </display:table>
                    </div>

                </div>

            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->


    <!-- Modal Fade -->
    <div class="modal" id="assignmentBuildingModal" style="font-family: 'Times New Roman', Times, serif;">
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
                    <button type="button" class="btn btn-primary" id="btn-aggsingmentBuilding">Giao tòa nhà</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" id="buildingId" value="">
</div><!-- /.main-container -->
<!--[if !IE]> -->
<script src="assets/js/jquery.2.1.1.min.js"></script>

<!-- <![endif]-->
<script>
    function assignmentBuilding(id) {
        console.log('Hello Nguyên');
        $('#buildingId').val(id);
        $('#assignmentBuildingModal').modal();
        loadStaff(id);
    }

    function loadStaff(buildingId) {
        $('#buildingId').val(buildingId); // Gán ID tòa nhà vào input ẩn

        $.ajax({
            url: "/api/building/" + buildingId + "/staffs",
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



    $('#btn-deleteBuilding').click(function(e) {
        e.preventDefault();
        var data = {};
        var ids = $('#building').find('tbody input[type="checkbox"]:checked').map(function() {
            return $(this).val();
        }).get();
        data['ids'] = ids;
        if (data['ids'].length > 0) {
            deleteBuilding(data['ids']);
        }
        else {
            alert('Vui lòng chọn tòa nhà cần xóa');
        }
    });


    $('#btn-aggsingmentBuilding').click(function(e) {
        e.preventDefault();
        var json = {};
        json['buildingId'] = $('#buildingId').val();
        var staffIds = $('#staff-list').find('tbody input[type="checkbox"]:checked').map(function() {
            return $(this).val();
        }).get();
        json['staffIds'] = staffIds;
        console.log(json);
        if (json['buildingId'].length > 0) {
            updateAssingment(json);
        }
        else {
            alert('Building ID is required');
        }


    });

    // Ajax Assignment Building
    function updateAssingment(data){
        $.ajax({
            url: '/api/assingments',
            type: 'POST',
            data: JSON.stringify(data), // Convert từ Object sang JSON
            contentType: 'application/json', // Kiểu dữ liệu gửi đi là JSON
            dataType: "JSON",
            success: function (response) {
                // alert('Assingment Success');
                alert(response.message);
                console.log('Success');
                window.location.href="<c:url value="/admin/building-list" />"
            },
            error: function (response) {
                console.log('Fail');
                alert(response.message);
                alert('Assingment Fail');
            }
        })
    }

    // Ajax Delete Building
    function deleteBuilding(data){
        $.ajax({
            url: '/api/building/' + data,
            type: 'DELETE',
            // data: JSON.stringify(data), // Convert từ Object sang JSON
            // contentType: 'application/json', // Kiểu dữ liệu gửi đi là JSON
            dataType: "JSON",
            success: function (response) {
                alert(response.message);
                console.log('Success');
                window.location.href="<c:url value="/admin/building-list" />"
            },
            error: function (response) {
                console.log('Fail');
                alert('Xóa tòa nhà thất bại');
            }
        })
    }

    // End Ajax Delete Building

    // Ajax Search Building
    $('#btnSearch').click(function(e) {
        e.preventDefault();
        $('#listForm').submit();
    });
</script>




<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='assets/js/jquery.min.js'>"+"<"+"/script>");
</script>

</body>
</html>
