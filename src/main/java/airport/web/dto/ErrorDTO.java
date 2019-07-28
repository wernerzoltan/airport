package airport.web.dto;

public class ErrorDTO {
	
	private int code; //error kódot tartjuk ebben
	private String message; //error szöveget tartjuk ebben
	
	public ErrorDTO() {
	}

	public ErrorDTO(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	

}
