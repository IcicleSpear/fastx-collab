package com.example.demo.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Seat;

@Repository
public interface SeatRepository  extends JpaRepository<Seat, Integer>  {
	List<Seat> findByBus_BusId(int busId);

}
