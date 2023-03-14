package iss.paf.pafday01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iss.paf.pafday01.model.Employee;
import iss.paf.pafday01.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository empRepo;

    public Boolean save(Employee employee) {

        return empRepo.save(employee);
    }

    public Integer update(Employee employee) {

        return empRepo.update(employee);
    }

    public Integer delete(Integer id) {

        return empRepo.delete(id);
    }

    public List<Employee> retrieveAll() {

        return empRepo.findAll();
    }

    public Employee retrieveById(Integer id) {

        return empRepo.findById(id);
    }
    
}
