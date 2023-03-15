package iss.paf.pafday05transaction.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import iss.paf.pafday05transaction.model.Reservation;

@Repository
public class ReservationRepo {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SELECT_SQL = "select * from the reservation";
    private static final String INSERT_SQL = "insert into reservation (reservation_date, full_name) values (?, ?)";

    public List<Reservation> getAllReservation() {

        return jdbcTemplate.query(SELECT_SQL, BeanPropertyRowMapper.newInstance(Reservation.class));
    }

    // return the id generated for the new record
    public Integer insertNewReservation(Reservation reservation) {

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                
                // specify an array of column names that should be return from the sql execution
                PreparedStatement ps = con.prepareStatement(INSERT_SQL, new String[] {"id"});
                ps.setDate(1, reservation.getReservationDate());
                ps.setString(2, reservation.getFullName());

                return ps;
            }
        };

        int rowsAffected = jdbcTemplate.update(psc, generatedKeyHolder);

        // Get auto-incremented ID
        Integer returnedId = generatedKeyHolder.getKey().intValue();

        return returnedId;
    }
}
