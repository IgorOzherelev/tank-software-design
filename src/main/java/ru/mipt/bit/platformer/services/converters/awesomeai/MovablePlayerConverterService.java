package ru.mipt.bit.platformer.services.converters.awesomeai;

import org.awesome.ai.state.movable.Player;
import ru.mipt.bit.platformer.models.movable.Movable;


public class MovablePlayerConverterService {
    public static Player convertMovableToPlayer(Movable playerMovable) {
        return new Player.PlayerBuilder()
                .x(playerMovable.getCurrentCoordinates().getX())
                .y(playerMovable.getCurrentCoordinates().getY())
                .destX(playerMovable.getDestinationCoordinates().getX())
                .destY(playerMovable.getDestinationCoordinates().getY())
                .orientation(OrientationConverterService.convertToOrientation(playerMovable.getRotation()))
                .source(playerMovable)
                .build();
    }
}
