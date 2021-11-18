package ru.mipt.bit.platformer.graphics.renderers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import ru.mipt.bit.platformer.graphics.objects.LibGdxGraphicObstacle;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.models.logic.LogicObstacle;

import java.util.ArrayList;
import java.util.List;

import static ru.mipt.bit.platformer.utils.LibGdxGraphicUtils.*;

public class LibGdxObstacleRenderer implements Renderer {
    private final Batch batch;
    private final List<LibGdxGraphicObstacle> libGdxGraphicObstacles;
    private final TiledMapTileLayer currentLayer;

    public LibGdxObstacleRenderer(Level level, Batch batch, TiledMapTileLayer currentLayer) {
        this.libGdxGraphicObstacles = createLibGdxGraphicTrees(level.getLogicObstacles());
        this.batch = batch;
        this.currentLayer = currentLayer;
    }

    private List<LibGdxGraphicObstacle> createLibGdxGraphicTrees(List<LogicObstacle> trees) {
        List<LibGdxGraphicObstacle> libGdxTrees = new ArrayList<>();
        trees.forEach(logicObstacle -> libGdxTrees.add(
                new LibGdxGraphicObstacle(
                        new Texture(treeLibGdxTexturePath),
                        logicObstacle.getCurrentCoordinates())
                )
        );

        return libGdxTrees;
    }

    @Override
    public void render() {
        libGdxGraphicObstacles.forEach(graphicObstacle ->
                placeRectangleAtTileCenter(
                        currentLayer,
                        graphicObstacle.getRectangle(),
                        convertPointToGridPoint(graphicObstacle
                                .getLogicObstacle()
                                .getCurrentCoordinates())
                )
        );

        libGdxGraphicObstacles.forEach(graphicObject -> graphicObject.drawTexture(batch));
    }

    @Override
    public void dispose() {
        libGdxGraphicObstacles.forEach(LibGdxGraphicObstacle::dispose);
    }
}
