package ru.mipt.bit.platformer.services.ai;

import org.awesome.ai.AI;
import org.awesome.ai.Recommendation;
import org.awesome.ai.state.GameState;
import ru.mipt.bit.platformer.models.commands.Command;
import ru.mipt.bit.platformer.services.colliding.CollidingManagerService;
import ru.mipt.bit.platformer.services.gamestate.AiGameStateService;

import java.util.List;

import static ru.mipt.bit.platformer.services.converters.awesomeai.RecommendationCommandConverter.convertRecommendationsToCommands;

public class AwesomeAiServiceAdapter implements CustomAiService {
    private float deltaTime;

    private final AiGameStateService<GameState> aiGameStateService;
    private final CollidingManagerService collidingManagerService;
    private final AI ai;

    public AwesomeAiServiceAdapter(AiGameStateService<GameState> aiGameStateService,
                                   CollidingManagerService collidingManagerService, AI ai) {
        this.aiGameStateService = aiGameStateService;
        this.collidingManagerService = collidingManagerService;
        this.ai = ai;
    }

    @Override
    public List<? extends Command> recommend() {
        List<Recommendation> recommendations = ai.recommend(aiGameStateService.getGameState());
        aiGameStateService.update();
        return convertRecommendationsToCommands(recommendations, collidingManagerService, deltaTime);
    }

    @Override
    public void setDeltaTime(float deltaTime) {
        this.deltaTime = deltaTime;
    }
}
