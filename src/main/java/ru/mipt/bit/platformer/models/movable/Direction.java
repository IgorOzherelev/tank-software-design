package ru.mipt.bit.platformer.models.movable;

import com.badlogic.gdx.math.GridPoint2;

public enum Direction {
    UP(new GridPoint2(0, 1), 90f), //w
    DOWN(new GridPoint2(0, -1), -90f), //s
    LEFT(new GridPoint2(-1, 0), -180f), //a
    RIGHT(new GridPoint2(1, 0),0f); //d

    private final GridPoint2 shift;
    private final float rotation;

    Direction(GridPoint2 shift, float rotation) {
        this.shift = shift;
        this.rotation = rotation;
    }

    public GridPoint2 getShift() {
        return shift;
    }

    public float getRotation() {
        return rotation;
    }
}
