package com.devs.demoCours.Auth.Jwt;


import com.devs.demoCours.utils.exeptions.JwtValidationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import static com.devs.demoCours.Auth.Jwt.TokenJwtConfig.HEADER_AUTHORIZATION;
import static com.devs.demoCours.Auth.Jwt.TokenJwtConfig.PREFIX_TOKEN;

@Component
@RequiredArgsConstructor

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String token = getTokenFromRequest(request);
        final String username;
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }
        username = jwtService.getUsernameFromToken(token);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtService.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);

            }else {
                throw new JwtValidationException("El token no es valido");

            }
        }
        filterChain.doFilter(request, response);


    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader = request.getHeader(HEADER_AUTHORIZATION);

        if (StringUtils.hasText(authHeader) && authHeader.startsWith(PREFIX_TOKEN)) {
            return authHeader.substring(7);
        }
        return null;
    }
}
