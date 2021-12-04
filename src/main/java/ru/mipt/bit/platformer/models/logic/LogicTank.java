package ru.mipt.bit.platformer.models.logic;

import ru.mipt.bit.platformer.geometry.Point;
import ru.mipt.bit.platformer.geometry.Direction;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.managers.CollidingLogicManager;
import ru.mipt.bit.platformer.models.Shooting;
import ru.mipt.bit.platformer.models.state.LogicTankState;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public class LogicTank extends BaseLogicObject implements Shooting {
    private final static float RELOADING_SPEED = 1.4f;
    private float reloadingProgress = MAX_PROGRESS;

    private LogicTankState logicTankState;

    public LogicTank(CollidingLogicManager collidingLogicManager, Level level,
                     Point currentCoordinates, LogicTankState logicTankState) {
        super(collidingLogicManager, level, currentCoordinates);
        this.logicTankState = logicTankState;
        logicTankState.setLogicTank(this);
    }

    public void setLogicTankState(LogicTankState logicTankState) {
        this.logicTankState = logicTankState;
    }

    @Override
    public void live(float deltaTime) {
        if (isAlive()) {
            movementProgress = continueProgress(movementProgress, deltaTime, logicTankState.getMovementSpeed());
            reloadingProgress = continueProgress(reloadingProgress, deltaTime, RELOADING_SPEED);
            if (isReadyToProceed()) {
                currentCoordinates = new Point(destinationCoordinates);
            }
        }
    }

    @Override
    public void registerCollisionDamage(float collisionDamage) {
        logicTankState.registerCollisionDamage(collisionDamage);
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
            logicTankState.shoot();
            reloadingProgress = MIN_PROGRESS;
        }
    }

    public float getHealth() {
        return logicTankState.getHealth();
    }

    @Override
    public boolean isReloaded() {
        return isEqual(reloadingProgress, MAX_PROGRESS);
    }

    private boolean isReadyToProceed() {
        return isStopped() && isReloaded();
    }
}
