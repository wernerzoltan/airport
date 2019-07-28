package airport.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import airport.web.dto.airport.AirportDTO;
import airport.web.dto.airport.NewAirportDTO;

//itt a service réteget teszteljük

public class AirportServiceImplTest {

	private AirportService airportService;
	
	//a BeforeEach-el az airportService-t inicializáljuk
	@BeforeEach
	void setup() {
		
		airportService = new AirportServiceImpl(); 
		
	}
	
	//itt meghivjuk az AirportService CreateAirport metódusát
	@Test
	void createAirport() {
		
		NewAirportDTO newAirport = new NewAirportDTO("Praha Airport", "PRA"); //itt létrehozzuk az új airportot
		AirportDTO savedAirport = airportService.createAirport(newAirport); //itt a createAirport metódussal létrehozzuk és beletesszük egy változóba 
		
		assertEquals(newAirport.getName(), savedAirport.getName()); //egyezzenek meg a nevek
		assertNotNull(savedAirport.getId()); //az id nem lehet NULL
		
	}
	
	
}
