package ru.mipt.bit.platformer.graphics.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.models.logic.LogicBullet;
import ru.mipt.bit.platformer.movement.TileMovement;

import static ru.mipt.bit.platformer.utils.LibGdxGraphicUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.utils.LibGdxGraphicUtils.drawRegionUnscaled;

public class LibGdxGraphicBullet implements Drawable {
    private final Texture texture;
    private final TextureRegion textureRegion;
    private final Rectangle rectangle;
    private final LogicBullet logicBullet;

    public LibGdxGraphicBullet(Texture texture, LogicBullet logicBullet) {
        this.logicBullet = logicBullet;
        this.texture = texture;
        this.textureRegion = new TextureRegion(texture);
        this.rectangle = createBoundingRectangle(textureRegion);
    }

    @Override
    public void dispose() {
        this.texture.dispose();
    }

    @Override
    public void drawTexture(Batch batch) {
        drawRegionUnscaled(batch, textureRegion, rectangle, logicBullet.getRotation());
    }

    @Override
    public void drawMovement(TileMovement tileMovement) {
        tileMovement.moveRectangleBetweenTileCenters(rectangle, logicBullet.getCurrentCoordinates(),
                logicBullet.getDestinationCoordinates(), logicBullet.getMovementProgress());
    }
}
