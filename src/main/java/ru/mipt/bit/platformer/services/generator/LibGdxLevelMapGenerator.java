package ru.mipt.bit.platformer.services.generator;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import ru.mipt.bit.platformer.logic.geometry.Point;
import ru.mipt.bit.platformer.models.movable.Tank;
import ru.mipt.bit.platformer.models.objects.GameGraphicObjectStorage;
import ru.mipt.bit.platformer.models.objects.GraphicObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static ru.mipt.bit.platformer.util.CommonUtils.*;
import static ru.mipt.bit.platformer.util.LibGdxGameLevelUtils.getTiledMapProperty;

public class LibGdxLevelMapGenerator implements MapGenerator {
    private TiledMap level;
    private final GameGraphicObjectStorage storage;

    public LibGdxLevelMapGenerator(TiledMap level, GameGraphicObjectStorage storage) {
        this.level = level;
        this.storage = storage;
    }

    public void setLevel(TiledMap level) {
        this.level = level;
    }

    @Override
    public void generateFromFile(String filePath) {
        int width = getTiledMapProperty("width", Integer.class, level);
        int height = getTiledMapProperty("height", Integer.class, level);

        String file = loadFile(filePath, LibGdxLevelMapGenerator.class);

        checkNotNull(filePath, file);
        checkStringLength(filePath, file.replaceAll("\\s", ""), width * height);
        List<GraphicObject> graphicObjects = new ArrayList<>();

        List<String> map = List.of(file.split("\n"));
        String line;
        GraphicObject tree;
        Tank tank;
        for (int i = 0; i < map.size(); i++) {
            line = map.get(i);
            checkStringLength(line, width);
            for (int j = 0; j < map.size(); j++) {
                char currentChar = line.charAt(j);
                switch (currentChar) {
                    case 'T':
                        tree = new GraphicObject(
                                new Texture("images/greenTree.png"),
                                new Point(i, j));
                        graphicObjects.add(tree);
                        break;
                    case 'X':
                        if (storage.getPlayerTank() != null) {
                            throw new IllegalArgumentException("Wrong map format, two player tanks:\n" + map);
                        }
                        tank = new Tank(
                                new Texture("images/tank_blue.png"),
                                new Point(i, j), 0f
                        );
                        storage.setPlayerTank(tank);
                        break;
                    case '_':
                        break;
                    default:
                        throw new IllegalArgumentException("Wrong map format, illegal tokens:\n" + map);
                }
            }
        }

        storage.setGraphicObjects(graphicObjects);
    }

    @Override
    public void generateRandomly(int treesQuantity, int tanksQuantity) {
        // пока что танк один, поэтому такая реализация для танков
        int width = getTiledMapProperty("width", Integer.class, level);
        int height = getTiledMapProperty("height", Integer.class, level);

        List<GraphicObject> trees = new ArrayList<>();

        int totalObjectsQuantity = treesQuantity + tanksQuantity;
        int[] x = new Random().ints(0, width)
                .distinct().limit(totalObjectsQuantity).toArray();
        int[] y = new Random().ints(0, height)
                .distinct().limit(totalObjectsQuantity).toArray();

        for (int i = 0; i < treesQuantity; i++) {
            trees.add(new GraphicObject(
                    new Texture("images/greenTree.png"),
                    new Point(x[i], y[i])));
        }

        Tank playerTank = new Tank(
                new Texture("images/tank_blue.png"),
                new Point(x[totalObjectsQuantity - 1], y[totalObjectsQuantity - 1]), 0f
        );

        storage.setPlayerTank(playerTank);
        storage.setGraphicObjects(trees);
    }
}
