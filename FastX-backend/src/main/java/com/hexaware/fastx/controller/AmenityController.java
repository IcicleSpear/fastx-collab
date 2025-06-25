package com.hexaware.fastx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hexaware.fastx.dto.AmenityDTO;
import com.hexaware.fastx.service.AmenityService;

@RestController
@RequestMapping("/api/amenities")
public class AmenityController {

    @Autowired
    private AmenityService amenityService;

    @PostMapping
    public ResponseEntity<String> addAmenity(@RequestBody AmenityDTO amenityDTO) {
        String message = amenityService.addAmenity(amenityDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("info", "Amenity added successfully");
        return new ResponseEntity<>(message, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AmenityDTO> updateAmenity(@PathVariable int id, @RequestBody AmenityDTO amenityDTO) {
        AmenityDTO updatedAmenity = amenityService.updateAmenity(id, amenityDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("info", "Amenity updated successfully");
        return new ResponseEntity<>(updatedAmenity, headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAmenity(@PathVariable int id) {
        String message = amenityService.deleteAmenity(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("info", "Amenity deleted successfully");
        return new ResponseEntity<>(message, headers, HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<AmenityDTO>> getAllAmenities() {
        List<AmenityDTO> amenities = amenityService.getAllAmenities();
        HttpHeaders headers = new HttpHeaders();
        headers.add("info", "All amenities fetched successfully");
        return new ResponseEntity<>(amenities, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AmenityDTO> getAmenityById(@PathVariable int id) {
        AmenityDTO amenityDTO = amenityService.getAmenityById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("info", "Amenity fetched successfully");
        return new ResponseEntity<>(amenityDTO, headers, HttpStatus.OK);
    }
}
