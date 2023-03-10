package iss.paf.pafday01.repository;

import java.util.List;
import java.util.Optional;

import iss.paf.pafday01.model.Room;

public interface IRoomRepo {

    int count();

    Boolean save(Room room);

    List<Room> findAll();

    Optional<Room> findById(Integer id);
    
    int update(Room room);

    int deleteById(Integer id);
    
}
