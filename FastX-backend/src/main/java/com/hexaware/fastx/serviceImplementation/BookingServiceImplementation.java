package com.hexaware.fastx.serviceImplementation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.fastx.dto.BookingDTO;
import com.hexaware.fastx.entity.Booking;
import com.hexaware.fastx.entity.Bus;
import com.hexaware.fastx.entity.Route;
import com.hexaware.fastx.entity.Seat;
import com.hexaware.fastx.entity.User;
import com.hexaware.fastx.exception.BookingNotFoundException;
import com.hexaware.fastx.exception.BusNotFoundException;
import com.hexaware.fastx.exception.RouteNotFoundException;
import com.hexaware.fastx.exception.SeatNotFoundException;
import com.hexaware.fastx.exception.UserNotFoundException;
import com.hexaware.fastx.mapper.BookingMapper;
import com.hexaware.fastx.repository.BookingRepository;
import com.hexaware.fastx.repository.BusRepository;
import com.hexaware.fastx.repository.RouteRepository;
import com.hexaware.fastx.repository.SeatRepository;
import com.hexaware.fastx.repository.UserRepository;
import com.hexaware.fastx.service.BookingService;

@Service
public class BookingServiceImplementation implements BookingService {

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BusRepository busRepo;

    @Autowired
    private RouteRepository routeRepo;

    @Autowired
    private SeatRepository seatRepo;

    @Autowired
    private BookingMapper bookingMapper;

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {

    	 User user = userRepo.findById(bookingDTO.getUserId())
    	            .orElseThrow(() -> new UserNotFoundException("User not found"));

    	    Route route = routeRepo.findById(bookingDTO.getRouteId())
    	            .orElseThrow(() -> new RouteNotFoundException("Route not found"));

    	    Bus bus = busRepo.findById(bookingDTO.getBusId())
    	            .orElseThrow(() -> new BusNotFoundException("Bus not found"));

    	    List<Seat> seats = new ArrayList<>();
    	    for (int seatId : bookingDTO.getSeatIds()) {
    	        Seat seat = seatRepo.findById(seatId)
    	                .orElseThrow(() -> new SeatNotFoundException("Seat not found with ID: " + seatId));

    	        if (!seat.getStatus().equalsIgnoreCase("AVAILABLE")) {
    	            throw new IllegalArgumentException("Seat " + seat.getSeatNumber() + " is not available");
    	        }

    	        seat.setStatus("BOOKED");
    	        seatRepo.save(seat);
    	        seats.add(seat);
    	    }
    	    double totalAmount = bus.getFare() * seats.size();

    	    Booking booking = new Booking();
    	    booking.setUser(user);
    	    booking.setRoute(route);
    	    booking.setBus(bus);
    	    booking.setSeats(seats);
    	    booking.setTotalAmount(totalAmount);  // <-- Set calculated amount
    	    booking.setBookingTime(LocalDateTime.now());
    	    booking.setTicketNumber(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
    	    booking.setStatus("CONFIRMED");

    	    bookingRepo.save(booking);

    	    return mapToDTO(booking);
    }

    @Override
    public void cancelBooking(int bookingId) {
        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found"));

        booking.getSeats().forEach(seat -> {
            seat.setStatus("AVAILABLE");
            seatRepo.save(seat);
        });

        bookingRepo.delete(booking);
    }

    @Override
    public BookingDTO getBookingById(int bookingId) {
        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found"));
        return mapToDTO(booking);
    }

    @Override
    public List<BookingDTO> getBookingsByUserId(int userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return bookingRepo.findByUser_UserId(userId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingDTO> getBookingsByDate(LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(23, 59, 59);

        return bookingRepo.findByBookingTimeBetween(start, end).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookingDTO getBookingByTicket(String ticketNumber) {
        Booking booking = bookingRepo.findByTicketNumber(ticketNumber)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with ticket: " + ticketNumber));
        return mapToDTO(booking);
    }

    @Override
    public void sendNotification(int userId, String message) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        
        System.out.println("Notification sent to " + user.getEmail() + ": " + message);
    }

    private BookingDTO mapToDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setBookingId(booking.getBookingId());
        dto.setUserId(booking.getUser().getUserId());
        dto.setRouteId(booking.getRoute().getRouteId());
        dto.setBusId(booking.getBus().getBusId());
        dto.setSeatIds(booking.getSeats().stream().map(Seat::getSeatId).collect(Collectors.toList()));
        dto.setTotalamount(booking.getTotalAmount());
        dto.setBookingTime(booking.getBookingTime());
        dto.setTicketNumber(booking.getTicketNumber());
        dto.setStatus(booking.getStatus());
        return dto;
    }
}
