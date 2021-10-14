package ru.mipt.bit.platformer.models.objects;

import ru.mipt.bit.platformer.models.movable.Tank;

import java.util.List;

public class GameGraphicObjectStorage {
    private List<GraphicObject> graphicObjects;
    private Tank playerTank;

    public List<GraphicObject> getGraphicObjects() {
        return graphicObjects;
    }

    public Tank getPlayerTank() {
        return playerTank;
    }

    public void setGraphicObjects(List<GraphicObject> gameObjects) {
        this.graphicObjects = gameObjects;
    }

    public void setPlayerTank(Tank playerTank) {
        this.playerTank = playerTank;
    }
}
