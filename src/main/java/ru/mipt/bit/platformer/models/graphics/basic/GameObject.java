package ru.mipt.bit.platformer.models.graphics.basic;

import com.badlogic.gdx.math.GridPoint2;

public class GameObject {
    protected float rotation;
    protected GridPoint2 coordinates;

    public GameObject(GridPoint2 coordinates, float rotation) {
        this.rotation = rotation;
        this.coordinates = coordinates;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public void setCoordinates(GridPoint2 coordinates) {
        this.coordinates = coordinates;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    public float getRotation() {
        return rotation;
    }
}
