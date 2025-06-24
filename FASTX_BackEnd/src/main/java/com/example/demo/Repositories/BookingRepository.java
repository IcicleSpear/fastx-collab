package com.example.demo.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Booking;

@Repository 
public interface BookingRepository extends JpaRepository<Booking, Integer> {
   
	
    List<Booking> findByUser_UserId(int userId);

	
}
