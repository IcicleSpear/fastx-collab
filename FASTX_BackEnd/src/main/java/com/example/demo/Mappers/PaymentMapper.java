package com.example.demo.Mappers;

import com.example.demo.DTO.PaymentDTO;
import com.example.demo.Entity.Booking;
import com.example.demo.Entity.Payment;

public class PaymentMapper {
	 public static PaymentDTO toDto(Payment payment) {
	        if (payment == null) return null;

	        PaymentDTO dto = new PaymentDTO();
	        dto.setPaymentId(payment.getPaymentId());
	        dto.setAmount(payment.getAmount());
	        dto.setPaymentMode(payment.getPaymentMode());
	        dto.setTransactionId(payment.getTransactionId());
	        dto.setPaymentDate(payment.getPaymentDate());
	        dto.setPaymentStatus(payment.getPaymentStatus());
	        if (payment.getBooking() != null) {
	            dto.setBookingId(payment.getBooking().getBookingId());
	        }

	        return dto;
	    }

	    public static Payment toEntity(PaymentDTO dto) {
	        if (dto == null) return null;

	        Payment payment = new Payment();
	        payment.setPaymentId(dto.getPaymentId());
	        payment.setAmount(dto.getAmount());
	        payment.setPaymentMode(dto.getPaymentMode());
	        payment.setTransactionId(dto.getTransactionId());
	        payment.setPaymentDate(dto.getPaymentDate());
	        payment.setPaymentStatus(dto.getPaymentStatus());

	        Booking booking = new Booking();
	        booking.setBookingId(dto.getBookingId());
	        payment.setBooking(booking);

	        return payment;
	    }
}
