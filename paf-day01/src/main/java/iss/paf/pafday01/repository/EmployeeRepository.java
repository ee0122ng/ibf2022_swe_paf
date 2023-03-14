package iss.paf.pafday01.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import iss.paf.pafday01.model.Dependent;
import iss.paf.pafday01.model.Employee;
import jakarta.annotation.Nullable;

@Repository
public class EmployeeRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String FINDALLSQL = "select e.id as emp_id, e.first_name, e.last_name, e.salary, " + 
    "d.id as dep_id, d.full_name, d.relationship, d.birth_date " +
    "from employee as e " +
    "inner join dependent as d " +
    "on e.id = d.employee_id";

    private final String FINDBYIDSQL = "select e.id as emp_id, e.first_name, e.last_name, e.salary, " + 
        "d.id as dep_id, d.full_name, d.relationship, d.birth_date " +
        "from employee as e " +
        "inner join dependent as d " +
        "on e.id = d.employee_id " +
        "where e.id = ?";

    private final String INSERTSQL = "insert into employee(first_name, last_name, salary) values (?, ?, ?)";

    private final String UPDATESQL = "update employee set first_name = ?, last_name = ?, salary = ? where id = ?";

    private final String DELETESQL = "delete from employee where id = ?";

    public Boolean save(Employee employee) {

        Boolean saved = false;

        saved = jdbcTemplate.execute(INSERTSQL, new PreparedStatementCallback<Boolean>() {
            
            @Override
            @Nullable
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, employee.getFirstName());
                ps.setString(2, employee.getLastName());
                ps.setFloat(3, employee.getSalary());

                Boolean rs = ps.execute();

                return rs;
            }

        });

        return saved;
    }

    public Integer update(Employee employee) {

        Integer result = jdbcTemplate.update(UPDATESQL, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, employee.getFirstName());
                ps.setString(2, employee.getLastName());
                ps.setFloat(3, employee.getSalary());
                ps.setInt(4, employee.getId());
            }
            
        });

        return result;
    }

    public Integer delete(Integer id) {

        Integer result = jdbcTemplate.update(DELETESQL, new PreparedStatementSetter(){

            @Override
            public void setValues(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setInt(1, id);
            }

        });

        return result;
    }
    
    public List<Employee> findAll() {

        List<Employee> empList = new ArrayList<Employee>();
        
        // empList = jdbcTemplate.query(FINDALLSQL, BeanPropertyRowMapper.newInstance(Employee.class));

        empList = jdbcTemplate.query(FINDALLSQL, new ResultSetExtractor<List<Employee>>() {
            @Override
            public List<Employee> extractData(ResultSet rs) throws SQLException, DataAccessException {
                
                List<Employee> eList = new ArrayList<Employee>();

                while(rs.next()) {
                    Employee emp = new Employee();

                    // update employee details
                    emp.setId(rs.getInt("emp_id"));
                    emp.setFirstName(rs.getString("first_name"));
                    emp.setLastName(rs.getString("last_name"));
                    emp.setSalary(rs.getFloat("salary"));
                    // emp.setDependents(new ArrayList<Dependent>());

                    // update dependent details
                    Dependent dep = new Dependent();
                    dep.setId(rs.getInt("dep_id"));
                    dep.setFullName(rs.getString("full_name"));
                    dep.setBirthDate(rs.getDate("birth_date"));
                    dep.setRelationship(rs.getString("relationship"));

                    if (eList.size() == 0) {
                        emp.getDependents().add(dep);
                        eList.add(emp);

                    } else {
                        
                        // add new dependent record to existing employee based on the matching employee_id found
                        List<Employee> tmpList = eList.stream().filter(e -> e.getId() == emp.getId()).collect(Collectors.toList());


                        if (tmpList.size() > 0) {
                            //append to dependent list for the found employee
                            int idx = eList.indexOf(tmpList.get(0));

                            if (idx >= 0) {
                                eList.get(idx).getDependents().add(dep);
                            }

                        } else {
                            // if the employee record not found in the list of employees
                            // add a new employee record together with the dependent information
                            emp.getDependents().add(dep);
                            eList.add(emp);
                        }

                    }

                }

                return eList;
            }
        });

        return empList;
    }

    public Employee findById(Integer id) {

        SqlRowSet rs = jdbcTemplate.queryForRowSet(FINDBYIDSQL, id);
        Employee emp = new Employee();

        while (rs.next()) {

            // update employee details only once
            // *** beware that getId() by default is 0 for a new Employee object
            if (null == emp.getFirstName()) {
                emp.setId(id);
                emp.setFirstName(rs.getString("first_name"));
                emp.setLastName(rs.getString("last_name"));
                emp.setSalary(rs.getFloat("salary"));
            }

            // update dependent details recursively due to one to many relationship
            Dependent dep = new Dependent();
            dep.setId(rs.getInt("dep_id"));
            dep.setFullName(rs.getString("full_name"));
            dep.setBirthDate(rs.getDate("birth_date"));
            dep.setRelationship(rs.getString("relationship"));
            emp.getDependents().add(dep);

        }

        return emp;

    }
}