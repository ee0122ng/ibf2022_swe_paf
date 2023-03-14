package iss.paf.pafday03.payload;

public class SearchRequest {

    private Integer orderId;

    public SearchRequest() {
    }

    public SearchRequest(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    
}
