package blogauthoringtool.blog.Service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import blogauthoringtool.blog.Model.User;
import blogauthoringtool.blog.Repository.UserRepository;
import blogauthoringtool.dto.RegisterRequest;
@Service
public class UserService 
{ 
	@Autowired
	private UserRepository userrepository;
	@Autowired
    private PasswordEncoder passwordEncoder;
    public User signup(RegisterRequest registerRequest) {
		   User us = new User();
	        us.setUserName(registerRequest.getUserName());
	        us.setEmail(registerRequest.getEmail());
	        us.setMobileNumber(registerRequest.getMobileNumber());
	        us.setPassword(encodePassword(registerRequest.getPassword()));
	        us=userrepository.save(us);
		
	return us;
	}
	
	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}
	
	 public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
	        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
	                getContext().getAuthentication().getPrincipal();
	        return Optional.of(principal);
	    }
	 
}
