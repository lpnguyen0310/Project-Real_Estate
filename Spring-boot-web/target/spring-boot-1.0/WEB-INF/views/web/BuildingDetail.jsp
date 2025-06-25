<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Building Details</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h2>Building Details</h2>

    <div class="row">
        <div class="col-md-6">
            <p><strong>Name:</strong> ${building.name}</p>
            <p><strong>Street:</strong> ${building.street}</p>
            <p><strong>Ward:</strong> ${building.ward}</p>
            <p><strong>District:</strong> ${building.district}</p>
            <p><strong>Structure:</strong> ${building.structure}</p>
            <p><strong>Number of Basement:</strong> ${building.numberOfBasement}</p>
            <p><strong>Floor Area:</strong> ${building.floorArea}</p>
            <p><strong>Direction:</strong> ${building.direction}</p>
            <p><strong>Level:</strong> ${building.level}</p>
            <p><strong>Rent Price:</strong> ${building.rentPrice}</p>
            <p><strong>Service Fee:</strong> ${building.serviceFee}</p>
            <p><strong>Car Fee:</strong> ${building.carFee}</p>
            <p><strong>Moto Fee:</strong> ${building.motoFee}</p>
            <p><strong>Brokerage Fee:</strong> ${building.brokerageFee}</p>
            <p><strong>Decoration Time:</strong> ${building.decorationTime}</p>
            <p><strong>Manager Name:</strong> ${building.managerName}</p>
            <p><strong>Manager Phone:</strong> ${building.managerPhone}</p>
        </div>

        <div class="col-md-6">
            <c:if test="${not empty building.images}">
                <div id="buildingGallery" class="carousel slide" data-ride="carousel">
                    <div class="carousel-inner">
                        <c:forEach var="img" items="${building.images}" varStatus="status">
                            <div class="carousel-item ${status.first ? 'active' : ''}">
                                <img class="d-block w-100" src="${img.imageUrl}" alt="Building Image">
                            </div>
                        </c:forEach>
                    </div>
                    <a class="carousel-control-prev" href="#buildingGallery" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon"></span>
                    </a>
                    <a class="carousel-control-next" href="#buildingGallery" role="button" data-slide="next">
                        <span class="carousel-control-next-icon"></span>
                    </a>
                </div>
            </c:if>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
