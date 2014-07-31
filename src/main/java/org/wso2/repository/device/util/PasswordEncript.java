package org.wso2.repository.device.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class PasswordEncript {
	
	private static MessageDigest md;
	
	public String getEncriptedPassword(String password) {
        try {
//            md = MessageDigest.getInstance("MD5");
        	md = MessageDigest.getInstance("SHA");
            byte[] passBytes = password.getBytes();

            md.reset();

            byte[] md5Digest = md.digest(passBytes);

            StringBuffer myStringBuffer = new StringBuffer();

            for (int i = 0; i < md5Digest.length; i++) {
                myStringBuffer.append(Integer.toHexString(0xff & md5Digest[i]));
            }
            return myStringBuffer.toString();

        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }

        return null;

    }
	
	static final String baseString = "abcdefghijklmnopqrstuvwxyz0123456789";
    static Random myRand = new Random();
    static String salt;
	
	public String buildSalt() {

        StringBuilder myStringBulder = new StringBuilder(15);

        for (int i = 0; i < 15; i++) {
            salt = myStringBulder.append(baseString.charAt(myRand.nextInt(baseString.length()))).toString();
        }

        return salt;
    }

}
