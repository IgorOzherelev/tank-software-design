package ru.mipt.bit.platformer.controllers.bot;

import ru.mipt.bit.platformer.models.movable.Movable;
import ru.mipt.bit.platformer.services.commands.BotCommandExecutorService;
import ru.mipt.bit.platformer.services.colliding.CollidingManagerService;
import ru.mipt.bit.platformer.services.movement.TileMovementService;

import java.util.List;

public class BotMoveController {
    private final TileMovementService tileMovementService;
    private final CollidingManagerService collidingManagerService;

    public BotMoveController(CollidingManagerService collidingManagerService, TileMovementService tileMovementService) {
        this.collidingManagerService = collidingManagerService;
        this.tileMovementService = tileMovementService;
    }

    public void handleAiMovements(List<? extends Movable> movables, float deltaTime) {
        BotCommandExecutorService commandExecutorService = new BotCommandExecutorService(movables, tileMovementService, collidingManagerService, deltaTime);

        commandExecutorService.execute();
    }
}
