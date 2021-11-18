package ru.mipt.bit.platformer.commands;

import ru.mipt.bit.platformer.geometry.Direction;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.models.logic.LogicTank;

public class MoveCommand implements Command {
    private final LogicTank logicTank;
    private final Direction direction;
    private final Level level;

    public MoveCommand(Level level, LogicTank logicTank, Direction direction) {
        this.level = level;
        this.logicTank = logicTank;
        this.direction = direction;
    }

    @Override
    public void execute() {
        logicTank.move(direction, level);
    }
}
