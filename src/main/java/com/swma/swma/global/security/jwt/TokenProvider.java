package com.swma.swma.global.security.jwt;

import com.swma.swma.global.security.auth.AuthDetailsService;
import com.swma.swma.global.security.jwt.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.SignatureException;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class TokenProvider {
    private final JwtProperties jwtProperties;
    private final AuthDetailsService authDetailsService;

    private String generateToken(String email,String type,String secret,Integer exp){
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secret.getBytes()))
                .claim("email",email)
                .claim("type",type)
                .setIssuedAt(new Date())
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .compact();
    }

    public String generateAccessToken(String userId){
        return generateToken(userId,"access",jwtProperties.getAccessSecret(),60*15);
    }
    public String generateRefreshToken(String userId){
        return generateToken(userId,"refresh",jwtProperties.getRefreshSecret(),60*60*24*7);
    }
    public Authentication getAuthentication(String token){
        UserDetails userDetails = authDetailsService.loadUserByUsername(getTokenSubject(token,jwtProperties.getAccessSecret()));
        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }
    private Claims getTokenBody(String token, String secret){
        try{
            return Jwts.parser()
                    .setSigningKey(Base64.getEncoder().encodeToString(secret.getBytes()))
                    .parseClaimsJws(token)
                    .getBody();
        }catch(ExpiredJwtException e){
            throw new ExpiredTokenException();
        }catch(MalformedJwtException | SignatureException e){
            throw new InvalidTokenException();
        }
    }
    public String resolveToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if((token != null) && token.startsWith ("Bearer "))
            return token.replace ("Bearer ", "");
        return null;
    }
    private String getTokenSubject(String token,String secret){
        return getTokenBody(token,secret).get("email",String.class);
    }
}
