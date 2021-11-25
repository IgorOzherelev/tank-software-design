package ru.mipt.bit.platformer.models.state;

import ru.mipt.bit.platformer.geometry.Direction;
import ru.mipt.bit.platformer.models.logic.LogicTank;

public abstract class TankState {
    protected LogicTank logicTank;
    protected int health;
    protected float movementSpeed;

    public TankState() {
    }

    public TankState(LogicTank logicTank) {
        this.logicTank = logicTank;
    }

    public void setLogicTank(LogicTank logicTank) {
        this.logicTank = logicTank;
    }

    public int getHealth() {
        return health;
    }

    public float getMovementSpeed() {
        return movementSpeed;
    }

    public void move(Direction direction) {
        logicTank.move(direction);
    }

    public void shoot() {
        logicTank.shoot();
    }

    protected abstract void registerCollisionDamage(int collisionDamage);
}
