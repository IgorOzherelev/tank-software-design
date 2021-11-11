package ru.mipt.bit.platformer.controllers;

import ru.mipt.bit.platformer.actions.Action;
import ru.mipt.bit.platformer.actions.ActionKeyboardMapper;
import ru.mipt.bit.platformer.models.Player;
import ru.mipt.bit.platformer.models.logic.LogicTank;
import ru.mipt.bit.platformer.managers.CollidingManager;

public class PlayerTankController implements TankController {
    private final Player player;
    private final CollidingManager collidingManager;
    private final ActionKeyboardMapper actionKeyboardMapper;

    public PlayerTankController(CollidingManager collidingManager, Player player,
                                ActionKeyboardMapper actionKeyboardMapper) {
        this.player = player;
        this.collidingManager = collidingManager;
        this.actionKeyboardMapper = actionKeyboardMapper;
    }

    @Override
    public void handleTickAction() {
        LogicTank playerTank = player.getPlayerTank();
        if (playerTank.isStopped()) {
            Action action = actionKeyboardMapper.getCalledAction();
            if (action != null) {
                action.createCommand(collidingManager, playerTank).execute();
            }
        }
    }
}
