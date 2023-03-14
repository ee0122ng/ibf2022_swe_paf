package iss.paf.pafday03.model;

import java.sql.Date;

public class Order {

    private Integer orderId;

    private Date orderDate;

    private Integer customerId;

    private Integer productId;

    private Float totalPrice;

    private Float costPrice;
    
    public Order() {
    }

    public Order(Integer orderId, Date orderDate, Integer customerId, Integer productId, Float totalPrice, Float costPrice) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerId = customerId;
        this.productId = productId;
        this.totalPrice = totalPrice;
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

    public Integer getProductId() {
        return this.productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Float getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Float getCostPrice() {
        return this.costPrice;
    }

    public void setCostPrice(Float costPrice) {
        this.costPrice = costPrice;
    }

}
