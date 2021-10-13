package ru.mipt.bit.platformer.models.objects;

import ru.mipt.bit.platformer.logic.geometry.Point;

public class GameObject {
    protected float rotation;
    protected Point coordinates;

    public GameObject(Point coordinates, float rotation) {
        this.rotation = rotation;
        this.coordinates = coordinates;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public float getRotation() {
        return rotation;
    }
}
