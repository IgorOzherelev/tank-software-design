package ru.mipt.bit.platformer.models.state;

import ru.mipt.bit.platformer.models.logic.LogicTank;

/**
 * Entity
 * */
public class Heavy extends TankState {
    public Heavy() {
    }

    public Heavy(LogicTank logicTank) {
        super(logicTank);
    }

    @Override
    protected void registerCollisionDamage(int collisionDamage) {
        logicTank.registerCollisionDamage(collisionDamage);

        // TODO сделать красиво, без if
        if (logicTank.getHealth() == 1) {
            logicTank.setTankState(new Light());
        } else if (logicTank.getHealth() == 2) {
            logicTank.setTankState(new Medium());
        }
    }
}
