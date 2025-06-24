package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.BookingDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Enum.UserRole;
import com.example.demo.Exceptions.EmailAlreadyExistsException;
import com.example.demo.Exceptions.InvalidCredentialsException;
import com.example.demo.Exceptions.UserNotFoundException;
import com.example.demo.Service.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/users")
public class UserController {
       
	@Autowired
    private UserService userService;
	
	
	// ✅ Register new user
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDto) throws EmailAlreadyExistsException {
        UserDTO saved = userService.registerUser(userDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("info", "User registered successfully");
        return new ResponseEntity<>(saved, headers, HttpStatus.CREATED);
    }

    // ✅ Login user
    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody UserDTO userDto) throws UserNotFoundException, InvalidCredentialsException {
        UserDTO response = userService.login(userDto.getEmail(), userDto.getPassword());
        HttpHeaders headers = new HttpHeaders();
        headers.add("info", "Login processed");
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    // ✅ Forgot password
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) throws UserNotFoundException {
        String result = userService.forgotPassword(email);
        HttpHeaders headers = new HttpHeaders();
        headers.add("info", "Password recovery email sent (if registered)");
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    // ✅ Update user
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody UserDTO userDto) throws UserNotFoundException {
        UserDTO updated = userService.updateUser(id, userDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("info", "User updated successfully");
        return new ResponseEntity<>(updated, headers, HttpStatus.OK);
    }

    // ✅ Delete user
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) throws UserNotFoundException {
        String result = userService.deleteUser(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("info", "User deleted successfully");
        return new ResponseEntity<>(result, headers, HttpStatus.NO_CONTENT);
    }

    // ✅ Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id) throws UserNotFoundException {
        UserDTO dto = userService.getUserById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("info", "User data fetched successfully");
        return new ResponseEntity<>(dto, headers, HttpStatus.OK);
    }

    // ✅ Get users by role
    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserDTO>> getUsersByRole(@PathVariable  UserRole role) {
        List<UserDTO> users = userService.getUsersByRole(role);
        HttpHeaders headers = new HttpHeaders();
        headers.add("info", "Users fetched by role");
        return new ResponseEntity<>(users, headers, HttpStatus.OK);
    }

    // ✅ Get logged-in user profile
    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getProfile(@RequestParam int userId) throws UserNotFoundException {
        UserDTO profile = userService.getUserById(userId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("info", "User profile data retrieved");
        return new ResponseEntity<>(profile, headers, HttpStatus.OK);
    }

    // ✅ View booking history
    @GetMapping("/bookings")
    public ResponseEntity<List<BookingDTO>> getUserBookings(@RequestParam int userId) {
        List<BookingDTO> bookings = userService.getUserBookings(userId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("info", "Booking history fetched");
        return new ResponseEntity<>(bookings, headers, HttpStatus.OK);
    }

    // ✅ Cancel a booking
    @DeleteMapping("/bookings/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable int bookingId) {
        String message = userService.cancelBooking(bookingId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("info", "Booking cancelled");
        return new ResponseEntity<>(message, headers, HttpStatus.NO_CONTENT);
    }

    // ✅ Book tickets
    @PostMapping("/bookings")
    public ResponseEntity<BookingDTO> bookTickets(@RequestBody BookingDTO bookingDto) {
        BookingDTO booked = userService.bookTicket(bookingDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("info", "Booking created successfully");
        return new ResponseEntity<>(booked, headers, HttpStatus.CREATED);
    }

    // ✅ Get available seats for a route
    @GetMapping("/available-seats/{routeId}")
    public ResponseEntity<List<String>> getAvailableSeats(@PathVariable int routeId) {
        List<String> seats = userService.getAvailableSeats(routeId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("info", "Available seats fetched");
        return new ResponseEntity<>(seats, headers, HttpStatus.OK);
    }

    // ✅ Search bus routes
    @GetMapping("/routes/search")
    public ResponseEntity<List<String>> searchRoutes(@RequestParam String origin,
                                                     @RequestParam String destination,
                                                     @RequestParam String date) {
        List<String> result = userService.searchRoutes(origin, destination, date);
        HttpHeaders headers = new HttpHeaders();
        headers.add("info", "Search results returned");
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }
	
	
	
	
	
}
