package ru.mipt.bit.platformer.services.ai;

import ru.mipt.bit.platformer.models.ai.AiMoveCommand;
import ru.mipt.bit.platformer.models.movable.Direction;
import ru.mipt.bit.platformer.models.movable.Movable;
import ru.mipt.bit.platformer.services.colliding.CollidingManagerService;
import ru.mipt.bit.platformer.services.movement.TileMovementService;

import java.util.List;

public class AiMoveCommandExecutorService implements AiCommandExecutorService {
    private final List<? extends Movable> movables;
    private final TileMovementService tileMovementService;

    private final CollidingManagerService collidingManagerService;
    private final float deltaTime;

    public AiMoveCommandExecutorService(List<? extends Movable> movables, TileMovementService tileMovementService,
                                        CollidingManagerService collidingManagerService, float deltaTime) {
        this.movables = movables;
        this.tileMovementService = tileMovementService;
        this.collidingManagerService = collidingManagerService;
        this.deltaTime = deltaTime;
    }

    @Override
    public void execute() {
        Direction randomDirection = chooseRandomDirection();
        AiMoveCommand moveCommand;

        for (Movable movable : movables) {
            moveCommand = new AiMoveCommand(collidingManagerService, movable, randomDirection, deltaTime);
            moveCommand.execute();
            tileMovementService.updateMovableGameObjectRectangle(movable);
        }
    }

    private Direction chooseRandomDirection() {
        Direction[] directions = Direction.values();
        int directionIndex = (int) (Math.random() * directions.length);

        return  directions[directionIndex];
    }
}
