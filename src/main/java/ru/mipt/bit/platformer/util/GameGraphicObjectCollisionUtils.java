package ru.mipt.bit.platformer.util;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.models.graphics.AbstractMovableGraphicObject;
import ru.mipt.bit.platformer.models.graphics.Direction;
import ru.mipt.bit.platformer.models.graphics.basic.GraphicObject;

import java.util.List;

public final class GameGraphicObjectCollisionUtils {
    public static boolean checkIsMoveSafe(Direction direction, List<GraphicObject> graphicObjects,
                                          AbstractMovableGraphicObject movableObject) {
        return graphicObjects.stream()
                .noneMatch(graphicObject ->
                        graphicObject.getGameObject().getCoordinates()
                                .equals(new GridPoint2(movableObject.getGameObject().getCoordinates()).add(direction.getShift())));
    }
}
