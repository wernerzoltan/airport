package airport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import airport.model.Airport;
import airport.web.dto.airport.AirportDTO;
import airport.web.dto.airport.NewAirportDTO;

@Service
public interface AirportService  {
	
	List<Airport> getAirports(String iata, Pageable pageable);
	
	Airport createAirport(NewAirportDTO newAirport);

	Optional<Airport> getAirportById(Long id);
	
	
	
}
