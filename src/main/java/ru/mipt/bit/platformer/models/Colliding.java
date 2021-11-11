package ru.mipt.bit.platformer.models;

import ru.mipt.bit.platformer.geometry.Point;

public interface Colliding {
    boolean isCollisionPossible(Point point);
    Point getCurrentCoordinates();
}
