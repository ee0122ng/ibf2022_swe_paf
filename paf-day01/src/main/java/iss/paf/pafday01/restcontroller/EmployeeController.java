package iss.paf.pafday01.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iss.paf.pafday01.model.Employee;
import iss.paf.pafday01.service.EmployeeService;

@RestController
@RequestMapping(path={"/api/employees"})
public class EmployeeController {

    @Autowired
    EmployeeService empSvc;

    @PostMapping
    public ResponseEntity<Boolean> insertRecord(@RequestBody Employee employee) {
        
        return ResponseEntity.status(HttpStatus.OK).body(empSvc.save(employee));
    }

    @PutMapping()
    public ResponseEntity<Integer> updateRecord(@RequestBody Employee employee) {

        return ResponseEntity.status(HttpStatus.OK).body(empSvc.update(employee));
    }

    @DeleteMapping(path={"/{id}"})
    public ResponseEntity<Integer> deleteRecord(@PathVariable Integer id) {

        return ResponseEntity.status(HttpStatus.OK).body(empSvc.delete(id));
    }

    @GetMapping()
    public ResponseEntity<List<Employee>> getAllRecords() {
        
        return ResponseEntity.status(HttpStatus.OK).body(empSvc.retrieveAll());
    }

    @GetMapping(path={"/{id}"})
    public ResponseEntity<Employee> getRecordById(@PathVariable Integer id) {

        return ResponseEntity.status(HttpStatus.OK).body(empSvc.retrieveById(id));
    }
    
}
