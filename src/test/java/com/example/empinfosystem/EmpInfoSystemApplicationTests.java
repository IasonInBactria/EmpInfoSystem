package com.example.empinfosystem;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class EmpInfoSystemApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testGenJwt(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("name", "Trotsky");
        String jwt_token = Jwts.builder().signWith(SignatureAlgorithm.HS256, "jwttest")
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)).compact();
        System.out.println(jwt_token);
    }

    @Test
    public void testParseJwt(){
        Claims claim = Jwts.parser().setSigningKey("jwttest")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiVHJvdHNreSIsImlkIjoxLCJleHAiOjE2ODk2NTQwOTN9.3en6nugxrV5S7s483FLch_xcJtKYu_s_MgVUEA33zLY")
                .getBody();
        System.out.println(claim);
    }
}
