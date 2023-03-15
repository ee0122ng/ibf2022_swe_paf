package iss.paf.pafday05transaction.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import iss.paf.pafday05transaction.model.ReservationDetails;

@Repository
public class ReservationDetailsRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String INSERT_SQL = "insert into reservation_details (book_id, reservation_id) values (?, ?)";

    public Integer insertNewRecord(ReservationDetails rd) {

        // create GeneratedKeyHOlder object
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(INSERT_SQL, new String[] {"id"});

                ps.setInt(1, rd.getBookId());
                ps.setInt(2, rd.getReservationId());

                return ps;
                
            }

        };

        // check row affected
        Integer rowAffected = jdbcTemplate.update(psc, generatedKeyHolder);

        // check returned id
        Integer returnedId = generatedKeyHolder.getKey().intValue();

        return returnedId;
    }
    
}
