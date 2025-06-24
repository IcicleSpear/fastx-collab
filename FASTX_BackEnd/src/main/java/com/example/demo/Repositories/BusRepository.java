package com.example.demo.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer> {

    List<Bus> findByRoute_RouteId(int routeId);  // to get buses by routeId

	
	
}
