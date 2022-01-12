package com.revature.utils;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordEncryption {
	
	public String generateStrongHashedPwd(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
			int iterations = 65536;
			
			char[] chars = password.toCharArray();
			byte[] salt = getSalt();
			
			PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 128);
			
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2withHmacSHA1");
			
			byte[] hash = skf.generateSecret(spec).getEncoded();
			
			return iterations + ":" + toHex(salt) + ":" + toHex(hash);
		
	}

	private String toHex(byte[] array) {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length*2) - hex.length();
		
		if(paddingLength > 0) {
			return String.format("%0" + paddingLength + "d", 0) + hex;
			
		}else {
			return hex;
		}
	}

	private byte[] getSalt() throws NoSuchAlgorithmException{
		
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}
	
	
	//From Here we will define passwordValidation
	
	public boolean validatePassword(String originalPassword, String storePassword) throws InvalidKeySpecException, NoSuchAlgorithmException {
		
		String[] parts = storePassword.split(":");
		int Iterations = Integer.parseInt(parts[0]);
		byte[] salt = fromHex(parts[1]);
		byte[] hash = fromHex(parts[2]);
		
		PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, Iterations, hash.length * 8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2withHmacSHA1");
		byte[] testHash = skf.generateSecret(spec).getEncoded();
		
		int diff = hash.length ^ testHash.length;
		
		for(int i = 0; i < hash.length && i< testHash.length; i++) {
			
			diff = hash[i] ^ testHash[i];
			
		}
		return diff == 0;
	}

	private byte[] fromHex(String hex) throws NoSuchAlgorithmException {
		byte[] bytes = new byte[hex.length() / 2];
		for(int i = 0; i < bytes.length; i ++) {
			
			bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		
		return bytes;
	}

	
	
}




