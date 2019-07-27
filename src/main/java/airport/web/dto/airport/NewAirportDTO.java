package airport.web.dto.airport;

import javax.validation.constraints.Size;

//új airportok hozzáadása, lásd swagger oldalon a NewAirport-ot

public class NewAirportDTO {
	
	private String name;
	
	@Size(min = 3, max = 3) //ezzel a mező méretét tudjuk befolyásolni
	private String iata;
	
	public NewAirportDTO() {
		super();
	}

	public NewAirportDTO(String name, String iata) {
		super();
		this.name = name;
		this.iata = iata;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIata() {
		return iata;
	}

	public void setIata(String iata) {
		this.iata = iata;
	}
		
}
