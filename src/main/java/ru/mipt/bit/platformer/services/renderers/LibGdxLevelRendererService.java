package ru.mipt.bit.platformer.services.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import ru.mipt.bit.platformer.models.Player;
import ru.mipt.bit.platformer.models.movable.Movable;
import ru.mipt.bit.platformer.models.storages.GameObjectStorage;

import java.util.List;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.util.LibGdxGameLevelUtils.createSingleLayerMapRenderer;

public class LibGdxLevelRendererService implements RendererService {
    private final MapRenderer levelRenderer;
    private final Batch batch;
    private final TiledMap level;

    private final LibGdxTreesRendererService objectsRendererService;
    private final LibGdxPlayerRendererService playerRendererService;
    private final LibGdxTanksRendererService tanksRendererService;

    public LibGdxLevelRendererService(GameObjectStorage storage, Player player, TiledMap level) {
        this.batch = new SpriteBatch();
        this.level = level;
        this.levelRenderer = createSingleLayerMapRenderer(level, batch);

        this.objectsRendererService = new LibGdxTreesRendererService(storage, batch);
        this.playerRendererService = new LibGdxPlayerRendererService(player, storage, batch);
        this.tanksRendererService = new LibGdxTanksRendererService(storage, batch);
    }

    public void setCurrentLayer(TiledMapTileLayer currentLayer) {
        this.objectsRendererService.setCurrentLayer(currentLayer);
    }

    public List<? extends Movable> getMovables() {
        // пока что двигаются только танки
        return tanksRendererService.getTanks();
    }

    @Override
    public void render() {
        clear();
        levelRenderer.render();
        batch.begin();

        objectsRendererService.render();
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
        level.dispose();
        objectsRendererService.dispose();
        playerRendererService.dispose();
        batch.dispose();
    }
}
