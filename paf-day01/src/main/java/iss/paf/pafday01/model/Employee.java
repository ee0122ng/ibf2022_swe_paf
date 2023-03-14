package iss.paf.pafday01.model;

import java.util.ArrayList;
import java.util.List;

public class Employee {

    private Integer id;
    private String firstName;
    private String lastName;
    private Float salary = 0f;
    // one employee can have many dependents
    private List<Dependent> dependents = new ArrayList<>();

    public Employee() {
    }

    public Employee(Integer id, String firstName, String lastName, Float salary, List<Dependent> dependents) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.dependents = dependents;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Float getSalary() {
        return this.salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public List<Dependent> getDependents() {
        return this.dependents;
    }

    public void setDependents(List<Dependent> dependents) {
        this.dependents = dependents;
    }
    
}