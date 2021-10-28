package ru.mipt.bit.platformer.services.ai;

import ru.mipt.bit.platformer.models.commands.Command;

import java.util.List;

public interface CustomAiService {
    List<? extends Command> recommend();
    void setDeltaTime(float deltaTime);
}
