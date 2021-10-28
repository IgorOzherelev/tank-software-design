package ru.mipt.bit.platformer.services.converters.awesomeai;

import org.awesome.ai.state.movable.Orientation;

// для скорости написания пока так, потом мб переделается
public class OrientationConverterService {
    public static Orientation convertToOrientation(float rotation) {
        if (rotation == 90f) {
            return Orientation.N;
        } else if (rotation == -90f) {
            return Orientation.S;
        } else if (rotation == -180f) {
            return Orientation.W;
        } else { //0f
            return Orientation.E;
        }
    }
}
