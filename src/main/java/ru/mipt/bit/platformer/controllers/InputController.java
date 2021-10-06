package ru.mipt.bit.platformer.controllers;

import com.badlogic.gdx.Input;
import ru.mipt.bit.platformer.models.graphics.Direction;

import java.util.Arrays;
import java.util.Optional;

public class InputController {
    public static Direction getCalledDirection(Input input) {
        Optional<Direction> called = Arrays.stream(Direction.values())
                .filter(direction -> input.isKeyPressed(direction.getKey())).findFirst();

        return called.orElse(null);
    }
}
