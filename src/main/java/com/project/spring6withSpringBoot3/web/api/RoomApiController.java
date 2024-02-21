package com.project.spring6withSpringBoot3.web.api;

// @RestController adds response body to every single method. // RESTfull web services: MVC: view is JSON here (or can be set as XML)

import com.project.spring6withSpringBoot3.data.entity.Room;
import com.project.spring6withSpringBoot3.data.repository.RoomRepository;
import com.project.spring6withSpringBoot3.web.exception.BadRequestException;
import com.project.spring6withSpringBoot3.web.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
public class RoomApiController {

    private final RoomRepository roomRepository;

    public RoomApiController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return this.roomRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Room createRoom(@RequestBody Room room) {
        return this.roomRepository.save(room);
    }

    @GetMapping("/{id}")
    public Room getRoom(@PathVariable("id") long id) {
        Optional<Room> room = this.roomRepository.findById(id);
        if(room.isEmpty()) {
            throw new NotFoundException("room not found with id: " + id);
        }
        return room.get();
    }

    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable("id") long id, @RequestBody Room room) {
        if(id != room.getId()) {
            throw new BadRequestException("id on path doesn't match body");
        }
        return this.roomRepository.save(room);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteRoom(@PathVariable("id") long id) {
        this.roomRepository.deleteById(id);
    }
}
