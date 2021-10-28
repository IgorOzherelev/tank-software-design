package ru.mipt.bit.platformer.models.colliding;

import ru.mipt.bit.platformer.geometry.Point;

public interface Colliding {
    boolean isCollisionPossible(Point point);
}
