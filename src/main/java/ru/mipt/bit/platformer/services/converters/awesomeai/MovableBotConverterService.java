package ru.mipt.bit.platformer.services.converters.awesomeai;

import org.awesome.ai.state.movable.Bot;
import ru.mipt.bit.platformer.models.movable.Movable;

import java.util.ArrayList;
import java.util.List;

public class MovableBotConverterService {
    public static Bot convertMovableToBot(Movable movable) {
        return new Bot.BotBuilder()
                .source(movable)
                .x(movable.getCurrentCoordinates().getX())
                .y(movable.getCurrentCoordinates().getY())
                .destX(movable.getDestinationCoordinates().getX())
                .destY(movable.getDestinationCoordinates().getY())
                .orientation(OrientationConverterService.convertToOrientation(movable.getRotation()))
                .build();
    }

    public static List<Bot> convertMovablesToBots(List<? extends Movable> movables) {
        List<Bot> bots = new ArrayList<>();
        for (Movable movable : movables) {
            bots.add(convertMovableToBot(movable));
        }

        return bots;
    }
}
