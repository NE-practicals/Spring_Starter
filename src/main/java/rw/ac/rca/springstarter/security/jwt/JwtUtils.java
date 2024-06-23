package rw.ac.rca.springstarter.security.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import rw.ac.rca.springstarter.exceptions.JwtVerificationException;
import rw.ac.rca.springstarter.security.user.UserSecurityDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

@Component
public class JwtUtils {
    @Value("${jwt.secret}")
    private String jwtSecretKey;

    private static final String CLAIM_KEY_USER_ID= "userId";
    private static final String CLAIM_KEY_EMAIL="email";
    private static final String CLAIM_KEY_ROLE="role";

    public String createToken(UUID id,String email,String role){
        Calendar calendar=  Calendar.getInstance();
        calendar.add(Calendar.MONTH,1);
        return Jwts.builder()
                .claim(CLAIM_KEY_USER_ID,id)
                .claim(CLAIM_KEY_EMAIL,email)
                .claim(CLAIM_KEY_ROLE,role)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS256,jwtSecretKey).compact();

    }
    public <T> T extractClaim(String token, Function<Claims,T> claimResolver){
        final Claims claims=extractAllClaims(token);
        return claimResolver.apply(claims);


    }
    public Date extractExpirationDate(String token){
        return extractClaim(token,Claims::getExpiration);

    }
    public Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody();
    }

    public JwtUserInfo decodeToken(String token) throws JwtVerificationException {
        Claims claims= extractAllClaims(token);
        String email= (String)claims.get(CLAIM_KEY_EMAIL);
        String id= (String)claims.get(CLAIM_KEY_USER_ID);
        String role=(String)claims.get(CLAIM_KEY_ROLE);
        return new JwtUserInfo(id,email,role);

    }
    public boolean isTokenExpired(String token){
        Date expirationDate=extractExpirationDate(token);
        Date currentDate= new Date();
        return currentDate.before(expirationDate);
    }

    public boolean isTokenValid(String token, UserSecurityDetails userSecurityDetails){
        Claims claims= extractAllClaims(token);
        String email=(String)claims.get(CLAIM_KEY_EMAIL);
        return email.equals(userSecurityDetails.getUsername());

    }
}
