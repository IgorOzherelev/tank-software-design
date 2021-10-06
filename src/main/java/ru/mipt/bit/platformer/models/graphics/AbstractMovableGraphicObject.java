package ru.mipt.bit.platformer.models.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.models.graphics.basic.GraphicObject;

import static com.badlogic.gdx.math.MathUtils.isEqual;

abstract public class AbstractMovableGraphicObject extends GraphicObject implements Movable {
    protected GridPoint2 destinationCoordinates = new GridPoint2(gameObject.getCoordinates());
    protected float movementProgress = 1f;

    public AbstractMovableGraphicObject(Texture texture, GridPoint2 coordinates, float rotation) {
        super(texture, coordinates, rotation);
    }

    public void setDestinationCoordinates(GridPoint2 destinationCoordinates) {
        this.destinationCoordinates = destinationCoordinates;
    }

    public void setMovementProgress(float movementProgress) {
        this.movementProgress = movementProgress;
    }

    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }

    public float getMovementProgress() {
        return movementProgress;
    }

    public boolean isStopped() {
        return isEqual(movementProgress, MAX_PROGRESS);
    }

    public void setRotation(float rotation) {
        this.gameObject.setRotation(rotation);
    }
}
