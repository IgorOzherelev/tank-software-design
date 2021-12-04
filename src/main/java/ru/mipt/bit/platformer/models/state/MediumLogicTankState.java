package ru.mipt.bit.platformer.models.state;

import ru.mipt.bit.platformer.models.logic.LogicTank;

/**
 * Entity
 * Domain
 * */
public class MediumLogicTankState extends LogicTankState {
    public static final float MAX_MEDIUM_TANK_HP;
    public static final float MAX_MEDIUM_TANK_MOVEMENT_SPEED;

    static {
        MAX_MEDIUM_TANK_HP = MAX_TANK_HP / 2;
        MAX_MEDIUM_TANK_MOVEMENT_SPEED = MAX_TANK_MOVEMENT_SPEED / 2;
    }

    public MediumLogicTankState() {}

    public MediumLogicTankState(LogicTank logicTank) {
        super(logicTank);
        this.health = MAX_MEDIUM_TANK_HP;
        this.movementSpeed = MAX_MEDIUM_TANK_MOVEMENT_SPEED;
    }

    public MediumLogicTankState(LogicTank logicTank, float health) {
        super(logicTank, health);
        this.movementSpeed = MAX_MEDIUM_TANK_MOVEMENT_SPEED;
    }
}
