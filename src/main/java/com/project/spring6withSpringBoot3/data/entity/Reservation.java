package com.project.spring6withSpringBoot3.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.sql.Date; // Because date is coming from DB : Check if its appropriate way to do that ??

@Entity
@Data
@ToString
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reservation_id")
    private Long reservationId;
    @Column(name = "room_id")
    private Long roomId;
    @Column(name = "guest_id")
    private Long guestId;
    @Column(name = "res_date")
    private Date reservationDate;

}
