package ru.mipt.bit.platformer.converters;

import org.awesome.ai.state.movable.Bot;
import ru.mipt.bit.platformer.models.logic.LogicTank;

public class LogicTankToBotConverter implements Converter<Bot, LogicTank> {
    private final RotationToOrientationConverter rotationToOrientationConverter;

    public LogicTankToBotConverter(RotationToOrientationConverter rotationToOrientationConverter) {
        this.rotationToOrientationConverter = rotationToOrientationConverter;
    }

    @Override
    public Bot convert(LogicTank logicTank) {
        return new Bot.BotBuilder()
                .source(logicTank)
                .x(logicTank.getCurrentCoordinates().getX())
                .y(logicTank.getCurrentCoordinates().getY())
                .orientation(rotationToOrientationConverter.convert(logicTank.getRotation()))
                .destX(logicTank.getDestinationCoordinates().getX())
                .destY(logicTank.getDestinationCoordinates().getY())
                .build();
    }
}
