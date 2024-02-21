package com.project.spring6withSpringBoot3.service;

import com.project.spring6withSpringBoot3.data.entity.Guest;
import com.project.spring6withSpringBoot3.data.entity.Reservation;
import com.project.spring6withSpringBoot3.data.entity.Room;
import com.project.spring6withSpringBoot3.data.repository.GuestRepository;
import com.project.spring6withSpringBoot3.data.repository.ReservationRepository;
import com.project.spring6withSpringBoot3.data.repository.RoomRepository;
    import com.project.spring6withSpringBoot3.service.model.RoomReservation;
import org.h2.util.StringUtils;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service // will allow the component scanning to occur here, which is required because we'll need some injections here
public class RoomReservationService {
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    public RoomReservationService(GuestRepository guestRepository, ReservationRepository reservationRepository, RoomRepository roomRepository) {
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
    }

    public List<RoomReservation> getRoomReservationForDate(String reservationDate) {
        Date date = null;
        if(!StringUtils.isNullOrEmpty(reservationDate)) {
            date = Date.valueOf(reservationDate);
        } else {
            date = new Date(new java.util.Date().getTime());
        }
        Map<Long, RoomReservation> roomReservations = new HashMap<>();
        List<Room> rooms = this.roomRepository.findAll();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservations.put(roomReservation.getRoomId(), roomReservation);
        });
        List<Reservation> reservations = this.reservationRepository.findAllByReservationDate(date);
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservations.get(reservation.getRoomId());
            roomReservation.setReservationId(reservation.getReservationId());
            roomReservation.setReservationDate(reservation.getReservationDate().toString());
            Optional<Guest> guest = this.guestRepository.findById(reservation.getGuestId());
            roomReservation.setGuestId(guest.get().getGuestId());
            roomReservation.setFirstName(guest.get().getFirstName());
            roomReservation.setLastName(guest.get().getLastName());
        });
        return roomReservations.values().stream().toList();
    }
}
