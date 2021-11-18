package ru.mipt.bit.platformer.commands;

import ru.mipt.bit.platformer.geometry.Direction;
import ru.mipt.bit.platformer.managers.CollidingManager;
import ru.mipt.bit.platformer.models.logic.LogicTank;

public class MoveCommand implements Command {
    private final LogicTank logicTank;
    private final Direction direction;
    private final CollidingManager collidingManager;

    public MoveCommand(CollidingManager collidingManager, LogicTank logicTank, Direction direction) {
        this.collidingManager = collidingManager;
        this.logicTank = logicTank;
        this.direction = direction;
    }

    @Override
    public void execute() {
        logicTank.move(direction, collidingManager);
    }
}
