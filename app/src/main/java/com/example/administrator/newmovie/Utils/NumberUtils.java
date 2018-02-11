
package com.example.administrator.newmovie.Utils;

import java.nio.ByteBuffer;

/**
 * Created by nash on 14-8-27.
 */
public class NumberUtils {

    /**
     * byte[]转为int
     * 
     * @param b
     * @return
     */
    public static int byteToInt(byte[] b) {
        int mask = 0xff;
        int temp = 0;
        int n = 0;
        for (int i = 0; i < 4; i++) {
            n <<= 8;
            temp = b[i] & mask;
            n |= temp;
        }
        return n;
    }

    public static byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(0, x);
        return buffer.array();
    }

    public static long bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.put(bytes, 0, bytes.length);
        buffer.flip();// need flip
        return buffer.getLong();
    }

    /**
     * 将越界的int型转为long型
     *
     * @param i
     * @return
     */
    public static long intToLong(int i) {
        long result = 0L;
        // 这里你用右位移运算，向右位移一位
        result = i >>> 1;
        result <<= 1;
        // 判断是否为奇数，奇数加1
        if (i % 2 != 0) {
            result += 1;
        }
        return result;
    }
}
