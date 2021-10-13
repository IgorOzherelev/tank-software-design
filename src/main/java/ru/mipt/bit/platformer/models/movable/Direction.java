package ru.mipt.bit.platformer.models.movable;

import ru.mipt.bit.platformer.logic.geometry.Point;

public enum Direction {
    UP(new Point(0, 1), 90f), //w
    DOWN(new Point(0, -1), -90f), //s
    LEFT(new Point(-1, 0), -180f), //a
    RIGHT(new Point(1, 0),0f); //d

    private final Point shift;
    private final float rotation;

    Direction(Point shift, float rotation) {
        this.shift = shift;
        this.rotation = rotation;
    }

    public Point getShift() {
        return shift;
    }

    public float getRotation() {
        return rotation;
    }
}
