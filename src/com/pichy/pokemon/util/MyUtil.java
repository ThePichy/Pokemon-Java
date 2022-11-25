package com.pichy.pokemon.util;

import java.util.Random;
import java.util.Scanner;

public class MyUtil {
    public static String readTxt(String path) {
        try {
            //Scanner in = new Scanner(new FileReader(path));
            Scanner in = new Scanner(MyUtil.class.getResourceAsStream(path));
            StringBuilder sb = new StringBuilder();
            while (in.hasNext())
                sb.append(in.next());
            in.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getRandomNumberInRange(int min, int max) {
        if (min >= max)
            throw new IllegalArgumentException("max must be greater than min");
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }
}
