	package com.example.demo.Entity;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.Enum.BusStatus;
import com.example.demo.Enum.BusType;

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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "buses")

public class Bus {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "bus_id")
	    private int busId;

	    @Column(name = "bus_number")
	    private String busNumber;

	    @Column(name = "bus_name")
	    private String busName;

	    @Enumerated(EnumType.STRING)
	    private BusType busType;

	    private int totalSeats;

	    
	    private String amenities;
	    
	    @Column(name = "travel_date")
	    private LocalDate date;
	    

	    @Enumerated(EnumType.STRING)
	    @Column(name = "bus_status")
	    private BusStatus busStatus;

	    @ManyToOne
	    @JoinColumn(name = "operator_id")
	    private User operator;

	    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
	    private List<Seat> seats;

	    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
	    private List<Booking> bookings;
	    
	    
	    @ManyToOne
	    @JoinColumn(name = "route_id")
	    private Route route;


		public Bus() {
			super();
			// TODO Auto-generated constructor stub
		}


		public Bus(int busId, String busNumber, String busName, BusType busType, int totalSeats, String amenities,
				LocalDate date, BusStatus busStatus, User operator, List<Seat> seats, List<Booking> bookings,
				Route route) {
			super();
			this.busId = busId;
			this.busNumber = busNumber;
			this.busName = busName;
			this.busType = busType;
			this.totalSeats = totalSeats;
			this.amenities = amenities;
			this.date = date;
			this.busStatus = busStatus;
			this.operator = operator;
			this.seats = seats;
			this.bookings = bookings;
			this.route = route;
		}


		public int getBusId() {
			return busId;
		}


		public void setBusId(int busId) {
			this.busId = busId;
		}


		public String getBusNumber() {
			return busNumber;
		}


		public void setBusNumber(String busNumber) {
			this.busNumber = busNumber;
		}


		public String getBusName() {
			return busName;
		}


		public void setBusName(String busName) {
			this.busName = busName;
		}


		public BusType getBusType() {
			return busType;
		}


		public void setBusType(BusType busType) {
			this.busType = busType;
		}


		public int getTotalSeats() {
			return totalSeats;
		}


		public void setTotalSeats(int totalSeats) {
			this.totalSeats = totalSeats;
		}


		public String getAmenities() {
			return amenities;
		}


		public void setAmenities(String amenities) {
			this.amenities = amenities;
		}


		public LocalDate getDate() {
			return date;
		}


		public void setDate(LocalDate date) {
			this.date = date;
		}


		public BusStatus getBusStatus() {
			return busStatus;
		}


		public void setBusStatus(BusStatus busStatus) {
			this.busStatus = busStatus;
		}


		public User getOperator() {
			return operator;
		}


		public void setOperator(User operator) {
			this.operator = operator;
		}


		public List<Seat> getSeats() {
			return seats;
		}


		public void setSeats(List<Seat> seats) {
			this.seats = seats;
		}


		public List<Booking> getBookings() {
			return bookings;
		}


		public void setBookings(List<Booking> bookings) {
			this.bookings = bookings;
		}


		public Route getRoute() {
			return route;
		}


		public void setRoute(Route route) {
			this.route = route;
		}


		@Override
		public String toString() {
			return "Bus [busId=" + busId + ", busNumber=" + busNumber + ", busName=" + busName + ", busType=" + busType
					+ ", totalSeats=" + totalSeats + ", amenities=" + amenities + ", date=" + date + ", busStatus="
					+ busStatus + ", operator=" + operator + ", seats=" + seats + ", bookings=" + bookings + ", route="
					+ route + "]";
		}

		
		
	
	
	
}
