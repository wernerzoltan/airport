package airport.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import airport.service.AirportService;
import airport.web.dto.airport.AirportDTO;
import airport.web.dto.airport.NewAirportDTO;

//ő lesz a controllerünk, ide érkeznek be a http kérések

@RestController //ezzel jelezzük, hogy ez az osztály olyan osztály, ami REST végpontok controllere
@RequestMapping("/api/v1/airports") //itt adjuk meg, hogy milyen uri-val érkező kéréseket szolgáljon ki
public class AirportController {
	
	private final AirportService airportService; //itt adjuk hozzá a service réteget
	
	//ez egy konstruktor, ami az airportService-t várja
	public AirportController(AirportService airportService) {
		this.airportService = airportService;
	}
		
	
	//get kérésre adja vissza a választ
	//1. lépésben csak visszaadjuk a listát
	//@GetMapping
	//List<AirportDTO> getAirports() { //listával tér vissza, amelyben AirportDTO-k vannak
	//	
	//	//return Collections.emptyList(); //egy üres listát ad vissza a get kérésre
	//	return airportService.getAirports(); //itt hívjuk meg a service réteg metódusát
	//	
	//}

	//2. lépésben a swagger-ben lévő extra lekérdezéseket hozzuk létre, 
	//GET-nél iata, page, size-t hozzuk létre
	@GetMapping
	List<AirportDTO> getAirports(@RequestParam(name = "iata", required = false) String iata, Pageable pageable) { 
		//iata nevű argumentumot várunk, amit a String iata argumentumba kell betenni
		//pageable: paginálva, azaz oldalakra bontva tudjuk átadni az argumentum adatait
		
		//return Collections.emptyList(); //egy üres listát ad vissza a get kérésre
		return airportService.getAirports(iata, pageable); //itt hívjuk meg a service réteg metódusát
		
	}
	
	//New Airport-hoz hozzuk létre, lásd insomnia oldalon a POST airports részt, POST kérést hozunk létre
	@PostMapping
	AirportDTO createAirport(@RequestBody @Valid NewAirportDTO newAirport) { //AirportDTO-t ad vissza, paraméterként pedig NewAirportDTO-t vár
											//a @RequestBody annotációval aztjelezzük, hogy a kérés törzséből szedje ki az új airport-ot
											//a @Valid érvényre juttatja a DTO-ban létrehozott szabályokat, pl. a size-ot
		
		return airportService.createAirport(newAirport);
		
		//insomnia-ban ha POST-ot létrehozunk, akkor JSON-ban kiadhatjuk pl. a következőt:
		//{
		//	"name": "Szarajevó"
		//}
		
	}
	
	
}
