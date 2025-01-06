package com.javaweb.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildingDTO extends AbstractDTO{
    private Long id;

    @NotNull(message = "Tên không được để trống")
    private String name;

    @NotNull(message = "Đường không được để trống")
    private String street;

    @NotNull(message = "Phường không được để trống")
    private String ward;

    @NotNull(message = "Vui lòng chọn quận")
    private String district;

    @NotNull(message = "Số tầng hầm không được để trống")
    private Long numberOfBasement;

    @NotNull(message = "Diện tích không được để trống")
    @Positive(message = "Diện tích phải lớn hơn 0")
    private Long floorArea;

    @NotNull(message = "Mức giá không được để trống")
    private String level;

    @NotNull(message = "Vui lòng chọn loại tòa nhà")
    private List<String> typeCode;

    @NotNull(message = "Phí quản lý không được để trống")
    private String overtimeFee;

    @NotNull(message = "Phí điện không được để trống")
    private String electricityFee;

    @NotNull(message = "Tiền cọc không được để trống")
    private String deposit;

    @NotNull(message = "Phương thức thanh toán không được để trống")
    private String payment;

    @NotNull(message = "Thời gian thuê không được để trống")
    private String rentTime;

    @NotNull(message = "Phí nước không được để trống")
    private String waterFee;

    @NotNull(message = "Thời gian trang trí không được để trống")
    private String decorationTime;

    @NotNull(message = "Mô tả giá thuê không được để trống")
    private String rentPriceDescription;

    @NotNull(message = "Phí đỗ xe không được để trống")
    private String carFee;

    @NotNull(message = "Phí xe máy không được để trống")
    private String motoFee;

    @NotNull(message = "Cấu trúc không được để trống")
    private String structure;

    @NotNull(message = "Hướng không được để trống")
    private String direction;

    @NotNull(message = "Ghi chú không được để trống")
    private String note;

    @NotNull(message = "Diện tích thuê không được để trống")
    private String rentArea;

    @NotNull(message = "Tên người quản lý không được để trống")
    private String managerName;

    @NotNull(message = "Số điện thoại người quản lý không được để trống")
    private String managerPhone;

    @Positive(message = "Giá thuê phải lớn hơn 0")
    @NotNull(message = "Giá thuê không được để trống")
    private Long rentPrice;

    @NotNull(message = "Phí dịch vụ không được để trống")
    private String serviceFee;

    @NotNull(message = "Phí môi giới không được để trống")
    @Positive(message = "Phí môi giới phải lớn hơn 0")
    private Double brokerageFee;
    private String avatar;
    private String imageBase64;
    private String imageName;

    private Map<String,String> buildingDTOs = new HashMap<>();


    public String getWaterFee() {
        return waterFee;
    }

    public void setWaterFee(String waterFee) {
        this.waterFee = waterFee;
    }

    public Map<String, String> getBuildingDTOs() {
        return buildingDTOs;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setBuildingDTOs(Map<String, String> buildingDTOs) {
        this.buildingDTOs = buildingDTOs;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getStructure() {
        return structure;
    }


    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getImageBase64() {
        if (imageBase64 != null) {
            return imageBase64.split(",")[1];
        }
        return null;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRentArea() {
        return rentArea;
    }

    public void setRentArea(String rentArea) {
        this.rentArea = rentArea;
    }

    public Long getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Long floorArea) {
        this.floorArea = floorArea;
    }

    public Long getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(Long numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }

    public List<String> getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(List<String> typeCode) {
        this.typeCode = typeCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Double getBrokerageFee() {
        return brokerageFee;
    }

    public void setBrokerageFee(Double brokerageFee) {
        this.brokerageFee = brokerageFee;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRentPriceDescription() {
        return rentPriceDescription;
    }

    public void setRentPriceDescription(String rentPriceDescription) {
        this.rentPriceDescription = rentPriceDescription;
    }

    public String getCarFee() {
        return carFee;
    }

    public void setCarFee(String carFee) {
        this.carFee = carFee;
    }

    public String getMotoFee() {
        return motoFee;
    }

    public void setMotoFee(String motoFee) {
        this.motoFee = motoFee;
    }

    public String getOvertimeFee() {
        return overtimeFee;
    }

    public void setOvertimeFee(String overtimeFee) {
        this.overtimeFee = overtimeFee;
    }

    public String getElectricityFee() {
        return electricityFee;
    }

    public void setElectricityFee(String electricityFee) {
        this.electricityFee = electricityFee;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getRentTime() {
        return rentTime;
    }

    public void setRentTime(String rentTime) {
        this.rentTime = rentTime;
    }

    public String getDecorationTime() {
        return decorationTime;
    }

    public void setDecorationTime(String decorationTime) {
        this.decorationTime = decorationTime;
    }

    public Long getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Long rentPrice) {
        this.rentPrice = rentPrice;
    }
}