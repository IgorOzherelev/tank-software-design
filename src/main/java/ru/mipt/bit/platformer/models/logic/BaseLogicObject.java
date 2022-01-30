package ru.mipt.bit.platformer.models.logic;

import ru.mipt.bit.platformer.geometry.Direction;
import ru.mipt.bit.platformer.geometry.Point;
import ru.mipt.bit.platformer.geometry.Rotation;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.managers.CollidingLogicManager;
import ru.mipt.bit.platformer.models.GameObject;

import static com.badlogic.gdx.math.MathUtils.isEqual;

/**
 * Entity
 * Domain
 * */
public abstract class BaseLogicObject implements GameObject {
    protected Point currentCoordinates;
    protected Point destinationCoordinates;
    protected Direction direction = Direction.RIGHT;

    protected CollidingLogicManager collidingLogicManager;
    protected Level level;

    protected float movementProgress = MAX_PROGRESS;
    protected float collisionDamage = 0;

    public BaseLogicObject(CollidingLogicManager collidingLogicManager, Level level, Point currentCoordinates) {
        this.currentCoordinates = currentCoordinates;
        this.destinationCoordinates = new Point(currentCoordinates);
        this.collidingLogicManager = collidingLogicManager;
        this.level = level;
    }

    public BaseLogicObject(Point currentCoordinates, Direction direction,
                           CollidingLogicManager collidingLogicManager, Level level) {
        this.currentCoordinates = currentCoordinates;
        this.destinationCoordinates = new Point(currentCoordinates);
        this.direction = direction;
        this.collidingLogicManager = collidingLogicManager;
        this.level = level;
    }

    public BaseLogicObject(Point currentCoordinates) {
        this.currentCoordinates = currentCoordinates;
        this.destinationCoordinates = new Point(currentCoordinates);
    }

    public Level getLevel() {
        return level;
    }

    public Rotation getRotation() {
        return direction.getRotation();
    }

    public Point getDestinationCoordinates() {
        return destinationCoordinates;
    }

    public float getMovementProgress() {
        return movementProgress;
    }

    public void setMovementProgress(float movementProgress) {
        this.movementProgress = movementProgress;
    }

    public Point getCurrentCoordinates() {
        return currentCoordinates;
    }

    public float getCollisionDamage() {
        return collisionDamage;
    }



    @Override
    public boolean isStopped() {
        return isEqual(movementProgress, MAX_PROGRESS);
    }

    @Override
    public boolean isCollisionPossible(Point point) {
        return point.equals(destinationCoordinates) || point.equals(currentCoordinates);
    }
}
