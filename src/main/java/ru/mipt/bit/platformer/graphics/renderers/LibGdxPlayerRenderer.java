package ru.mipt.bit.platformer.graphics.renderers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.graphics.objects.LibGdxGraphicTank;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.movement.TileMovement;

public class LibGdxPlayerRenderer implements RendererService {
    private final Batch batch;
    private final LibGdxGraphicTank libGdxGraphicTank;
    private final TileMovement tileMovement;

    public LibGdxPlayerRenderer(Level level, Batch batch, TileMovement tileMovement) {
        this.libGdxGraphicTank = new LibGdxGraphicTank(new Texture(tankLibGdxTexturePath), level.getPlayerLogicTank());
        this.batch = batch;
        this.tileMovement = tileMovement;
    }

    @Override
    public void render() {
        libGdxGraphicTank.drawTexture(batch);
        libGdxGraphicTank.drawMovement(tileMovement);
    }

    @Override
    public void dispose() {
        libGdxGraphicTank.dispose();
    }
}
