package blogauthoringtool.blog.Model;

import blogauthoringtool.dto.jwtResponse;

public class loginResponse {
	private User user;
	private jwtResponse jwttoken;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public jwtResponse getJwttoken() {
		return jwttoken;
	}
	public void setJwttoken(jwtResponse jwttoken) {
		this.jwttoken = jwttoken;
	}

}
