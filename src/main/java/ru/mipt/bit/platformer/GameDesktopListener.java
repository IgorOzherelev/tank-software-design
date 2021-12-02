package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import org.awesome.ai.strategy.NotRecommendingAI;
import ru.mipt.bit.platformer.actions.LibGdxActionKeyboardMapper;
import ru.mipt.bit.platformer.controllers.AiTankControllerAdapter;
import ru.mipt.bit.platformer.controllers.PlayerTankController;
import ru.mipt.bit.platformer.controllers.TankController;
import ru.mipt.bit.platformer.models.Player;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.preferences.LibGdxGameTexturePreferences;
import ru.mipt.bit.platformer.preferences.TexturePreferences;
import ru.mipt.bit.platformer.level.LevelGeneratorFromFile;
import ru.mipt.bit.platformer.level.LevelGenerator;
import ru.mipt.bit.platformer.movement.TileMovement;
import ru.mipt.bit.platformer.graphics.renderers.LibGdxLevelRenderer;

import static ru.mipt.bit.platformer.utils.LibGdxGameUtils.getSingleLayer;

/**
 * Adapter
 * */
public class GameDesktopListener implements ApplicationListener {
    private TankController playerTankController;
    private TankController aiTankController;

    private LibGdxLevelRenderer rendererService;

    private Level level;

    @Override
    public void create() {
        TiledMap tiledMap = new TmxMapLoader().load("level.tmx");
        TiledMapTileLayer groundLayer = getSingleLayer(tiledMap);

        TexturePreferences texturePreferences = new LibGdxGameTexturePreferences(tiledMap);

        LevelGenerator levelGenerator = new LevelGeneratorFromFile("level.map", texturePreferences);
//      LevelGenerator levelGenerator = new LevelGeneratorRandom(6, 6, texturePreferences);
        level = levelGenerator.generate();
        TileMovement tileMovement = new TileMovement(groundLayer);

        rendererService = new LibGdxLevelRenderer(level, tiledMap, tileMovement);
        Player player = new Player("nick", level.getPlayerLogicTank());

        level.init();

        playerTankController = new PlayerTankController(player, new LibGdxActionKeyboardMapper());
//        aiTankController = new AiRandomTankController(level);
        aiTankController = new AiTankControllerAdapter(level, new NotRecommendingAI());
    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        rendererService.render();
        level.handleTick(deltaTime);
        playerTankController.handleTickAction();
        aiTankController.handleTickAction();
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
