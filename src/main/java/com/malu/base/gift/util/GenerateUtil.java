package com.malu.base.gift.util;

import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class GenerateUtil {

    // TODO: not support multiple instant

    private static final Integer STRING_LENGTH = 8;

    private static final String SALT_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    private static final AtomicLong sequenceEpay = new AtomicLong(Instant.now().getEpochSecond() - 1583824902);

    private static final AtomicLong sequenceProduct = new AtomicLong(Instant.now().getEpochSecond() - 1583887458);

    private static final AtomicLong sequenceAccount = new AtomicLong(Instant.now().getEpochSecond() - 1583894383);

    private static final AtomicLong sequenceOrder = new AtomicLong(Instant.now().getEpochSecond() - 1583885333);

    private static final AtomicLong sequence = new AtomicLong(Instant.now().toEpochMilli());

    private static final int prefix = new Random().nextInt(89) + 10;

    public static String getRandomString() {
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < STRING_LENGTH) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALT_CHARS.length());
            salt.append(SALT_CHARS.charAt(index));
        }
        return salt.toString();
    }

    public static String shortUUID() {
        UUID uuid = UUID.randomUUID();
        long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
        return Long.toString(l, Character.MAX_RADIX);
    }

    public static String shortUniqueCode() {
        return String.valueOf(sequence.incrementAndGet());
    }

    private static String convert7(Long num) {
        String str = String.valueOf(num);
        while (str.length() < 7)
            str = "0" + str;
        return str;
    }

    private static String convert9(Long num) {
        String str = String.valueOf(num);
        while (str.length() < 9)
            str = "0" + str;
        return str;
    }

    public static String getCode(String prefixUser) {
        return prefixUser + "_" + prefix + convert7(sequenceAccount.incrementAndGet());
    }
}
