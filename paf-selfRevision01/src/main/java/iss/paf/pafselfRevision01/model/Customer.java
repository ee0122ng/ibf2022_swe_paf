package iss.paf.pafselfRevision01.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Customer {

    private Integer id;

    @Size(max=50, message="Company name can have at most 50 characters")
    private String company;

    @Size(max=50, message="Last name can have at most 50 characters")
    private String lastName;

    @Size(max=50, message="First name can have at most 50 characters")
    private String firstName;

    @Email(message="Invalid email format")
    @Size(max=50, message="Email can have at most 50 characters")
    private String emailAddress;

    @Size(max=50, message="Job title can have at most 50 characters")
    private String jobTitle;

    @Size(max=25, message="Business phone can have at most 25 digits")
    @Pattern(regexp="[0-9]{0,25}", message="Business phone must be in digit with max length of 25")
    private String businessPhone;

    @Size(max=25, message="Home phone can have at most 25 digits")
    @Pattern(regexp="[0-9]{0,25}", message="Home phone must be in digit with max length of 25")
    private String homePhone;

    @Size(max=25, message="Mobile phone can have at most 25 digits")
    @Pattern(regexp="[0-9]{0,25}", message="Mobile phone must be in digit with max length 25")
    private String mobilePhone;

    @Size(max=25, message="Fax number can have at most 25 digits")
    @Pattern(regexp="[0-9]{0,25}", message="Fax number must be in digit with max length of 25")
    private String faxNumber;

    private String address;

    @Size(max=50, message="City can have at most 50 characters")
    private String city;

    @Size(max=50, message="State province can have at most 50 characters")
    private String stateProvince;

    @Size(max=15, message="Zip postal code can have at most 15 characters")
    private String zipPostalCode;

    @Size(max=50, message="Country region can have at most 50 characters")
    private String countryRegion;

    public Customer() {
    }

    public Customer(Integer id, String company, String lastName, String firstName, String emailAddress, String jobTitle, String businessPhone, String homePhone, String mobilePhone, String faxNumber, String address, String city, String stateProvince, String zipPostalCode, String countryRegion) {
        this.id = id;
        this.company = company;
        this.lastName = lastName;
        this.firstName = firstName;
        this.emailAddress = emailAddress;
        this.jobTitle = jobTitle;
        this.businessPhone = businessPhone;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.faxNumber = faxNumber;
        this.address = address;
        this.city = city;
        this.stateProvince = stateProvince;
        this.zipPostalCode = zipPostalCode;
        this.countryRegion = countryRegion;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getJobTitle() {
        return this.jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getBusinessPhone() {
        return this.businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public String getHomePhone() {
        return this.homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getMobilePhone() {
        return this.mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getFaxNumber() {
        return this.faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return this.stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public String getZipPostalCode() {
        return this.zipPostalCode;
    }

    public void setZipPostalCode(String zipPostalCode) {
        this.zipPostalCode = zipPostalCode;
    }

    public String getCountryRegion() {
        return this.countryRegion;
    }

    public void setCountryRegion(String countryRegion) {
        this.countryRegion = countryRegion;
    }
    
}