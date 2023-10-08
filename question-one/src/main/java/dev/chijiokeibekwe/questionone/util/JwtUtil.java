package dev.chijiokeibekwe.questionone.util;

import dev.chijiokeibekwe.questionone.data.UserData;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class JwtUtil {

    private JwtUtil(){}

    private static final String SECRET_KEY = "TBhaKt64Ie4uUTqeUpiM/l+CkmQjJwTXFNg";

    public static void generateSignedJWT(UserData userData) {
        Map<String, Object> tokenBody = new HashMap<>();
        tokenBody.put("username", userData.getUsername());
        tokenBody.put("email", userData.getEmail());

        try {
            String jwt = Jwts.builder()
                    .addClaims(tokenBody)
                    .setIssuedAt(new Date())
                    .setSubject((String)tokenBody.get("username"))
                    .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
                    .compact();

            System.out.println("Token: " + jwt);
        } catch (JwtException e){

            System.err.println("Error encountered in JWT generation: " + e.getMessage());
            System.exit(1);
        }
    }

    public static void verifyJWT(String token) {

        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
                    .build().parseClaimsJws(token);

            System.out.print("\nVerification pass");
        } catch (JwtException e) {

            System.err.print("\nVerification fails");
            System.exit(1);
        }
    }
}
