package iss.paf.pafday01app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iss.paf.pafday01app.model.Employee;
import iss.paf.pafday01app.repository.EmployeeRepo;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo empRepo;

    public List<Employee> retrieveAll() {

        return empRepo.findAll();
    }

    public Employee retrieveById(Integer id) {

        return empRepo.findById(id);
    }

    public Boolean createRecord(Employee employee) {

        return empRepo.save(employee);
    }

    public Integer updateRecord(Employee employee) {

        return empRepo.update(employee); 
    }

    public Integer deleteRecord(Integer id) {

        return empRepo.delete(id);
    }
    
}
