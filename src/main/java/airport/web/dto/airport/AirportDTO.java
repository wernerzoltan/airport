package airport.web.dto.airport;

import java.time.LocalDateTime;

import airport.web.dto.BaseEntityDTO;

public class AirportDTO extends BaseEntityDTO {
	
	private String name;
	private String iata;
	
	public AirportDTO() {
		super();
	}
	
	public AirportDTO(Long id, LocalDateTime createdAt, LocalDateTime modifiedAt, String name, String iata) {
		super(id, createdAt, modifiedAt);
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
