package ru.mipt.bit.platformer.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.models.graphics.basic.GameGraphicObject;
import ru.mipt.bit.platformer.models.graphics.Direction;
import ru.mipt.bit.platformer.models.graphics.Tank;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.List;

import static ru.mipt.bit.platformer.models.graphics.Direction.getCalledDirection;
import static ru.mipt.bit.platformer.util.GdxGameUtils.checkIsMoveSafe;

public class Player {
    private final TileMovement tileMovement;
    private final Tank tank;

    public Player(Tank tank, TileMovement tileMovement) {
        this.tank = tank;
        this.tileMovement = tileMovement;
    }

    public void handleKeyEvent(Input input, List<GameGraphicObject> gameGraphicObjects) {
        float deltaTime = Gdx.graphics.getDeltaTime();
        Direction direction;
        if (tank.isStopped()) {
            direction = getCalledDirection(input);
            if (direction != null && checkIsMoveSafe(direction, gameGraphicObjects, tank)) {
                tank.setMovementProgress(Tank.MIN_PROGRESS);
                tank.setDestinationCoordinates(new GridPoint2(tank.getCoordinates()).add(direction.getShift()));
                tank.setRotation(direction.getRotation());
            }
        }

        tank.move(deltaTime);
        tileMovement.calculateMovableGameObjectCoordinates(tank);
    }

    public Tank getTank() {
        return tank;
    }
}
