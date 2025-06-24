package com.hexaware.fastx.dto;

public class SeatDTO {
	private int seatId;
	private String seatNumber;
	private String seatType;
	private boolean isAvailable;
	private int busId;
	
	public SeatDTO() {
	}

	public SeatDTO(int seatId, String seatNumber, String seatType, boolean isAvailable, int busId) {
		super();
		this.seatId = seatId;
		this.seatNumber = seatNumber;
		this.seatType = seatType;
		this.isAvailable = isAvailable;
		this.busId = busId;
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

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public int getBusId() {
		return busId;
	}

	public void setBusId(int busId) {
		this.busId = busId;
	}

	@Override
	public String toString() {
		return "SeatDTO [seatId=" + seatId + ", seatNumber=" + seatNumber + ", seatType=" + seatType + ", isAvailable="
				+ isAvailable + ", busId=" + busId + "]";
	}
	
	

}
