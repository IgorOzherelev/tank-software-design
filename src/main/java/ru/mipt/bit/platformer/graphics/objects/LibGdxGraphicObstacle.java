package ru.mipt.bit.platformer.graphics.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.geometry.Rotation;
import ru.mipt.bit.platformer.models.logic.LogicObstacle;
import ru.mipt.bit.platformer.movement.TileMovement;

import static ru.mipt.bit.platformer.utils.LibGdxGraphicUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.utils.LibGdxGraphicUtils.drawRegionUnscaled;

public class LibGdxGraphicObstacle implements Drawable {
    private final Texture texture;
    private final TextureRegion textureRegion;
    private final Rectangle rectangle;
    private final LogicObstacle logicObstacle;

    public LibGdxGraphicObstacle(Texture texture, LogicObstacle logicObstacle) {
        this.logicObstacle = logicObstacle;
        this.texture = texture;
        this.textureRegion = new TextureRegion(texture);
        this.rectangle = createBoundingRectangle(textureRegion);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public LogicObstacle getLogicObstacle() {
        return logicObstacle;
    }

    @Override
    public void dispose() {
        this.texture.dispose();
    }

    @Override
    public void drawTexture(Batch batch) {
        drawRegionUnscaled(batch, textureRegion, rectangle, Rotation.E);
    }

    @Override
    public void drawMovement(TileMovement tileMovement) {}
}
