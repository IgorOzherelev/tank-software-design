package ru.mipt.bit.platformer.models.logic;

import ru.mipt.bit.platformer.geometry.Point;
import ru.mipt.bit.platformer.models.GameObject;

public class LogicObstacle implements GameObject {
    private final Point currentCoordinates;

    public LogicObstacle(Point currentCoordinates) {
        this.currentCoordinates = currentCoordinates;
    }

    @Override
    public Point getCurrentCoordinates() {
        return currentCoordinates;
    }

    @Override
    public boolean isCollisionPossible(Point point) {
        return currentCoordinates.equals(point);
    }
}
