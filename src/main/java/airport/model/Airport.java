package airport.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//ez az osztály lesz az összekötő kapocs az adatbázis mezők és a java között
//jelen esetben nem közvetlenül hozzuk létre az adatbázis entitásokat, hanem ez egy plusz réteg
//hogy ha mondjuk db oldalon változás van, akkor az rejtve legyen a java többi részétől

@Entity
public class Airport {
	
	@Id
	@GeneratedValue
	private Long id;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
	private String name;
	private String iata;
	
	public Airport() {
	}
	
	public Airport(Long id, LocalDateTime createdAt, LocalDateTime modifiedAt, String name, String iata) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.name = name;
		this.iata = iata;
	}




	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
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
