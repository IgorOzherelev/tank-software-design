package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.controllers.PlayerController;
import ru.mipt.bit.platformer.models.Player;
import ru.mipt.bit.platformer.models.objects.GameGraphicObjectStorage;
import ru.mipt.bit.platformer.services.generator.LibGdxLevelMapGenerator;
import ru.mipt.bit.platformer.services.movement.LibGdxTileMovementService;
import ru.mipt.bit.platformer.services.movement.TileMovementService;
import ru.mipt.bit.platformer.services.renderers.LibGdxLevelRendererService;

import static ru.mipt.bit.platformer.util.LibGdxGameLevelUtils.getSingleLayer;

public class GameDesktopListener implements ApplicationListener {
    private GameGraphicObjectStorage storage;

    private LibGdxLevelRendererService rendererService;

    private PlayerController playerController;

    @Override
    public void create() {
        TiledMap level = new TmxMapLoader().load("level.tmx");
        TiledMapTileLayer groundLayer = getSingleLayer(level);

        Player player = new Player("SomeNick");

        storage = new GameGraphicObjectStorage();
        LibGdxLevelMapGenerator mapGenerator = new LibGdxLevelMapGenerator(level, storage);
        //mapGenerator.generateFromFile("level.map");
        mapGenerator.generateRandomly(5, 1);
        player.setPlayerObject(storage.getPlayerTank());

        TileMovementService tileMovementService = new LibGdxTileMovementService(groundLayer, Interpolation.smooth);
        rendererService = new LibGdxLevelRendererService(storage.getGraphicObjects(), player, level);
        rendererService.setCurrentLayer(groundLayer);

        playerController = new PlayerController(player, tileMovementService);
    }

    @Override
    public void render() {
        playerController.handleKeyEvent(Gdx.input, storage.getGraphicObjects());
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
}
