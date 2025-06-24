package com.example.demo.DTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class RouteDTO {

	private int routeId; // Typically DB-generated

    @NotBlank(message = "Origin is required")
    @Size(min = 2, max = 100, message = "Origin must be between 2 and 100 characters")
    private String origin;

    @NotBlank(message = "Destination is required")
    @Size(min = 2, max = 100, message = "Destination must be between 2 and 100 characters")
    private String destination;

    @NotNull(message = "Departure time is required")
    private LocalDateTime departureTime;

    @NotNull(message = "Arrival time is required")
    private LocalDateTime arrivalTime;

    @Positive(message = "Distance must be positive")
    private double distance;

    @Positive(message = "Fare must be positive")
    private double fare;

	public RouteDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RouteDTO(int routeId,
			@NotBlank(message = "Origin is required") @Size(min = 2, max = 100, message = "Origin must be between 2 and 100 characters") String origin,
			@NotBlank(message = "Destination is required") @Size(min = 2, max = 100, message = "Destination must be between 2 and 100 characters") String destination,
			@NotNull(message = "Departure time is required") LocalDateTime departureTime,
			@NotNull(message = "Arrival time is required") LocalDateTime arrivalTime,
			@Positive(message = "Distance must be positive") double distance,
			@Positive(message = "Fare must be positive") double fare) {
		super();
		this.routeId = routeId;
		this.origin = origin;
		this.destination = destination;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.distance = distance;
		this.fare = fare;
	}

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	@Override
	public String toString() {
		return "RouteDTO [routeId=" + routeId + ", origin=" + origin + ", destination=" + destination
				+ ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", distance=" + distance
				+ ", fare=" + fare + "]";
	}
	
	
	
}
