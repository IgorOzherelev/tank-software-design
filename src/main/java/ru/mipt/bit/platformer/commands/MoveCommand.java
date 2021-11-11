package ru.mipt.bit.platformer.commands;

import ru.mipt.bit.platformer.geometry.Direction;
import ru.mipt.bit.platformer.models.Movable;
import ru.mipt.bit.platformer.managers.CollidingManager;

public class MoveCommand implements Command {
    private final Movable movable;
    private final Direction direction;
    private final CollidingManager collidingManager;

    public MoveCommand(CollidingManager collidingManager, Movable movable, Direction direction) {
        this.collidingManager = collidingManager;
        this.movable = movable;
        this.direction = direction;
    }

    @Override
    public void execute() {
        movable.move(direction, collidingManager);
    }
}
