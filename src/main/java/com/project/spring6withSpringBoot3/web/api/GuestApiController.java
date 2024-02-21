package com.project.spring6withSpringBoot3.web.api;

import com.project.spring6withSpringBoot3.data.entity.Guest;
import com.project.spring6withSpringBoot3.data.repository.GuestRepository;
import com.project.spring6withSpringBoot3.web.exception.BadRequestException;
import com.project.spring6withSpringBoot3.web.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/guests")
public class GuestApiController {

    private final GuestRepository guestRepository;

    public GuestApiController(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @GetMapping
    public List<Guest> getAllGuests() {
        return this.guestRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Guest addGuest(@RequestBody Guest guest) {
        return this.guestRepository.save(guest);
    }

    @GetMapping("/{id}")
    public Guest getGuest(@PathVariable long id) {
        Optional<Guest> guest = this.guestRepository.findById(id);
        if(guest.isEmpty()) {
            throw new NotFoundException("guest not found with id: " + id);
        }
        return guest.get();
    }

    @PutMapping("/{id}")
    public Guest updateGuest(@PathVariable long id, @RequestBody Guest guest) {
        if(id != guest.getGuestId()) {
            throw new BadRequestException("id on path must match body");
        }
        return this.guestRepository.save(guest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteGuest(@PathVariable long id) {
        this.guestRepository.deleteById(id);
    }
}
