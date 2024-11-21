package com.example.demo.security.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.common.net.HttpHeaders;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private JwtService jwtSer;
	private UserDetailsService userSer;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String token=getTokenFromRequest(request);
		final String username;
		
		if(token==null) {
			filterChain.doFilter(request, response);
			return;
		}
		
		username=jwtSer.getUsernameFromToken(token);
		
		if(username!=null&&SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails user=userSer.loadUserByUsername(username);
			
			if(jwtSer.isTokenValid(token, user)) {
				UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
	}

	private String getTokenFromRequest(HttpServletRequest request) {
		final String authHeader=request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if(StringUtils.hasText(authHeader)&&authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7);
		}
		return null;
	}
	
}
