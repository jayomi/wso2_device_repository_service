package org.wso2.repository.device.util;

import java.util.Random;

public class Salt {

    static final String baseString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    static Random myRand = new Random();
    static String salt;

    public static String buildSalt() {

        StringBuilder myStringBulder = new StringBuilder(12);

        for (int i = 0; i < 12; i++) {
            salt = myStringBulder.append(baseString.charAt(myRand.nextInt(baseString.length()))).toString();
        }

        return salt;
    }
}
