package ru.mipt.bit.platformer.models;

import ru.mipt.bit.platformer.models.movable.Movable;

public class Player {
    private String nick;
    private Movable playerObject;

    public Player(String nick) {
        this.nick = nick;
    }

    public Player(String nick, Movable playerObject) {
        this.nick = nick;
        this.playerObject = playerObject;
    }

    public String getNick() {
        return nick;
    }

    public Movable getPlayerObject() {
        return playerObject;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setPlayerObject(Movable playerObject) {
        this.playerObject = playerObject;
    }
}
