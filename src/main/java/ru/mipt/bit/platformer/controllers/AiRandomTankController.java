package ru.mipt.bit.platformer.controllers;

import ru.mipt.bit.platformer.actions.Action;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.models.logic.LogicTank;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Adapter
 * */
public class AiRandomTankController implements TankController {
    private final List<LogicTank> aiTanks;
    private final Random random = new Random();

    public AiRandomTankController(Level level) {
        this.aiTanks = new ArrayList<>(level.getBotLogicTanks());
    }

    @Override
    public void handleTickAction() {
        for (LogicTank aiTank : aiTanks) {
            recommendAction().createCommand(aiTank).execute();
        }
    }

    private Action recommendAction() {
        return Action.values()[random.nextInt(Action.values().length)];
    }
}
