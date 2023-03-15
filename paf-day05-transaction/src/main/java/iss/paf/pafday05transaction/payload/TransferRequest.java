package iss.paf.pafday05transaction.payload;

public class TransferRequest {
    
    private Integer accFromId;

    private Integer accToId;

    private Float amount;

    private String message;

    public TransferRequest() {
    }

    public TransferRequest(Integer accFromId, Integer accToId, Float amount, String message) {
        this.accFromId = accFromId;
        this.accToId = accToId;
        this.amount = amount;
        this.message = message;
    }

    public Integer getAccFromId() {
        return this.accFromId;
    }

    public void setAccFromId(Integer accFromId) {
        this.accFromId = accFromId;
    }

    public Integer getAccToId() {
        return this.accToId;
    }

    public void setAccToId(Integer accToId) {
        this.accToId = accToId;
    }

    public Float getAmount() {
        return this.amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
