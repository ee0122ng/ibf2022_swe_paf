package iss.ibf2022.pafworkshopday01.model;

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

    @Size(max=50, message="Email address can have at most 50 characters")
    private String emailAddress;

    @Size(max=50, message="Job title can have at most 50 characters")
    private String jobTitle;

    @Pattern(regexp="[0-9]{1,25}", message="Business phone number must be between 1 and 25 digits")
    private String businessPhone;

    @Pattern(regexp="[0-9]{1,25}", message="Home phone number must be between 1 and 25 digits")
    private String homePhone;

    @Pattern(regexp="[0-9]{1,25}", message="Mobile phone number must be between 1 and 25 digits")
    private String mobilePhone;

    @Pattern(regexp="[0-9]{1,25}", message="Fax number must be between 1 and 25 digits")
    private String faxNumber;

    private String address;

    @Size(max=50, message="City name can have at most 50 characters")
    private String cityName;

    @Size(max=50, message="State province can have at most 50 characters")
    private String stateProvince;

    @Size(max=15, message="Zip postal code can have at most 15 characters")
    private String zipPostalCode;

    @Size(max=50, message="Country region can have at most 50 characters")
    private String countryRegion;

    private String webPage;

    private String notes;

    public Customer() {
    }

    public Customer(Integer id, String company, String lastName, String firstName, String emailAddress, String jobTitle, String businessPhone, String homePhone, String mobilePhone, String faxNumber, String address, String cityName, String stateProvince, String zipPostalCode, String countryRegion, String webPage, String notes) {
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
        this.cityName = cityName;
        this.stateProvince = stateProvince;
        this.zipPostalCode = zipPostalCode;
        this.countryRegion = countryRegion;
        this.webPage = webPage;
        this.notes = notes;
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

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public String getWebPage() {
        return this.webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
}
