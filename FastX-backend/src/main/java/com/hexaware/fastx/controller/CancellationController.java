package com.hexaware.fastx.controller;

import com.hexaware.fastx.dto.CancellationDTO;
import com.hexaware.fastx.service.CancellationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cancellations")
public class CancellationController {

    @Autowired
    private CancellationService cancellationService;

    @PostMapping("/{bookingId}")
    public ResponseEntity<CancellationDTO> cancelBooking(@PathVariable int bookingId, @RequestParam String reason) {
        return ResponseEntity.ok(cancellationService.cancelBooking(bookingId, reason));
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<CancellationDTO> getCancellation(@PathVariable int bookingId) {
        return ResponseEntity.ok(cancellationService.getCancellationByBookingId(bookingId));
    }
}
