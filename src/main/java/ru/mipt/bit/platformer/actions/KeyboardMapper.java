package ru.mipt.bit.platformer.actions;

import ru.mipt.bit.platformer.commands.Command;

/**
 * Adapter
 * */
public interface KeyboardMapper {
    Action getCalledAction();

    Command getCalledCommand();

    boolean isKeyPressed(Integer key);
}
