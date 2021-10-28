package ru.mipt.bit.platformer.services.commands;

import ru.mipt.bit.platformer.models.commands.Command;
import ru.mipt.bit.platformer.services.ai.CustomAiService;

public class BotCommandExecutorService implements CommandExecutorService {
    private final CustomAiService customAIService;

    public BotCommandExecutorService(CustomAiService customAIService) {
        this.customAIService = customAIService;
    }

    @Override
    public void execute() {
        customAIService.recommend().forEach(Command::execute);
    }
}
