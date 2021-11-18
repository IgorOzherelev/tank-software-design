package ru.mipt.bit.platformer.models.logic;

import ru.mipt.bit.platformer.geometry.Direction;
import ru.mipt.bit.platformer.geometry.Point;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.models.Movable;

import java.util.Objects;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public class LogicBullet implements Movable {
    private static final float MOVEMENT_SPEED = 0.4f;

    private Point currentCoordinates;
    private Point destinationCoordinates;
    private Direction direction;

    private float movementProgress = MAX_PROGRESS;

    public LogicBullet(Point currentCoordinates, Direction direction) {
        this.currentCoordinates = currentCoordinates;
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogicBullet that = (LogicBullet) o;
        return currentCoordinates.equals(that.currentCoordinates) && destinationCoordinates.equals(that.destinationCoordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentCoordinates, destinationCoordinates);
    }

    @Override
    public boolean isCollisionPossible(Point point) {
        return point.equals(destinationCoordinates) || point.equals(currentCoordinates);
    }

    @Override
    public Point getCurrentCoordinates() {
        return currentCoordinates;
    }

    @Override
    public boolean isStopped() {
        return isEqual(movementProgress, MAX_PROGRESS);
    }

    @Override
    public void move(Direction direction, Level level) {}

    @Override
    public void move(Level level) {

    }
}
