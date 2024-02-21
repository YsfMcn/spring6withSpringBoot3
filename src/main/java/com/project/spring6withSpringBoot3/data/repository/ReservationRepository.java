package com.project.spring6withSpringBoot3.data.repository;

import com.project.spring6withSpringBoot3.data.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

// @Repository ??
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByReservationDate(Date date);
}
