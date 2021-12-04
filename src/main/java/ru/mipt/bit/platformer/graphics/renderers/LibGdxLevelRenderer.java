package ru.mipt.bit.platformer.graphics.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import ru.mipt.bit.platformer.event.Event;
import ru.mipt.bit.platformer.graphics.objects.Drawable;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.models.GameObject;
import ru.mipt.bit.platformer.movement.TileMovement;

import java.util.HashMap;
import java.util.Map;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.utils.LibGdxGameUtils.createSingleLayerMapRenderer;

public class LibGdxLevelRenderer implements Renderer {
    private final MapRenderer levelRenderer;
    private final Batch batch;
    private final TiledMap tiledMap;
    private final TileMovement tileMovement;
    private final ShapeRenderer shapeRenderer = new ShapeRenderer();

    private final Map<GameObject, Drawable> gameObjectToDrawableMap = new HashMap<>();
    private boolean isToggledHealth;

    public LibGdxLevelRenderer(Level level, TiledMap tiledMap, TileMovement tileMovement) {
        this.batch = new SpriteBatch();
        this.tiledMap = tiledMap;
        this.levelRenderer = createSingleLayerMapRenderer(tiledMap, batch);
        this.tileMovement = tileMovement;

        level.subscribeAll(this);
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    @Override
    public void render() {
        clear();
        levelRenderer.render();
        batch.begin();
        renderDrawables();
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        renderShapes();
        shapeRenderer.end();
    }

    private void renderShapes() {
        gameObjectToDrawableMap.values().forEach(Drawable::drawShape);
    }

    private void renderDrawables() {
        gameObjectToDrawableMap.values().forEach(drawable -> {
            drawable.draw(batch, tileMovement);
        });
    }

    /**
     * Releases all resources of this object.
     */
    @Override
    public void dispose() {
        tiledMap.dispose();
        gameObjectToDrawableMap.values().forEach(Drawable::dispose);
        batch.dispose();
    }

    @Override
    public Map<GameObject, Drawable> getDrawablesMap() {
        return gameObjectToDrawableMap;
    }

    @Override
    public void toggleHealth() {
        this.isToggledHealth = !isToggledHealth;
    }

    @Override
    public void onEvent(Event event, GameObject gameObject) {
        event.performGameObjectToRenderer(this, gameObject);
    }

    private void clear() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    public boolean isHealthToggled() {
        return isToggledHealth;
    }
}
