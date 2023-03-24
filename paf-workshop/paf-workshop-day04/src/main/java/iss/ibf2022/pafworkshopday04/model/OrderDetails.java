package iss.ibf2022.pafworkshopday04.model;

public class OrderDetails {

    private Integer id;
    private Integer orderId;
    private String product;
    private Float unitPrice;
    private Float discount = 1.0f;
    private Integer quantity;

    public OrderDetails() {
    }

    public OrderDetails(Integer id, Integer orderId, String product, Float unitPrice, Float discount, Integer quantity) {
        this.id = id;
        this.orderId = orderId;
        this.product = product;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.quantity = quantity;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getProduct() {
        return this.product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Float getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Float getDiscount() {
        return this.discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
}
