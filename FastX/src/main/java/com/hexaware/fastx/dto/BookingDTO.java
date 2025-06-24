package com.hexaware.fastx.dto;

import java.time.LocalDateTime;
import java.util.List;

public class BookingDTO {
	private int bookingId;
	private int userId;
	private int routeId;
	private int busId;
	private List<Integer> seatIds;
	private double totalamount;
	private LocalDateTime bookingTime;
	private String ticketNumber;
	private String status;
	
	public BookingDTO() {}

	public BookingDTO(int bookingId, int userId, int routeId, int busId, List<Integer> seatIds, double totalamount,
			LocalDateTime bookingTime, String ticketNumber, String status) {
		super();
		this.bookingId = bookingId;
		this.userId = userId;
		this.routeId = routeId;
		this.busId = busId;
		this.seatIds = seatIds;
		this.totalamount = totalamount;
		this.bookingTime = bookingTime;
		this.ticketNumber = ticketNumber;
		this.status = status;
	}

	@Override
	public String toString() {
		return "BookingDTO [bookingId=" + bookingId + ", userId=" + userId + ", routeId=" + routeId + ", busId=" + busId
				+ ", seatIds=" + seatIds + ", totalamount=" + totalamount + ", bookingTime=" + bookingTime
				+ ", ticketNumber=" + ticketNumber + ", status=" + status + "]";
	}
	
	
}
