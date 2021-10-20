package ru.mipt.bit.platformer.controllers.input;

import com.badlogic.gdx.Input;
import ru.mipt.bit.platformer.models.movable.Direction;
import static com.badlogic.gdx.Input.Keys.*;

import java.util.Map;

public class InputController {
    private static Map<Integer, Direction> directionKeyMap;

    static {
        directionKeyMap = Map.of(
                W, Direction.UP,
                S, Direction.DOWN,
                A, Direction.LEFT,
                D, Direction.RIGHT
        );
    }

    public static Direction getCalledDirection(Input input) {
        for (Integer key : directionKeyMap.keySet()) {
            if (input.isKeyPressed(key)) {
                return directionKeyMap.get(key);
            }
        }

        return null;
    }
}
