package ru.mipt.bit.platformer.graphics.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.movement.TileMovement;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.utils.LibGdxGameUtils.createSingleLayerMapRenderer;

public class LibGdxLevelRenderer implements Renderer {
    private final MapRenderer levelRenderer;
    private final Batch batch;
    private final TiledMap tiledMap;

    private final LibGdxObstacleRenderer obstacleRendererService;
    private final LibGdxPlayerRenderer playerRendererService;
    private final LibGdxTanksRenderer tanksRendererService;

    public LibGdxLevelRenderer(Level level, TiledMap tiledMap, TileMovement tileMovement) {
        this.batch = new SpriteBatch();
        this.tiledMap = tiledMap;
        this.levelRenderer = createSingleLayerMapRenderer(tiledMap, batch);

        this.obstacleRendererService = new LibGdxObstacleRenderer(level, batch, tileMovement.getTileLayer());
        this.playerRendererService = new LibGdxPlayerRenderer(level, batch, tileMovement);
        this.tanksRendererService = new LibGdxTanksRenderer(level, batch, tileMovement);
    }

    @Override
    public void render() {
        clear();
        levelRenderer.render();
        batch.begin();

        obstacleRendererService.render();
        playerRendererService.render();
        tanksRendererService.render();

        batch.end();
    }

    private void clear() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    /**
     * Releases all resources of this object.
     */
    @Override
    public void dispose() {
        tiledMap.dispose();
        obstacleRendererService.dispose();
        playerRendererService.dispose();
        batch.dispose();
    }
}
