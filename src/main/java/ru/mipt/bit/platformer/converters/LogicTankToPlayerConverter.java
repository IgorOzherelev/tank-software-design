package ru.mipt.bit.platformer.converters;

import org.awesome.ai.state.movable.Player;
import ru.mipt.bit.platformer.models.logic.LogicTank;

public class LogicTankToPlayerConverter implements Converter<Player, LogicTank> {
    private final RotationToOrientationConverter rotationToOrientationConverter;

    public LogicTankToPlayerConverter(RotationToOrientationConverter rotationToOrientationConverter) {
        this.rotationToOrientationConverter = rotationToOrientationConverter;
    }

    @Override
    public Player convert(LogicTank logicTank) {
        return new Player.PlayerBuilder()
                .source(logicTank)
                .x(logicTank.getCurrentCoordinates().getX())
                .y(logicTank.getCurrentCoordinates().getY())
                .orientation(rotationToOrientationConverter.convert(logicTank.getRotation()))
                .destX(logicTank.getDestinationCoordinates().getX())
                .destY(logicTank.getDestinationCoordinates().getY())
                .build();
    }
}
