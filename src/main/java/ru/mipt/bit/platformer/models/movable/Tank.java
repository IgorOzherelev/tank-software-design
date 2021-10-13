package ru.mipt.bit.platformer.models.movable;

import com.badlogic.gdx.graphics.Texture;
import ru.mipt.bit.platformer.logic.geometry.Point;


public class Tank extends AbstractMovableObject {
    private static final float MOVEMENT_SPEED = 0.4f;

    public Tank(Texture texture, Point coordinates, float rotation) {
        super(texture, coordinates, rotation);
    }

    public Tank(Point coordinates, float rotation) {
        super(coordinates, rotation);
    }

    @Override
    public void prepareForMove(Direction direction) {
        this.movementProgress = AbstractMovableObject.MIN_PROGRESS;
        this.destinationCoordinates = new Point(this.getCurrentCoordinates()).add(direction.getShift());
        this.graphicObject.getGameObject().setRotation(direction.getRotation());
    }

    @Override
    public void move(float deltaTime) {
        movementProgress = continueProgress(movementProgress, deltaTime, MOVEMENT_SPEED);

        if (isStopped()) {
            this.graphicObject.getGameObject().setCoordinates(destinationCoordinates);
        }
    }
}
