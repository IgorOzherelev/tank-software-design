package ru.mipt.bit.platformer.graphics.decorators;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.graphics.objects.Drawable;
import ru.mipt.bit.platformer.graphics.objects.LibGdxGraphicHealthBar;
import ru.mipt.bit.platformer.graphics.objects.LibGdxGraphicTank;
import ru.mipt.bit.platformer.graphics.renderers.LibGdxLevelRenderer;
import ru.mipt.bit.platformer.movement.TileMovement;

/**
 * Adapter
 * */
public class LibGdxGraphicTankWithHealthDecorator implements Drawable {
    private final LibGdxGraphicTank graphicTank;
    private final LibGdxGraphicHealthBar graphicHealthBar;
    private final LibGdxLevelRenderer levelRenderer;

    public LibGdxGraphicTankWithHealthDecorator(LibGdxGraphicTank graphicTank,
                                                LibGdxGraphicHealthBar graphicHealthBar,
                                                LibGdxLevelRenderer levelRenderer) {
        this.graphicTank = graphicTank;
        this.graphicHealthBar = graphicHealthBar;
        this.levelRenderer = levelRenderer;
    }

    @Override
    public void draw(Batch batch, TileMovement tileMovement) {
        graphicTank.draw(batch, tileMovement);
    }

    @Override
    public void drawShape() {
        if (levelRenderer.isHealthToggled()) {
            graphicHealthBar.drawShape();
        }
    }

    @Override
    public void dispose() {
        graphicTank.dispose();
    }
}
