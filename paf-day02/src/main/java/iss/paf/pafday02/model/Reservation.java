package iss.paf.pafday02.model;

import java.sql.Date;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Reservation {

    @Id
    private Integer id;

    @NotBlank(message="Name cannot be blank")
    @Size(min=2, max=150, message="Name length must be between 2 and 150")
    private String fullName;

    @NotBlank(message="Email cannot be blank")
    @Email(message="Invalid email format")
    private String email;

    @Pattern(regexp="[0-9]{8}", message="Phone number must have exactly 8 digits")
    private String phone;

    private Date confirmationDate;

    private String comments;

    public Reservation() {
    }

    public Reservation(Integer id, String fullName, String email, String phone, Date confirmationDate, String comments) {
        this.id = id;
        this.fullName = fullName;
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

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public void setConfirmationDate(Date confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
}
