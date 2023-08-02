package com.example.windertontutor;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;

public class aa {
    public static void main(String[] args) {

        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        byte[] secretBytes = secretKey.getEncoded();
        String base64SecretBytes = Base64.getEncoder().encodeToString(secretBytes);

        System.out.println("Base64 Encoded Secret Key: " + base64SecretBytes);
    }
}
