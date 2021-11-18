package ru.mipt.bit.platformer.graphics.renderers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.graphics.objects.LibGdxGraphicTank;
import ru.mipt.bit.platformer.models.logic.LogicTank;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.movement.TileMovement;

import java.util.ArrayList;
import java.util.List;

public class LibGdxTanksRenderer implements Renderer {
    private final Batch batch;
    private final List<LibGdxGraphicTank> libGdxGraphicTanks;
    private final TileMovement tileMovement;

    public LibGdxTanksRenderer(Level level, Batch batch, TileMovement tileMovement) {
        this.batch = batch;
        this.libGdxGraphicTanks = createLibGdxGraphicTanks(level.getBotLogicTanks());
        this.tileMovement = tileMovement;
    }

    private List<LibGdxGraphicTank> createLibGdxGraphicTanks(List<LogicTank> logicTanks) {
        List<LibGdxGraphicTank> libGdxTanks = new ArrayList<>();

        logicTanks.forEach(logicTank -> libGdxTanks.add(
                new LibGdxGraphicTank(new Texture(tankLibGdxTexturePath), logicTank))
        );

        return libGdxTanks;
    }

    @Override
    public void render() {
        libGdxGraphicTanks.forEach(libGdxGraphicTank -> {
            libGdxGraphicTank.drawTexture(batch);
            libGdxGraphicTank.drawMovement(tileMovement);
        });
    }

    @Override
    public void dispose() {
        libGdxGraphicTanks.forEach(LibGdxGraphicTank::dispose);
    }
}
