package ru.mipt.bit.platformer.util;

import ru.mipt.bit.platformer.logic.geometry.Point;
import ru.mipt.bit.platformer.models.movable.AbstractMovableObject;
import ru.mipt.bit.platformer.models.movable.Direction;
import ru.mipt.bit.platformer.models.objects.LibGdxGraphicObject;

import java.util.List;

public final class GameGraphicObjectCollisionUtils {
    public static boolean checkIsMoveSafe(Direction direction, List<LibGdxGraphicObject> libGdxGraphicObjects,
                                          AbstractMovableObject movableObject) {
        return libGdxGraphicObjects.stream()
                .noneMatch(graphicObject ->
                        graphicObject.getGameObject().getCoordinates()
                                .equals(new Point(movableObject.getCurrentCoordinates()).add(direction.getShift())));
    }
}
