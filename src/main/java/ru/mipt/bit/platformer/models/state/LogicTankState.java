package ru.mipt.bit.platformer.models.state;

import ru.mipt.bit.platformer.event.EventAddBullet;
import ru.mipt.bit.platformer.event.EventRemove;
import ru.mipt.bit.platformer.models.logic.LogicBullet;
import ru.mipt.bit.platformer.models.logic.LogicTank;

public abstract class LogicTankState {
    public static final float MAX_TANK_HP = 100f;
    public static final float MAX_TANK_MOVEMENT_SPEED = 1.6f;

    protected LogicTank logicTank;

    protected float health;
    protected float movementSpeed;

    public LogicTankState() {
    }

    public LogicTankState(LogicTank logicTank) {
        this.logicTank = logicTank;
    }

    public LogicTankState(LogicTank logicTank, float health) {
        this.logicTank = logicTank;
        this.health = health;
    }

    public void setLogicTank(LogicTank logicTank) {
        this.logicTank = logicTank;
    }

    public float getMovementSpeed() {
        return movementSpeed;
    }

    public void registerCollisionDamage(float collisionDamage) {
        health -= collisionDamage;
        if (health <= 0) {
            logicTank.getLevel().registerEvent(new EventRemove(), logicTank);
        } else if (health <= HeavyLogicTankState.MAX_HEAVY_TANK_HP) {
            logicTank.setLogicTankState(new HeavyLogicTankState(logicTank, health));
        } else if (health <= MediumLogicTankState.MAX_MEDIUM_TANK_HP) {
            logicTank.setLogicTankState(new MediumLogicTankState(logicTank, health));
        }
    }

    public void shoot() {
        LogicBullet logicBullet = new LogicBullet(logicTank);
        logicTank.getLevel().registerEvent(new EventAddBullet(), logicBullet);
    }

    public float getHealth() {
        return this.health;
    }
}
