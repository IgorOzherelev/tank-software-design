package ru.mipt.bit.platformer.models.logic;

import ru.mipt.bit.platformer.geometry.Rotation;
import ru.mipt.bit.platformer.geometry.Point;
import ru.mipt.bit.platformer.geometry.Direction;
import ru.mipt.bit.platformer.managers.CollidingManager;
import ru.mipt.bit.platformer.models.Movable;
import ru.mipt.bit.platformer.models.Shooting;

import java.util.Objects;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public class LogicTank implements Movable, Shooting {
    private static final float MOVEMENT_SPEED = 0.4f;

    private Point currentCoordinates;
    private Point destinationCoordinates;

    private Direction direction = Direction.RIGHT;
    private float movementProgress = MAX_PROGRESS;

    public LogicTank(Point currentCoordinates) {
        this.currentCoordinates = currentCoordinates;
        this.destinationCoordinates = new Point(currentCoordinates);
    }

    public Point getCurrentCoordinates() {
        return currentCoordinates;
    }

    public Point getDestinationCoordinates() {
        return destinationCoordinates;
    }

    public Rotation getRotation() {
        return direction.getRotation();
    }

    public float getMovementProgress() {
        return movementProgress;
    }

    public void tick(float deltaTime) {
        movementProgress = continueProgress(movementProgress, deltaTime, MOVEMENT_SPEED);
        if (isStopped()) {
            currentCoordinates = destinationCoordinates;
        }
    }

    @Override
    public boolean isCollisionPossible(Point point) {
        return point.equals(destinationCoordinates) || point.equals(currentCoordinates);
    }

    @Override
    public boolean isStopped() {
        return isEqual(movementProgress, MAX_PROGRESS);
    }

    @Override
    public void move(Direction direction, CollidingManager collidingManager) {
        if (isStopped()) {
            if (collidingManager.isMoveSafe(direction, this)) {
                this.destinationCoordinates = new Point(this.currentCoordinates).add(direction.getShift());
                this.direction = direction;
                this.movementProgress = MIN_PROGRESS;
            }
        }
    }

    @Override
    public void move(CollidingManager collidingManager) {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogicTank logicTank = (LogicTank) o;
        return currentCoordinates.equals(logicTank.currentCoordinates) &&
                destinationCoordinates.equals(logicTank.destinationCoordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentCoordinates, destinationCoordinates);
    }

    @Override
    public String toString() {
        return "LogicTank{" +
                "currentCoordinates=" + currentCoordinates +
                ", destinationCoordinates=" + destinationCoordinates +
                ", direction=" + direction +
                ", movementProgress=" + movementProgress +
                '}';
    }

    @Override
    public void shoot(CollidingManager collidingManager) {
        LogicBullet logicBullet = new LogicBullet(new Point(currentCoordinates).add(direction.getShift()), direction);
        //collidingManager.subscribe();
        logicBullet.move(collidingManager);
    }
}
