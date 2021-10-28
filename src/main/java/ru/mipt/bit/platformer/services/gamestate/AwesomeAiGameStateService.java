package ru.mipt.bit.platformer.services.gamestate;

import org.awesome.ai.state.GameState;
import org.awesome.ai.state.immovable.Obstacle;
import org.awesome.ai.state.movable.Bot;
import org.awesome.ai.state.movable.Player;
import ru.mipt.bit.platformer.models.movable.Movable;
import ru.mipt.bit.platformer.models.objects.GameObject;
import ru.mipt.bit.platformer.preferences.TexturePreferences;

import java.util.List;

import static ru.mipt.bit.platformer.services.converters.awesomeai.GameObjectObstacleConverterService.convertGameObjectsToObstacles;
import static ru.mipt.bit.platformer.services.converters.awesomeai.MovableBotConverterService.convertMovablesToBots;
import static ru.mipt.bit.platformer.services.converters.awesomeai.MovablePlayerConverterService.convertMovableToPlayer;

public class AwesomeAiGameStateService implements AiGameStateService<GameState> {
    private GameState gameState;
    private final TexturePreferences texturePreferences;

    private final List<? extends Movable> tanks;
    private final List<GameObject> trees;
    private final Movable movablePlayer;

    public AwesomeAiGameStateService(TexturePreferences texturePreferences, List<? extends Movable> tanks,
                                     List<GameObject> trees, Movable movablePlayer) {
        this.texturePreferences = texturePreferences;
        this.tanks = tanks;
        this.trees = trees;
        this.movablePlayer = movablePlayer;
    }

    @Override
    public void init() {
        update();
    }

    @Override
    public void update() {
        List<Bot> bots = convertMovablesToBots(tanks);
        List<Obstacle> obstacles = convertGameObjectsToObstacles(trees);
        Player player = convertMovableToPlayer(movablePlayer);

        this.gameState = new GameState.GameStateBuilder()
                .bots(bots)
                .obstacles(obstacles)
                .player(player)
                .levelHeight(texturePreferences.getMapHeight())
                .levelWidth(texturePreferences.getMapWidth())
                .build();
    }

    @Override
    public GameState getGameState() {
        return this.gameState;
    }
}
