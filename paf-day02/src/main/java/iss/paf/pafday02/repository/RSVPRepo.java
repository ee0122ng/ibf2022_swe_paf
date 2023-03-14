package iss.paf.pafday02.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import iss.paf.pafday02.model.Reservation;

@Repository
public class RSVPRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    private final String INSERTNEWRECORD = "insert into reservations(full_name, email, phone, confirmation_date, comments) values(?, ? , ?, ?, ?)";
    private final String COUNTALL = "select count(*) from reservations";
    private final String FINDALL = "select * from reservations";
    private final String FINDBYID = "select * from reservations where id = ?";
    private final String FINDBYNAME = "select * from reservations where full_name like ?";
    private final String UPDATERECORD = "update reservations set " +
                                            "full_name = ?, email = ?, phone = ?, confirmation_date = ?, comments = ? " +
                                            "where id = ?";

    public List<Reservation> findAll() {

        return jdbcTemplate.query(FINDALL, BeanPropertyRowMapper.newInstance(Reservation.class));
    }

    public Integer countAll() {

        return jdbcTemplate.queryForObject(COUNTALL, Integer.class);
    }

    public Reservation findById(Integer id) {

        return jdbcTemplate.queryForObject(FINDBYID, BeanPropertyRowMapper.newInstance(Reservation.class), id);
    }

    public Reservation findByName(String fullName) {

        return jdbcTemplate.queryForObject(FINDBYNAME, BeanPropertyRowMapper.newInstance(Reservation.class), fullName);
    }

    public Boolean save(Reservation reservation) {

        Integer result = jdbcTemplate.update(INSERTNEWRECORD, reservation.getFullName(), reservation.getEmail(), reservation.getPhone(), reservation.getConfirmationDate(), reservation.getComments());

        return result > 0 ? true : false;
    }

    public Boolean update(Reservation reservation) {

        Boolean result = jdbcTemplate.execute(UPDATERECORD, new PreparedStatementCallback<Boolean>() {

            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException {

                ps.setString(1, reservation.getFullName());
                ps.setString(2, reservation.getEmail());
                ps.setString(3, reservation.getPhone());
                ps.setDate(4, reservation.getConfirmationDate());
                ps.setString(5, reservation.getComments());
                ps.setInt(6, reservation.getId());
                

                Boolean rslt = ps.execute();

                return rslt;
            }

        });

        return result;
    }

    public int[] batchUpdate(List<Reservation> rsvps) {

        return jdbcTemplate.batchUpdate(INSERTNEWRECORD, new BatchPreparedStatementSetter() {

                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setString(1, rsvps.get(i).getFullName());
                    ps.setString(2, rsvps.get(i).getEmail());
                    ps.setString(3, rsvps.get(i).getPhone());
                    ps.setDate(4, rsvps.get(i).getConfirmationDate());
                    ps.setString(5, rsvps.get(i).getComments());
                }

                public int getBatchSize() {

                    return rsvps.size();
                }
            });
    }

}