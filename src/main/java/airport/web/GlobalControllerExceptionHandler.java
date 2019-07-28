package airport.web;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import airport.web.dto.ErrorDTO;

//ez az osztály arra jó, hogy mindenhol, ahol exception-t dob a java, 
//azokat az exception-öket ide irnyjitjuk, és ez fogja feldolgozni azt 

@ControllerAdvice //minden controller megkapja ezt az exception kezelést
public class GlobalControllerExceptionHandler {
	
	//az ErrorDTO osztálytól kapja meg a hibakódot és message-t
	@ExceptionHandler(NoSuchElementException.class) //NoSuchElement-eket kezeljük
	ResponseEntity<ErrorDTO> handleNoSuchElementException(NoSuchElementException exception) {
		ErrorDTO error = new ErrorDTO(404, exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error); //NOT_FOUND esetén az error-t adjuk vissza
	}	
	
	

}
