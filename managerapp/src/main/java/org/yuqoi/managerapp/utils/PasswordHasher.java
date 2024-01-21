package org.yuqoi.managerapp.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordHasher {

    public static String passwordHasher(String password){
        try {
            // hashing type
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // update the message with the password bytes
            md.update(password.getBytes());

            // hashed password to bytes
            byte[] hashedBytes = md.digest();

            // return the hashed byte to string
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

}
