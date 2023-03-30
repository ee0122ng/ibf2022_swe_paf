package ibf2022.batch2.paf.serverstub.model;

public class Account {
    
    private Integer id;
    private String accountName;
    private Float balance;

    public Account() {
    }

    public Account(Integer id, String accountName, Float balance) {
        this.id = id;
        this.accountName = accountName;
        this.balance = balance;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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

}