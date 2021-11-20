package ru.mipt.bit.platformer.models.logic;

import ru.mipt.bit.platformer.event.EventAddBullet;
import ru.mipt.bit.platformer.event.EventRemove;
import ru.mipt.bit.platformer.geometry.Rotation;
import ru.mipt.bit.platformer.geometry.Point;
import ru.mipt.bit.platformer.geometry.Direction;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.managers.CollidingManager;
import ru.mipt.bit.platformer.models.GameObject;
import ru.mipt.bit.platformer.models.Shooting;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public class LogicTank implements GameObject, Shooting {
    private float movementSpeed = 0.5f;
    private float reloadingSpeed = 0.5f;
    private float health = 1;

    private final CollidingManager collidingManager;
    private final Level level;

    private Point currentCoordinates;
    private Point destinationCoordinates;
    private Direction direction = Direction.RIGHT;

    private float movementProgress = MAX_PROGRESS;
    private float reloadingProgress = MAX_PROGRESS;

    public LogicTank(CollidingManager collidingManager, Level level, Point currentCoordinates) {
        this.collidingManager = collidingManager;
        this.level = level;
        this.currentCoordinates = currentCoordinates;
        this.destinationCoordinates = new Point(currentCoordinates);
    }

    public LogicTank(float movementSpeed, float reloadingSpeed,
                     float health, CollidingManager collidingManager, Level level) {
        this.movementSpeed = movementSpeed;
        this.reloadingSpeed = reloadingSpeed;
        this.health = health;
        this.collidingManager = collidingManager;
        this.level = level;
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

    @Override
    public Point getCurrentCoordinates() {
        return currentCoordinates;
    }

    @Override
    public void live(float deltaTime) {
        if (isAlive()) {
            movementProgress = continueProgress(movementProgress, deltaTime, movementSpeed);
            reloadingProgress = continueProgress(reloadingProgress, deltaTime, reloadingSpeed);
            if (isReadyToProceed()) {
                currentCoordinates = new Point(destinationCoordinates);
            }
        }
        // gameOver()
    }

    @Override
    public void registerCollisionDamage(int collisionDamage) {
        health -= collisionDamage;
        if (!isAlive()) {
            level.registerEvent(new EventRemove(), this);
        }
    }

    @Override
    public void move(Direction direction) {
        if (isReadyToProceed()) {
            if (collidingManager.isSafeDirection(direction, this)) {
                this.destinationCoordinates = new Point(this.currentCoordinates).add(direction.getShift());
                this.direction = direction;
                this.movementProgress = MIN_PROGRESS;
            }
        }
    }

    @Override
    public void shoot() {
        if (isReadyToProceed()) {
            Point bulletCoordinates = new Point(currentCoordinates);
            LogicBullet logicBullet = new LogicBullet(bulletCoordinates, direction, collidingManager, level);
            level.registerEvent(new EventAddBullet(), logicBullet);

            reloadingProgress = MIN_PROGRESS;
        }
    }

    @Override
    public boolean isAlive() {
        return health > 0;
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
    public boolean isReloaded() {
        return isEqual(reloadingProgress, MAX_PROGRESS);
    }

    private boolean isReadyToProceed() {
        return isStopped() && isReloaded();
    }
}
