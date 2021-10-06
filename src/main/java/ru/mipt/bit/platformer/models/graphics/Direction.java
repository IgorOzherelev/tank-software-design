package ru.mipt.bit.platformer.models.graphics;

import com.badlogic.gdx.math.GridPoint2;

import static com.badlogic.gdx.Input.Keys.*;

public enum Direction {
    UP(W, new GridPoint2(0, 1), 90f),
    DOWN(S, new GridPoint2(0, -1), -90f),
    LEFT(A, new GridPoint2(-1, 0), -180f),
    RIGHT(D, new GridPoint2(1, 0),0f);

    private final Integer key;
    private final GridPoint2 shift;
    private final float rotation;

    Direction(Integer key, GridPoint2 shift, float rotation) {
        this.key = key;
        this.shift = shift;
        this.rotation = rotation;
    }

    public GridPoint2 getShift() {
        return shift;
    }

    public float getRotation() {
        return rotation;
    }

    public Integer getKey() {
        return key;
    }
}
