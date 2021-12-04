package ru.mipt.bit.platformer.models.state;

import ru.mipt.bit.platformer.models.logic.LogicTank;

public class LightLogicTankState extends LogicTankState {
    private static final float MAX_LIGHT_TANK_HP;
    private static final float MAX_LIGHT_TANK_MOVEMENT_SPEED;

    static {
        MAX_LIGHT_TANK_HP = MAX_TANK_HP;
        MAX_LIGHT_TANK_MOVEMENT_SPEED = MAX_TANK_MOVEMENT_SPEED;
    }

    public LightLogicTankState() {
        this.health = MAX_LIGHT_TANK_HP;
        this.movementSpeed = MAX_LIGHT_TANK_MOVEMENT_SPEED;
    }

    public LightLogicTankState(LogicTank logicTank) {
        super(logicTank);
        this.health = MAX_LIGHT_TANK_HP;
        this.movementSpeed = MAX_LIGHT_TANK_MOVEMENT_SPEED;
    }
}
