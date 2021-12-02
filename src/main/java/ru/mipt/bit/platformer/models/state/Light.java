package ru.mipt.bit.platformer.models.state;

import ru.mipt.bit.platformer.models.logic.LogicTank;

/**
 * Use case
 * */
public class Light extends TankState {
    public Light() {
    }

    public Light(LogicTank logicTank) {
        super(logicTank);
        this.health = 1;
        this.movementSpeed = 0.5f;
    }

    @Override
    public void registerCollisionDamage(int collisionDamage) {
        logicTank.registerCollisionDamage(collisionDamage);

        // TODO сделать красиво, без if
        if (logicTank.getHealth() == 3) {
            logicTank.setTankState(new Heavy());
        } else if (logicTank.getHealth() == 2) {
            logicTank.setTankState(new Medium());
        }
    }
}
