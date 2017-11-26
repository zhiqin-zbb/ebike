package com.aaebike.common.utils;

import java.util.Random;

public class RandomUtils {
    private static final String[] CODE = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public static String getRandomCode(int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(CODE[random.nextInt(10)]);
        }

        return sb.toString();
    }
}
