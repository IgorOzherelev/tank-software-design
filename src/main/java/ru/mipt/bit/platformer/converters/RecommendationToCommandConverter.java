package ru.mipt.bit.platformer.converters;

import org.awesome.ai.Recommendation;
import ru.mipt.bit.platformer.actions.Action;
import ru.mipt.bit.platformer.commands.Command;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.models.logic.LogicTank;

public class RecommendationToCommandConverter implements Converter<Command, Recommendation> {
    private final Level level;
    private final ActionConverter actionConverter;

    public RecommendationToCommandConverter(Level level) {
        this.level = level;
        this.actionConverter = new ActionConverter();
    }

    @Override
    public Command convert(Recommendation recommendation) {
        LogicTank logicTank = (LogicTank) recommendation.getActor().getSource();

        Action action = actionConverter.convert(recommendation.getAction());
        return action.createCommand(level, logicTank);
    }
}
