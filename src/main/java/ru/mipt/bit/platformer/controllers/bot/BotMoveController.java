package ru.mipt.bit.platformer.controllers.bot;

import ru.mipt.bit.platformer.models.movable.Movable;
import ru.mipt.bit.platformer.services.ai.CustomAI;
import ru.mipt.bit.platformer.services.commands.BotCommandExecutorService;
import ru.mipt.bit.platformer.services.movement.TileMovementService;

import java.util.List;

public class BotMoveController {
    private final TileMovementService tileMovementService;
    private final CustomAI customAI;

    public BotMoveController(TileMovementService tileMovementService, CustomAI customAI) {
        this.tileMovementService = tileMovementService;
        this.customAI = customAI;
    }

    public void handleAiMovements(List<? extends Movable> movables, float deltaTime) {
        customAI.setDeltaTime(deltaTime);
        BotCommandExecutorService commandExecutorService = new BotCommandExecutorService(customAI);

        commandExecutorService.execute();

        for (Movable movable : movables) {
            tileMovementService.updateMovableGameObjectRectangle(movable);
        }
    }
}
