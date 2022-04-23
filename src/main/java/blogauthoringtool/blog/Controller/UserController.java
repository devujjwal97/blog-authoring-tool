package blogauthoringtool.blog.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import blogauthoringtool.blog.Exception.CustomException;
import blogauthoringtool.blog.Model.User;
import blogauthoringtool.blog.Model.loginResponse;
import blogauthoringtool.blog.Repository.UserRepository;
import blogauthoringtool.blog.Security.jwtProvider;
import blogauthoringtool.blog.Service.UserDetailsServiceImpl;
import blogauthoringtool.blog.Service.UserService;
import blogauthoringtool.dto.LoginRequest;
import blogauthoringtool.dto.RegisterRequest;
import blogauthoringtool.dto.jwtResponse;
@RestController
@RequestMapping("/api/user")
public class UserController {
	
	    @Autowired
	    private UserService userservice;
	    @Autowired
	    private AuthenticationManager authenticationManager;
	    @Autowired
	    private UserDetailsServiceImpl userDetailsService;
	    @Autowired 
	    private jwtProvider jwtUtil;
	    @Autowired
	    private UserRepository userRepo;
	    
	    @PostMapping("/signup")
	    public Object signup(@RequestBody RegisterRequest registerRequest) {
	    	try {
	        User signup = userservice.signup(registerRequest);
	        
	        return  signup;}
	    	catch(Exception e) {
	    		CustomException ce=new CustomException();
	    		ce.setStatuscode("500");
	    		ce.setMsg(e.getMessage());
	    		return ce;
	    	}
	    }

	    @PostMapping("/login")
	    public ResponseEntity<?> generateToken(@RequestBody LoginRequest loginRequest) throws Exception {
	        System.out.println(loginRequest);
	        try {
	    		this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),
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
	        UserDetails userDetails=null;
	        try {
	        	System.out.println("printing from logincontrol");
	        	System.out.println(loginRequest.getUserName());
	        userDetails=userDetailsService.loadUserByUsername(loginRequest.getUserName());
	        }catch(Exception e)
	        {
	        	throw new Exception("Bad Credentials");
	        }
	        String token=this.jwtUtil.generateToken(userDetails);
	        Optional<User> us=userRepo.findByUserName(loginRequest.getUserName());
	        System.out.println("JWT"+token);
	        loginResponse logres=new loginResponse();
	        logres.setJwttoken(new jwtResponse(token));
	        logres.setUser(us.get());
	        return ResponseEntity.ok(logres);
	        
	    }
	    
	    
	}

