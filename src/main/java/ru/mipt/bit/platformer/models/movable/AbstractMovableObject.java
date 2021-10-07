package ru.mipt.bit.platformer.models.movable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Disposable;
import ru.mipt.bit.platformer.models.objects.Drawable;
import ru.mipt.bit.platformer.models.objects.GraphicObject;

import static com.badlogic.gdx.math.MathUtils.clamp;
import static com.badlogic.gdx.math.MathUtils.isEqual;

abstract public class AbstractMovableObject implements Drawable, Disposable {
    protected static final float MAX_PROGRESS = 1f;
    protected static final float MIN_PROGRESS = 0f;

    protected float movementProgress = MAX_PROGRESS;

    protected GraphicObject graphicObject;
    protected GridPoint2 destinationCoordinates;

    public AbstractMovableObject(Texture texture, GridPoint2 coordinates, float rotation) {
        this.graphicObject = new GraphicObject(texture, coordinates, rotation);
        this.destinationCoordinates = new GridPoint2(coordinates);
    }

    public AbstractMovableObject(GridPoint2 coordinates, float rotation) {
        this.graphicObject = new GraphicObject(coordinates, rotation);
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

    public float getRotation() {
        return this.graphicObject.getGameObject().getRotation();
    }

    float continueProgress(float previousProgress, float deltaTime, float speed) {
        return clamp(previousProgress + deltaTime / speed, 0f, 1f);
    }

    abstract public void prepareForMove(Direction direction);

    abstract public void move(float deltaTime);

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
