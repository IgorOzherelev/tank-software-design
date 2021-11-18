package ru.mipt.bit.platformer.commands;

import ru.mipt.bit.platformer.managers.CollidingManager;
import ru.mipt.bit.platformer.models.logic.LogicTank;

public class ShootCommand implements Command {
    private final LogicTank logicTank;
    private final CollidingManager collidingManager;

    public ShootCommand(CollidingManager collidingManager, LogicTank logicTank) {
        this.logicTank = logicTank;
        this.collidingManager = collidingManager;
    }

    @Override
    public void execute() {
        logicTank.shoot(collidingManager);
    }
}
