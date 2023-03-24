package iss.ibf2022.pafworkshopday04.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import iss.ibf2022.pafworkshopday04.model.Order;
import iss.ibf2022.pafworkshopday04.model.OrderDetails;

@Repository
public class OrderRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    private final String INSERT_ORDER_SQL = "insert into orders (order_date, customer_name, ship_address, notes, tax) values (?, ?, ?, ?, ?)";
    private final String INSERT_ORDER_DETAILS_SQL = "insert into order_details (order_id, product, unit_price, discount, quantity) values (?, ?, ?, ?, ?)";

    public Integer insertNewOrder(Order order) {

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(INSERT_ORDER_SQL, new String[] {"orderId"});

                ps.setDate(1, order.getOrderDate());
                ps.setString(2, order.getCustomerName());
                ps.setString(3, order.getShipAddress());
                ps.setString(4, order.getNotes());
                ps.setFloat(5, order.getTax());

                return ps;

            }
            
        };

        Integer affectedRow = jdbcTemplate.update(psc, generatedKeyHolder);

        Integer returnedId = generatedKeyHolder.getKey().intValue();

        return affectedRow > 0 ? returnedId : -1000;

    }

    public Integer insertOrderDetails(List<OrderDetails> orderDetails) {

        // List<Object[]> params = orderDetails.stream().map(od -> new Object[]{
        //     od.getProduct(),
        //     od.getUnitPrice(),
        //     od.getDiscount(),
        //     od.getQuantity(),
        //     od.getOrderId()
        // }).toList();

        // int[] rowAffected = jdbcTemplate.batchUpdate(INSERT_ORDER_DETAILS_SQL, params);

        int[] rowAffected = jdbcTemplate.batchUpdate(INSERT_ORDER_DETAILS_SQL, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, orderDetails.get(i).getOrderId());
                ps.setString(2, orderDetails.get(i).getProduct());
                ps.setFloat(3, orderDetails.get(i).getUnitPrice());
                ps.setFloat(4, orderDetails.get(i).getDiscount());
                ps.setInt(5, orderDetails.get(i).getQuantity());
            }

            @Override
            public int getBatchSize() {
                return orderDetails.size();
            }
            
        });

        return rowAffected.length;

    }

    public List<Integer> insertOrderDetailsList(List<OrderDetails> orderDetails) {

        List<Integer> keyHolderList = new LinkedList<>();

        for (OrderDetails od : orderDetails) {
            KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

            PreparedStatementCreator psc = new PreparedStatementCreator() {
                
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

                    PreparedStatement ps = con.prepareStatement(INSERT_ORDER_DETAILS_SQL, new String[]{"id"});
                    ps.setInt(1, od.getOrderId());
                    ps.setString(2, od.getProduct());
                    ps.setFloat(3, od.getUnitPrice());
                    ps.setFloat(4, od.getDiscount());
                    ps.setInt(5, od.getQuantity());

                    return ps;
                }
            };

            Integer affectedRow = jdbcTemplate.update(psc, generatedKeyHolder);
            Integer returnedId = generatedKeyHolder.getKey().intValue();

            keyHolderList.add(returnedId);
        }

        return keyHolderList;
    }
    
}
