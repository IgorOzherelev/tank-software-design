package ru.mipt.bit.platformer.models;

import ru.mipt.bit.platformer.models.movable.AbstractMovableGraphicObject;

public class Player {
    private String nick;
    private AbstractMovableGraphicObject playerObject;

    public Player(String nick) {
        this.nick = nick;
    }

    public Player(String nick, AbstractMovableGraphicObject playerObject) {
        this.nick = nick;
        this.playerObject = playerObject;
    }

    public String getNick() {
        return nick;
    }

    public AbstractMovableGraphicObject getPlayerObject() {
        return playerObject;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setPlayerObject(AbstractMovableGraphicObject playerObject) {
        this.playerObject = playerObject;
    }
}
