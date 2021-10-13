package ru.mipt.bit.platformer.models;

import ru.mipt.bit.platformer.models.movable.AbstractMovableObject;

public class Player {
    private String nick;
    private AbstractMovableObject playerObject;

    public Player(String nick) {
        this.nick = nick;
    }

    public Player(String nick, AbstractMovableObject playerObject) {
        this.nick = nick;
        this.playerObject = playerObject;
    }

    public String getNick() {
        return nick;
    }

    public AbstractMovableObject getPlayerObject() {
        return playerObject;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setPlayerObject(AbstractMovableObject playerObject) {
        this.playerObject = playerObject;
    }
}
