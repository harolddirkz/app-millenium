package com.devs.demoCours.Auth.Jwt;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static com.devs.demoCours.Auth.Jwt.TokenJwtConfig.SECRET_KEY;
@Service
public class JwtService {
    public Map<String, Object> getToken(UserDetails user){
        return getToken(new HashMap<>(),user);
    }
    private Map<String,Object> getToken(Map<String,Object> extraClaims, UserDetails user)  {
        String username = user.getUsername();
        Collection<? extends GrantedAuthority> roles = user.getAuthorities();

        boolean isAdmin = roles.stream().anyMatch(r->r.getAuthority().equals("ROLE_ADMIN"));
        boolean isDocente = roles.stream().anyMatch(r->r.getAuthority().equals("ROLE_TEACH"));
        boolean isStudent = roles.stream().anyMatch(r->r.getAuthority().equals("ROLE_STUDENT"));
        Map<String,Object> response = new HashMap<>();
        String token =Jwts
                .builder()
                .claims(extraClaims)
                .claim("roles",user.getAuthorities())
                .claim("isAdmin",isAdmin)
                .claim("isDocente",isDocente)
                .claim("isStudent",isStudent)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*60*8))
                .signWith(getKey())
                .compact();
        response.put("token",token);
        response.put("message",String.format("Hola %s, ha iniciado sesión satisfactoriamente ",username));
        response.put("username",username);

        return response;
    }
    private SecretKey getKey() {
        byte[] keyBytes= SECRET_KEY.getEncoded();
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username=getUsernameFromToken(token);


        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }
    private Claims getAllClaims(String token)
    {
        return Jwts
                .parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public <T> T getClaim(String token, Function<Claims,T> claimsResolver)
    {
        final Claims claims=getAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Date getExpiration(String token)
    {
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token)
    {
        return getExpiration(token).before(new Date());
    }


}
