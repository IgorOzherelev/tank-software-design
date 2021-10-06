package ru.mipt.bit.platformer.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.models.Player;
import ru.mipt.bit.platformer.models.movable.AbstractMovableGraphicObject;
import ru.mipt.bit.platformer.models.movable.Direction;
import ru.mipt.bit.platformer.models.movable.Tank;
import ru.mipt.bit.platformer.models.objects.GraphicObject;
import ru.mipt.bit.platformer.services.movement.TileMovementService;

import java.util.List;

import static ru.mipt.bit.platformer.controllers.InputController.getCalledDirection;
import static ru.mipt.bit.platformer.util.GameGraphicObjectCollisionUtils.checkIsMoveSafe;

public class PlayerController {
    private final TileMovementService tileMovementService;
    private final Player player;

    public PlayerController(Player player,
                            TileMovementService tileMovementService) {
        this.player = player;
        this.tileMovementService = tileMovementService;
    }

    public void handleKeyEvent(Input input, List<GraphicObject> gameObjects) {
        float deltaTime = Gdx.graphics.getDeltaTime();
        AbstractMovableGraphicObject playerControllableObject = player.getPlayerObject();
        Direction direction;
        if (playerControllableObject.isStopped()) {
            direction = getCalledDirection(input);
            if (direction != null && checkIsMoveSafe(direction, gameObjects, playerControllableObject)) {
                playerControllableObject.setMovementProgress(Tank.MIN_PROGRESS);
                playerControllableObject.setDestinationCoordinates(new GridPoint2(playerControllableObject.getGameObject().getCoordinates()).add(direction.getShift()));
                playerControllableObject.setRotation(direction.getRotation());
            }
        }

        playerControllableObject.move(deltaTime);
        tileMovementService.updateMovableGameObjectCoordinates(playerControllableObject);
    }
}
