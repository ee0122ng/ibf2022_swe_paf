package iss.paf.pafday01.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iss.paf.pafday01.model.Room;
import iss.paf.pafday01.repository.IRoomRepo;

@Service
public class RoomService {

    @Autowired
    IRoomRepo roomRepo;

    public int count() {

        return roomRepo.count();
        
    }

    public Boolean save(Room room) {

        return roomRepo.save(room);

    }

    public List<Room> findAll() {

        return roomRepo.findAll();

    }

    public Optional<Room> findById(Integer id) {

        Optional<Room> opt = roomRepo.findById(id);

        if (null == opt) {

            return Optional.empty();
        }

        return opt;

    }

    public int update(Room room) {

        return roomRepo.update(room);

    }

    public int deleteById(Integer id) {

        return roomRepo.deleteById(id);

    }

}
