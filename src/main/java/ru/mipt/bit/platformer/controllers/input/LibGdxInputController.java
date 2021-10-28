package ru.mipt.bit.platformer.controllers.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import ru.mipt.bit.platformer.models.movable.Direction;
import static com.badlogic.gdx.Input.Keys.*;

import java.util.Map;

public class LibGdxInputController {
    private static final Map<Integer, Direction> directionKeyMap;
    private static final Input input = Gdx.input;

    static {
        directionKeyMap = Map.of(
                W, Direction.UP,
                S, Direction.DOWN,
                A, Direction.LEFT,
                D, Direction.RIGHT
        );
    }

    public static Direction getCalledDirection() {
        for (Integer key : directionKeyMap.keySet()) {
            if (input.isKeyPressed(key)) {
                return directionKeyMap.get(key);
            }
        }

        return null;
    }
}
