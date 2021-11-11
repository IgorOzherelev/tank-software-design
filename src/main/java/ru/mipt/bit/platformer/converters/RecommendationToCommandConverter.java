package ru.mipt.bit.platformer.converters;

import org.awesome.ai.Recommendation;
import ru.mipt.bit.platformer.actions.Action;
import ru.mipt.bit.platformer.commands.Command;
import ru.mipt.bit.platformer.managers.CollidingManager;
import ru.mipt.bit.platformer.models.logic.LogicTank;

public class RecommendationToCommandConverter implements Converter<Command, Recommendation> {
    private final CollidingManager collidingManager;
    private final ActionConverter actionConverter;

    public RecommendationToCommandConverter(CollidingManager collidingManager) {
        this.collidingManager = collidingManager;
        this.actionConverter = new ActionConverter();
    }

    @Override
    public Command convert(Recommendation recommendation) {
        LogicTank logicTank = (LogicTank) recommendation.getActor().getSource();

        Action action = actionConverter.convert(recommendation.getAction());
        return action.createCommand(collidingManager, logicTank);
    }
}
