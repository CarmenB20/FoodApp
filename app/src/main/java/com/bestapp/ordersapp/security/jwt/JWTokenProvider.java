package com.bestapp.ordersapp.security.jwt;

import com.bestapp.ordersapp.security.model.CurrentUser;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;

import static com.bestapp.ordersapp.security.SecurityConstants.*;


@Component
public class JWTokenProvider {

    public String generateJWToken(Authentication authentication){
        CurrentUser userPrincipal = (CurrentUser) authentication.getPrincipal();

        Date expiryDate = new Date(new Date().getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(userPrincipal.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public String getUserEmailFromJWT(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(jwt)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String jwt) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwt);
            return true;
        } catch (SignatureException ex) {
            System.out.println("ERROR " + "Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            System.out.println("ERROR " + "Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.out.println("ERROR " + "Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("ERROR " + "Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("ERROR " + "JWT claims string is empty.");
        }
        return false;
    }

    public String getJwtFromRequest(HttpServletRequest request) {
        String jwToken = request.getHeader(HEADER_STRING);
        if (StringUtils.hasText(jwToken)) {
            return jwToken;
        }
        return null;
    }



}
