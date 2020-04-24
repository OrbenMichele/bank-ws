package com.morben.bank.ws.shared;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

import com.morben.bank.ws.security.SecurityConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

@Service
public class Utils {

    private final Random RANDOM = new SecureRandom();
    private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final String NUMBER = "1234567890";

    public String generateClienteId(int length) {
        return generateRandomString(length);
    }
    
    public String generateEnderecoId(int length) {
        return generateRandomString(length);
    }
    
    public String generateContaId(int length) {
        return generateRandomString(length);
    }
   
    public String generateCartaoId(int length) {
        return generateRandomString(length);
    }
    
    public String generateNumeroConta(int length) {
        return generateRandomNumber(length);
    }
    
    public String generateNumeroCartao(int length) {
        return generateRandomNumber(length);
    }
    public String generateNumeroCCVCartao(int length) {
            return generateRandomNumber(length);
    }
    
    private String generateRandomString(int length) {
        StringBuilder returnValue = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return new String(returnValue);
    }
    
    private String generateRandomNumber(int length) {
        StringBuilder returnValue = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            returnValue.append(NUMBER.charAt(RANDOM.nextInt(NUMBER.length())));
        }


        return new String(returnValue);
    }
    
    public Date getDataVencimento() {
    	ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDate localDate = LocalDate.now().plusYears(Constantes.VENCIMENTO_ANOS);
		return Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

    }

    public String generateUserId(int length) {
        return generateRandomString(length);
    }

    public String generateEmailVerificationToken(String userId) {
        String token = Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
                .compact();
        return token;
    }
}
