package org.test.showtime.config;

//import org.test.showtime.model.ApiErrorResponse;
import org.test.showtime.model.ApiErrorResponse;
import org.test.showtime.security.JwtUtil;
import org.test.showtime.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

  @Autowired
  JwtUtil jwtUtil;

  @Autowired
  UserService demoService;

  private final ObjectMapper objectMapper;

  public JwtAuthFilter(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      String authHeader = request.getHeader("Authorization");

      String token = null;
      String email = null;
      if (authHeader != null && authHeader.startsWith("Bearer ")) {
        token = authHeader.substring(7);
        email = jwtUtil.extractUsername(token);
      }

//      If the accessToken is null. It will pass the request to next filter in the chain.
//      Any login and signup requests will not have jwt token in their header, therefore they will be passed to next filter chain.
      if (token == null) {
        filterChain.doFilter(request, response);
        return;
      }

//       If any accessToken is present, then it will validate the token and then authenticate the request in security context
      if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = demoService.loadUserByUsername(email);
        if (jwtUtil.validateToken(token, userDetails.getUsername())) {
          UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, null);
          authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
      }

      filterChain.doFilter(request, response);
    } catch (AccessDeniedException e) {
      ApiErrorResponse errorResponse = new ApiErrorResponse(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      response.getWriter().write(toJson(errorResponse));
    }
  }

  private String toJson(ApiErrorResponse response) {
    try {
      return objectMapper.writeValueAsString(response);
    } catch (Exception e) {
      return ""; // Return an empty string if serialization fails
    }
  }
}