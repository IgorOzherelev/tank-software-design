package ru.mipt.bit.platformer.level;

import ru.mipt.bit.platformer.geometry.Point;
import ru.mipt.bit.platformer.managers.CollidingLogicManager;
import ru.mipt.bit.platformer.models.logic.LogicObstacle;
import ru.mipt.bit.platformer.models.logic.LogicTank;
import ru.mipt.bit.platformer.preferences.TexturePreferences;

import java.util.ArrayList;
import java.util.List;

import static ru.mipt.bit.platformer.utils.CommonUtils.*;
import static ru.mipt.bit.platformer.utils.CommonUtils.checkStringLength;

public class LevelGeneratorFromFile implements LevelGenerator {
    private final static char TREE_TOKEN = 'T';
    private final static char EMPTY_TILE_TOKEN = '_';
    private final static char TANK_TOKEN = 'X';

    private final String filePath;
    private final TexturePreferences texturePreferences;

    public LevelGeneratorFromFile(String filePath, TexturePreferences texturePreferences) {
        this.filePath = filePath;
        this.texturePreferences = texturePreferences;
    }

    @Override
    public Level generate() {
        int width = texturePreferences.getMapWidth();
        int height = texturePreferences.getMapHeight();

        String file = loadFile(filePath, LevelGeneratorRandom.class);

        checkNotNull(filePath, file);
        checkStringLength(filePath, file.replaceAll("\\s", ""), width * height);
        List<LogicObstacle> trees = new ArrayList<>();

        List<String> map = splitFile(file);
        String line;
        LogicObstacle tree;
        List<LogicTank> logicTanks = new ArrayList<>();

        Level level = new Level(trees, logicTanks);
        CollidingLogicManager collidingLogicManager = new CollidingLogicManager(level, texturePreferences);
        for (int i = 0; i < map.size(); i++) {
            line = map.get(i);
            checkStringLength(line, width);
            for (int j = 0; j < map.size(); j++) {
                char currentChar = line.charAt(j);
                switch (currentChar) {
                    case TREE_TOKEN:
                        tree = new LogicObstacle(new Point(i, j));
                        trees.add(tree);
                        break;
                    case TANK_TOKEN:
                        logicTanks.add(new LogicTank(collidingLogicManager, level, new Point(i, j)));
                        break;
                    case EMPTY_TILE_TOKEN:
                        break;
                    default:
                        throw new IllegalArgumentException("Wrong map format, illegal tokens:\n" + map);
                }
            }
        }

        return level;
    }
}
