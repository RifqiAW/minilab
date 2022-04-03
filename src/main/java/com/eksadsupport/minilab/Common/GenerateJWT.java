package com.eksadsupport.minilab.Common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class GenerateJWT {

    public static String createToken(String email){
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        JwtBuilder builder = Jwts.builder()
                //Set Header
                .setHeaderParam("type", "JWT")
                .setHeaderParam("alg", "HS256")
                //set payload claims
                .setId(email)
                .setIssuedAt(now)
                .setSubject("bootcamp")
                .setIssuer("EKSAD")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+600000))
                .signWith(SignatureAlgorithm.HS256, "PASSWORD");


        return builder.compact();


    }

    public static Claims validateToken(String token){

        Claims claims = null;

        claims = Jwts.parser()
                .setSigningKey("PASSWORD")
                .parseClaimsJws(token)
                .getBody();

        return claims;

    }
}
