package com.example.demo.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.demo.Enum.BusStatus;
import com.example.demo.Enum.BusType;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



public class BusDTO {

	 private int busId; // Usually auto-generated

	    @NotBlank(message = "Bus number is required")
	    @Pattern(regexp = "^[A-Z0-9\\-]+$", message = "Bus number must contain only uppercase letters, digits, or hyphens")
	    private String busNumber;

	    @NotBlank(message = "Bus name is required")
	    @Size(min = 3, message = "Bus name should be at least 3 characters")
	    private String busName;

	    @NotNull(message = "Bus type must not be null")
	    private BusType busType;

	    @Min(value = 1, message = "Total seats must be at least 1")
	    private int totalSeats;

	    @NotBlank(message = "Amenities field must not be empty")
	    private String amenities;

	    @NotNull(message = "Bus status must not be null")
	    private BusStatus busStatus;

	    @NotNull(message = "Arrival time must not be null")
	    private LocalTime arrival;

	    @NotNull(message = "Departure time must not be null")
	    private LocalTime departure;

	    @Positive(message = "Price per seat must be positive")
	    private double pricePerSeat;

	    @NotNull(message = "Date must not be null")
	    @FutureOrPresent(message = "Date must be today or in the future")
	    private LocalDate date;

	    // Nested objects
	    private UserDTO operator;

	    private RouteDTO route;

		public BusDTO() {
			super();
			// TODO Auto-generated constructor stub
		}

		public BusDTO(int busId,
				@NotBlank(message = "Bus number is required") @Pattern(regexp = "^[A-Z0-9\\-]+$", message = "Bus number must contain only uppercase letters, digits, or hyphens") String busNumber,
				@NotBlank(message = "Bus name is required") @Size(min = 3, message = "Bus name should be at least 3 characters") String busName,
				@NotNull(message = "Bus type must not be null") BusType busType,
				@Min(value = 1, message = "Total seats must be at least 1") int totalSeats,
				@NotBlank(message = "Amenities field must not be empty") String amenities,
				@NotNull(message = "Bus status must not be null") BusStatus busStatus,
				@NotNull(message = "Arrival time must not be null") LocalTime arrival,
				@NotNull(message = "Departure time must not be null") LocalTime departure,
				@Positive(message = "Price per seat must be positive") double pricePerSeat,
				@NotNull(message = "Date must not be null") @FutureOrPresent(message = "Date must be today or in the future") LocalDate date,
				UserDTO operator, RouteDTO route) {
			super();
			this.busId = busId;
			this.busNumber = busNumber;
			this.busName = busName;
			this.busType = busType;
			this.totalSeats = totalSeats;
			this.amenities = amenities;
			this.busStatus = busStatus;
			this.arrival = arrival;
			this.departure = departure;
			this.pricePerSeat = pricePerSeat;
			this.date = date;
			this.operator = operator;
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

		public BusStatus getBusStatus() {
			return busStatus;
		}

		public void setBusStatus(BusStatus busStatus) {
			this.busStatus = busStatus;
		}

		public LocalTime getArrival() {
			return arrival;
		}

		public void setArrival(LocalTime arrival) {
			this.arrival = arrival;
		}

		public LocalTime getDeparture() {
			return departure;
		}

		public void setDeparture(LocalTime departure) {
			this.departure = departure;
		}

		public double getPricePerSeat() {
			return pricePerSeat;
		}

		public void setPricePerSeat(double pricePerSeat) {
			this.pricePerSeat = pricePerSeat;
		}

		public LocalDate getDate() {
			return date;
		}

		public void setDate(LocalDate date) {
			this.date = date;
		}

		public UserDTO getOperator() {
			return operator;
		}

		public void setOperator(UserDTO operator) {
			this.operator = operator;
		}

		public RouteDTO getRoute() {
			return route;
		}

		public void setRoute(RouteDTO route) {
			this.route = route;
		}

		@Override
		public String toString() {
			return "BusDTO [busId=" + busId + ", busNumber=" + busNumber + ", busName=" + busName + ", busType="
					+ busType + ", totalSeats=" + totalSeats + ", amenities=" + amenities + ", busStatus=" + busStatus
					+ ", arrival=" + arrival + ", departure=" + departure + ", pricePerSeat=" + pricePerSeat + ", date="
					+ date + ", operator=" + operator + ", route=" + route + "]";
		}
	
	
	
}
