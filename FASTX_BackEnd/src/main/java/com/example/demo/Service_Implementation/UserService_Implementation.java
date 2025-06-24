	package com.example.demo.Service_Implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.BookingDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Entity.Booking;
import com.example.demo.Entity.Bus;
import com.example.demo.Entity.Route;
import com.example.demo.Entity.Seat;
import com.example.demo.Entity.User;
import com.example.demo.Enum.UserRole;
import com.example.demo.Exceptions.EmailAlreadyExistsException;
import com.example.demo.Exceptions.InvalidCredentialsException;
import com.example.demo.Exceptions.UserNotFoundException;
import com.example.demo.Mappers.BookingMapper;
import com.example.demo.Mappers.UserMapper;
import com.example.demo.Repositories.BookingRepository;
import com.example.demo.Repositories.BusRepository;
import com.example.demo.Repositories.RouteRepository;
import com.example.demo.Repositories.SeatRepository;
import com.example.demo.Repositories.UserRepository;

import com.example.demo.Service.UserService;

@Service
public class UserService_Implementation implements UserService {

	@Autowired
    private UserRepository UserRepo;
	
	@Autowired
    private BookingRepository BookingRepo;

    @Autowired
    private BusRepository BusRepo;

    @Autowired
    private RouteRepository RouteRepo;

    @Autowired
    private SeatRepository SeatRepo;
	
	@Override
	public UserDTO registerUser(UserDTO userDto) throws EmailAlreadyExistsException {
		 if (UserRepo.existsByEmail(userDto.getEmail())) {
	            throw new EmailAlreadyExistsException("Email already exists");
	        }

	        User user = UserMapper.toEntity(userDto);
	        User saved = UserRepo.save(user);
	        return UserMapper.toDTO(saved);
	}

	@Override
	public UserDTO login(String email, String password) throws UserNotFoundException, InvalidCredentialsException {
		User user = UserRepo.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found with email: " + email);
        }

        if (!user.getPassword().equals(password)) {
            throw new InvalidCredentialsException("Invalid password for email: " + email);
        }

        return UserMapper.toDTO(user);
	}

	@Override
	public UserDTO updateUser(int id, UserDTO userDto) throws UserNotFoundException {
		User existing = UserRepo.findById(id).orElse(null);
        if (existing == null) {
            throw new UserNotFoundException("User not found with ID: " + id);
        }

        existing.setUserName(userDto.getUserName());
        existing.setEmail(userDto.getEmail());
        existing.setPhone(userDto.getPhone());
        existing.setAddress(userDto.getAddress());
        existing.setPassword(userDto.getPassword());
        existing.setRole(userDto.getRole());

        User updated = UserRepo.save(existing);
        return UserMapper.toDTO(updated);
	}

	@Override
	public String deleteUser(int id) throws UserNotFoundException {
		 if (!UserRepo.existsById(id)) {
	            throw new UserNotFoundException("Cannot delete. User not found with ID: " + id);
	        }
		  UserRepo.deleteById(id);
	     return "User Deleted Successfully" ;   
	   
		
	}

	@Override
	public UserDTO getUserById(int id) throws UserNotFoundException {
		User user = UserRepo.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User not found with ID: " + id);
        }
        return UserMapper.toDTO(user);
	}

	@Override
	public List<UserDTO> getUsersByRole(UserRole role) {
		 List<User> users = UserRepo.findByRole(role);
	        return users.stream()
	        	     .map(user -> UserMapper.toDTO(user))
	        	     .collect(Collectors.toList());
	}

	@Override
	public UserDTO viewProfile(int userId) throws UserNotFoundException  {
		  return getUserById(userId);
	}

	@Override
	public String forgotPassword(String email) throws UserNotFoundException {
		User user = UserRepo.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("Email not found: " + email);
        }

       
        return "Reset password link sent to " + email;
    }

	@Override
	public List<BookingDTO> getUserBookings(int userId) {
		List<Booking> bookings = BookingRepo.findByUser_UserId(userId);
        return bookings.stream()
                .map(b -> BookingMapper.toDto(b))
                .collect(Collectors.toList());
	}

	@Override
	public String cancelBooking(int bookingId) {
		Booking booking = BookingRepo.findById(bookingId).orElse(null);
        if (booking == null) {
            throw new RuntimeException("Booking not found with ID: " + bookingId);
        }

        // Make seats available again
        List<Seat> seats = booking.getSeats();
        for (Seat seat : seats) {
            seat.setAvailable(true);
            seat.setBooking(null);
        }
        SeatRepo.saveAll(seats);

        BookingRepo.delete(booking);
        return "Booking cancelled successfully";
	}

	@Override
	public BookingDTO bookTicket(BookingDTO bookingDto) {
		  Booking booking = BookingMapper.toEntity(bookingDto);
	        Booking saved = BookingRepo.save(booking);
	        return BookingMapper.toDto(saved);
	}

	@Override
	public List<String> getAvailableSeats(int routeId) {
		Route route = RouteRepo.findById(routeId).orElse(null);
        if (route == null) {
            throw new RuntimeException("Route not found with ID: " + routeId);
        }

        List<Bus> buses = BusRepo.findByRoute_RouteId(routeId);
        List<String> availableSeats = new ArrayList<>();

        for (Bus bus : buses) {
            List<Seat> seats = SeatRepo.findByBus_BusId(bus.getBusId());
            for (Seat seat : seats) {
                if (seat.isAvailable()) {
                    availableSeats.add(seat.getSeatNumber());
                }
            }
        }
        return availableSeats;
	}

	@Override
	public List<String> searchRoutes(String origin, String destination, String date) {
		LocalDate parsedDate = LocalDate.parse(date); // Format should be yyyy-MM-dd
        List<Route> routes = RouteRepo.findByOriginAndDestination(origin, destination);
        List<String> result = new ArrayList<>();

        for (Route route : routes) {
            List<Bus> buses = BusRepo.findByRoute_RouteId(route.getRouteId());
            for (Bus bus : buses) {
                if (bus.getDate().equals(parsedDate)) {
                    result.add("Bus: " + bus.getBusName() + ", Fare: " + route.getFare() + ", Seats: " + bus.getTotalSeats());
                }
            }
        }

        return result;
    }
	}

	


