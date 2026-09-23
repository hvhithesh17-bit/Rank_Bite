package com.rankbite.util;

public class NormalizationUtil {
    
    public static double normalizeHigherBetter(double value, double min, double max) {
        if (max == min) {
            return 100.0;
        }
        return ((value - min) / (max - min)) * 100.0;
    }

    public static double normalizeLowerBetter(double value, double min, double max) {
        if (max == min) {
            return 100.0;
        }
        return ((max - value) / (max - min)) * 100.0;
    }
}
