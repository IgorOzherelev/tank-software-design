package ru.mipt.bit.platformer.models.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.models.graphics.basic.GameGraphicObject;

abstract public class AbstractMovableGameGraphicObject extends GameGraphicObject implements Movable {
    protected GridPoint2 destinationCoordinates = new GridPoint2(coordinates);
    protected float movementProgress = 1f;

    public AbstractMovableGameGraphicObject(Texture texture, GridPoint2 coordinates, float rotation) {
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
}
