package airport.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import airport.web.dto.airport.AirportDTO;
import airport.web.dto.airport.NewAirportDTO;

@Service
public interface AirportService  {
	
	List<AirportDTO> getAirports(String iata, Pageable pageable);
	
	AirportDTO createAirport(NewAirportDTO newAirport);
	
}
