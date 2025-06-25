package com.hexaware.fastx.service;

import com.hexaware.fastx.dto.BookingDTO;
import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    BookingDTO createBooking(BookingDTO bookingDTO);

    void cancelBooking(int bookingId);

    BookingDTO getBookingById(int bookingId);

    List<BookingDTO> getBookingsByUserId(int userId);

    List<BookingDTO> getBookingsByDate(LocalDate date);

    BookingDTO getBookingByTicket(String ticketNumber);

    void sendNotification(int userId, String message);
}
