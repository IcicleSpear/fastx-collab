package com.example.demo.DTO;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.Enum.BookingStatus;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class BookingDTO {
   
	private int bookingId;

    @NotNull(message = "Booking date is required")
    @FutureOrPresent(message = "Booking date cannot be in the past")
    private LocalDate bookingDate;

    @Positive(message = "Total amount must be positive")
    private double totalAmount;

    @NotNull(message = "Booking status is required")
    private BookingStatus bookingStatus;

    @NotNull(message = "User is required")
    private UserDTO user;

    @NotNull(message = "Route is required")
    private RouteDTO route;

    @NotNull(message = "Bus is required")
    private BusDTO bus;

    private PaymentDTO payment;

    private List<PassengerDTO> passengers;

    private List<SeatDTO> seats;

    public BookingDTO() {
        super();
    }

    public BookingDTO(int bookingId, LocalDate bookingDate, double totalAmount, BookingStatus bookingStatus,
                      UserDTO user, RouteDTO route, BusDTO bus, PaymentDTO payment,
                      List<PassengerDTO> passengers, List<SeatDTO> seats) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.totalAmount = totalAmount;
        this.bookingStatus = bookingStatus;
        this.user = user;
        this.route = route;
        this.bus = bus;
        this.payment = payment;
        this.passengers = passengers;
        this.seats = seats;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public RouteDTO getRoute() {
        return route;
    }

    public void setRoute(RouteDTO route) {
        this.route = route;
    }

    public BusDTO getBus() {
        return bus;
    }

    public void setBus(BusDTO bus) {
        this.bus = bus;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
    }

    public List<PassengerDTO> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<PassengerDTO> passengers) {
        this.passengers = passengers;
    }

    public List<SeatDTO> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatDTO> seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "BookingDTO [bookingId=" + bookingId + ", bookingDate=" + bookingDate +
               ", totalAmount=" + totalAmount + ", bookingStatus=" + bookingStatus +
               ", user=" + user + ", route=" + route + ", bus=" + bus +
               ", payment=" + payment + ", passengers=" + passengers + ", seats=" + seats + "]";
    }
}
