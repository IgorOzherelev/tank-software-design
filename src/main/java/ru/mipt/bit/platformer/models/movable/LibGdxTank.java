package ru.mipt.bit.platformer.models.movable;

import com.badlogic.gdx.graphics.Texture;
import ru.mipt.bit.platformer.logic.geometry.Point;
import ru.mipt.bit.platformer.models.objects.GameObject;


public class LibGdxTank extends AbstractLibGdxMovableObject {
    private static final float MOVEMENT_SPEED = 0.4f;

    public LibGdxTank(Texture texture, GameObject gameObject) {
        super(texture, gameObject);
    }

    public LibGdxTank(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void prepareForMove(Direction direction) {
        this.movementProgress = AbstractLibGdxMovableObject.MIN_PROGRESS;
        this.destinationCoordinates = new Point(this.getCurrentCoordinates()).add(direction.getShift());
        this.libGdxGraphicObject.getGameObject().setRotation(direction.getRotation());
    }

    @Override
    public void move(float deltaTime) {
        movementProgress = continueProgress(movementProgress, deltaTime, MOVEMENT_SPEED);

        if (isStopped()) {
            this.libGdxGraphicObject.getGameObject().setCoordinates(destinationCoordinates);
        }
    }
}
