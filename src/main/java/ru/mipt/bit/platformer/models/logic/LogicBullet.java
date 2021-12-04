package ru.mipt.bit.platformer.models.logic;

import ru.mipt.bit.platformer.event.EventRemove;
import ru.mipt.bit.platformer.geometry.Point;

/**
 * Entity
 * */
public class LogicBullet extends BaseLogicObject {
    private final static float BULLET_MOVEMENT_SPEED = 1.7f;

    public LogicBullet(LogicTank logicTank) {
        super(logicTank.currentCoordinates, logicTank.direction, logicTank.collidingLogicManager, logicTank.level);

        this.destinationCoordinates = new Point(currentCoordinates).add(direction.getShift());
        this.collisionDamage = 20f;
    }

    @Override
    public void registerCollisionDamage() {
        level.registerEvent(new EventRemove(), this);
    }

    @Override
    public void live(float deltaTime) {
        movementProgress = continueProgress(movementProgress, deltaTime, BULLET_MOVEMENT_SPEED);
        if (isStopped()) {
            if (collidingLogicManager.isSafeDirection(direction, this)) {
                currentCoordinates = new Point(destinationCoordinates);
                destinationCoordinates = new Point(destinationCoordinates).add(direction.getShift());
                movementProgress = MIN_PROGRESS;
            } else {
                registerCollisionDamage();
            }
        }
    }
}
