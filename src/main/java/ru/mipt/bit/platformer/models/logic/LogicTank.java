package ru.mipt.bit.platformer.models.logic;

import ru.mipt.bit.platformer.event.EventAddBullet;
import ru.mipt.bit.platformer.event.EventRemove;
import ru.mipt.bit.platformer.geometry.Point;
import ru.mipt.bit.platformer.geometry.Direction;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.managers.CollidingLogicManager;
import ru.mipt.bit.platformer.models.Shooting;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public class LogicTank extends BaseLogicObject implements Shooting {
    private final static float RELOADING_SPEED = 0.5f;
    private float reloadingProgress = MAX_PROGRESS;

    public LogicTank(CollidingLogicManager collidingLogicManager, Level level, Point currentCoordinates) {
        super(collidingLogicManager, level, currentCoordinates);
        this.currentCoordinates = currentCoordinates;
        this.destinationCoordinates = new Point(currentCoordinates);
    }

    @Override
    public void live(float deltaTime) {
        if (isAlive()) {
            movementProgress = continueProgress(movementProgress, deltaTime, movementSpeed);
            reloadingProgress = continueProgress(reloadingProgress, deltaTime, RELOADING_SPEED);
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
            if (collidingLogicManager.isSafeDirection(direction, this)) {
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
            LogicBullet logicBullet = new LogicBullet(bulletCoordinates, direction, collidingLogicManager, level);
            level.registerEvent(new EventAddBullet(), logicBullet);

            reloadingProgress = MIN_PROGRESS;
        }
    }

    @Override
    public boolean isReloaded() {
        return isEqual(reloadingProgress, MAX_PROGRESS);
    }

    private boolean isReadyToProceed() {
        return isStopped() && isReloaded();
    }
}
