package blogauthoringtool.blog.Security;

import org.springframework.web.filter.OncePerRequestFilter;

import blogauthoringtool.blog.Service.UserDetailsServiceImpl;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private jwtProvider jwtUtil;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
    	String path = request.getRequestURI().substring(request.getContextPath().length());
    	System.out.println(path);
    	String bearerToken = request.getHeader("Authorization");
    	String username=null;
    	String jwtToken=null;
    	

    	if(bearerToken!=null && bearerToken.startsWith("Bearer "))
    	{
    		jwtToken=bearerToken.substring(7);
    		
    		try {
    			username=this.jwtUtil.extractUsername(jwtToken);
    			}
    		catch(Exception e){
    			e.printStackTrace();
    		}
    		
    		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
    		{
    		    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
    		    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    		    SecurityContextHolder.getContext().setAuthentication(authentication);
    		}
    		else {
    			System.out.println("Token is not Validated");
    		}
    		
    		
    		}
    	filterChain.doFilter(request, response);
    	}

  }

 