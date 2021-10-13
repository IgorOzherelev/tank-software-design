package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.controllers.PlayerController;
import ru.mipt.bit.platformer.logic.geometry.Point;
import ru.mipt.bit.platformer.models.Player;
import ru.mipt.bit.platformer.models.movable.Tank;
import ru.mipt.bit.platformer.models.objects.GraphicObject;
import ru.mipt.bit.platformer.services.movement.LibGdxTileMovementService;
import ru.mipt.bit.platformer.services.movement.TileMovementService;
import ru.mipt.bit.platformer.services.renderers.LibGdxLevelRendererService;

import java.util.ArrayList;
import java.util.List;

import static ru.mipt.bit.platformer.util.GameLevelLayerUtils.getSingleLayer;

public class GameDesktopListener implements ApplicationListener {
    private TiledMap level;

    private final List<GraphicObject> gameGraphicObjects = new ArrayList<>();
    private Player player;

    private LibGdxLevelRendererService rendererService;
    private TileMovementService tileMovementService;

    private PlayerController playerController;

    @Override
    public void create() {
        level = new TmxMapLoader().load("level.tmx");
        TiledMapTileLayer groundLayer = getSingleLayer(level);

        player = new Player("SomeNick");

        initGameObjects();

        tileMovementService = new LibGdxTileMovementService(groundLayer, Interpolation.smooth);
        rendererService = new LibGdxLevelRendererService(gameGraphicObjects, player, level);
        rendererService.setCurrentLayer(groundLayer);

        playerController = new PlayerController(player, tileMovementService);
    }

    @Override
    public void render() {
        playerController.handleKeyEvent(Gdx.input, gameGraphicObjects);
        rendererService.render();
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
        rendererService.dispose();
    }

    private void initGameObjects() {
        Tank tank = new Tank(
                new Texture("images/tank_blue.png"),
                new Point(3, 5), 0f
        );

        GraphicObject tree = new GraphicObject(
                new Texture("images/greenTree.png"),
                new Point(1, 5)
        );

        player.setPlayerObject(tank);
        gameGraphicObjects.add(tree);
    }
}
