package ru.mipt.bit.platformer.models.logic;

import ru.mipt.bit.platformer.geometry.Direction;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.geometry.Point;
import ru.mipt.bit.platformer.models.GameObject;

import java.util.Objects;

public class LogicObstacle implements GameObject {
    private final Point currentCoordinates;
    private final Level level;

    public LogicObstacle(Point currentCoordinates, Level level) {
        this.currentCoordinates = currentCoordinates;
        this.level = level;
    }

    public Point getCurrentCoordinates() {
        return currentCoordinates;
    }

    @Override
    public boolean isCollisionPossible(Point point) {
        return currentCoordinates.equals(point);
    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public void live(float deltaTime) {

    }

    @Override
    public boolean isStopped() {
        return false;
    }

    @Override
    public void move(Direction direction) {

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
}
