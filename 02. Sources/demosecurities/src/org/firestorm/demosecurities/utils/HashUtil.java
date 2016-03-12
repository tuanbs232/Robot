package org.firestorm.demosecurities.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	public static String SHA_256 = "SHA-256";
	public static String SHA_512 = "SHA-512";
	public static String MD5 = "MD5";
	
	public static String hash(String password, String alg){
	    MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance(alg);
		    byte[] passBytes = password.getBytes();
		    byte[] passHash = messageDigest.digest(passBytes);
		    char[] hexChars = new char[passHash.length * 2];
		    for ( int j = 0; j < passHash.length; j++ ) {
		        int v = passHash[j] & 0xFF;
		        hexChars[j * 2] = hexArray[v >>> 4];
		        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		    }
		    return new String(hexChars);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}        
	}
}
