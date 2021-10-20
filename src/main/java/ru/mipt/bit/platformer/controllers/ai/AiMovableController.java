package ru.mipt.bit.platformer.controllers.ai;

import ru.mipt.bit.platformer.models.movable.Movable;
import ru.mipt.bit.platformer.models.storages.GameObjectStorage;
import ru.mipt.bit.platformer.preferences.TexturePreferences;
import ru.mipt.bit.platformer.services.ai.AiMoveCommandExecutorService;
import ru.mipt.bit.platformer.services.movement.TileMovementService;

import java.util.List;

public class AiMovableController {
    private final TexturePreferences preferences;
    private final TileMovementService tileMovementService;

    public AiMovableController(TexturePreferences preferences, TileMovementService tileMovementService) {
        this.preferences = preferences;
        this.tileMovementService = tileMovementService;
    }

    public void handleAiMovements(GameObjectStorage storage, List<? extends Movable> movables, float deltaTime) {
        AiMoveCommandExecutorService commandExecutorService = new AiMoveCommandExecutorService(movables, tileMovementService, storage, preferences, deltaTime);

        commandExecutorService.execute();
    }
}
