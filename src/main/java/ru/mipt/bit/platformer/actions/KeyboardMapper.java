package ru.mipt.bit.platformer.actions;

import ru.mipt.bit.platformer.commands.Command;

public interface KeyboardMapper {
    Action getCalledAction();

    Command getCalledCommand();

    boolean isKeyPressed(Integer key);
}
