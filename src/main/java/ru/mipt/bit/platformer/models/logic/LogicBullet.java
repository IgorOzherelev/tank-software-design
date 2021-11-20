package ru.mipt.bit.platformer.models.logic;

import ru.mipt.bit.platformer.event.EventRemove;
import ru.mipt.bit.platformer.geometry.Direction;
import ru.mipt.bit.platformer.geometry.Point;
import ru.mipt.bit.platformer.geometry.Rotation;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.managers.CollidingManager;
import ru.mipt.bit.platformer.models.GameObject;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public class LogicBullet implements GameObject {
    private float movementSpeed = 0.4f;
    private int collisionDamage = 1;

    private Point currentCoordinates;
    private Point destinationCoordinates;
    private final Direction direction;

    private final CollidingManager collidingManager;
    private final Level level;

    private float movementProgress = MAX_PROGRESS;

    public LogicBullet(Point currentCoordinates, Direction direction, CollidingManager collidingManager, Level level) {
        this.currentCoordinates = currentCoordinates;
        this.destinationCoordinates = new Point(currentCoordinates).add(direction.getShift());
        this.direction = direction;
        this.collidingManager = collidingManager;
        this.level = level;
    }

    public LogicBullet(float movementSpeed, int collisionDamage,
                       Direction direction, CollidingManager collidingManager, Level level) {
        this.movementSpeed = movementSpeed;
        this.collisionDamage = collisionDamage;
        this.direction = direction;
        this.collidingManager = collidingManager;
        this.level = level;
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

    @Override
    public Point getCurrentCoordinates() {
        return currentCoordinates;
    }

    @Override
    public int getCollisionDamage() {
        return collisionDamage;
    }

    @Override
    public void registerCollisionDamage() {
        level.registerEvent(new EventRemove(), this);
    }

    @Override
    public void live(float deltaTime) {
        if (isAlive()) {
            movementProgress = continueProgress(movementProgress, deltaTime, movementSpeed);
            if (isStopped()) {
                if (collidingManager.isSafeDirection(direction, this)) {
                    currentCoordinates = new Point(destinationCoordinates);
                    destinationCoordinates = new Point(destinationCoordinates).add(direction.getShift());
                    movementProgress = MIN_PROGRESS;
                } else {
                    registerCollisionDamage();
                }
            }
        }
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
