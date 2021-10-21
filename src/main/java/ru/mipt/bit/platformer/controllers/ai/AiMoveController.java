package ru.mipt.bit.platformer.controllers.ai;

import ru.mipt.bit.platformer.models.movable.Movable;
import ru.mipt.bit.platformer.services.ai.AiMoveCommandExecutorService;
import ru.mipt.bit.platformer.services.colliding.CollidingManagerService;
import ru.mipt.bit.platformer.services.movement.TileMovementService;

import java.util.List;

public class AiMoveController {
    private final TileMovementService tileMovementService;
    private final CollidingManagerService collidingManagerService;

    public AiMoveController(CollidingManagerService collidingManagerService, TileMovementService tileMovementService) {
        this.collidingManagerService = collidingManagerService;
        this.tileMovementService = tileMovementService;
    }

    public void handleAiMovements(List<? extends Movable> movables, float deltaTime) {
        AiMoveCommandExecutorService commandExecutorService = new AiMoveCommandExecutorService(movables, tileMovementService, collidingManagerService, deltaTime);

        commandExecutorService.execute();
    }
}
