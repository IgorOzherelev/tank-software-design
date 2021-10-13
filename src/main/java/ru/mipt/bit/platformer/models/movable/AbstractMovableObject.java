package ru.mipt.bit.platformer.models.movable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Disposable;
import ru.mipt.bit.platformer.logic.geometry.Point;
import ru.mipt.bit.platformer.models.objects.Drawable;
import ru.mipt.bit.platformer.models.objects.GraphicObject;

import static com.badlogic.gdx.math.MathUtils.clamp;
import static com.badlogic.gdx.math.MathUtils.isEqual;

abstract public class AbstractMovableObject implements Drawable, Disposable, Movable {
    protected static final float MAX_PROGRESS = 1f;
    protected static final float MIN_PROGRESS = 0f;

    protected float movementProgress = MAX_PROGRESS;

    protected GraphicObject graphicObject;
    protected Point destinationCoordinates;

    public AbstractMovableObject(Texture texture, Point coordinates, float rotation) {
        this.graphicObject = new GraphicObject(texture, coordinates, rotation);
        this.destinationCoordinates = new Point(coordinates);
    }

    public AbstractMovableObject(Point coordinates, float rotation) {
        this.graphicObject = new GraphicObject(coordinates, rotation);
        this.destinationCoordinates = new Point(coordinates);
    }

    public GraphicObject getGraphicObject() {
        return this.graphicObject;
    }

    public Point getCurrentCoordinates() {
        return this.graphicObject.getGameObject().getCoordinates();
    }

    public Point getDestinationCoordinates() {
        return destinationCoordinates;
    }

    public float getMovementProgress() {
        return movementProgress;
    }

    public float getRotation() {
        return this.graphicObject.getGameObject().getRotation();
    }

    public float continueProgress(float previousProgress, float deltaTime, float speed) {
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
