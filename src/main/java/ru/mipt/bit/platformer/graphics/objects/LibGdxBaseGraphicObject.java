package ru.mipt.bit.platformer.graphics.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.models.logic.BaseLogicObject;
import ru.mipt.bit.platformer.movement.TileMovement;

import static ru.mipt.bit.platformer.utils.LibGdxGraphicUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.utils.LibGdxGraphicUtils.drawRegionUnscaled;

public abstract class LibGdxBaseGraphicObject implements Drawable {
    private final Texture texture;
    private final TextureRegion textureRegion;
    private final Rectangle rectangle;
    private final BaseLogicObject baseLogicObject;

    public LibGdxBaseGraphicObject(Texture texture, BaseLogicObject baseLogicObject) {
        this.baseLogicObject = baseLogicObject;
        this.texture = texture;
        this.textureRegion = new TextureRegion(texture);
        this.rectangle = createBoundingRectangle(textureRegion);
    }

    public Texture getTexture() {
        return texture;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public BaseLogicObject getBaseLogicObject() {
        return baseLogicObject;
    }

    @Override
    public void dispose() {
        this.texture.dispose();
    }

    @Override
    public void draw(Batch batch, TileMovement tileMovement) {
        drawRegionUnscaled(batch, textureRegion, rectangle, baseLogicObject.getRotation());

        tileMovement.moveRectangleBetweenTileCenters(rectangle, baseLogicObject.getCurrentCoordinates(),
                baseLogicObject.getDestinationCoordinates(), baseLogicObject.getMovementProgress());
    }
}
