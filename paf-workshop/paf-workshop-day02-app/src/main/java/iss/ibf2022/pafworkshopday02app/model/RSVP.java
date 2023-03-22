package iss.ibf2022.pafworkshopday02app.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class RSVP {

    private Integer id;

    @NotBlank(message="Name cannot be blank")
    private String customerName;

    @NotBlank(message="Email cannot be blank")
    @Email(message="Invalid email format")
    private String email;

    @Pattern(regexp="[0-9]{0,8}", message="Phone number must be less than 8 digits")
    private String phone;

    // @DataTimeFormat only work for java.util.Date
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date confirmationDate;

    private String comments;

    public RSVP() {
    }

    public RSVP(Integer id, String customerName, String email, String phone, Date confirmationDate, String comments) {
        this.id = id;
        this.customerName = customerName;
        this.email = email;
        this.phone = phone;
        this.confirmationDate = confirmationDate;
        this.comments = comments;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getConfirmationDate() {
        return this.confirmationDate;
    }

    public void setConfirmationDate(Date date) {
        this.confirmationDate = date;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
}
