package ru.mipt.bit.platformer.models.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;


public class Tank extends AbstractMovableGameObject {
    private static final float MOVEMENT_SPEED = 0.4f;

    public Tank(Texture texture, GridPoint2 coordinates, float rotation) {
        super(texture, coordinates, rotation);
    }

    @Override
    public void move(float deltaTime) {
        movementProgress = continueProgress(movementProgress, deltaTime, MOVEMENT_SPEED);

        if (isStopped()) {
            this.gameObject.setCoordinates(destinationCoordinates);
        }
    }
}
