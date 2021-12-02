package ru.mipt.bit.platformer.models.logic;

import ru.mipt.bit.platformer.geometry.Point;

/**
 * Entity
 * */
public class LogicObstacle extends BaseLogicObject {
    public LogicObstacle(Point currentCoordinates) {
        super(currentCoordinates);
    }

    @Override
    public boolean isCollisionPossible(Point point) {
        return currentCoordinates.equals(point);
    }
}
