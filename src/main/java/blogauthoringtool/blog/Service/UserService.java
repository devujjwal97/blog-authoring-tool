package blogauthoringtool.blog.Service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import blogauthoringtool.blog.Model.User;
import blogauthoringtool.blog.Repository.UserRepository;
import blogauthoringtool.blog.Security.jwtProvider;
import blogauthoringtool.dto.LoginRequest;
import blogauthoringtool.dto.RegisterRequest;
@Service
public class UserService 
{ 
	@Autowired
	private UserRepository userrepository;
	@Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private jwtProvider jwtprovider;
    
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
	
	public AuthenticationResponse login(LoginRequest loginRequest) throws Exception {
		try {
		this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
				loginRequest.getPassword()));
		}catch(UsernameNotFoundException e)
		{
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}
		catch(BadCredentialsException e)
		{
			e.printStackTrace();
			throw new Exception("Bad Credentials");
			
		}
		UserDetails userDetails=this.userDetailsService.loadUserByUsername(loginRequest.getUsername());
		
         String authenticationToken = jwtprovider.generateToken(userDetails);
        return new AuthenticationResponse(authenticationToken, loginRequest.getUsername());
	}
	

	
	 public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
	        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
	                getContext().getAuthentication().getPrincipal();
	        return Optional.of(principal);
	    }
	 
}
