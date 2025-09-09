package com.jwt.example.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestHeader = request.getHeader("Authorization");
        logger.info("Header: {}", requestHeader);

        String username = null;
        String token = null;

        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            token = requestHeader.substring(7);
            logger.info("Extracted token (first 30 chars): {}", token.substring(0, Math.min(30, token.length())) + "...");

            try {
                username = this.jwtHelper.getUsernameFromToken(token);
                logger.info("Username extracted from token: '{}'", username);

            } catch (IllegalArgumentException e) {
                logger.error("Illegal Argument while fetching username: {}", e.getMessage());
            } catch (ExpiredJwtException e) {
                logger.error("JWT token is expired: {}", e.getMessage());
            } catch (MalformedJwtException e) {
                logger.error("Invalid JWT token: {}", e.getMessage());
            } catch (Exception e) {
                logger.error("Error processing JWT token: {}", e.getMessage());
                e.printStackTrace();
            }
        } else {
            logger.info("Invalid Header Value - missing or malformed Authorization header");
        }

        // Validate and authenticate
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            logger.info("Username '{}' found, proceeding with authentication", username);

            try {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                logger.info("UserDetails loaded successfully for username: '{}'", userDetails.getUsername());

                Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
                logger.info("Token validation result for '{}': {}", username, validateToken);

                if (validateToken) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    logger.info("Authentication successful for user: '{}'", username);
                } else {
                    logger.warn("Token validation failed for user: '{}'", username);
                }

            } catch (Exception e) {
                logger.error("Error during authentication for user '{}': {}", username, e.getMessage());
                e.printStackTrace();
            }
        } else if (username != null) {
            logger.info("User '{}' already authenticated", username);
        }

        filterChain.doFilter(request, response);
    }
}