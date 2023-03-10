package iss.paf.pafday01.model;

import java.sql.Time;
import java.time.LocalDateTime;

public class Order {

    private Integer id;
    private Integer customerId;
    private LocalDateTime orderDate;
    private LocalDateTime shippedDate;
    private Integer shipperId;
    private String shipName;
    // private Integer employeeId;
    // private String shipAddress;
    // private String shipCity;
    // private String shipStateProvince;
    // private String shipZipPostalCode;
    // private String shipCountryRegion;
    // private Float shippingFee;
    // private Float taxes;
    // private String paymentType;
    // private Time paidDate;
    // private String notes;
    // private Double taxRate;
    // private Integer taxStatusId;
    // private Integer statusId;

    public Order() {
    }

    public Order(Integer id, Integer customerId, LocalDateTime orderDate, LocalDateTime shippedDate, Integer shipperId, String shipName) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.shippedDate = shippedDate;
        this.shipperId = shipperId;
        this.shipName = shipName;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getShippedDate() {
        return this.shippedDate;
    }

    public void setShippedDate(LocalDateTime shippedDate) {
        this.shippedDate = shippedDate;
    }

    public Integer getShipperId() {
        return this.shipperId;
    }

    public void setShipperId(Integer shipperId) {
        this.shipperId = shipperId;
    }

    public String getShipName() {
        return this.shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }
    
}