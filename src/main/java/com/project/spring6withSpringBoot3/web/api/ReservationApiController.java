package com.project.spring6withSpringBoot3.web.api;

import com.project.spring6withSpringBoot3.data.entity.Reservation;
import com.project.spring6withSpringBoot3.data.repository.ReservationRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationApiController {

    private final ReservationRepository reservationRepository;

    public ReservationApiController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping
    public List<Reservation> getAllReservations(@RequestParam(value="date", required = false) String dateString) {
        if(StringUtils.isNotBlank(dateString)) {
            return this.reservationRepository.findAllByReservationDate(Date.valueOf(dateString));
        }
        return this.reservationRepository.findAll();
    }
}
