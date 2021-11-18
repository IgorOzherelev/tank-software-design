package ru.mipt.bit.platformer.controllers;

import ru.mipt.bit.platformer.actions.Action;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.models.logic.LogicTank;

import java.util.List;
import java.util.Random;

public class AiRandomTankController implements TankController {
    private final List<LogicTank> aiTanks;
    private final Level level;
    private final Random random = new Random();

    public AiRandomTankController(Level level, List<LogicTank> aiTanks) {
        this.aiTanks = aiTanks;
        this.level = level;
    }

    @Override
    public void handleTickAction() {
        for (LogicTank aiTank : aiTanks) {
            recommendAction().createCommand(level, aiTank).execute();
        }
    }

    private Action recommendAction() {
        return Action.values()[random.nextInt(Action.values().length)];
    }
}
