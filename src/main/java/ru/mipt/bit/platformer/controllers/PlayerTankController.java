package ru.mipt.bit.platformer.controllers;

import ru.mipt.bit.platformer.actions.Action;
import ru.mipt.bit.platformer.actions.ActionKeyboardMapper;
import ru.mipt.bit.platformer.models.Player;
import ru.mipt.bit.platformer.models.logic.LogicTank;

/**
 * Use-case
 * */
public class PlayerTankController implements TankController {
    private final Player player;
    private final ActionKeyboardMapper actionKeyboardMapper;

    public PlayerTankController(Player player, ActionKeyboardMapper actionKeyboardMapper) {
        this.player = player;
        this.actionKeyboardMapper = actionKeyboardMapper;
    }

    @Override
    public void handleTickAction() {
        LogicTank playerTank = player.getPlayerTank();
        if (playerTank.isStopped()) {
            Action action = actionKeyboardMapper.getCalledAction();
            if (action != null) {
                action.createCommand(playerTank).execute();
            }
        }
    }
}
