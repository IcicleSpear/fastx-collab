package com.example.demo.Service;

import java.util.List;

import com.example.demo.DTO.BookingDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Enum.UserRole;
import com.example.demo.Exceptions.EmailAlreadyExistsException;
import com.example.demo.Exceptions.InvalidCredentialsException;
import com.example.demo.Exceptions.UserNotFoundException;

public interface UserService  {

	 UserDTO registerUser(UserDTO userDto) throws EmailAlreadyExistsException;

	    UserDTO login(String email, String password) throws UserNotFoundException, InvalidCredentialsException;

	    UserDTO updateUser(int id, UserDTO userDto) throws UserNotFoundException;

	    String deleteUser(int id) throws UserNotFoundException;

	    UserDTO getUserById(int id) throws UserNotFoundException;    // this is for admin when he wnats get usewr detials by id

	    List<UserDTO> getUsersByRole(UserRole role);

	    UserDTO viewProfile(int userId) throws UserNotFoundException ;     // this is for User nwho wants see his profile 

	    String forgotPassword(String email) throws UserNotFoundException;
	
	    List<BookingDTO> getUserBookings(int userId);
	    
	    String cancelBooking(int bookingId);
	    
	    BookingDTO bookTicket(BookingDTO bookingDto);
	    
	    List<String> getAvailableSeats(int routeId);
	    
	    List<String> searchRoutes(String origin, String destination, String date);
	
}
