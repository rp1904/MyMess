package com.byb.bhojan.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtils {
	
	private static SecretKeySpec secretKey;
	private static byte[] key;
	
	public static void setKey(String myKey) {
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static String encrypt(String strToEncrypt) {
		try {
			setKey(ProjectConstant.PROJECT_NAME);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(
					cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}

	public static String decrypt(String strToDecrypt) {
		try {
			setKey(ProjectConstant.PROJECT_NAME);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(
					strToDecrypt)));
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}
	
	public static void main(String[] args)
	{
	    
	    Long expiresOnMillis =  Dates.getMillisAfterHours(new Date(), 24);
		
		String link = UUID.randomUUID() + ProjectConstant.STRING_SEPERATOR + expiresOnMillis;
	    
	    String encryptedString = EncryptionUtils.encrypt(link) ;
	    String decryptedString = EncryptionUtils.decrypt(encryptedString) ;
	     
	    System.out.println(link);
	    System.out.println(encryptedString);
	    System.out.println(decryptedString);
	}
}