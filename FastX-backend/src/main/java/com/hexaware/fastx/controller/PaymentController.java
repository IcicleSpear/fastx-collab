package com.hexaware.fastx.controller;

import com.hexaware.fastx.dto.PaymentDTO;
import com.hexaware.fastx.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/{bookingId}")
    public ResponseEntity<PaymentDTO> makePayment(@PathVariable int bookingId, @RequestParam String method) {
        return ResponseEntity.ok(paymentService.makePayment(bookingId, method));
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<PaymentDTO> getPaymentByBooking(@PathVariable int bookingId) {
        return ResponseEntity.ok(paymentService.getPaymentByBookingId(bookingId));
    }
}
