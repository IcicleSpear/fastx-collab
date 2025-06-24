package com.example.demo.DTO;

import java.time.LocalDateTime;

import com.example.demo.Enum.PaymentStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class PaymentDTO {

	private int paymentId;

    @Positive(message = "Amount must be a positive number")
    private double amount;

    @NotBlank(message = "Payment mode is required")
    private String paymentMode;

    @NotBlank(message = "Transaction ID is required")
    private String transactionId;

    @NotNull(message = "Payment date is required")
    private LocalDateTime paymentDate;

    @NotNull(message = "Payment status is required")
    private PaymentStatus paymentStatus;

    private int bookingId; // Used to link to booking

	public PaymentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentDTO(int paymentId, @Positive(message = "Amount must be a positive number") double amount,
			@NotBlank(message = "Payment mode is required") String paymentMode,
			@NotBlank(message = "Transaction ID is required") String transactionId,
			@NotNull(message = "Payment date is required") LocalDateTime paymentDate,
			@NotNull(message = "Payment status is required") PaymentStatus paymentStatus, int bookingId) {
		super();
		this.paymentId = paymentId;
		this.amount = amount;
		this.paymentMode = paymentMode;
		this.transactionId = transactionId;
		this.paymentDate = paymentDate;
		this.paymentStatus = paymentStatus;
		this.bookingId = bookingId;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	@Override
	public String toString() {
		return "PaymentDTO [paymentId=" + paymentId + ", amount=" + amount + ", paymentMode=" + paymentMode
				+ ", transactionId=" + transactionId + ", paymentDate=" + paymentDate + ", paymentStatus="
				+ paymentStatus + ", bookingId=" + bookingId + "]";
	}
	
	
	
}
