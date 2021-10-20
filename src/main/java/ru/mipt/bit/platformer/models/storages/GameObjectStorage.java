package ru.mipt.bit.platformer.models.storages;

import ru.mipt.bit.platformer.models.objects.GameObject;

import java.util.List;

public class GameObjectStorage {
    private List<GameObject> trees;
    private List<GameObject> tanks;
    private GameObject playerGameObject;

    public List<GameObject> getTrees() {
        return trees;
    }

    public GameObject getPlayerGameObject() {
        return playerGameObject;
    }

    public List<GameObject> getTanks() {
        return tanks;
    }

    public void setTanks(List<GameObject> tanks) {
        this.tanks = tanks;
    }

    public void setTrees(List<GameObject> trees) {
        this.trees = trees;
    }

    public void setPlayerGameObject(GameObject playerGameObject) {
        this.playerGameObject = playerGameObject;
    }
}
