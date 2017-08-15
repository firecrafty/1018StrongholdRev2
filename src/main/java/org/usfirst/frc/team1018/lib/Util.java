package org.usfirst.frc.team1018.lib;

/**
 * @author Ryan Blue
 */
public class Util {
    public static double limit(double value, double limit) {
        return limit(value, -limit, limit);
    }

    public static double limit(double value, double min, double max) {
        return (value > max) ? max : (value < min ? min : value);
    }

}
