package org.kk;

import java.nio.charset.Charset;
import java.util.Random;

public class CustomerGenerator {
    public static Customer getNext() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return new Customer((int) Math.random(), generatedString);
    }
}
