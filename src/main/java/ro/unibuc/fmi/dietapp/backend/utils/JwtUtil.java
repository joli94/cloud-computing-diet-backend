package ro.unibuc.fmi.dietapp.backend.utils;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ro.unibuc.fmi.dietapp.backend.exception.JwtTokenMalformedException;
import ro.unibuc.fmi.dietapp.backend.exception.JwtTokenMissingException;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String jwtSecret;

	public Claims getClaims(final String token_in) {
		final String token = token_in.substring(1, token_in.length() - 1);

		try {
			Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
			return body;
		} catch (Exception e) {
			System.out.println(e.getMessage() + " => " + e);
		}
		return null;
	}

	public void validateToken(final String token_in) throws JwtTokenMalformedException, JwtTokenMissingException {
		final String token = token_in.substring(1, token_in.length() - 1);

		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
		} catch (SignatureException ex) {
			throw new JwtTokenMalformedException("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			throw new JwtTokenMalformedException("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			throw new JwtTokenMalformedException("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			throw new JwtTokenMalformedException("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			throw new JwtTokenMissingException("JWT claims string is empty.");
		}
	}
}
