package ru.mipt.bit.platformer.controllers;

import org.awesome.ai.AI;
import org.awesome.ai.state.GameState;
import ru.mipt.bit.platformer.commands.Command;
import ru.mipt.bit.platformer.converters.LevelToGameStateConverter;
import ru.mipt.bit.platformer.converters.RecommendationToCommandConverter;
import ru.mipt.bit.platformer.level.Level;

import java.util.List;

/**
 * Adapter
 * */
public class AiTankControllerAdapter implements TankController {
    private final Level level;
    private final AI ai;

    private final LevelToGameStateConverter levelToGameStateConverter;
    private final RecommendationToCommandConverter recommendationToCommandConverter;

    public AiTankControllerAdapter(Level level, AI ai) {
        this.level = level;
        this.ai = ai;

        this.levelToGameStateConverter = new LevelToGameStateConverter();
        this.recommendationToCommandConverter = new RecommendationToCommandConverter();
    }

    @Override
    public void handleTickAction() {
        GameState gameState = levelToGameStateConverter.convert(level);
        List<Command> commands = recommendationToCommandConverter.convert(ai.recommend(gameState));

        commands.forEach(Command::execute);
    }
}
