package com.example.demo.Entity;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.Enum.BookingStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bookings")



public class Booking {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "booking_id")
	    private int bookingId;

	    private LocalDate bookingDate;

	    private double totalAmount;

	    @Enumerated(EnumType.STRING)
	    @Column(name = "booking_status")
	    private BookingStatus bookingStatus;

	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    private User user;

	    @ManyToOne
	    @JoinColumn(name = "route_id")
	    private Route route;

	    @ManyToOne
	    @JoinColumn(name = "bus_id")
	    private Bus bus;

	    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
	    private Payment payment;

	    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
	    private List<Passenger> passengers;

	    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
	    private List<Seat> seats;

		public Booking() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Booking(int bookingId, LocalDate bookingDate, double totalAmount, BookingStatus bookingStatus, User user,
				Route route, Bus bus, Payment payment, List<Passenger> passengers, List<Seat> seats) {
			super();
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

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Route getRoute() {
			return route;
		}

		public void setRoute(Route route) {
			this.route = route;
		}

		public Bus getBus() {
			return bus;
		}

		public void setBus(Bus bus) {
			this.bus = bus;
		}

		public Payment getPayment() {
			return payment;
		}

		public void setPayment(Payment payment) {
			this.payment = payment;
		}

		public List<Passenger> getPassengers() {
			return passengers;
		}

		public void setPassengers(List<Passenger> passengers) {
			this.passengers = passengers;
		}

		public List<Seat> getSeats() {
			return seats;
		}

		public void setSeats(List<Seat> seats) {
			this.seats = seats;
		}

		@Override
		public String toString() {
			return "Booking [bookingId=" + bookingId + ", bookingDate=" + bookingDate + ", totalAmount=" + totalAmount
					+ ", bookingStatus=" + bookingStatus + ", user=" + user + ", route=" + route + ", bus=" + bus
					+ ", payment=" + payment + ", passengers=" + passengers + ", seats=" + seats + "]";
		}
	
	    
	    
	    
}
