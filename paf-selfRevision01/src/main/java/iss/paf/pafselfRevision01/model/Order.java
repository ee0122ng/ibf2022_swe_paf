package iss.paf.pafselfRevision01.model;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;

public class Order {
    
    private Integer id;
    private Integer employeeId;
    private Integer customerId;
    private LocalDateTime orderDate;
    private LocalDateTime shippedDate;
    private LocalDateTime paidDate;
    private Integer shipperId;
    private String shipAddress;
    private String notes;
    private Double taxRate = 0d;
    private Integer taxStatudId;
    private Integer statudId = 0;

    @Size(max=50, message="Ship name can have at most 50 characters")
    private String shipName;

    @Size(max=50, message="Ship city can have at most 50 characters")
    private String shipCity;

    @Size(max=50, message="Ship state province can have at most 50 characters")
    private String shipStateProvince;

    @Size(max=50, message="Ship zip postal code can have at most 50 characters")
    private String shipZipPostalCode;

    @Size(max=50, message="Ship country region can have at most 50 characters")
    private String shipCountryRegion;

    @Digits(integer=19, fraction=4, message="Shipping fee can only have 19 digits and 4 decimal place")
    private Double shippingFee;

    @Digits(integer=19, fraction=4, message="Taxes can only have 19 digits and 4 decimal place")
    private Double taxes;

    @Size(max=50, message="Payment type can have at most 50 characters")
    private String paymentType;

    public Order() {
    }

    public Order(Integer id, Integer employeeId, Integer customerId, LocalDateTime orderDate, LocalDateTime shippedDate, LocalDateTime paidDate, Integer shipperId, String shipAddress, String notes, Double taxRate, Integer taxStatudId, Integer statudId, String shipName, String shipCity, String shipStateProvince, String shipZipPostalCode, String shipCountryRegion, Double shippingFee, Double taxes, String paymentType) {
        this.id = id;
        this.employeeId = employeeId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.shippedDate = shippedDate;
        this.paidDate = paidDate;
        this.shipperId = shipperId;
        this.shipAddress = shipAddress;
        this.notes = notes;
        this.taxRate = taxRate;
        this.taxStatudId = taxStatudId;
        this.statudId = statudId;
        this.shipName = shipName;
        this.shipCity = shipCity;
        this.shipStateProvince = shipStateProvince;
        this.shipZipPostalCode = shipZipPostalCode;
        this.shipCountryRegion = shipCountryRegion;
        this.shippingFee = shippingFee;
        this.taxes = taxes;
        this.paymentType = paymentType;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
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

    public LocalDateTime getPaidDate() {
        return this.paidDate;
    }

    public void setPaidDate(LocalDateTime paidDate) {
        this.paidDate = paidDate;
    }

    public Integer getShipperId() {
        return this.shipperId;
    }

    public void setShipperId(Integer shipperId) {
        this.shipperId = shipperId;
    }

    public String getShipAddress() {
        return this.shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Double getTaxRate() {
        return this.taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Integer getTaxStatudId() {
        return this.taxStatudId;
    }

    public void setTaxStatudId(Integer taxStatudId) {
        this.taxStatudId = taxStatudId;
    }

    public Integer getStatudId() {
        return this.statudId;
    }

    public void setStatudId(Integer statudId) {
        this.statudId = statudId;
    }

    public String getShipName() {
        return this.shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getShipCity() {
        return this.shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public String getShipStateProvince() {
        return this.shipStateProvince;
    }

    public void setShipStateProvince(String shipStateProvince) {
        this.shipStateProvince = shipStateProvince;
    }

    public String getShipZipPostalCode() {
        return this.shipZipPostalCode;
    }

    public void setShipZipPostalCode(String shipZipPostalCode) {
        this.shipZipPostalCode = shipZipPostalCode;
    }

    public String getShipCountryRegion() {
        return this.shipCountryRegion;
    }

    public void setShipCountryRegion(String shipCountryRegion) {
        this.shipCountryRegion = shipCountryRegion;
    }

    public Double getShippingFee() {
        return this.shippingFee;
    }

    public void setShippingFee(Double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public Double getTaxes() {
        return this.taxes;
    }

    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }

    public String getPaymentType() {
        return this.paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

}