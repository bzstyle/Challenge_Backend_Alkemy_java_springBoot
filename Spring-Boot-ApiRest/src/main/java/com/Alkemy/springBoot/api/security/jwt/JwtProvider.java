package com.Alkemy.springBoot.api.security.jwt;

//Genera token , valida que no este mal formado ni expirado.

import com.Alkemy.springBoot.api.security.model.UserDetailClass;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    private final static Logger LOGGER = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication){

        UserDetailClass userDetailClass = (UserDetailClass) authentication.getPrincipal();

        return Jwts.builder().setSubject(userDetailClass.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512,secret).compact();
    }

    public String getUserNameFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJwt(token).getBody().getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJwt(token);
            return true;

        }catch (MalformedJwtException e){
            LOGGER.error("token mal formado");
        }catch (UnsupportedJwtException e){
            LOGGER.error("token no soportado");
        }catch (ExpiredJwtException e){
            LOGGER.error("token expired");
        }catch (IllegalArgumentException e){
            LOGGER.error("token vacio");
        }catch (SignatureException e){
            LOGGER.error("fallo en la firma");
        }
        return false;
    }

}
