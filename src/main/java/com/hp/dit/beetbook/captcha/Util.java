package com.hp.dit.beetbook.captcha;

import com.hp.dit.beetbook.utilities.Constants;

import java.util.Random;

public class Util {
    public static String generateCaptchaTextMethod1() 	 {

        Random rdm=new Random();
        int rl=rdm.nextInt();
        String hash1 = Integer.toHexString(rl);

        return hash1;

    }

    public static String generateCaptchaTextMethod2(int captchaLength) 	 {

        StringBuffer captchaStrBuffer = new StringBuffer();
        Random rnd = new Random();

        while (captchaStrBuffer.length() < captchaLength)
        {
            int index = (int) (rnd.nextFloat() * Constants.captchaSaltCharacters.length());
            captchaStrBuffer.append(Constants.captchaSaltCharacters.substring(index, index+1));
        }

        return captchaStrBuffer.toString();

    }
}

