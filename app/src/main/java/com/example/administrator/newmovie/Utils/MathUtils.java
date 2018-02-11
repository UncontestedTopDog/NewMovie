package com.example.administrator.newmovie.Utils;

/**
 * Created by huangweiliang on 18/02/11.
 */
public class MathUtils {
    /**
     * 约等于
     *
     * @param a
     * @param b
     * @param precision 约定允许误差
     * @return
     */
    public static boolean isApproximatelyEqual(float a, float b, float precision) {
        return Math.abs(a - b) <= precision;
    }

    /**
     * 约等于，约定允许误差为0.01
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean isApproximatelyEqual(float a, float b) {
        return isApproximatelyEqual(a, b, 0.01f);
    }

    public static int bound(int val, int min, int max) {
        return Math.min(Math.max(val, min), max);
    }

    public static float boundF(float val, float min, float max) {
        return Math.min(Math.max(val, min), max);
    }
}
