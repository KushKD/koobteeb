package com.hp.dit.police.inventory.utilities;

import java.lang.Math;
import java.lang.StringBuilder;
import java.util.Random;

public class random24 {
    static char digits[] = {'1','2','3','4','5','6','7','8','9','0'};

    public static char randomDecimalDigit() {
        return digits[(int)Math.floor(Math.random() * 10)];
    }

    public static String randomDecimalString(int ndigits) {
        StringBuilder result = new StringBuilder();
        for(int i=0; i<ndigits; i++) {
            result.append(randomDecimalDigit());
        }
        return result.toString();
    }

    public static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }

   
}


