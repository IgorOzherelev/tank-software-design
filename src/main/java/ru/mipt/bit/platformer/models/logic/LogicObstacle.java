package ru.mipt.bit.platformer.models.logic;

import ru.mipt.bit.platformer.models.Colliding;
import ru.mipt.bit.platformer.geometry.Point;

import java.util.Objects;

public class LogicObstacle implements Colliding {
    private final Point currentCoordinates;

    public LogicObstacle(Point currentCoordinates) {
        this.currentCoordinates = currentCoordinates;
    }

    public Point getCurrentCoordinates() {
        return currentCoordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogicObstacle that = (LogicObstacle) o;
        return currentCoordinates.equals(that.currentCoordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentCoordinates);
    }

    @Override
    public boolean isCollisionPossible(Point point) {
        return currentCoordinates.equals(point);
    }
}
