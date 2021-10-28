package ru.mipt.bit.platformer.models.commands;

import ru.mipt.bit.platformer.geometry.Point;
import ru.mipt.bit.platformer.models.movable.Direction;
import ru.mipt.bit.platformer.models.movable.Movable;
import ru.mipt.bit.platformer.services.colliding.CollidingManagerService;

public class MoveCommand implements Command {
    private final Movable movable;
    private final Direction direction;

    private final float deltaTime;
    private final CollidingManagerService collidingManagerService;

    public MoveCommand(CollidingManagerService collidingManagerService, Movable movable, Direction direction,
                       float deltaTime) {
        this.collidingManagerService = collidingManagerService;
        this.movable = movable;
        this.direction = direction;
        this.deltaTime = deltaTime;
    }

    @Override
    public void execute() {
        if (movable.isStopped()) {
            if (direction != null) {
                Point destinationCoordinates = new Point(movable.getCurrentCoordinates()).add(direction.getShift());
                if (collidingManagerService.isMoveSafe(destinationCoordinates, movable)) {
                    movable.prepareForMove(direction);
                }
            }
        }

        movable.move(deltaTime);
    }
}
