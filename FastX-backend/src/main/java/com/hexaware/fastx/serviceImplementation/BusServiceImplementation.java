package com.hexaware.fastx.serviceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.fastx.dto.BusDTO;
import com.hexaware.fastx.entity.Amenity;
import com.hexaware.fastx.entity.Bus;
import com.hexaware.fastx.entity.Route;
import com.hexaware.fastx.exception.BusNotFoundException;
import com.hexaware.fastx.exception.RouteNotFoundException;
import com.hexaware.fastx.repository.AmenityRepository;
import com.hexaware.fastx.repository.BusRepository;
import com.hexaware.fastx.repository.RouteRepository;
import com.hexaware.fastx.service.BusService;

@Service
public class BusServiceImplementation implements BusService {
	
	  @Autowired 
	  private BusRepository busRepo;
	  @Autowired 
	  private RouteRepository routeRepo;
	  @Autowired 
	  private AmenityRepository amenityRepo;
	  @Autowired 
	  private ModelMapper modelMapper;

	@Override
	public String addBus(BusDTO dto) {
		Route route = routeRepo.findById(dto.getRouteId())
				.orElseThrow(() -> new RouteNotFoundException("Route not found with ID: " + dto.getRouteId()));
	        List<Amenity> amenities = amenityRepo.findByNameInIgnoreCase(dto.getAmenities());
	        Bus bus = new Bus();
	        bus.setBusName(dto.getBusName());
	        bus.setBusNumber(dto.getBusNumber());
	        bus.setBusType(dto.getBusType());
	        bus.setTotalSeats(dto.getSeatCount());
	        bus.setFare(dto.getFare());
	        bus.setRoute(route);
	        bus.setAmenities(amenities);
	        busRepo.save(bus);
	        return "Bus added with ID " + bus.getBusId();
	}

	@Override
	public BusDTO updateBus(int id, BusDTO dto) {
		 Bus bus = busRepo.findById(id)
	                .orElseThrow(() -> new BusNotFoundException("Bus not found with ID: " + id));

	        bus.setBusName(dto.getBusName());
	        bus.setBusNumber(dto.getBusNumber());
	        bus.setBusType(dto.getBusType());
	        bus.setTotalSeats(dto.getSeatCount());
	        bus.setFare(dto.getFare());

	        Route route = routeRepo.findById(dto.getRouteId())
	                .orElseThrow(() -> new RouteNotFoundException("Route not found with ID: " + dto.getRouteId()));
	        bus.setRoute(route);

	        List<Amenity> amenities = amenityRepo.findByNameInIgnoreCase(dto.getAmenities());
	        bus.setAmenities(amenities);

	        busRepo.save(bus);
	        return modelMapper.map(bus, BusDTO.class);
	}

	@Override
	public String deleteBus(int id) {
		if (!busRepo.existsById(id)) {
            throw new BusNotFoundException("Bus not found with ID: " + id);
        }
        busRepo.deleteById(id);
        return "Bus deleted successfully.";
	}

	 @Override
	    public List<BusDTO> getAllBuses() {
	        return busRepo.findAll().stream()
	                .map(this::convertToDtoWithExtras)
	                .collect(Collectors.toList());
	    }

	    @Override
	    public BusDTO getBusById(int id) {
	        Bus bus = busRepo.findById(id)
	                .orElseThrow(() -> new BusNotFoundException("Bus not found with ID: " + id));
	        return convertToDtoWithExtras(bus);
	    }
	    
	    @Override
	    public List<BusDTO> getBusesByOriginAndDestination(String origin, String destination) {
	        return busRepo.findByRoute_OriginAndRoute_Destination(origin, destination).stream()
	                .map(this::convertToDtoWithExtras)
	                .collect(Collectors.toList());
	    }

	    @Override
	    public List<BusDTO> getBusesByAmenities(List<String> amenities) {
	        return busRepo.findByAmenities_NameIn(amenities).stream()
	                .map(this::convertToDtoWithExtras)
	                .collect(Collectors.toList());
	    }

	    @Override
	    public List<BusDTO> getBusesByRouteId(int routeId) {
	        return busRepo.findByRoute_RouteId(routeId).stream()
	                .map(this::convertToDtoWithExtras)
	                .collect(Collectors.toList());
	    }

	    @Override
	    public List<BusDTO> searchByBusName(String name) {
	        return busRepo.findByBusNameContainingIgnoreCase(name).stream()
	                .map(this::convertToDtoWithExtras)
	                .collect(Collectors.toList());
	    }

	    @Override
	    public List<BusDTO> searchByBusType(String type) {
	        return busRepo.findByBusTypeIgnoreCase(type).stream()
	                .map(this::convertToDtoWithExtras)
	                .collect(Collectors.toList());
	    }

	    @Override
	    public List<BusDTO> filterByFare(double minFare, double maxFare) {
	        return busRepo.findByFareBetween(minFare, maxFare).stream()
	                .map(this::convertToDtoWithExtras)
	                .collect(Collectors.toList());
	    }  
	    
	
	    private BusDTO convertToDtoWithExtras(Bus bus) {
	        BusDTO dto = modelMapper.map(bus, BusDTO.class);
	        dto.setAmenities(bus.getAmenities().stream().map(a -> a.getName()).toList());
	        dto.setRouteId(bus.getRoute().getRouteId());
	        return dto;
	    }
}
