package ru.mipt.bit.platformer.converters;

import org.awesome.ai.state.movable.Orientation;
import ru.mipt.bit.platformer.geometry.Rotation;

public class RotationToOrientationConverter implements Converter<Orientation, Rotation> {

    @Override
    public Orientation convert(Rotation rotation) {
        switch (rotation) {
            case N:
                return Orientation.N;
            case S:
                return Orientation.S;
            case E:
                return Orientation.E;
            case W:
                return Orientation.W;
            default:
                throw new RuntimeException("Unexpected orientation: " + rotation);
        }
    }
}
