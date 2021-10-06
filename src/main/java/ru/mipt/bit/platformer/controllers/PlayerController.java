package ru.mipt.bit.platformer.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.models.graphics.AbstractMovableGameObject;
import ru.mipt.bit.platformer.models.graphics.Direction;
import ru.mipt.bit.platformer.models.graphics.Tank;
import ru.mipt.bit.platformer.models.graphics.basic.GraphicObject;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.List;

import static ru.mipt.bit.platformer.controllers.InputController.getCalledDirection;
import static ru.mipt.bit.platformer.util.GameGraphicObjectCollisionUtils.checkIsMoveSafe;

public class PlayerController {
    private final TileMovement tileMovement;
    private final AbstractMovableGameObject playerControllableObject;

    public PlayerController(AbstractMovableGameObject playerControllableObject, TileMovement tileMovement) {
        this.playerControllableObject = playerControllableObject;
        this.tileMovement = tileMovement;
    }

    public void handleKeyEvent(Input input, List<GraphicObject> gameObjects) {
        float deltaTime = Gdx.graphics.getDeltaTime();
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
        tileMovement.calculateMovableGameObjectCoordinates(playerControllableObject);
    }

    public AbstractMovableGameObject getPlayerControllableObject() {
        return playerControllableObject;
    }
}
