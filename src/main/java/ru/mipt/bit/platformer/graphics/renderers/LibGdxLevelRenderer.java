package ru.mipt.bit.platformer.graphics.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import ru.mipt.bit.platformer.event.EventGameType;
import ru.mipt.bit.platformer.graphics.objects.Drawable;
import ru.mipt.bit.platformer.graphics.objects.LibGdxGraphicBullet;
import ru.mipt.bit.platformer.graphics.objects.LibGdxGraphicObstacle;
import ru.mipt.bit.platformer.graphics.objects.LibGdxGraphicTank;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.models.Colliding;
import ru.mipt.bit.platformer.models.GameObject;
import ru.mipt.bit.platformer.models.logic.LogicBullet;
import ru.mipt.bit.platformer.models.logic.LogicObstacle;
import ru.mipt.bit.platformer.models.logic.LogicTank;
import ru.mipt.bit.platformer.movement.TileMovement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.utils.LibGdxGameUtils.createSingleLayerMapRenderer;
import static ru.mipt.bit.platformer.utils.LibGdxGraphicUtils.convertPointToGridPoint;
import static ru.mipt.bit.platformer.utils.LibGdxGraphicUtils.placeRectangleAtTileCenter;

public class LibGdxLevelRenderer implements Renderer {
    private final MapRenderer levelRenderer;
    private final Batch batch;
    private final TiledMap tiledMap;
    private final TileMovement tileMovement;

    private final List<LibGdxGraphicTank> libGdxGraphicTanks;
    private final List<LibGdxGraphicObstacle> libGdxGraphicObstacles;

    private final Map<Colliding, Drawable> collidingToDrawableMap = new HashMap<>();

    public LibGdxLevelRenderer(Level level, TiledMap tiledMap, TileMovement tileMovement) {
        this.batch = new SpriteBatch();
        this.tiledMap = tiledMap;
        this.levelRenderer = createSingleLayerMapRenderer(tiledMap, batch);
        this.tileMovement = tileMovement;

        this.libGdxGraphicTanks = createLibGdxGraphicTanks(level.getAllLogicTanks());
        this.libGdxGraphicObstacles = createLibGdxGraphicTrees(level.getLogicObstacles());
    }

    @Override
    public void render() {
        clear();
        levelRenderer.render();
        batch.begin();

        renderObstacles();
        renderTanks();

        batch.end();
    }

    /**
     * Releases all resources of this object.
     */
    @Override
    public void dispose() {
        tiledMap.dispose();

        libGdxGraphicObstacles.forEach(LibGdxGraphicObstacle::dispose);
        libGdxGraphicTanks.forEach(LibGdxGraphicTank::dispose);

        batch.dispose();
    }

    @Override
    public void onEvent(EventGameType eventGameType, GameObject gameObject) {
        switch (eventGameType) {
            case ADD_BULLET:
                collidingToDrawableMap.put(
                        gameObject,
                        new LibGdxGraphicBullet(new Texture(bulletTexturePath), (LogicBullet) gameObject)
                );
                break;
            case REMOVE:
                collidingToDrawableMap.remove(gameObject);
        }
    }

    private void renderTanks() {
        libGdxGraphicTanks.forEach(libGdxGraphicTank -> {
            libGdxGraphicTank.drawTexture(batch);
            libGdxGraphicTank.drawMovement(tileMovement);
        });
    }

    private void renderObstacles() {
        libGdxGraphicObstacles.forEach(graphicObstacle ->
                placeRectangleAtTileCenter(
                        tileMovement.getTileLayer(),
                        graphicObstacle.getRectangle(),
                        convertPointToGridPoint(graphicObstacle
                                .getLogicObstacle()
                                .getCurrentCoordinates())
                )
        );

        libGdxGraphicObstacles.forEach(graphicObject -> graphicObject.drawTexture(batch));
    }

    private void clear() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    private List<LibGdxGraphicTank> createLibGdxGraphicTanks(List<LogicTank> logicTanks) {
        List<LibGdxGraphicTank> libGdxTanks = new ArrayList<>();

        logicTanks.forEach(logicTank -> libGdxTanks.add(
                new LibGdxGraphicTank(new Texture(tankTexturePath), logicTank))
        );

        return libGdxTanks;
    }

    private List<LibGdxGraphicObstacle> createLibGdxGraphicTrees(List<LogicObstacle> trees) {
        List<LibGdxGraphicObstacle> libGdxTrees = new ArrayList<>();
        trees.forEach(logicObstacle -> libGdxTrees.add(
                        new LibGdxGraphicObstacle(new Texture(treeTexturePath), logicObstacle)
                )
        );

        return libGdxTrees;
    }
}
