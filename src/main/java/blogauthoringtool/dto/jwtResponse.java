package blogauthoringtool.dto;

public class jwtResponse {
	String token;

	public jwtResponse(String token) {
		
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public jwtResponse() {
		
	}
	

}
