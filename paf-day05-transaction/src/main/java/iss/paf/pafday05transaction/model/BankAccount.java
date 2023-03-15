package iss.paf.pafday05transaction.model;

public class BankAccount {

    private Integer id;

    private String fullName;

    private String accType;

    private Float balance;

    private Boolean isActive;

    public BankAccount() {
    }

    public BankAccount(Integer id, String fullName, String accType, Float balance, Boolean isActive) {
        this.id = id;
        this.fullName = fullName;
        this.accType = accType;
        this.balance = balance;
        this.isActive = isActive;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAccType() {
        return this.accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public Float getBalance() {
        return this.balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Boolean isIsActive() {
        return this.isActive;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

}