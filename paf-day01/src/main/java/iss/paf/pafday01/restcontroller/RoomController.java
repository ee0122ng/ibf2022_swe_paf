package iss.paf.pafday01.restcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import iss.paf.pafday01.exception.ResourceNotFoundException;
import iss.paf.pafday01.model.Room;
import iss.paf.pafday01.service.RoomService;

@Tag(description= "Room API", name="Room Resources")
@RestController
@RequestMapping(path={"/api/rooms"})
public class RoomController {

    @Autowired
    RoomService roomSvc;

    @GetMapping(path={"/count"})
    public ResponseEntity<Integer> getRoomCount() {

        Integer roomCount = roomSvc.count();

        return new ResponseEntity<Integer>(roomCount, HttpStatus.OK);
    }

    @GetMapping(path={"/save"})
    public ResponseEntity<List<Room>> getAllRooms() {

        List<Room> rooms = new ArrayList<>();
        rooms = roomSvc.findAll();

        if (rooms.isEmpty()) {
            // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // return ResponseEntity.noContent().build();

            throw new ResourceNotFoundException("No room found");
            

        } else {

            return ResponseEntity.ok().body(rooms);

        }
    }

    @GetMapping(path={"/{room_id}"})
    public ResponseEntity<Room> getRoomById(@PathVariable Integer room_id) {

        Optional<Room> opt = roomSvc.findById(room_id);

        if (null == opt) {

            throw new ResourceNotFoundException("id %d not found".formatted(room_id));
        }

        return ResponseEntity.ok().body(opt.get());

    }

    @PostMapping()
    public ResponseEntity<Boolean> createRoom(@RequestBody Room room) {

        Boolean created = roomSvc.save(room);

        if (created) {
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(created, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path={"/update"})
    public ResponseEntity<Integer> updateRoom(@RequestBody Room room) {

        int updated = 0;

        updated = roomSvc.update(room);

        if (updated == 1) {

            return new ResponseEntity<>(updated, HttpStatus.OK);

        } else {

            return new ResponseEntity<>(updated, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping(path={"/{room_id}/delete"})
    public ResponseEntity<Integer> deleteById(@PathVariable Integer room_id) {

        int deleted = 0;
        
        deleted = roomSvc.deleteById(room_id);

        if (deleted == 1) {

            return new ResponseEntity<>(deleted, HttpStatus.OK);

        } else {

            return new ResponseEntity<>(deleted, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
