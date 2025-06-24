package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class SeatDTO {

	 private int seatId;

	    @NotBlank(message = "Seat number is required")
	    @Pattern(regexp = "^[A-Z][0-9]{1,2}$", message = "Seat number must be in format like A1, B12")
	    private String seatNumber;

	    private boolean available;

	    private int busId;     // To associate the seat with a Bus

	    private Integer bookingId; // Nullable — only set if seat is booked

		public SeatDTO() {
			super();
			// TODO Auto-generated constructor stub
		}

		public SeatDTO(int seatId,
				@NotBlank(message = "Seat number is required") @Pattern(regexp = "^[A-Z][0-9]{1,2}$", message = "Seat number must be in format like A1, B12") String seatNumber,
				boolean available, int busId, Integer bookingId) {
			super();
			this.seatId = seatId;
			this.seatNumber = seatNumber;
			this.available = available;
			this.busId = busId;
			this.bookingId = bookingId;
		}

		public int getSeatId() {
			return seatId;
		}

		public void setSeatId(int seatId) {
			this.seatId = seatId;
		}

		public String getSeatNumber() {
			return seatNumber;
		}

		public void setSeatNumber(String seatNumber) {
			this.seatNumber = seatNumber;
		}

		public boolean isAvailable() {
			return available;
		}

		public void setAvailable(boolean available) {
			this.available = available;
		}

		public int getBusId() {
			return busId;
		}

		public void setBusId(int busId) {
			this.busId = busId;
		}

		public Integer getBookingId() {
			return bookingId;
		}

		public void setBookingId(Integer bookingId) {
			this.bookingId = bookingId;
		}

		@Override
		public String toString() {
			return "SeatDTO [seatId=" + seatId + ", seatNumber=" + seatNumber + ", available=" + available + ", busId="
					+ busId + ", bookingId=" + bookingId + "]";
		}
	
	
	    
	    
}
