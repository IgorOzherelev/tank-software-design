package ru.mipt.bit.platformer.commands;

import ru.mipt.bit.platformer.models.logic.LogicTank;

/**
 * Application
 * Use case
 * */
public class ShootCommand implements Command {
    private final LogicTank logicTank;

    public ShootCommand(LogicTank logicTank) {
        this.logicTank = logicTank;
    }

    @Override
    public void execute() {
        logicTank.shoot();
    }
}
