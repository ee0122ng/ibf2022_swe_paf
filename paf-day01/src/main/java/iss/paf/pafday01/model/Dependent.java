package iss.paf.pafday01.model;

import java.sql.Date;

public class Dependent {

    private Integer id;
    private String fullName;
    private String relationship;
    private Date birthDate;
    private Employee employee;

    public Dependent() {
    }

    public Dependent(Integer id, String fullName, String relationship, Date birthDate, Employee employee) {
        this.id = id;
        this.fullName = fullName;
        this.relationship = relationship;
        this.birthDate = birthDate;
        this.employee = employee;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRelationship() {
        return this.relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
}
