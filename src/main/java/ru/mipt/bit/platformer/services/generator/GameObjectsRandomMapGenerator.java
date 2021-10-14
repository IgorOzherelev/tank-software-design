package ru.mipt.bit.platformer.services.generator;

import ru.mipt.bit.platformer.logic.geometry.Point;
import ru.mipt.bit.platformer.models.objects.GameObject;
import ru.mipt.bit.platformer.models.storages.GameObjectStorage;
import ru.mipt.bit.platformer.preferences.TexturePreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameObjectsRandomMapGenerator extends AbstractGameObjectGenerator {
    private final TexturePreferences texturePreferences;

    int treesQuantity;
    int tanksQuantity;

    public GameObjectsRandomMapGenerator(int treesQuantity,
                                         int tanksQuantity, TexturePreferences texturePreferences) {
        this.tanksQuantity = tanksQuantity;
        this.treesQuantity = treesQuantity;
        this.texturePreferences = texturePreferences;
    }

    @Override
    public GameObjectStorage generate() {
        // пока что танк один, поэтому такая реализация для танков
        int width = texturePreferences.getMapWidth();
        int height = texturePreferences.getMapHeight();

        List<GameObject> trees = new ArrayList<>();

        int totalObjectsQuantity = treesQuantity + tanksQuantity;
        int[] x = new Random().ints(0, width)
                .distinct().limit(totalObjectsQuantity).toArray();
        int[] y = new Random().ints(0, height)
                .distinct().limit(totalObjectsQuantity).toArray();

        for (int i = 0; i < treesQuantity; i++) {
            trees.add(new GameObject(new Point(x[i], y[i]), 0f));
        }

        GameObject playerTank = new GameObject(
                new Point(x[totalObjectsQuantity - 1], y[totalObjectsQuantity - 1]), 0f
        );

        storage.setPlayerGameObject(playerTank);
        storage.setGameObjects(trees);

        return storage;
    }
}
