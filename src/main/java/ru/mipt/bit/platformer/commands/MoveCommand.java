package ru.mipt.bit.platformer.commands;

import ru.mipt.bit.platformer.geometry.Direction;
import ru.mipt.bit.platformer.models.logic.LogicTank;

/**
 * Application
 * Use case
 * */
public class MoveCommand implements Command {
    private final LogicTank logicTank;
    private final Direction direction;

    public MoveCommand(LogicTank logicTank, Direction direction) {
        this.logicTank = logicTank;
        this.direction = direction;
    }

    @Override
    public void execute() {
        logicTank.move(direction);
    }
}
