package ru.mipt.bit.platformer.models.movable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Disposable;
import ru.mipt.bit.platformer.geometry.Point;
import ru.mipt.bit.platformer.models.objects.Drawable;
import ru.mipt.bit.platformer.models.objects.GameObject;
import ru.mipt.bit.platformer.models.objects.LibGdxGraphicObject;

import java.util.Objects;

import static com.badlogic.gdx.math.MathUtils.clamp;
import static com.badlogic.gdx.math.MathUtils.isEqual;

abstract public class AbstractLibGdxMovableObject implements Drawable, Disposable, Movable {
    protected static final float MAX_PROGRESS = 1f;
    protected static final float MIN_PROGRESS = 0f;

    protected float movementProgress = MAX_PROGRESS;

    protected LibGdxGraphicObject libGdxGraphicObject;
    protected Point destinationCoordinates;

    public AbstractLibGdxMovableObject(Texture texture, GameObject gameObject) {
        this.libGdxGraphicObject = new LibGdxGraphicObject(texture, gameObject);
        this.destinationCoordinates = new Point(gameObject.getCoordinates());
    }

    public AbstractLibGdxMovableObject(GameObject gameObject) {
        this.libGdxGraphicObject = new LibGdxGraphicObject(gameObject);
        this.destinationCoordinates = new Point(gameObject.getCoordinates());
    }

    public LibGdxGraphicObject getGraphicObject() {
        return this.libGdxGraphicObject;
    }

    @Override
    public Point getCurrentCoordinates() {
        return this.libGdxGraphicObject.getGameObject().getCoordinates();
    }

    @Override
    public Point getDestinationCoordinates() {
        return destinationCoordinates;
    }

    @Override
    public float getMovementProgress() {
        return movementProgress;
    }

    @Override
    public float getRotation() {
        return this.libGdxGraphicObject.getGameObject().getRotation();
    }

    @Override
    public float continueProgress(float previousProgress, float deltaTime, float speed) {
        return clamp(previousProgress + deltaTime / speed, 0f, 1f);
    }

    @Override
    abstract public void prepareForMove(Direction direction);

    @Override
    abstract public void move(float deltaTime);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractLibGdxMovableObject that = (AbstractLibGdxMovableObject) o;
        return libGdxGraphicObject.equals(that.libGdxGraphicObject) && destinationCoordinates.equals(that.destinationCoordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libGdxGraphicObject, destinationCoordinates);
    }

    @Override
    public boolean isCollisionPossible(Point point) {
        return point.equals(destinationCoordinates) || point.equals(this.getCurrentCoordinates());
    }

    @Override
    public boolean isStopped() {
        return isEqual(movementProgress, MAX_PROGRESS);
    }

    @Override
    public void draw(Batch batch) {
        libGdxGraphicObject.draw(batch);
    }

    @Override
    public void dispose() {
        libGdxGraphicObject.dispose();
    }
}
