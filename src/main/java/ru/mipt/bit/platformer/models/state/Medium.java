package ru.mipt.bit.platformer.models.state;

import ru.mipt.bit.platformer.models.logic.LogicTank;

/**
 * Use case
 * */
public class Medium extends TankState {
    public Medium() {
    }

    public Medium(LogicTank logicTank) {
        super(logicTank);
    }

    @Override
    protected void registerCollisionDamage(int collisionDamage) {
        logicTank.registerCollisionDamage(collisionDamage);

        // TODO сделать красиво, без if
        if (logicTank.getHealth() == 3) {
            logicTank.setTankState(new Heavy());
        } else if (logicTank.getHealth() == 1) {
            logicTank.setTankState(new Light());
        }
    }
}
