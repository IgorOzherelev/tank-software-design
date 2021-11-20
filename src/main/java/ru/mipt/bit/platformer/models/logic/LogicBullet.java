package ru.mipt.bit.platformer.models.logic;

import ru.mipt.bit.platformer.event.EventRemove;
import ru.mipt.bit.platformer.geometry.Direction;
import ru.mipt.bit.platformer.geometry.Point;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.managers.CollidingLogicManager;

public class LogicBullet extends BaseLogicObject {
    public LogicBullet(Point currentCoordinates, Direction direction,
                       CollidingLogicManager collidingLogicManager, Level level) {
        super(currentCoordinates, direction, collidingLogicManager, level);

        this.destinationCoordinates = new Point(currentCoordinates).add(direction.getShift());
        this.movementSpeed = 0.4f;
        this.collisionDamage = 1;
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
}
