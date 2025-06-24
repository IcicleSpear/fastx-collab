package com.example.demo.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class PassengerDTO {
     
	 private int passengerId;

	    @NotBlank(message = "Passenger name is required")
	    @Size(min = 2, max = 50, message = "Passenger name must be between 2 and 50 characters")
	    private String name;

	    @NotNull(message = "Age is required")
	    @Min(value = 0, message = "Age cannot be negative")
	    @Max(value = 120, message = "Age seems invalid")
	    private int age;

	    @NotBlank(message = "Gender is required")
	    @Pattern(regexp = "^(Male|Female|Other)$", message = "Gender must be Male, Female, or Other")
	    private String gender;

	   

	    @NotBlank(message = "Seat number is required")
	    private String seatNumber;

	    private int bookingId; // Just passing ID to link to booking, no nested object here

		public PassengerDTO() {
			super();
			// TODO Auto-generated constructor stub
		}

		public PassengerDTO(int passengerId,
				@NotBlank(message = "Passenger name is required") @Size(min = 2, max = 50, message = "Passenger name must be between 2 and 50 characters") String name,
				@NotNull(message = "Age is required") @Min(value = 0, message = "Age cannot be negative") @Max(value = 120, message = "Age seems invalid") int age,
				@NotBlank(message = "Gender is required") @Pattern(regexp = "^(Male|Female|Other)$", message = "Gender must be Male, Female, or Other") String gender,
				@NotBlank(message = "Seat number is required") String seatNumber, int bookingId) {
			super();
			this.passengerId = passengerId;
			this.name = name;
			this.age = age;
			this.gender = gender;
			this.seatNumber = seatNumber;
			this.bookingId = bookingId;
		}

		public int getPassengerId() {
			return passengerId;
		}

		public void setPassengerId(int passengerId) {
			this.passengerId = passengerId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
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

		public int getBookingId() {
			return bookingId;
		}

		public void setBookingId(int bookingId) {
			this.bookingId = bookingId;
		}

		@Override
		public String toString() {
			return "PassengerDTO [passengerId=" + passengerId + ", name=" + name + ", age=" + age + ", gender=" + gender
					+ ", seatNumber=" + seatNumber + ", bookingId=" + bookingId + "]";
		}

		
	
	
	
	
}
