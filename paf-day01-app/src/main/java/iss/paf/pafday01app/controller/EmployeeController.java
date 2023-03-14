package iss.paf.pafday01app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import iss.paf.pafday01app.model.Employee;
import iss.paf.pafday01app.service.EmployeeService;

@Controller
@RequestMapping(path={"/employees"})
public class EmployeeController {

    @Autowired
    EmployeeService empSvc;

    @GetMapping
    public String listEmployee(Model model) {

        List<Employee> empList = empSvc.retrieveAll();
        model.addAttribute("employeeList", empList);

        return "employeeList";
    }

    @GetMapping(path={"/addnew"})
    public String addNewEmployee(Model model) {

        model.addAttribute("employee", new Employee());

        return "addemployee";
        
    }

    // as there is no dependent record created
    // the sql statement we have not show any new record as the sql is using inner join
    @PostMapping(path={"/addnew"})
    public String saveEmployee(@ModelAttribute Employee emp, BindingResult result, Model model) {

        if(result.hasErrors()) {

            return "addemployee";
        }

        // save the record to the persistence
        empSvc.createRecord(emp);

        return "redirect:/employees";
    }

    @GetMapping(path={"/delete/{id}"})
    public String deleteEmployee(@PathVariable Integer id) {

        empSvc.deleteRecord(id);

        return "redirect:/employees";
    }

    @GetMapping(path={"/edit/{id}"})
    public String editEmployee(@PathVariable Integer id, Model model) {

        Employee emp = empSvc.retrieveById(id);
        model.addAttribute("retrievedEmp", emp);

        return "updateemployee";

    }

    @PostMapping(path={"/saveupdate"})
    public String saveEdittedRecord(@ModelAttribute Employee emp, BindingResult result) {

        if (result.hasErrors()) {
            return "updateemployee";
        }

        empSvc.updateRecord(emp);

        return "redirect:/employees";
    }
    
}
