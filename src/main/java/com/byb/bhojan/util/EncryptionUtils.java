package com.byb.bhojan.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtils {

	private static final String characterEncoding = "UTF-8";
	private static final String cipherTransformation = "AES/CBC/PKCS5PADDING";
	private static final String aesEncryptionAlgorithem = "AES";

	public static String encrypt(String plainText) {
		String encryptedText = "";
		try {
			Cipher cipher = Cipher.getInstance(cipherTransformation);
			byte[] key = getMD5(ProjectConstant.STRING_SEPERATOR).substring(0, 16).getBytes(characterEncoding);
			SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
			IvParameterSpec ivparameterspec = new IvParameterSpec(key);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparameterspec);
			byte[] cipherText = cipher.doFinal(plainText.getBytes("UTF8"));
			encryptedText = Base64.encodeBase64String(cipherText);

		} catch (Exception E) {
			System.err.println("Encrypt Exception : " + E.getMessage());
		}
		return encryptedText;
	}

	public static String decrypt(String encryptedText) {
		String decryptedText = "";
		try {
			Cipher cipher = Cipher.getInstance(cipherTransformation);
			byte[] key = getMD5(ProjectConstant.STRING_SEPERATOR).substring(0, 16).getBytes(characterEncoding);
			SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
			IvParameterSpec ivparameterspec = new IvParameterSpec(key);
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivparameterspec);
			byte[] cipherText = Base64.decodeBase64(encryptedText.getBytes("UTF8"));
			decryptedText = new String(cipher.doFinal(cipherText), "UTF-8");
		} catch (Exception E) {
			System.err.println("decrypt Exception : " + E.getMessage());
		}
		return decryptedText;
	}

	public static String getMD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);
			// Now we need to zero pad it if you actually want the full 32
			// chars.
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {

		String link = "1505330151645RP_WORLD2fc9cb23-7a0a-4dd9-9044-cbb27bdb7ddeRP_WORLD1505330151645";

		String encryptedString = EncryptionUtils.encrypt(link);
		String decryptedString = EncryptionUtils.decrypt(encryptedString);

		System.out.println(link);
		System.out.println(encryptedString);
		System.out.println(decryptedString);
	}
}