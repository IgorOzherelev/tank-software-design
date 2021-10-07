package ru.mipt.bit.platformer.models.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;

import static ru.mipt.bit.platformer.util.GameGraphicUtils.createBoundingRectangle;

public class GraphicObject implements Disposable, Drawable {
    private Texture texture;
    private TextureRegion textureRegion;
    private Rectangle rectangle;

    protected GameObject gameObject;

    public GraphicObject(Texture texture, GridPoint2 coordinates, float rotation) {
        this.gameObject = new GameObject(coordinates, rotation);
        this.texture = texture;
        this.textureRegion = new TextureRegion(texture);
        this.rectangle = createBoundingRectangle(textureRegion);
    }

    public GraphicObject(Texture texture, GridPoint2 coordinates) {
        this.gameObject = new GameObject(coordinates, 0);
        this.texture = texture;
        this.textureRegion = new TextureRegion(texture);
        this.rectangle = createBoundingRectangle(textureRegion);
    }

    public GraphicObject(GridPoint2 coordinates, float rotation) {
        this.gameObject = new GameObject(coordinates, rotation);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    /**
     * Releases all resources of this object.
     */
    @Override
    public void dispose() {
        this.texture.dispose();
    }

    @Override
    public void draw(Batch batch) {
        int regionWidth, regionHeight;
        float regionOriginX, regionOriginY;

        regionWidth = textureRegion.getRegionWidth();
        regionHeight = textureRegion.getRegionHeight();

        regionOriginX = regionWidth / 2f;
        regionOriginY = regionHeight / 2f;

        batch.draw(
                textureRegion, rectangle.x, rectangle.y,
                regionOriginX, regionOriginY, regionWidth, regionHeight, 1f, 1f, gameObject.getRotation()
        );
    }
}
