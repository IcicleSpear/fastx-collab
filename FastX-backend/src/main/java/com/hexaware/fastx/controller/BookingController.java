package com.hexaware.fastx.controller;

import com.hexaware.fastx.dto.BookingDTO;
import com.hexaware.fastx.service.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO) {
        BookingDTO created = bookingService.createBooking(bookingDTO);
        return ResponseEntity.ok(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelBooking(@PathVariable int id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.ok("Booking cancelled successfully.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable int id) {
        BookingDTO booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingDTO>> getBookingsByUser(@PathVariable int userId) {
        List<BookingDTO> bookings = bookingService.getBookingsByUserId(userId);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/date")
    public ResponseEntity<List<BookingDTO>> getBookingsByDate(@RequestParam String date) {
        List<BookingDTO> bookings = bookingService.getBookingsByDate(LocalDate.parse(date));
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/ticket/{ticketNumber}")
    public ResponseEntity<BookingDTO> getBookingByTicket(@PathVariable String ticketNumber) {
        BookingDTO booking = bookingService.getBookingByTicket(ticketNumber);
        return ResponseEntity.ok(booking);
    }

    @PostMapping("/notifications")
    public ResponseEntity<String> sendNotification(@RequestParam int userId, @RequestParam String message) {
        bookingService.sendNotification(userId, message);
        return ResponseEntity.ok("Notification sent to user.");
    }
}
