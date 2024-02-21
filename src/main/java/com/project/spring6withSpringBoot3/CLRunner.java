package com.project.spring6withSpringBoot3;

import com.project.spring6withSpringBoot3.data.entity.Guest;
import com.project.spring6withSpringBoot3.data.entity.Reservation;
import com.project.spring6withSpringBoot3.data.entity.Room;
import com.project.spring6withSpringBoot3.data.repository.GuestRepository;
import com.project.spring6withSpringBoot3.data.repository.ReservationRepository;
import com.project.spring6withSpringBoot3.data.repository.RoomRepository;
import com.project.spring6withSpringBoot3.service.RoomReservationService;
import com.project.spring6withSpringBoot3.service.model.RoomReservation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CLRunner implements CommandLineRunner {

    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    private final RoomReservationService roomReservationService;

    public CLRunner(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository, RoomReservationService roomReservationService) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
        this.roomReservationService = roomReservationService;
    }

    @Override
    public void run(String... args) throws Exception {

        List<RoomReservation> reservations = this.roomReservationService.getRoomReservationForDate("2023-08-28");
        reservations.forEach(System.out::println);

       /* System.out.println("*** GUESTS ***");
        List<Guest> guests = this.guestRepository.findAll();
        guests.forEach(System.out::println);
        System.out.println("*** ROOMS ***");
        List<Room> rooms = this.roomRepository.findAll();
        rooms.forEach(System.out::println);
        System.out.println("*** RESERVATIONS ***");
        List<Reservation> reservations = this.reservationRepository.findAll();
        reservations.forEach(System.out::println);*/

/*        List<Room> rooms = this.roomRepository.findAll();
        Optional<Room> room = this.roomRepository.findByRoomNumberIgnoreCase("p1");
        System.out.println(room);
        rooms.forEach(System.out::println);*/
    }
}
