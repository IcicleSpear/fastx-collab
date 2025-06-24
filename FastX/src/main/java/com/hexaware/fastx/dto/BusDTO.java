package com.hexaware.fastx.dto;

import java.util.List;

public class BusDTO {
	private int busId;
	private String busName;
	private String busNumber;
	private String busType;
	private int seatCount;
	private List<String> amenities;
	public BusDTO() {
		
	}
	public BusDTO(int busId, String busName, String busNumber, String busType, int seatCount, List<String> amenities) {
		super();
		this.busId = busId;
		this.busName = busName;
		this.busNumber = busNumber;
		this.busType = busType;
		this.seatCount = seatCount;
		this.amenities = amenities;
	}
	public int getBusId() {
		return busId;
	}
	public void setBusId(int busId) {
		this.busId = busId;
	}
	public String getBusName() {
		return busName;
	}
	public void setBusName(String busName) {
		this.busName = busName;
	}
	public String getBusNumber() {
		return busNumber;
	}
	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}
	public String getBusType() {
		return busType;
	}
	public void setBusType(String busType) {
		this.busType = busType;
	}
	public int getSeatCount() {
		return seatCount;
	}
	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}
	public List<String> getAmenities() {
		return amenities;
	}
	public void setAmenities(List<String> amenities) {
		this.amenities = amenities;
	}
	@Override
	public String toString() {
		return "BusDTO [busId=" + busId + ", busName=" + busName + ", busNumber=" + busNumber + ", busType=" + busType
				+ ", seatCount=" + seatCount + ", amenities=" + amenities + "]";
	}

	
}
