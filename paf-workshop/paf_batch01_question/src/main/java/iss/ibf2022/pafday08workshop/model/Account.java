package iss.ibf2022.pafday08workshop.model;

public class Account {

    private String accountId;
    private String accountName;
    private Float balance;

    public Account() {
    }

    public Account(String accountId, String accountName, Float balance) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.balance = balance;
    }

    public String getAccountId() {
        return this.accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Float getBalance() {
        return this.balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "{" +
            " accountId='" + getAccountId() + "'" +
            ", accountName='" + getAccountName() + "'" +
            ", balance='" + getBalance() + "'" +
            "}";
    }

    
}
