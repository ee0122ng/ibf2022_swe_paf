package iss.ibf2022.pafday08workshop.payload;

import java.io.Serializable;
import java.util.Date;

public class TransferPayload implements Serializable{

    private Float amount;
    private String accountFromId;
    private String accountFromName;
    private String accountToId;
    private String accountToName;
    private String transactionId;
    private Date date;

    public TransferPayload() {
    }

    public TransferPayload(Float amount, String accountFromId, String accountFromName, String accountToId, String accountToName, String transactionId, Date date) {
        this.amount = amount;
        this.accountFromId = accountFromId;
        this.accountFromName = accountFromName;
        this.accountToId = accountToId;
        this.accountToName = accountToName;
        this.transactionId = transactionId;
        this.date = date;
    }

    public Float getAmount() {
        return this.amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getAccountFromId() {
        return this.accountFromId;
    }

    public void setAccountFromId(String accountFromId) {
        this.accountFromId = accountFromId;
    }

    public String getAccountFromName() {
        return this.accountFromName;
    }

    public void setAccountFromName(String accountFromName) {
        this.accountFromName = accountFromName;
    }

    public String getAccountToId() {
        return this.accountToId;
    }

    public void setAccountToId(String accountToId) {
        this.accountToId = accountToId;
    }

    public String getAccountToName() {
        return this.accountToName;
    }

    public void setAccountToName(String accountToName) {
        this.accountToName = accountToName;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    
    
}
