package iss.ibf2022.pafworkshopday04.model;

import java.sql.Date;
import java.util.List;

public class Order {

    private Integer orderId;
    private Date orderDate;
    private String customerName;
    private String shipAddress;
    private String notes;
    private Float tax = 0.05f;
    private List<OrderDetails> details;

    public Order() {
    }

    public Order(Integer orderId, Date orderDate, String customerName, String shipAddress, String notes, Float tax, List<OrderDetails> details) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.shipAddress = shipAddress;
        this.notes = notes;
        this.tax = tax;
        this.details = details;
    }

    public Integer getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public Float getTax() {
        return this.tax;
    }

    public void setTax(Float tax) {
        this.tax = tax;
    }

    public List<OrderDetails> getDetails() {
        return this.details;
    }

    public void setDetails(List<OrderDetails> details) {
        this.details = details;
    }
    
}
