package ru.mipt.bit.platformer.commands;

import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.models.logic.LogicTank;

public class ShootCommand implements Command {
    private final LogicTank logicTank;
    private final Level level;

    public ShootCommand(Level level, LogicTank logicTank) {
        this.logicTank = logicTank;
        this.level = level;
    }

    @Override
    public void execute() {
        logicTank.shoot(level);
    }
}
