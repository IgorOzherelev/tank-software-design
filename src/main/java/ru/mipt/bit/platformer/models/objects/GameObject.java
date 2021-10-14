package ru.mipt.bit.platformer.models.objects;

import ru.mipt.bit.platformer.logic.geometry.Point;

import java.util.Objects;

public class GameObject {
    protected float rotation;
    protected Point coordinates;

    public GameObject(Point coordinates, float rotation) {
        this.rotation = rotation;
        this.coordinates = coordinates;
    }

    public GameObject(Point coordinates) {
        this.coordinates = coordinates;
        this.rotation = 0;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameObject that = (GameObject) o;
        return coordinates.equals(that.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates);
    }
}
