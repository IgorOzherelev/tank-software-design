package ru.mipt.bit.platformer.models;

import ru.mipt.bit.platformer.models.logic.LogicTank;

public class Player {
    private String nick;
    private LogicTank playerTank;

    public Player(String nick) {
        this.nick = nick;
    }

    public Player(String nick, LogicTank playerTank) {
        this.nick = nick;
        this.playerTank = playerTank;
    }

    public String getNick() {
        return nick;
    }

    public LogicTank getPlayerTank() {
        return playerTank;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setPlayerTank(LogicTank playerTank) {
        this.playerTank = playerTank;
    }
}
