package iss.paf.pafday03.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import iss.paf.pafday03.model.Order;

@Repository
public class OrderRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String FINDORDERDETAILSBYID = "select od.order_id, o.order_date, od.quantity * od.unit_price * if(od.discount > 0, od.discount, 1) as total_price, p.id as product_id, od.quantity * p.standard_cost as cost_price, o.customer_id from order_details as od left join products as p on od.product_id = p.id inner join orders as o on od.order_id = o.id where o.id = ? ";

    public List<Order> getOrderDetailsById(Integer id) throws DataAccessException {

        return jdbcTemplate.query(FINDORDERDETAILSBYID, BeanPropertyRowMapper.newInstance(Order.class), id);
        // return jdbcTemplate.queryForList(FINDORDERDETAILSBYID, Order.class, id);
    }
    
}

