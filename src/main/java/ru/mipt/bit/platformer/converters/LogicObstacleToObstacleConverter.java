package ru.mipt.bit.platformer.converters;

import org.awesome.ai.state.immovable.Obstacle;
import ru.mipt.bit.platformer.geometry.Point;
import ru.mipt.bit.platformer.models.logic.LogicObstacle;

/**
 * Adapter
 * */
public class LogicObstacleToObstacleConverter implements Converter<Obstacle, LogicObstacle> {

    @Override
    public Obstacle convert(LogicObstacle logicObstacle) {
        Point coordinates = logicObstacle.getCurrentCoordinates();
        return new Obstacle(coordinates.getX(), coordinates.getY());
    }
}
