package iss.ibf2022.pafworkshopday04.payload;

import java.util.List;

public class RequestPayload {

    private Boolean insertedOrder;
    private Integer orderId;
    private Integer updatedRow;
    private List<Integer> orderDetailsId;

    public RequestPayload() {
    }

    public RequestPayload(Boolean insertedOrder, Integer orderId, Integer updatedRow, List<Integer> orderDetailsId) {
        this.insertedOrder = insertedOrder;
        this.orderId = orderId;
        this.updatedRow = updatedRow;
        this.orderDetailsId = orderDetailsId;
    }

    public Integer getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUpdatedRow() {
        return this.updatedRow;
    }

    public void setUpdatedRow(Integer updatedRow) {
        this.updatedRow = updatedRow;
    }

    public Boolean isInsertedOrder() {
        return this.insertedOrder;
    }

    public Boolean getInsertedOrder() {
        return this.insertedOrder;
    }

    public void setInsertedOrder(Boolean insertedOrder) {
        this.insertedOrder = insertedOrder;
    }

    public List<Integer> getOrderDetailsId() {
        return this.orderDetailsId;
    }

    public void setOrderDetailsId(List<Integer> orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }
    
}
