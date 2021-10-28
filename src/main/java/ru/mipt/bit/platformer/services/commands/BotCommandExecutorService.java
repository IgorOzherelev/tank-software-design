package ru.mipt.bit.platformer.services.commands;

import ru.mipt.bit.platformer.models.commands.Command;
import ru.mipt.bit.platformer.services.ai.CustomAI;

public class BotCommandExecutorService implements CommandExecutorService {
    private final CustomAI customAI;

    public BotCommandExecutorService(CustomAI customAI) {
        this.customAI = customAI;
    }

    @Override
    public void execute() {
        customAI.recommend().forEach(Command::execute);
    }
}
