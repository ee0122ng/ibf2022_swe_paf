package iss.paf.pafday01app.repository;

import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import iss.paf.pafday01app.model.Employee;

@Repository
public class EmployeeRepo {
    
     private static final RestTemplate restTemplate = new RestTemplate();

    // define various endpoint
    private static final String GET_EMPLOYEES_ENDPOINT_URL = "http://localhost:8080/api/employees";
    private static final String GET_EMPLOYEEBYID_ENDPOINT_URL = "http://localhost:8080/api/employees/{id}";
    private static final String CREATE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/api/employees";
    private static final String UPDATE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/api/employees";
    private static final String DELETE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/api/employees/{id}";

    public List<Employee> findAll() {

        RequestEntity<Void> req = RequestEntity.get(GET_EMPLOYEES_ENDPOINT_URL).build();
        ResponseEntity<List<Employee>> rep = restTemplate.exchange(req, 
            new ParameterizedTypeReference<List<Employee>>() {
            });

        return rep.getBody();
    }

    public Employee findById(Integer id) {

        Employee emp = restTemplate.getForObject(GET_EMPLOYEEBYID_ENDPOINT_URL, Employee.class, Map.of("id", id));

        return emp;
    }

    public Boolean save(Employee employee) {

        Boolean result = restTemplate.postForObject(CREATE_EMPLOYEE_ENDPOINT_URL, employee, Boolean.class);

        return result;
    }

    public Integer update(Employee employee) {

        restTemplate.put(UPDATE_EMPLOYEE_ENDPOINT_URL, employee);

        return 1;
    }

    public Integer delete(Integer id) {

        restTemplate.delete(DELETE_EMPLOYEE_ENDPOINT_URL, Map.of("id", id));

        return 1;
    }

}
