package ru.mipt.bit.platformer.converters;

import org.awesome.ai.Recommendation;
import ru.mipt.bit.platformer.actions.Action;
import ru.mipt.bit.platformer.commands.Command;
import ru.mipt.bit.platformer.models.logic.LogicTank;

/**
 * Adapter
 * */
public class RecommendationToCommandConverter implements Converter<Command, Recommendation> {
    private final ActionConverter actionConverter;

    public RecommendationToCommandConverter() {
        this.actionConverter = new ActionConverter();
    }

    @Override
    public Command convert(Recommendation recommendation) {
        LogicTank logicTank = (LogicTank) recommendation.getActor().getSource();

        Action action = actionConverter.convert(recommendation.getAction());
        return action.createCommand(logicTank);
    }
}
