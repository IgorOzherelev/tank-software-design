package ru.mipt.bit.platformer.graphics.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;
import ru.mipt.bit.platformer.models.logic.LogicTank;
import ru.mipt.bit.platformer.movement.TileMovement;

import static ru.mipt.bit.platformer.utils.LibGdxGraphicUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.utils.LibGdxGraphicUtils.drawRegionUnscaled;

public class LibGdxGraphicTank implements Disposable, Drawable {
    private final Texture texture;
    private final TextureRegion textureRegion;
    private final Rectangle rectangle;
    private final LogicTank logicTank;

    public LibGdxGraphicTank(Texture texture, LogicTank logicTank) {
        this.logicTank = logicTank;
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
        drawRegionUnscaled(batch, textureRegion, rectangle, logicTank.getRotation());
    }

    @Override
    public void drawMovement(TileMovement tileMovement) {
        tileMovement.moveRectangleBetweenTileCenters(rectangle, logicTank.getCurrentCoordinates(),
                logicTank.getDestinationCoordinates(), logicTank.getMovementProgress());
    }
}
