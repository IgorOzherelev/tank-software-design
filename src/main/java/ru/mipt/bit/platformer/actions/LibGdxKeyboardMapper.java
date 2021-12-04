package ru.mipt.bit.platformer.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import ru.mipt.bit.platformer.commands.Command;

import static com.badlogic.gdx.Input.Keys.*;

import java.util.Map;

/**
 * Adapter
 * */
public class LibGdxKeyboardMapper implements KeyboardMapper {
    private final Map<String, Action> keyToActionMap;
    private Map<String, Command> keyToCommandMap;
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

    public void setKeyToCommandMap(Map<String, Command> keyToCommandMap) {
        this.keyToCommandMap = keyToCommandMap;
    }

    @Override
    public Action getCalledAction() {
        for (String key : keyToActionMap.keySet()) {
            if (isKeyPressed(Integer.parseInt(key))) {
                return keyToActionMap.get(key);
            }
        }

        return null;
    }

    @Override
    public Command getCalledCommand() {
        for (String key : keyToCommandMap.keySet()) {
            if (isKeyPressed(Integer.parseInt(key))) {
                return keyToCommandMap.get(key);
            }
        }

        return null;
    }

    @Override
    public boolean isKeyPressed(Integer key) {
        return input.isKeyPressed(key);
    }
}
