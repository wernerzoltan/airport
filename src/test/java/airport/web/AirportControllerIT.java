package airport.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import airport.web.dto.airport.AirportDTO;
import airport.web.dto.airport.NewAirportDTO;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) //beágyazott web container-t fogunk ezzel futtatni
					//RANDOM_PORT = a szerver véletlenszerű porton fog elindulni
public class AirportControllerIT {
	
	private WebTestClient client; //ez a Spring reaktiv kliense (webflux)
	
	@LocalServerPort
	private int serverPort; //ebbe fogjuk elmenteni azt a portszámot, amin a szerver futni fog

	@BeforeEach
	void setup() { //ez a metódus az URL-ünk elejét fogja automatikusan beállitani
		client = WebTestClient
				.bindToServer()
				.baseUrl(String.format("http://localhost:%d/api/v1", serverPort)) //ez az url, a %d-be adjuk át a serverPort értékét
				.responseTimeout(Duration.ofMinutes(1)) //1 perc timeout értéke
				.build(); //ezzel hozzzuk létre
		
	}
	
	//itt irjuk meg a teszt esetetet, azt vizsgáljuk, ami a NewAirportDTO-ban van megkötés (az iata 3 karakter legyen)
	@Test
	void createValidAirport() {
		
		NewAirportDTO newAirport = new NewAirportDTO("Praha Airport", "PRA");
		AirportDTO result = client.post() //a client-be fogunk postolni
			.uri("/airports") //erre az uri-ra postolunk
			.syncBody(newAirport) //itt tudunk átadni egy törzset, azaz létrehozunk egy új aiportot
			.accept(MediaType.APPLICATION_JSON_UTF8) //ezzel a header-t állituk be
			.exchange().expectStatus().isOk() //itt azt várjuk, hogy OK legyen a státusza a válasznak
			.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8) //azt várjuk, hogy a válasz header-je legyen JSON
			.expectBody(AirportDTO.class) //azt várjuk, hogy a válasz body airportDTO tipusú lesz
			.returnResult().getResponseBody(); //ezzel megkapjuk a választ, ami az AiportDTO lesz
		
		
		//a result-tal visszakapott válasz tartalmát vizsgáljuk meg az alábbiakban assert-ekkel
		assertNotNull(result.getId()); //a visszaadott ID nem lehet NULL
		assertEquals(newAirport.getName(), result.getName()); //az elküldött getName egyezzen meg a visszakapott getName-el
		assertEquals(newAirport.getIata(), result.getIata()); //az elküldött getIata egyezzen meg a visszakapott getIata-el		
		assertNotNull(result.getCreatedAt());
		assertNotNull(result.getModifiedAt());
			
	}
	
	//az alábbi tesztesetben invalid adatokkal tesztelünk, tehát hibás lefutást várunk
	
	@ParameterizedTest //ezzel egy tömbbe fel tudjuk sorolni a tesztelendő értékeket, és nem kell for ciklust futtatni a különböző értékekre
	@ValueSource(strings = {"", "BU", "BUDA"})
	void invalidAirportCannotBeCreated(String iata) {
		
		NewAirportDTO newAirport = new NewAirportDTO("Praha Airport", iata);
		
		client.post() //a client-be fogunk postolni
		.uri("/airports") //erre az uri-ra postolunk
		.syncBody(newAirport) //itt tudunk átadni egy törzset, azaz létrehozunk egy új aiportot
		.accept(MediaType.APPLICATION_JSON_UTF8) //ezzel a header-t állituk be
		.exchange()
		.expectStatus()
		.isBadRequest(); //azt várjuk, hogy a válasz rossz legyen
		
	}
	
	
	
	
	
	
}
