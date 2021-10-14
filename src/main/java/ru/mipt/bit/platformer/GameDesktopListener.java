package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.controllers.PlayerController;
import ru.mipt.bit.platformer.models.Player;
import ru.mipt.bit.platformer.models.movable.Tank;
import ru.mipt.bit.platformer.models.storages.GameObjectStorage;
import ru.mipt.bit.platformer.models.objects.LibGdxGraphicObject;
import ru.mipt.bit.platformer.preferences.LibGdxGameTexturePreferences;
import ru.mipt.bit.platformer.preferences.TexturePreferences;
import ru.mipt.bit.platformer.services.generator.GameObjectsFromFileMapGenerator;
import ru.mipt.bit.platformer.services.generator.MapGenerator;
import ru.mipt.bit.platformer.services.movement.LibGdxTileMovementService;
import ru.mipt.bit.platformer.services.movement.TileMovementService;
import ru.mipt.bit.platformer.services.renderers.LibGdxLevelRendererService;

import java.util.ArrayList;
import java.util.List;

import static ru.mipt.bit.platformer.util.LibGdxGameLevelUtils.getSingleLayer;

public class GameDesktopListener implements ApplicationListener {
    private final List<LibGdxGraphicObject> trees = new ArrayList<>();
    private LibGdxLevelRendererService rendererService;

    private PlayerController playerController;

    @Override
    public void create() {
        TiledMap level = new TmxMapLoader().load("level.tmx");
        TiledMapTileLayer groundLayer = getSingleLayer(level);

        Player player = new Player("SomeNick");

        TexturePreferences texturePreferences = new LibGdxGameTexturePreferences(level);
//        MapGenerator mapGenerator = new GameObjectsRandomGenerator(5, 1, texturePreferences);

        MapGenerator mapGenerator = new GameObjectsFromFileMapGenerator("level.map", texturePreferences);

        GameObjectStorage storage = mapGenerator.generate();
        player.setPlayerObject(new Tank(new Texture("images/tank_blue.png"),
                storage.getPlayerGameObject().getCoordinates(), 0f));

        TileMovementService tileMovementService = new LibGdxTileMovementService(groundLayer, Interpolation.smooth);

        storage.getGameObjects().forEach(gameObject -> trees.add(
                new LibGdxGraphicObject(
                    new Texture("images/greenTree.png"),
                    gameObject.getCoordinates()))
        );

        rendererService = new LibGdxLevelRendererService(trees, player, level);
        rendererService.setCurrentLayer(groundLayer);

        playerController = new PlayerController(player, tileMovementService);
    }

    @Override
    public void render() {
        playerController.handleKeyEvent(Gdx.input, trees);
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
