package airport.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import airport.model.Airport;

//ez az airport.model-ben lévő Airport POJO-nak a repository-ja

public interface AirportRepository extends JpaRepository<Airport, Long> {
												//az Airport lesz az enitátsa, a Long pedig az ID tipusa
	
	Page<Airport> findByIata(String iata, Pageable pageable); //ezzel iata alapján keresünk az adatbázisban
	//a Page meta adatokkal adja vissza a rekordokat, pl. hányadik oldalon van, mekkora, hány oldal van, stb.

}
