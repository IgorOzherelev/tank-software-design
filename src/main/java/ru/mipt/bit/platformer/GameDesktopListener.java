package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import org.awesome.ai.state.GameState;
import org.awesome.ai.strategy.NotRecommendingAI;
import ru.mipt.bit.platformer.controllers.bot.BotMoveController;
import ru.mipt.bit.platformer.controllers.player.PlayerController;
import ru.mipt.bit.platformer.models.Player;
import ru.mipt.bit.platformer.models.colliding.Colliding;
import ru.mipt.bit.platformer.models.storages.GameObjectStorage;
import ru.mipt.bit.platformer.preferences.LibGdxGameTexturePreferences;
import ru.mipt.bit.platformer.preferences.TexturePreferences;
import ru.mipt.bit.platformer.services.ai.AwesomeAiServiceAdapter;
import ru.mipt.bit.platformer.services.ai.CustomAiService;
import ru.mipt.bit.platformer.services.ai.CustomRandomAiService;
import ru.mipt.bit.platformer.services.colliding.CollidingManagerService;
import ru.mipt.bit.platformer.services.commands.BotCommandExecutorService;
import ru.mipt.bit.platformer.services.commands.CommandExecutorService;
import ru.mipt.bit.platformer.services.gamestate.AiGameStateService;
import ru.mipt.bit.platformer.services.gamestate.AwesomeAiGameStateService;
import ru.mipt.bit.platformer.services.generator.GameObjectsFromFileMapGenerator;
import ru.mipt.bit.platformer.services.generator.MapGenerator;
import ru.mipt.bit.platformer.services.movement.LibGdxTileMovementService;
import ru.mipt.bit.platformer.services.movement.TileMovementService;
import ru.mipt.bit.platformer.services.renderers.LibGdxLevelRendererService;

import java.util.ArrayList;
import java.util.List;

import static ru.mipt.bit.platformer.util.LibGdxGameLevelUtils.getSingleLayer;

public class GameDesktopListener implements ApplicationListener {
    private LibGdxLevelRendererService rendererService;
    private CustomAiService customAiService;

    private PlayerController playerController;
    private BotMoveController botMoveController;
    private CollidingManagerService collidingManagerService;
    private TexturePreferences texturePreferences;

    private final Player player = new Player("SomeNick");

    @Override
    public void create() {
        TiledMap level = new TmxMapLoader().load("level.tmx");
        TiledMapTileLayer groundLayer = getSingleLayer(level);

        texturePreferences = new LibGdxGameTexturePreferences(level);
        //MapGenerator mapGenerator = new GameObjectsRandomMapGenerator(10, 1, texturePreferences);

        MapGenerator mapGenerator = new GameObjectsFromFileMapGenerator("level.map", texturePreferences);

        GameObjectStorage storage = mapGenerator.generate();

        TileMovementService tileMovementService = new LibGdxTileMovementService(groundLayer, Interpolation.smooth);

        rendererService = new LibGdxLevelRendererService(storage, player, level);
        rendererService.setCurrentLayer(groundLayer);

        //        customAiService = new CustomRandomAiService(rendererService.getMovables(), collidingManagerService);

        initCollidingManagerService(storage);
        initMovableObjectControllers(storage, tileMovementService);
    }

    private void initCollidingManagerService(GameObjectStorage storage) {
        List<? extends Colliding> tanks = rendererService.getMovables();
        List<? extends Colliding> trees = storage.getTrees();

        List<Colliding> collidingList = new ArrayList<>();
        collidingList.addAll(tanks);
        collidingList.addAll(trees);
        collidingList.add(player.getPlayerObject());

        collidingManagerService = new CollidingManagerService(
                texturePreferences.getMapWidth(),
                texturePreferences.getMapHeight()
        );
        collidingManagerService.addCollidings(collidingList);
    }

    private void initMovableObjectControllers(GameObjectStorage storage,
                                              TileMovementService tileMovementService) {
        AiGameStateService<GameState> aiGameStateService = new AwesomeAiGameStateService(
                texturePreferences,
                rendererService.getMovables(),
                storage.getTrees(),
                player.getPlayerObject()
        );
        aiGameStateService.init();

        customAiService = new AwesomeAiServiceAdapter(aiGameStateService, collidingManagerService, new NotRecommendingAI());

        CommandExecutorService commandExecutorService = new BotCommandExecutorService(customAiService);

        botMoveController = new BotMoveController(tileMovementService, commandExecutorService);
        playerController = new PlayerController(collidingManagerService, player, tileMovementService);
    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        playerController.handleKeyEvent(deltaTime);
        customAiService.setDeltaTime(deltaTime);
        botMoveController.handleAiMovements(rendererService.getMovables());
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
