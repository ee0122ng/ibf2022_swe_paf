package iss.paf.pafday01.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import iss.paf.pafday01.model.Room;

@Repository
public class RoomRepo implements IRoomRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String countAllListings = "select count(*) from room";
    private final String insertNewRoom = "insert into room (room_type, price) values (?, ?)";
    private final String findAllListings = "select * from room";
    private final String findRoomById = "select * from room where id = ?";
    private final String updateRoomPriceById = "update room set price = ? where id = ?";
    private final String deleteRoomById = "delete from room where id = ?";

    @Override
    public int count() {

        Integer result = 0;
        result = jdbcTemplate.queryForObject(countAllListings, Integer.class);

        if (null == result) {

            return 0;

        } else {

            return result;
        }
        
    }

    // create new room
    @Override
    public Boolean save(Room room) {

        Boolean save = false;

        save = jdbcTemplate.execute(insertNewRoom, new PreparedStatementCallback<Boolean>() {

            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException {

                ps.setString(1, room.getRoomType());
                ps.setFloat(2, room.getPrice());

                Boolean rslt = ps.execute();

                return rslt;
            }
        });

        return save;
    }

    @Override
    public List<Room> findAll() {
        
        List<Room> rooms = new ArrayList<>();
        
        // map all query statement (row basis) to a room class 
        rooms = jdbcTemplate.query(findAllListings, BeanPropertyRowMapper.newInstance(Room.class));

        return rooms;
    }

    @Override
    public Optional<Room> findById(Integer id) {

        try {
            
            Room room = jdbcTemplate.queryForObject(findRoomById, BeanPropertyRowMapper.newInstance(Room.class), id);

            return Optional.of(room);

        } catch (Exception ex) {

            System.out.println(">>>error from retrieving by id: " + ex.getMessage());

            return Optional.empty();
        }

    }

    @Override
    public int update(Room room) {

        int updated = 0;

        // return number of affected rows
        updated = jdbcTemplate.update(updateRoomPriceById, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {

                ps.setFloat(1, room.getPrice());
                ps.setInt(2, room.getId());
            }

        });
        
        return updated; 
    }

    @Override
    public int deleteById(Integer id) {
        
        int deleted = 0;

        PreparedStatementSetter pss = new PreparedStatementSetter() {
            
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, id);
            }
        };

        deleted = jdbcTemplate.update(deleteRoomById, pss);

        return deleted;
    }
    
}
