package ibf2022.batch2.paf.serverstub.model;

public class Transaction {

    private Integer id;
    private Integer accountId;
    private String userFrom;
    private String userTo;
    private Float amount;

    public Transaction() {
    }

    public Transaction(Integer id, Integer accountId, String userFrom, String userTo, Float amount) {
        this.id = id;
        this.accountId = accountId;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.amount = amount;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return this.accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getUserFrom() {
        return this.userFrom;
    }

    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }

    public String getUserTo() {
        return this.userTo;
    }

    public void setUserTo(String userTo) {
        this.userTo = userTo;
    }

    public Float getAmount() {
        return this.amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
    
}
