package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.models.Player;
import ru.mipt.bit.platformer.models.graphics.Tank;
import ru.mipt.bit.platformer.models.graphics.basic.GameGraphicObject;
import ru.mipt.bit.platformer.models.graphics.basic.GameGraphicObjectFactory;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;
import static ru.mipt.bit.platformer.util.GdxGameUtils.moveRectangleAtTileCenter;

public class GameDesktopLauncher implements ApplicationListener {
    private Batch batch;

    private TiledMap level;
    private MapRenderer levelRenderer;
    private TileMovement tileMovement;

    private Player player;
    private final List<GameGraphicObject> gameGraphicObjects = new ArrayList<>();

    @Override
    public void create() {
        TiledMapTileLayer groundLayer = initTiledMapTileLayer();
        initGameObjects(groundLayer);
    }

    @Override
    public void render() {
        clear();

        // render each tile of the level
        player.handleKeyEvent(Gdx.input, gameGraphicObjects);

        levelRenderer.render();

        // start recording all drawing commands
        batch.begin();

        // render graphic objects
        drawGameGraphicObjectsUnscaled(batch, gameGraphicObjects, player.getTank());

        // submit all drawing requests
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // do not react to window resizing
    }

    @Override
    public void pause() {
        // game doesn't get paused
    }

    @Override
    public void resume() {
        // game doesn't get paused
    }

    @Override
    public void dispose() {
        // dispose of all the native resources (classes which implement com.badlogic.gdx.utils.Disposable)
        gameGraphicObjects.forEach(GameGraphicObject::dispose);
        player.getTank().dispose();

        level.dispose();
        batch.dispose();
    }

    private TiledMapTileLayer initTiledMapTileLayer() {
        batch = new SpriteBatch();

        // load level tiles
        level = new TmxMapLoader().load("level.tmx");
        levelRenderer = createSingleLayerMapRenderer(level, batch);
        TiledMapTileLayer groundLayer = getSingleLayer(level);

        tileMovement = new TileMovement(groundLayer, Interpolation.smooth);

        return groundLayer;
    }

    private void initGameObjects(TiledMapTileLayer groundLayer) {
        Tank tank = GameGraphicObjectFactory.createTank(
                new Texture("images/tank_blue.png"),
                new GridPoint2(3, 5), 0f
        );

        GameGraphicObject tree = GameGraphicObjectFactory.createGameGraphicObject(
                new Texture("images/greenTree.png"),
                new GridPoint2(1, 5)
        );

        gameGraphicObjects.add(tree);
        player = new Player(tank, tileMovement);

        moveRectangleAtTileCenter(groundLayer, tree.getRectangle(), tree.getCoordinates());
    }

    // clear the screen
    private void clear() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
