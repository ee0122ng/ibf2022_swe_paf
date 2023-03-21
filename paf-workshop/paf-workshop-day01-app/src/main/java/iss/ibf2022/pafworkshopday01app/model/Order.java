package iss.ibf2022.pafworkshopday01app.model;

import java.sql.Date;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;

public class Order {

    private Integer id;
    
    private Integer employeeId;

    private Integer customerId;

    private Date orderDate;

    private Date shippedDate;

    private Integer shipperId;

    @Size(max=50, message="Ship name can have at most 50 characters")
    private String shipName;

    private String shipAddress;

    @Size(max=50, message="Ship city name can have at most 50 characters")
    private String shipCity;

    @Size(max=50, message="Ship state province can have at most 50 characters")
    private String shipStateProvince;

    @Size(max=50, message="Ship zip postal code can have at most 50 characters")
    private String shipZipPostalCode;

    @Size(max=50, message="Ship country region can have at most 50 characters")
    private String shipCountryRegion;

    @Digits(integer=19, fraction=4, message="Shipping fee can have maximum 19 digits and 4 decimal")
    private Float shippingFee;

    @Digits(integer=19, fraction=4, message="Taxes can have maximum 19 digits and 4 decimal")
    private Float taxes;

    @Size(max=50, message="Payment type can have at most 50 characters")
    private String paymentType;

    private Date paidDate;

    private String notes;

    private Double taxRate = 0d;

    private Integer taxStatusId;

    private Integer statusId = 0;

    public Order() {
    }

    public Order(Integer id, Integer employeeId, Integer customerId, Date orderDate, Date shippedDate, Integer shipperId, String shipName, String shipAddress, String shipCity, String shipStateProvince, String shipZipPostalCode, String shipCountryRegion, Float shippingFee, Float taxes, String paymentType, Date paidDate, String notes, Double taxRate, Integer taxStatusId, Integer statusId) {
        this.id = id;
        this.employeeId = employeeId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.shippedDate = shippedDate;
        this.shipperId = shipperId;
        this.shipName = shipName;
        this.shipAddress = shipAddress;
        this.shipCity = shipCity;
        this.shipStateProvince = shipStateProvince;
        this.shipZipPostalCode = shipZipPostalCode;
        this.shipCountryRegion = shipCountryRegion;
        this.shippingFee = shippingFee;
        this.taxes = taxes;
        this.paymentType = paymentType;
        this.paidDate = paidDate;
        this.notes = notes;
        this.taxRate = taxRate;
        this.taxStatusId = taxStatusId;
        this.statusId = statusId;
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

    public Date getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getShippedDate() {
        return this.shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
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

    public String getShipAddress() {
        return this.shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
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

    public Float getShippingFee() {
        return this.shippingFee;
    }

    public void setShippingFee(Float shippingFee) {
        this.shippingFee = shippingFee;
    }

    public Float getTaxes() {
        return this.taxes;
    }

    public void setTaxes(Float taxes) {
        this.taxes = taxes;
    }

    public String getPaymentType() {
        return this.paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Date getPaidDate() {
        return this.paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
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

    public Integer getTaxStatusId() {
        return this.taxStatusId;
    }

    public void setTaxStatusId(Integer taxStatusId) {
        this.taxStatusId = taxStatusId;
    }

    public Integer getStatusId() {
        return this.statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }
    
}
