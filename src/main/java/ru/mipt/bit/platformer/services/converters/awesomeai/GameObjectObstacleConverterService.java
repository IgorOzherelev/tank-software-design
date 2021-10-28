package ru.mipt.bit.platformer.services.converters.awesomeai;

import org.awesome.ai.state.immovable.Obstacle;
import ru.mipt.bit.platformer.models.objects.GameObject;

import java.util.ArrayList;
import java.util.List;

public class GameObjectObstacleConverterService {
    public static Obstacle convertToObstacle(GameObject gameObject) {
        return new Obstacle(gameObject.getCoordinates().getX(), gameObject.getCoordinates().getY());
    }

    public static List<Obstacle> convertGameObjectsToObstacles(List<GameObject> gameObjects) {
        List<Obstacle> obstacles = new ArrayList<>();
        for (GameObject gameObject : gameObjects) {
            obstacles.add(convertToObstacle(gameObject));
        }

        return obstacles;
    }
}
