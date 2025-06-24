package com.example.demo.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Route;

@Repository
public interface  RouteRepository extends JpaRepository<Route, Integer> {
    List<Route> findByOriginAndDestination(String origin, String destination);  // search route

}
