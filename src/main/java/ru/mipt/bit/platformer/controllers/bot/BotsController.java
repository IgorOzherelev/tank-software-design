package ru.mipt.bit.platformer.controllers.bot;

import ru.mipt.bit.platformer.models.movable.Movable;
import ru.mipt.bit.platformer.services.commands.CommandExecutorService;
import ru.mipt.bit.platformer.services.movement.TileMovementService;

import java.util.List;

public class BotsController {
    private final TileMovementService tileMovementService;
    private final CommandExecutorService commandExecutorService;

    public BotsController(TileMovementService tileMovementService, CommandExecutorService commandExecutorService) {
        this.tileMovementService = tileMovementService;
        this.commandExecutorService = commandExecutorService;
    }

    public void handleAiMovements(List<? extends Movable> movables) {
        commandExecutorService.execute();

        for (Movable movable : movables) {
            tileMovementService.updateMovableGameObjectRectangle(movable);
        }
    }
}
