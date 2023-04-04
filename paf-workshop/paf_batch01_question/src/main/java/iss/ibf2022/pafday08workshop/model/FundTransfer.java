package iss.ibf2022.pafday08workshop.model;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class FundTransfer {
    
    private List<Account> fromAccount;
    private List<Account> toAccount;

    @NotNull(message="Amount cannot be null")
    @Min(value=10, message="Minimum transfer amount is 10")
    private Float amount;
    private String comment;

    public FundTransfer() {

    }

    public FundTransfer(List<Account> fromAccount, List<Account> toAccount, Float amount, String comment) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.comment = comment;
    }

    public List<Account> getFromAccount() {
        return this.fromAccount;
    }

    public void setFromAccount(List<Account> fromAccount) {
        this.fromAccount = fromAccount;
    }

    public List<Account> getToAccount() {
        return this.toAccount;
    }

    public void setToAccount(List<Account> toAccount) {
        this.toAccount = toAccount;
    }

    public Float getAmount() {
        return this.amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
