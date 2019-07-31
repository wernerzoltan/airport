package airport.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import airport.model.Airport;
import airport.repository.AirportRepository;
import airport.web.dto.airport.AirportDTO;
import airport.web.dto.airport.NewAirportDTO;

//itt a service réteget teszteljük

@ExtendWith(MockitoExtension.class) //ez a 3. verzihóz kell, a mockito-t itt húzzuk be
public class AirportServiceImplTest {

	@InjectMocks //ez a 3. verzihóz kell, a mockito-t itt húzzuk be
	private AirportServiceImpl airportService;
	
	@Mock //ez a 3. verzihóz kell
	private AirportRepository airportRepository; //ez a 3. verzihóz kell
	
	//a BeforeEach-el az airportService-t inicializáljuk - a 3. verzióhoz nem kell
//	@BeforeEach
//	void setup() {
//		
//		airportService = new AirportServiceImpl(); 
//		
//	}
	
	//itt meghivjuk az AirportService CreateAirport metódusát
	@Test
	void createAirport() {
		
		//ez még az 1. és 2. verzió tesztelési módja
//		NewAirportDTO newAirport = new NewAirportDTO("Praha Airport", "PRA"); //itt létrehozzuk az új airportot
//		AirportDTO savedAirport = airportService.createAirport(newAirport); //itt a createAirport metódussal létrehozzuk és beletesszük egy változóba 
//		
//		assertEquals(newAirport.getName(), savedAirport.getName()); //egyezzenek meg a nevek
//		assertNotNull(savedAirport.getId()); //az id nem lehet NULL
//		
		
		
		//a 3. verziónál már mock-okat tesztelünk, azaz meghivjuk a mockito-t
		//a mock-ot tesztelésnél használjuk, nagy segitséget ad hibakeresésnél
		//mocking is creating objects that mimic the behavior of real objects
		
		
		NewAirportDTO newAirport = new NewAirportDTO("Praha Airport", "PRA"); //itt létrehozzuk az új airportot
		
		when(airportRepository.save(any())).thenAnswer( //ha létrehozunk egy új rekordot a repóban, akkor a válasz legyen az alábbi
				
				i ->  {  //ha kapok egy i inputot, akkor a mellette lévők lesznek az eredmények
					Airport arg = (Airport)i.getArgument(0);  //betesszük egy Airport arg változóba: elkérjük az argumentumokat, a 0.-at
					return new Airport(100L, arg.getCreatedAt(), arg.getModifiedAt(), arg.getName(), arg.getIata()); //itt visszaadok egy new aiportot, benne kitöltve a mezőket
				
				}
				);
		
		Airport savedAirport = airportService.createAirport(newAirport); //itt a createAirport metódussal létrehozzuk és beletesszük egy változóba 
		
		assertEquals(newAirport.getName(), savedAirport.getName()); //egyezzenek meg a nevek
		assertNotNull(savedAirport.getId()); //az id nem lehet NULL
		
		
	}
	
	
}
