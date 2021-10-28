package ru.mipt.bit.platformer.services.converters.awesomeai;

import org.awesome.ai.Action;
import org.awesome.ai.Recommendation;
import ru.mipt.bit.platformer.models.commands.Command;
import ru.mipt.bit.platformer.models.commands.MoveCommand;
import ru.mipt.bit.platformer.models.movable.Direction;
import ru.mipt.bit.platformer.models.movable.Movable;
import ru.mipt.bit.platformer.services.colliding.CollidingManagerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RecommendationCommandConverter {
    private static final Set<Action> movingActions = Set.of(Action.values());

    public static Command convertRecommendationToCommand(Recommendation recommendation,
                                                         CollidingManagerService collidingManagerService,
                                                         float deltaTime) {
        Movable movable = (Movable) recommendation.getActor().getSource();

        Action recommendedAction = recommendation.getAction();
        Command command = null;
        if (movingActions.contains(recommendedAction)) {
            Direction direction = convertActionToDirection(recommendation.getAction());
            command = new MoveCommand(collidingManagerService, movable, direction, deltaTime);
        }

        return command;
    }

    public static List<Command> convertRecommendationsToCommands(List<Recommendation> recommendations,
                                                                 CollidingManagerService collidingManagerService,
                                                                 float deltaTime) {
        List<Command> commands = new ArrayList<>();
        for (Recommendation recommendation : recommendations) {
            commands.add(convertRecommendationToCommand(recommendation, collidingManagerService, deltaTime));
        }

        return commands;
    }

    private static Direction convertActionToDirection(Action action) {
        Direction direction = null;
        switch (action) {
            case MoveEast:
                direction = Direction.RIGHT;
                break;
            case MoveWest:
                direction = Direction.LEFT;
                break;
            case MoveNorth:
                direction = Direction.UP;
                break;
            case MoveSouth:
                direction = Direction.DOWN;
                break;
        }

        return direction;
    }
}
