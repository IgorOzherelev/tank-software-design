package ru.mipt.bit.platformer.models.movable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Disposable;
import ru.mipt.bit.platformer.models.objects.Drawable;
import ru.mipt.bit.platformer.models.objects.GraphicObject;

import static com.badlogic.gdx.math.MathUtils.isEqual;

abstract public class AbstractMovableObject implements Movable, Drawable, Disposable {
    protected GraphicObject graphicObject;
    protected GridPoint2 destinationCoordinates;
    protected float movementProgress = 1f;

    public AbstractMovableObject(Texture texture, GridPoint2 coordinates, float rotation) {
        this.graphicObject = new GraphicObject(texture, coordinates, rotation);
        this.destinationCoordinates = new GridPoint2(coordinates);
    }

    public GraphicObject getGraphicObject() {
        return this.graphicObject;
    }

    public void setDestinationCoordinates(GridPoint2 destinationCoordinates) {
        this.destinationCoordinates = destinationCoordinates;
    }

    public void setCurrentCoordinates(GridPoint2 currentCoordinates) {
        this.graphicObject.getGameObject().setCoordinates(currentCoordinates);
    }

    public GridPoint2 getCurrentCoordinates() {
        return this.graphicObject.getGameObject().getCoordinates();
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

    public void setRotation(float rotation) {
        this.graphicObject.getGameObject().setRotation(rotation);
    }

    @Override
    public boolean isStopped() {
        return isEqual(movementProgress, MAX_PROGRESS);
    }

    @Override
    public void draw(Batch batch) {
        graphicObject.draw(batch);
    }

    @Override
    public void dispose() {
        graphicObject.dispose();
    }
}
