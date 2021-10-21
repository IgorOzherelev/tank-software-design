package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.controllers.ai.AiMoveController;
import ru.mipt.bit.platformer.controllers.player.PlayerController;
import ru.mipt.bit.platformer.models.Player;
import ru.mipt.bit.platformer.models.colliding.Colliding;
import ru.mipt.bit.platformer.models.storages.GameObjectStorage;
import ru.mipt.bit.platformer.preferences.LibGdxGameTexturePreferences;
import ru.mipt.bit.platformer.preferences.TexturePreferences;
import ru.mipt.bit.platformer.services.colliding.CollidingManagerService;
import ru.mipt.bit.platformer.services.generator.GameObjectsRandomMapGenerator;
import ru.mipt.bit.platformer.services.generator.MapGenerator;
import ru.mipt.bit.platformer.services.movement.LibGdxTileMovementService;
import ru.mipt.bit.platformer.services.movement.TileMovementService;
import ru.mipt.bit.platformer.services.renderers.LibGdxLevelRendererService;

import java.util.ArrayList;
import java.util.List;

import static ru.mipt.bit.platformer.util.LibGdxGameLevelUtils.getSingleLayer;

public class GameDesktopListener implements ApplicationListener {
    private LibGdxLevelRendererService rendererService;

    private PlayerController playerController;
    private AiMoveController aiMoveController;

    @Override
    public void create() {
        TiledMap level = new TmxMapLoader().load("level.tmx");
        TiledMapTileLayer groundLayer = getSingleLayer(level);

        Player player = new Player("SomeNick");

        TexturePreferences texturePreferences = new LibGdxGameTexturePreferences(level);
        MapGenerator mapGenerator = new GameObjectsRandomMapGenerator(3, 5, texturePreferences);

//        MapGenerator mapGenerator = new GameObjectsFromFileMapGenerator("level.map", texturePreferences);

        GameObjectStorage storage = mapGenerator.generate();

        TileMovementService tileMovementService = new LibGdxTileMovementService(groundLayer, Interpolation.smooth);

        rendererService = new LibGdxLevelRendererService(storage, player, level);
        rendererService.setCurrentLayer(groundLayer);

        List<? extends Colliding> tanks = rendererService.getMovables();
        List<? extends Colliding> trees = storage.getTrees();

        List<Colliding> collidingList = new ArrayList<>();
        collidingList.addAll(tanks);
        collidingList.addAll(trees);
        collidingList.add(player.getPlayerObject());

        CollidingManagerService collidingManagerService = new CollidingManagerService(texturePreferences.getMapWidth(), texturePreferences.getMapHeight());
        collidingManagerService.addCollidings(collidingList);

        aiMoveController = new AiMoveController(collidingManagerService, tileMovementService);
        playerController = new PlayerController(collidingManagerService, player, tileMovementService);
    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        playerController.handleKeyEvent(Gdx.input, deltaTime);
        aiMoveController.handleAiMovements(rendererService.getMovables(), deltaTime);
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
