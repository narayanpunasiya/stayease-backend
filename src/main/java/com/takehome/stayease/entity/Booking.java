// package com.takehome.stayease.entity;

// import jakarta.persistence.*;
// import lombok.*;

// import java.time.LocalDate;

// @Entity
// @Table(name = "bookings")
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// public class Booking {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     @JoinColumn(name = "hotel_id", nullable = false)
//     private Hotel hotel;

//     @ManyToOne
//     @JoinColumn(name = "user_id", nullable = false)
//     private User user; // Customer who booked

//     @Column(nullable = false)
//     private LocalDate checkInDate;

//     @Column(nullable = false)
//     private LocalDate checkOutDate;
// }
