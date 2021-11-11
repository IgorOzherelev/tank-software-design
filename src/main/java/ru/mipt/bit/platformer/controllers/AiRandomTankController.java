package ru.mipt.bit.platformer.controllers;

import ru.mipt.bit.platformer.actions.Action;
import ru.mipt.bit.platformer.managers.CollidingManager;
import ru.mipt.bit.platformer.models.logic.LogicTank;

import java.util.List;
import java.util.Random;

public class AiRandomTankController implements TankController {
    private final List<LogicTank> aiTanks;
    private final CollidingManager collidingManager;
    private final Random random = new Random();

    public AiRandomTankController(CollidingManager collidingManager,
                                  List<LogicTank> aiTanks) {
        this.aiTanks = aiTanks;
        this.collidingManager = collidingManager;
    }

    @Override
    public void handleTickAction() {
        for (LogicTank aiTank : aiTanks) {
            recommendAction().createCommand(collidingManager, aiTank).execute();
        }
    }

    private Action recommendAction() {
        return Action.values()[random.nextInt(Action.values().length)];
    }
}
