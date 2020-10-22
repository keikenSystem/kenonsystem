package com.keiken.kenonuserinterface.security;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

//Password encoder Class to encode password

@ComponentScan
@Service
public class PasswordEncoder {
	
	private static final Charset UTF_8 = StandardCharsets.UTF_8;
    private static final String OUTPUT_FORMAT = "%-20s:%s";
    private String encodedText;
    

    public String getEncodedText() {
		return encodedText;
	}

	public void setEncodedText(String encodedText) {
		this.encodedText = encodedText;
	}

	private static byte[] digest(byte[] input) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        byte[] result = md.digest(input);
        return result;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public String  encodedPassword(String password) {
        byte[] md5InBytes = PasswordEncoder.digest(password.getBytes(UTF_8));
        String encodedText = bytesToHex(md5InBytes);
        
             return encodedText;
          
    }
	

}
