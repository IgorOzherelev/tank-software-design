package ru.mipt.bit.platformer.models.storages;

import ru.mipt.bit.platformer.models.objects.GameObject;

import java.util.List;

public class GameObjectStorage {
    private List<GameObject> gameObjects;
    private GameObject playerGameObject;

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public GameObject getPlayerGameObject() {
        return playerGameObject;
    }

    public void setGameObjects(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public void setPlayerGameObject(GameObject playerGameObject) {
        this.playerGameObject = playerGameObject;
    }
}
