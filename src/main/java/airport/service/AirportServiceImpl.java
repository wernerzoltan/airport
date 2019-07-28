package airport.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import airport.model.Airport;
import airport.repository.AirportRepository;
import airport.web.dto.airport.AirportDTO;
import airport.web.dto.airport.NewAirportDTO;

@Service //itt jelezzük, hogy ez service
public class AirportServiceImpl implements AirportService {

	//az alábbi kikommentezett részt az 1. és 2. lépés során használtuk H2 tesztelésére
//	private List<AirportDTO> airports = new ArrayList<>(); //ez a lista tartalmazza az AirportDTO-kat
//	private Long nextId = 10L; //ez lesz az ID mező, amelyet a NewAirport-nál fogunk használni, 10-es Long értékkel indulunk
	
//	//a service létrehozásakor az init metódust automatikusan futtassa le
//	@PostConstruct
//	public void init() {
//		//1. lépésben: itt hozzáadunk kezdeti adatokat a listához
//		airports.add(new AirportDTO(1L, LocalDateTime.now(), LocalDateTime.now(), "Budapest", "BUD"));
//		airports.add(new AirportDTO(2L, LocalDateTime.now(), LocalDateTime.now(), "Praha", "PRA"));
//		airports.add(new AirportDTO(3L, LocalDateTime.now(), LocalDateTime.now(), "Braitslava", "BRA"));
//				
//	}

	
	
	//1. lépésben csak a fenti listát adtuk vissza
	//@Override
	//public List<AirportDTO> getAirports() {
	//
		
		//return airports; 
	//	
	//}
	
	//2. lépésben objektum folyammá alakítjuk át a listát - H2-nél használtuk
	//ez kéri le az Airport okat
//	@Override
//	public List<AirportDTO> getAirports(String iata, Pageable pageable) {
//		
//		if (StringUtils.isEmpty(iata)) { //ha üres az iata, akkor adjuk vissza a listát
//		
//			return airports
//					.stream() //a stream hívással elkészítettük az objektum folyamot
//					.skip(pageable.getOffset()) //átugrunk x számú elemet
//					.limit(pageable.getPageSize()) //a page size lesz a limit
//					.collect(Collectors.toList()); //a megkapott adatfolyamot beteszi egy objektumba 
//			
//		} else {  //ha nem üres az iata, akkor fusson le az alábbi
//			
//			return airports
//					.stream() //a stream hívással elkészítettük az objektum folyamot
//					.filter(a -> a.getIata().equals(iata)) //szűrünk, itt lambda metódussal az 'a'-val jelzett airport argumentumot vár, 
//											//amely annyit csinál, hogy elkéri az iata kódot és megnézi, hogy
//											//az egyezik-e az általunk megkapott iata kóddal
//							.collect(Collectors.toList()); //a megkapott adatfolyamot beteszi egy objektumba 
//						
//		}
//	
//}
	
//	//ez adja hozzá a NewAirport-ot
//		@Override
//		public AirportDTO createAirport(NewAirportDTO newAirport) {
//			AirportDTO airport = new AirportDTO(nextId++, LocalDateTime.now(), LocalDateTime.now(), newAirport.getName(), newAirport.getIata());
//			
//			airports.add(airport);
//			
//			return airport;
//		}
//
//		
//		//ID alapján adja vissza az Aiportokat
//		@Override
//		public Optional<AirportDTO> getAirportById(Long id) {
//					
//			Optional<AirportDTO> optional = airports.stream().filter(a -> a.getId().equals(id)).findAny();
//			if(optional == null) //ha nincs ilyen érték, akkor az alábbi szöveget irjuk ki
//				throw new NoSuchElementException("Nincs ilyen ID: " + id);
//			return optional;
//			
//		}
//	}

	
	

	//innentől már direkt adatbázis hozzáféréssel dolgozunk

			@Autowired //ezzel tudjuk beinjektálni az AirportRepository tagváltozót
			AirportRepository airportRepository;
			
			//3. vezió, itt már adatbzissal dolgozunk
			@Override
			public List<Airport> getAirports(String iata, Pageable pageable) {
				
				if (StringUtils.isEmpty(iata)) { //ha üres az iata, akkor adjuk vissza a listát
				
					return airportRepository.findAll(pageable).getContent(); //ez visszadja az összes rekordot pageable módon (csak az adott lapot adja vissza)
					
				} else {  //ha nem üres az iata, akkor fusson le az alábbi
					
					return airportRepository.findByIata(iata, pageable).getContent();
					
				}
			 
		}

			
	
	
	//ez adja hozzá a NewAirport-ot
	@Override
	public Airport createAirport(NewAirportDTO newAirport) {
		Airport airport = new Airport();
		airport.setCreatedAt(LocalDateTime.now());  //itt adunk hozzá adatokat
		airport.setModifiedAt(LocalDateTime.now());
		airport.setIata(newAirport.getIata()); 
		airport.setName(newAirport.getName());
				
		return airportRepository.save(airport); //itt adjuk hozzá a repo-hoz
		
	}

	
	//ID alapján adja vissza az Aiportokat
	@Override
	public Optional<Airport> getAirportById(Long id) {
				
		Optional<Airport> optional = airportRepository.findById(id);
		
		if(optional == null) //ha nincs ilyen érték, akkor az alábbi szöveget irjuk ki
			throw new NoSuchElementException("Nincs ilyen ID: " + id);
		return optional;
		
	}
}
