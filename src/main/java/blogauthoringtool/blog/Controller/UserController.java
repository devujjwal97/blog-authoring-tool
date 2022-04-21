package blogauthoringtool.blog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import blogauthoringtool.blog.Exception.CustomException;
import blogauthoringtool.blog.Model.User;
import blogauthoringtool.blog.Service.AuthenticationResponse;
import blogauthoringtool.blog.Service.UserService;
import blogauthoringtool.dto.LoginRequest;
import blogauthoringtool.dto.RegisterRequest;
@RestController
@RequestMapping("/api/user")
public class UserController {
	
	    @Autowired
	    private UserService userservice;
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
	    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) throws Exception {
	        return userservice.login(loginRequest);
	    }
	    
	    
	}

