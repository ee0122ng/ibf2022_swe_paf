package iss.paf.pafselfRevision01.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import iss.paf.pafselfRevision01.model.Order;

@Repository
public class OrderRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private Logger logger = Logger.getLogger(OrderRepository.class.getName());

    private final String SELECTORDERBYCUSTOMERID = "select * from orders where customer_id = ?";

    public Optional<List<Order>> getOrderByCustomerId(Integer id) {
        
        try {
            // orderList might return as an empty list
            List<Order> orderList = jdbcTemplate.query(SELECTORDERBYCUSTOMERID, BeanPropertyRowMapper.newInstance(Order.class), id);
            
            if (orderList.size() > 0) {
                return Optional.of(orderList);
            } else {
                return Optional.empty();
            }

        } catch (EmptyResultDataAccessException e) {

            logger.log(Level.INFO, "No record found in the database for id = %d".formatted(id));

            return Optional.empty();
        }
    }
    
}