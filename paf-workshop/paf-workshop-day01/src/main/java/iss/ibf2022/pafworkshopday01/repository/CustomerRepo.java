package iss.ibf2022.pafworkshopday01.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import iss.ibf2022.pafworkshopday01.model.Customer;
import iss.ibf2022.pafworkshopday01.model.Order;

@Repository
public class CustomerRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String GET_ALL_SQL = "select * from customers limit ? offset ?";
    private final String GET_BY_ID_SQL = "select * from customers where id = ?";
    private final String GET_ORDER_BY_CUSTOMERID_SQL = "select * from orders where customer_id = ?";

    public Optional<List<Customer>> getAll(Integer limit, Integer offset) {

        List<Customer> customerList = jdbcTemplate.query(GET_ALL_SQL, BeanPropertyRowMapper.newInstance(Customer.class), limit, offset);

        if (customerList.size() > 0) {

            return Optional.of(customerList);

        }

        return Optional.empty();

    }

    public Optional<Customer> getById(Integer id) {
        
        try {

            Customer customer = jdbcTemplate.queryForObject(GET_BY_ID_SQL, BeanPropertyRowMapper.newInstance(Customer.class), id);
            return Optional.of(customer);

        } catch (Exception ex) {

        }
    
        return Optional.empty();

    }

    public Optional<List<Order>> getOrderById(Integer id) {

        List<Order> orderList = jdbcTemplate.query(GET_ORDER_BY_CUSTOMERID_SQL, BeanPropertyRowMapper.newInstance(Order.class), id);
        
        if (orderList.size() > 0) {
            return Optional.of(orderList);
        }
        
        return Optional.empty();
    }
    
}
