package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "passengers")

public class Passenger {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private int passengerId;

    @Column(name = "passenger_name")
    private String passengerName;

    @Column(name = "passenger_age")
    private int passengerAge;

    private String gender;

    @Column(name = "seat_number")
    private String seatNumber;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    
	public Passenger() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Passenger(int passengerId, String passengerName, int passengerAge, String gender, String seatNumber,
			Booking booking) {
		super();
		this.passengerId = passengerId;
		this.passengerName = passengerName;
		this.passengerAge = passengerAge;
		this.gender = gender;
		this.seatNumber = seatNumber;
		this.booking = booking;
	}


	public int getPassengerId() {
		return passengerId;
	}


	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}


	public String getPassengerName() {
		return passengerName;
	}


	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}


	public int getPassengerAge() {
		return passengerAge;
	}


	public void setPassengerAge(int passengerAge) {
		this.passengerAge = passengerAge;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getSeatNumber() {
		return seatNumber;
	}


	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}


	public Booking getBooking() {
		return booking;
	}


	public void setBooking(Booking booking) {
		this.booking = booking;
	}


	@Override
	public String toString() {
		return "Passenger [passengerId=" + passengerId + ", passengerName=" + passengerName + ", passengerAge="
				+ passengerAge + ", gender=" + gender + ", seatNumber=" + seatNumber + ", booking=" + booking + "]";
	}
	
	
	
}
