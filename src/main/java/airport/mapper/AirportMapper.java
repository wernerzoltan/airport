package airport.mapper;

//ez fogja elvégezni a mappaléseket az AirportDTO és az Airport között
//automatikusan létrehozza a target/generated-sources mappát (?)


import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import airport.model.Airport;
import airport.web.dto.airport.AirportDTO;
import airport.web.dto.airport.NewAirportDTO;

@Mapper
public interface AirportMapper {
	
	
	AirportDTO airportToDto(Airport airport); //ez hozza létre a target/generated-sources mappát, ami a mappeléseket végzi
	
	void updateFromDto(NewAirportDTO dto, @MappingTarget Airport airport); //ez szolgál arra, hogy a meglévő Airport-okat felülrjuk AirportDTO-val

}
