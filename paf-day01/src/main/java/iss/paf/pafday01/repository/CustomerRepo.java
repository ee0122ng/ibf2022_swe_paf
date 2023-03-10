package iss.paf.pafday01.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import iss.paf.pafday01.model.Customer;
import iss.paf.pafday01.model.Order;

@Repository
public class CustomerRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    // add query statement here
    // for leisure database
    private final String findAllSQL = "select * from customer";
    private final String findAllSQLLimitOffset = "select * from customer limit ? offset ?";
    private final String findById = "select * from customer where id = ?";

    // for northwind database
    private final String findOrderByCustomer = "select id, customer_id, order_date, shipped_date, shipper_id, ship_name from orders where customer_id = ?";

    public List<Customer> getAllCustomers() {

        final List<Customer> customerList = new ArrayList<Customer>();
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(findAllSQL);

        while (rs.next()) {
            Customer cust = new Customer();
            cust.setId(rs.getInt("id"));
            cust.setFirstName(rs.getString("first_name"));
            cust.setLastName(rs.getString("last_name"));
            cust.setDob(rs.getDate("dob"));
            customerList.add(cust);
        }
        
        return Collections.unmodifiableList(customerList);
    }

    public List<Customer> getAllCustomersWithLimitOffset(Integer limit, Integer offset) {

        final List<Customer> customerList = new ArrayList<Customer>();

        final SqlRowSet rs = jdbcTemplate.queryForRowSet(findAllSQLLimitOffset, limit, offset);

        while (rs.next()) {
            Customer cust = new Customer();
            cust.setId(rs.getInt("id"));
            cust.setFirstName(rs.getString("first_name"));
            cust.setLastName(rs.getString("last_name"));
            cust.setDob(rs.getDate("dob"));
            customerList.add(cust);
        }

        return Collections.unmodifiableList(customerList);
    }

    public Customer getCustomerById(Integer id) {

        return jdbcTemplate.queryForObject(findById, BeanPropertyRowMapper.newInstance(Customer.class), id);
    }

    public List<Order> getCustomerOrder(Integer customerId) {
        
        final List<Order> orderList = new LinkedList<>();

        final SqlRowSet rs = jdbcTemplate.queryForRowSet(findOrderByCustomer, customerId);

        while (rs.next()) {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            order.setCustomerId(rs.getInt("customer_id"));
            order.setOrderDate((LocalDateTime) rs.getObject("order_date"));
            order.setShippedDate((LocalDateTime) rs.getObject("shipped_date"));
            order.setShipName(rs.getString("ship_name"));
            order.setShipperId(rs.getInt("shipper_id"));
            orderList.add(order);
        }
        
        return orderList;
    }
    
}
