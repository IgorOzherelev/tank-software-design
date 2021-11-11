package ru.mipt.bit.platformer.geometry;

public enum Direction {
    UP(new Point(0, 1), Rotation.N),
    DOWN(new Point(0, -1), Rotation.S),
    LEFT(new Point(-1, 0), Rotation.W),
    RIGHT(new Point(1, 0), Rotation.E);

    private final Point shift;
    private final Rotation rotation;

    Direction(Point shift, Rotation rotation) {
        this.shift = shift;
        this.rotation = rotation;
    }

    public Point getShift() {
        return shift;
    }

    public Rotation getOrientation() {
        return rotation;
    }
}
