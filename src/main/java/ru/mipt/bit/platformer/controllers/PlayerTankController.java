package ru.mipt.bit.platformer.controllers;

import ru.mipt.bit.platformer.actions.Action;
import ru.mipt.bit.platformer.actions.KeyboardMapper;
import ru.mipt.bit.platformer.commands.Command;
import ru.mipt.bit.platformer.models.Player;
import ru.mipt.bit.platformer.models.logic.LogicTank;

/**
 * Adapter
 * */
public class PlayerTankController implements TankController {
    private final Player player;
    private final KeyboardMapper keyboardMapper;

    public PlayerTankController(Player player, KeyboardMapper keyboardMapper) {
        this.player = player;
        this.keyboardMapper = keyboardMapper;
    }

    @Override
    public void handleTickAction() {
        LogicTank playerTank = player.getPlayerTank();
        if (playerTank.isStopped()) {
            Action action = keyboardMapper.getCalledAction();
            if (action != null) {
                action.createCommand(playerTank).execute();
            } else {
                Command command = keyboardMapper.getCalledCommand();
                if (command != null) {
                    command.execute();
                }
            }
        }
    }
}
