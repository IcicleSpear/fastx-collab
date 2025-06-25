package com.hexaware.fastx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.fastx.dto.SeatDTO;
import com.hexaware.fastx.service.SeatService;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;
    
    @PostMapping
    public ResponseEntity<SeatDTO> addSeat(@RequestBody SeatDTO seatDTO) {
        SeatDTO createdSeat = seatService.addSeat(seatDTO);
        return ResponseEntity.ok(createdSeat);
    }

    @PutMapping("/{seatId}")
    public ResponseEntity<SeatDTO> updateSeat(@PathVariable int seatId, @RequestBody SeatDTO seatDTO) {
        SeatDTO updatedSeat = seatService.updateSeat(seatId, seatDTO);
        return ResponseEntity.ok(updatedSeat);
    }

    @DeleteMapping("/{seatId}")
    public ResponseEntity<String> deleteSeat(@PathVariable int seatId) {
        seatService.deleteSeat(seatId);
        return ResponseEntity.ok("Seat deleted successfully.");
    }

    @GetMapping("/{seatId}")
    public ResponseEntity<SeatDTO> getSeatById(@PathVariable int seatId) {
        SeatDTO seatDTO = seatService.getSeatById(seatId);
        return ResponseEntity.ok(seatDTO);
    }
    @GetMapping
    public ResponseEntity<List<SeatDTO>> getAllSeats() {
        List<SeatDTO> seats = seatService.getAllSeats();
        return ResponseEntity.ok(seats);
    }

    @PatchMapping("/{seatId}/status")
    public ResponseEntity<SeatDTO> updateSeatStatus(@PathVariable int seatId, @RequestParam String status) {
        SeatDTO updatedSeat = seatService.updateSeatStatus(seatId, status);
        return ResponseEntity.ok(updatedSeat);
    }

    @GetMapping("/{status}/{busId}")
    public ResponseEntity<List<SeatDTO>> getAvailableSeats(@PathVariable int busId,@PathVariable String status) {
        List<SeatDTO> seats = seatService.getAvailableSeatsByBusId(busId,status);
        return ResponseEntity.ok(seats);
    }
    
    @GetMapping("/available/byname/{busName}")
    public ResponseEntity<List<SeatDTO>> getAvailableSeatsByBusName(@PathVariable String busName) {
        List<SeatDTO> seats = seatService.getAvailableSeatsByBusName(busName);
        return ResponseEntity.ok(seats);
    }
}
