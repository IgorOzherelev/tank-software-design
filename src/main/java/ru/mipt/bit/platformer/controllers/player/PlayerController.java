package ru.mipt.bit.platformer.controllers.player;

import ru.mipt.bit.platformer.geometry.Point;
import ru.mipt.bit.platformer.models.Player;
import ru.mipt.bit.platformer.models.movable.Direction;
import ru.mipt.bit.platformer.models.movable.Movable;
import ru.mipt.bit.platformer.services.colliding.CollidingManagerService;
import ru.mipt.bit.platformer.services.movement.TileMovementService;

import static ru.mipt.bit.platformer.controllers.input.LibGdxInputController.getCalledDirection;

public class PlayerController {
    private final TileMovementService tileMovementService;
    private final Player player;
    private final CollidingManagerService collidingManagerService;

    public PlayerController(CollidingManagerService collidingManagerService, Player player,
                            TileMovementService tileMovementService) {
        this.player = player;
        this.tileMovementService = tileMovementService;
        this.collidingManagerService = collidingManagerService;
    }

    public void handleKeyEvent(float deltaTime) {
        Movable playerControllableObject = player.getPlayerObject();
        Direction direction;
        if (playerControllableObject.isStopped()) {
            direction = getCalledDirection();
            if (direction != null) {
                Point destinationCoordinates = new Point(playerControllableObject.getCurrentCoordinates()).add(direction.getShift());
                if (collidingManagerService.isMoveSafe(destinationCoordinates, playerControllableObject)) {
                    playerControllableObject.prepareForMove(direction);
                }
            }
        }

        playerControllableObject.move(deltaTime);
        tileMovementService.updateMovableGameObjectRectangle(playerControllableObject);
    }
}
