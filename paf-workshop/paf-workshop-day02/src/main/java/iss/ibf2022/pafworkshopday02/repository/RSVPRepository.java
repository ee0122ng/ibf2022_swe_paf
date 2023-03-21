package iss.ibf2022.pafworkshopday02.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import iss.ibf2022.pafworkshopday02.exception.ResourceNotFoundException;
import iss.ibf2022.pafworkshopday02.model.RSVP;

@Repository
public class RSVPRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String SELECT_ALL_SQL = "select * from rsvp";
    private final String SELECT_BY_NAME_SQL = "select * from rsvp where customer_name like ?";
    private final String ADD_NEW_RECORD_SQL = "insert into rsvp(customer_name, email, phone, confirmation_date, comments) values(?, ?, ?, ?, ?)";
    private final String UPDATE_RECORD_SQL = "update rsvp set customer_name = ?, email = ?, phone = ?, confirmation_date = ?, comments = ? where id = ?";
    private final String COUNT_ALL_RECORD_SQL = "select count(*) from rsvp";

    public List<RSVP> getAllRsvp() {

        return jdbcTemplate.query(SELECT_ALL_SQL, BeanPropertyRowMapper.newInstance(RSVP.class));

    }

    public List<RSVP> getRsvpByName(String name) {

        // reformat name to wild card
        String sqlName = "%" + name + "%";

        try {

            List<RSVP> rsvpList = jdbcTemplate.query(SELECT_BY_NAME_SQL, BeanPropertyRowMapper.newInstance(RSVP.class), sqlName);
            return rsvpList;

        } catch (Exception ex) {

            throw new ResourceNotFoundException("No matching name found from the record");
        }

    }

    public Integer addNewRecord(RSVP rsvp) {

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

                PreparedStatement ps = con.prepareStatement(ADD_NEW_RECORD_SQL, new String[]{"id"});

                ps.setString(1, rsvp.getCustomerName());
                ps.setString(2, rsvp.getEmail());
                ps.setString(3, rsvp.getPhone());
                ps.setDate(4, rsvp.getConfirmationDate());
                ps.setString(5, rsvp.getComments());

                return ps;

            }
        };

        int rowAffected = jdbcTemplate.update(psc, generatedKeyHolder);

        Integer returnedId = generatedKeyHolder.getKey().intValue();

        return returnedId;
    }

    public Integer updateBatchRecord(List<RSVP> rsvpList) {

        List<Object[]> params = rsvpList.stream().map(rsvp -> new Object[]{
            rsvp.getCustomerName(),
            rsvp.getEmail(),
            rsvp.getPhone(),
            rsvp.getConfirmationDate(),
            rsvp.getComments(),
            rsvp.getId()
        }).collect(Collectors.toList());

        int[] updated = jdbcTemplate.batchUpdate(UPDATE_RECORD_SQL, params);

        return updated.length;
    }

    public Integer updateRecord(RSVP rsvp) {

        Integer updated = 0;

        updated = jdbcTemplate.update(UPDATE_RECORD_SQL, new PreparedStatementSetter(){

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, rsvp.getCustomerName());
                ps.setString(2, rsvp.getEmail());
                ps.setString(3, rsvp.getPhone());
                ps.setDate(4, rsvp.getConfirmationDate());
                ps.setString(5, rsvp.getComments());
                ps.setInt(6, rsvp.getId());
            }
        });

        return updated;
    }

    public Integer countRecord() {

        try {

            Integer count = jdbcTemplate.queryForObject(COUNT_ALL_RECORD_SQL, Integer.class);
            return count;

        } catch (Exception ex) {

            throw new ResourceNotFoundException("Null entry found from the database");
        }

    }
}