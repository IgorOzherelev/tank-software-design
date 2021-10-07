package ru.mipt.bit.platformer.util;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.models.movable.AbstractMovableObject;
import ru.mipt.bit.platformer.models.movable.Direction;
import ru.mipt.bit.platformer.models.objects.GraphicObject;

import java.util.List;

public final class GameGraphicObjectCollisionUtils {
    public static boolean checkIsMoveSafe(Direction direction, List<GraphicObject> graphicObjects,
                                          AbstractMovableObject movableObject) {
        return graphicObjects.stream()
                .noneMatch(graphicObject ->
                        graphicObject.getGameObject().getCoordinates()
                                .equals(new GridPoint2(movableObject.getCurrentCoordinates()).add(direction.getShift())));
    }
}
