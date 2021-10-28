package ru.mipt.bit.platformer.services.commands;

import ru.mipt.bit.platformer.models.commands.MoveCommand;
import ru.mipt.bit.platformer.models.movable.Direction;
import ru.mipt.bit.platformer.models.movable.Movable;
import ru.mipt.bit.platformer.services.colliding.CollidingManagerService;
import ru.mipt.bit.platformer.services.movement.TileMovementService;

import java.util.List;
import java.util.Random;

public class BotCommandExecutorService implements CommandExecutorService {
    private final List<? extends Movable> movables;
    private final TileMovementService tileMovementService;

    private final CollidingManagerService collidingManagerService;
    private final float deltaTime;
    private final Random random = new Random();

    public BotCommandExecutorService(List<? extends Movable> movables, TileMovementService tileMovementService,
                                     CollidingManagerService collidingManagerService, float deltaTime) {
        this.movables = movables;
        this.tileMovementService = tileMovementService;
        this.collidingManagerService = collidingManagerService;
        this.deltaTime = deltaTime;
    }

    @Override
    public void execute() {
        executeMoveCommands();
    }

    private void executeMoveCommands() {
        Direction randomDirection = chooseRandomDirection();
        MoveCommand moveCommand;

        for (Movable movable : movables) {
            moveCommand = new MoveCommand(collidingManagerService, movable, randomDirection, deltaTime);
            moveCommand.execute();
            tileMovementService.updateMovableGameObjectRectangle(movable);
        }
    }

    private Direction chooseRandomDirection() {
        Direction[] directions = Direction.values();
        int directionIndex = random.nextInt(Direction.values().length);

        return  directions[directionIndex];
    }
}
