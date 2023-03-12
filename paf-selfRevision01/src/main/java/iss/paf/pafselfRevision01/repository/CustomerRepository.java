package iss.paf.pafselfRevision01.repository;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import iss.paf.pafselfRevision01.model.Customer;

@Repository
public class CustomerRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String SELECTALLWITHLIMITOFFSET = "select * from customers limit ? offset ?";
    private final String SELECTCUSTOMERBYID = "select * from customers where id = ?";
    private Logger logger = Logger.getLogger(CustomerRepository.class.getName());

    public List<Customer> getCustomersWithLimitOffset(Integer limit, Integer offset) {
            
        return jdbcTemplate.query(SELECTALLWITHLIMITOFFSET, BeanPropertyRowMapper.newInstance(Customer.class), limit, offset);

    }

    public Optional<Customer> getCustomerById(Integer id) {

        try {

            Customer customer = jdbcTemplate.queryForObject(SELECTCUSTOMERBYID, BeanPropertyRowMapper.newInstance(Customer.class), id);

            return Optional.of(customer);
        
        } catch (EmptyResultDataAccessException e) {

            logger.debug("No record found in database for id = %d".formatted(id));

            return Optional.empty();
        }

    }

    
}