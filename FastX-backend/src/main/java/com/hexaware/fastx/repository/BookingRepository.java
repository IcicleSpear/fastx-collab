package com.hexaware.fastx.repository;

import com.hexaware.fastx.entity.Booking;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query(value = "SELECT * FROM booking WHERE ticket_number = :ticketNumber", nativeQuery = true)
    Optional<Booking> findByTicketNumber(String ticketNumber);

    @Query("SELECT b FROM Booking b WHERE b.user.userId = :userId")
    List<Booking> findByUser_UserId(int userId);


    @Query("SELECT b FROM Booking b WHERE b.bookingTime BETWEEN :start AND :end")
    List<Booking> findByBookingTimeBetween(LocalDateTime start, LocalDateTime end);
}
