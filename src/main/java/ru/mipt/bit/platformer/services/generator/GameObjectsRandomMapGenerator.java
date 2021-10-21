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

        checkTreesAndTanksQuantity(treesQuantity, tanksQuantity);
        checkTotalObjectsQuantity();
    }

    @Override
    public GameObjectStorage generate() {
        int width = texturePreferences.getMapWidth();
        int height = texturePreferences.getMapHeight();

        List<GameObject> trees = new ArrayList<>();
        List<GameObject> tanks = new ArrayList<>();

        int totalObjectsQuantity = treesQuantity + tanksQuantity;
        int[] x = new Random().ints(0, width)
                .distinct().limit(totalObjectsQuantity).toArray();
        int[] y = new Random().ints(0, height)
                .distinct().limit(totalObjectsQuantity).toArray();

        int i;
        for (i = 0; i < treesQuantity; i++) {
            trees.add(new GameObject(new Point(x[i], y[i]), 0f));
        }

        if (tanksQuantity > 1) {
            for (; i < totalObjectsQuantity - 1; i++) {
                tanks.add(new GameObject(new Point(x[i], y[i]), 0f));
            }
        }

        GameObject playerTank = new GameObject(
                new Point(x[totalObjectsQuantity - 1], y[totalObjectsQuantity - 1]), 0f
        );

        storage.setPlayerGameObject(playerTank);
        storage.setTrees(trees);
        storage.setTanks(tanks);

        return storage;
    }

    private void checkTreesAndTanksQuantity(int treesQuantity, int tanksQuantity) {
        if (tanksQuantity <= 0 || treesQuantity < 0) {
            throw new IllegalArgumentException("tanksQuantity <=0 or treesQuantity < 0" +
                    " tanksQuantity: " + tanksQuantity + " treesQuantity: " + treesQuantity);
        }
    }

    private void checkTotalObjectsQuantity() {
        int halfOfMapSquare = texturePreferences.getMapWidth() * texturePreferences.getMapHeight() / 2;
        if (halfOfMapSquare <= tanksQuantity * treesQuantity) {
            throw new IllegalArgumentException("Wrong total tanks and trees quantity >= map square / 2: "
                    + "trees: " + treesQuantity + "tanks: " + tanksQuantity + "map square / 2: " + halfOfMapSquare);
        }
    }
}
