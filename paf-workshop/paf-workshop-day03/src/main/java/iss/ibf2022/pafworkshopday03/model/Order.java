package iss.ibf2022.pafworkshopday03.model;

import java.sql.Date;

public class Order {

    private Integer orderId;
    private Date orderDate;
    private Integer customerId;
    private Float totalCost;
    private Float costPrice;

    public Order() {
    }

    public Order(Integer orderId, Date orderDate, Integer customerId, Float totalCost, Float costPrice) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerId = customerId;
        this.totalCost = totalCost;
        this.costPrice = costPrice;
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

    public Integer getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Float getTotalCost() {
        return this.totalCost;
    }

    public void setTotalCost(Float totalCost) {
        this.totalCost = totalCost;
    }

    public Float getCostPrice() {
        return this.costPrice;
    }

    public void setCostPrice(Float costPrice) {
        this.costPrice = costPrice;
    }
    
}