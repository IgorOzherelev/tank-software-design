package ru.mipt.bit.platformer.services.generator;

import ru.mipt.bit.platformer.logic.geometry.Point;
import ru.mipt.bit.platformer.models.objects.GameObject;
import ru.mipt.bit.platformer.models.objects.GameObjectStorage;
import ru.mipt.bit.platformer.preferences.TexturePreferences;

import java.util.ArrayList;
import java.util.List;

import static ru.mipt.bit.platformer.util.CommonUtils.*;
import static ru.mipt.bit.platformer.util.CommonUtils.checkStringLength;

public class GameObjectsFromFileGenerator extends AbstractGameObjectGenerator {
    private final static char TREE_TOKEN = 'T';
    private final static char FREE_TOKEN = '_';
    private final static char PLAYER_TOKEN = 'X';

    private final String filePath;
    private final TexturePreferences texturePreferences;

    public GameObjectsFromFileGenerator(String filePath, TexturePreferences texturePreferences) {
        this.filePath = filePath;
        this.texturePreferences = texturePreferences;
    }

    @Override
    public GameObjectStorage generate() {
        int width = texturePreferences.getMapWidth();
        int height = texturePreferences.getMapHeight();

        String file = loadFile(filePath, GameObjectsRandomGenerator.class);

        checkNotNull(filePath, file);
        checkStringLength(filePath, file.replaceAll("\\s", ""), width * height);
        List<GameObject> trees = new ArrayList<>();

        List<String> map = List.of(file.split("\r\n"));
        String line;
        GameObject tree;
        GameObject tank;
        for (int i = 0; i < map.size(); i++) {
            line = map.get(i);
            checkStringLength(line, width);
            for (int j = 0; j < map.size(); j++) {
                char currentChar = line.charAt(j);
                switch (currentChar) {
                    case TREE_TOKEN:
                        tree = new GameObject(new Point(i, j), 0f);
                        trees.add(tree);
                        break;
                    case PLAYER_TOKEN:
                        if (storage.getPlayerGameObject() != null) {
                            throw new IllegalArgumentException("Wrong map format, two player tanks:\n" + map);
                        }
                        tank = new GameObject(
                                new Point(i, j), 0f
                        );
                        storage.setPlayerGameObject(tank);
                        break;
                    case FREE_TOKEN:
                        break;
                    default:
                        throw new IllegalArgumentException("Wrong map format, illegal tokens:\n" + map);
                }
            }
        }

        storage.setGameObjects(trees);

        return storage;
    }
}
