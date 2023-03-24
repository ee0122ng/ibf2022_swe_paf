package iss.ibf2022.pafworkshopday03.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import iss.ibf2022.pafworkshopday03.exception.ResourceNotFoundException;
import iss.ibf2022.pafworkshopday03.model.Order;

@Repository
public class OrderRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String GET_ORDER_BY_ID_SQL = "SELECT o.id AS order_id, o.order_date, o.customer_id, od.quantity * od.unit_price * IF(od.discount > 0, 1 - od.discount, 1) AS total_cost, od.quantity * p.standard_cost AS cost_price " +
    "FROM orders AS o " +
    "LEFT JOIN order_details AS od " +
    "ON o.id = od.order_id " +
    "LEFT JOIN products AS p " +
    "ON od.product_id = p.id " +
    "WHERE o.id = ?";

    public List<Order> getOrderById(String id) {

        List<Order> orderList = jdbcTemplate.query(GET_ORDER_BY_ID_SQL, BeanPropertyRowMapper.newInstance(Order.class), id);
        
        if (orderList.size() > 0) {
            return orderList;
        } else {
            throw new ResourceNotFoundException("Id=%s provided not found".formatted(id));
        }
                
    }
    
}
