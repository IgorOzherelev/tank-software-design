package ru.mipt.bit.platformer.util;

import ru.mipt.bit.platformer.logic.geometry.Point;
import ru.mipt.bit.platformer.models.movable.Direction;
import ru.mipt.bit.platformer.models.movable.Movable;
import ru.mipt.bit.platformer.models.objects.GameObject;
import ru.mipt.bit.platformer.models.storages.GameObjectStorage;
import ru.mipt.bit.platformer.preferences.TexturePreferences;

import java.util.List;

public final class GameObjectCollisionUtils {
    public static boolean checkIsNonCollidingMove(Direction direction, GameObjectStorage storage,
                                          Movable movableObject) {

        boolean noCollidingWithTrees = checkIsNonCollidingMoveWithSomeObjects(
                direction, storage.getTrees(), movableObject
        );

        if (!noCollidingWithTrees) {
            return false;
        }

        boolean noCollidingWithTanks = checkIsNonCollidingMoveWithSomeObjects(
                direction, storage.getTanks(), movableObject
        );

        if (!noCollidingWithTanks) {
            return false;
        }

        Point playerCoordinates = storage.getPlayerGameObject().getCoordinates();
        return !playerCoordinates.equals(new Point(movableObject.getCurrentCoordinates()).add(direction.getShift()));
    }

    private static boolean checkIsNonCollidingMoveWithSomeObjects(Direction direction, List<GameObject> gameObjects, Movable movableObject) {
        return gameObjects.stream()
                .noneMatch(object ->
                        object.getCoordinates()
                                .equals(new Point(movableObject.getCurrentCoordinates()).add(direction.getShift())));
    }

    public static boolean checkIsMoveOutOfMapBounds(Direction direction, Movable movableObject, TexturePreferences preferences) {
        Point newCoordinates = new Point(movableObject.getCurrentCoordinates()).add(direction.getShift());

        return newCoordinates.getX() != preferences.getMapWidth()
                && newCoordinates.getX() != -1
                && newCoordinates.getY() != preferences.getMapHeight()
                && newCoordinates.getY() != -1;
    }
}
