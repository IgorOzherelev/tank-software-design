package ru.mipt.bit.platformer.graphics.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.graphics.renderers.LibGdxLevelRenderer;
import ru.mipt.bit.platformer.models.logic.LogicTank;
import ru.mipt.bit.platformer.models.state.LogicTankState;
import ru.mipt.bit.platformer.movement.TileMovement;

public class LibGdxGraphicHealthBar implements Drawable {
    private static final float MAX_WIDTH = 50;
    private static final float HEIGHT = 10;

    private final Rectangle rectangle;
    private final float healthBarWidth;
    private final float healthBarHeight;
    private final ShapeRenderer shapeRenderer;
    private final LogicTank logicTank;

    public LibGdxGraphicHealthBar(LibGdxLevelRenderer renderer, LibGdxGraphicTank graphicTank) {
        this.shapeRenderer = renderer.getShapeRenderer();
        this.logicTank = (LogicTank) graphicTank.getBaseLogicObject();
        this.rectangle = graphicTank.getRectangle();

        Texture texture = graphicTank.getTexture();
        this.healthBarWidth = texture.getWidth() * 0.25f;
        this.healthBarHeight = texture.getHeight() * 0.75f;
    }

    @Override
    public void drawShape() {
        shapeRenderer.setColor(Color.RED);
        float width = MAX_WIDTH * logicTank.getHealth() / LogicTankState.MAX_TANK_HP;
        shapeRenderer.rect(rectangle.x + healthBarWidth, rectangle.y + healthBarHeight, width, HEIGHT);
    }

    @Override
    public void draw(Batch batch, TileMovement tileMovement) {}

    @Override
    public void dispose() {}
}
