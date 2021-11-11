package ru.mipt.bit.platformer.converters;

import org.awesome.ai.state.GameState;
import ru.mipt.bit.platformer.level.Level;

public class LevelToGameStateConverter implements Converter<GameState, Level> {
    private final LogicObstacleToObstacleConverter logicObstacleToObstacleConverter;
    private final LogicTankToBotConverter logicTankToBotConverter;
    private final LogicTankToPlayerConverter logicTankToPlayerConverter;

    public LevelToGameStateConverter() {
        this.logicObstacleToObstacleConverter = new LogicObstacleToObstacleConverter();
        this.logicTankToBotConverter = new LogicTankToBotConverter(new RotationToOrientationConverter());
        this.logicTankToPlayerConverter = new LogicTankToPlayerConverter(new RotationToOrientationConverter());
    }

    @Override
    public GameState convert(Level level) {
        return new GameState.GameStateBuilder()
                .obstacles(logicObstacleToObstacleConverter.convert(level.getLogicObstacles()))
                .bots(logicTankToBotConverter.convert(level.getBotLogicTanks()))
                .player(logicTankToPlayerConverter.convert(level.getPlayerLogicTank()))
                .build();
    }
}
