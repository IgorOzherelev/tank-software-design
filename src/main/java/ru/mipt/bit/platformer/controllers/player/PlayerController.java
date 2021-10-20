package ru.mipt.bit.platformer.controllers.player;

import com.badlogic.gdx.Input;
import ru.mipt.bit.platformer.models.Player;
import ru.mipt.bit.platformer.models.movable.Direction;
import ru.mipt.bit.platformer.models.movable.Movable;
import ru.mipt.bit.platformer.models.storages.GameObjectStorage;
import ru.mipt.bit.platformer.preferences.TexturePreferences;
import ru.mipt.bit.platformer.services.movement.TileMovementService;

import static ru.mipt.bit.platformer.controllers.input.InputController.getCalledDirection;
import static ru.mipt.bit.platformer.util.GameObjectCollisionUtils.checkIsMoveOutOfMapBounds;
import static ru.mipt.bit.platformer.util.GameObjectCollisionUtils.checkIsNonCollidingMove;

public class PlayerController {
    private final TileMovementService tileMovementService;
    private final Player player;
    private final TexturePreferences preferences;

    public PlayerController(Player player,
                            TileMovementService tileMovementService,
                            TexturePreferences preferences) {
        this.player = player;
        this.tileMovementService = tileMovementService;
        this.preferences = preferences;
    }

    public void handleKeyEvent(Input input, GameObjectStorage storage, float deltaTime) {
        Movable playerControllableObject = player.getPlayerObject();
        Direction direction;
        if (playerControllableObject.isStopped()) {
            direction = getCalledDirection(input);
            if (direction != null && checkIsNonCollidingMove(direction, storage, playerControllableObject)
            && checkIsMoveOutOfMapBounds(direction, playerControllableObject, preferences)) {
                playerControllableObject.prepareForMove(direction);
            }
        }

        playerControllableObject.move(deltaTime);
        tileMovementService.updateMovableGameObjectRectangle(playerControllableObject);
    }
}
