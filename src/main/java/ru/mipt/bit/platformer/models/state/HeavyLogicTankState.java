package ru.mipt.bit.platformer.models.state;

import ru.mipt.bit.platformer.models.logic.LogicTank;

/**
 * Entity
 * Domain
 * */
public class HeavyLogicTankState extends LogicTankState {
    public static final float MAX_HEAVY_TANK_HP;
    public static final float MAX_HEAVY_TANK_MOVEMENT_SPEED;

    static {
        MAX_HEAVY_TANK_HP = MAX_TANK_HP / 3;
        MAX_HEAVY_TANK_MOVEMENT_SPEED = MAX_TANK_MOVEMENT_SPEED / 3;
    }

    public HeavyLogicTankState() {}

    public HeavyLogicTankState(LogicTank logicTank) {
        super(logicTank);
        this.health = MAX_HEAVY_TANK_HP;
        this.movementSpeed = MAX_HEAVY_TANK_MOVEMENT_SPEED;
    }

    public HeavyLogicTankState(LogicTank logicTank, float health) {
        super(logicTank, health);
        this.movementSpeed = MAX_HEAVY_TANK_MOVEMENT_SPEED;
    }
}
