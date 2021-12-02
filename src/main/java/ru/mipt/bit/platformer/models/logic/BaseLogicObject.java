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
 * */
public abstract class BaseLogicObject implements GameObject {
    protected Point currentCoordinates;
    protected Point destinationCoordinates;
    protected Direction direction = Direction.RIGHT;

    protected CollidingLogicManager collidingLogicManager;
    protected Level level;

    protected float movementSpeed = 0.5f;
    protected float movementProgress = MAX_PROGRESS;
    protected int collisionDamage = 0;
    protected int health = 1;

    public BaseLogicObject(CollidingLogicManager collidingLogicManager, Level level, Point currentCoordinates) {
        this.currentCoordinates = currentCoordinates;
        this.collidingLogicManager = collidingLogicManager;
        this.level = level;
    }

    public BaseLogicObject(Point currentCoordinates, Direction direction,
                           CollidingLogicManager collidingLogicManager, Level level) {
        this.currentCoordinates = currentCoordinates;
        this.direction = direction;
        this.collidingLogicManager = collidingLogicManager;
        this.level = level;
    }

    public BaseLogicObject(Point currentCoordinates) {
        this.currentCoordinates = currentCoordinates;
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

    public Point getCurrentCoordinates() {
        return currentCoordinates;
    }

    public int getCollisionDamage() {
        return collisionDamage;
    }

    public int getHealth() {
        return health;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
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
