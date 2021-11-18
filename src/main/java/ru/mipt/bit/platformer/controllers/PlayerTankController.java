package ru.mipt.bit.platformer.controllers;

import ru.mipt.bit.platformer.actions.Action;
import ru.mipt.bit.platformer.actions.ActionKeyboardMapper;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.models.Player;
import ru.mipt.bit.platformer.models.logic.LogicTank;

public class PlayerTankController implements TankController {
    private final Player player;
    private final Level level;
    private final ActionKeyboardMapper actionKeyboardMapper;

    public PlayerTankController(Level level, Player player,
                                ActionKeyboardMapper actionKeyboardMapper) {
        this.player = player;
        this.level = level;
        this.actionKeyboardMapper = actionKeyboardMapper;
    }

    @Override
    public void handleTickAction() {
        LogicTank playerTank = player.getPlayerTank();
        if (playerTank.isStopped()) {
            Action action = actionKeyboardMapper.getCalledAction();
            if (action != null) {
                action.createCommand(level, playerTank).execute();
            }
        }
    }
}
