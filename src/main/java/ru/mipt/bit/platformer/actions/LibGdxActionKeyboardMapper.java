package ru.mipt.bit.platformer.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import static com.badlogic.gdx.Input.Keys.*;

import java.util.Map;

public class LibGdxActionKeyboardMapper implements ActionKeyboardMapper {
    private final Map<String, Action> keyToActionMap;
    private final Input input = Gdx.input;

    {
        keyToActionMap = Map.of(
                String.valueOf(W), Action.MoveNorth,
                String.valueOf(S), Action.MoveSouth,
                String.valueOf(A), Action.MoveWest,
                String.valueOf(D), Action.MoveEast,
                String.valueOf(SPACE), Action.Shoot
        );
    }

    public Action getCalledAction() {
        for (String key : keyToActionMap.keySet()) {
            if (input.isKeyPressed(Integer.parseInt(key))) {
                return keyToActionMap.get(key);
            }
        }

        return null;
    }
}
