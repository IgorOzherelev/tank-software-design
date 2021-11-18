package ru.mipt.bit.platformer.level;

import ru.mipt.bit.platformer.geometry.Point;
import ru.mipt.bit.platformer.models.logic.LogicObstacle;
import ru.mipt.bit.platformer.models.logic.LogicTank;
import ru.mipt.bit.platformer.preferences.TexturePreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static ru.mipt.bit.platformer.utils.LevelGeneratorUtils.checkTotalObjectsQuantity;
import static ru.mipt.bit.platformer.utils.LevelGeneratorUtils.checkTreesAndTanksQuantity;

public class LevelGeneratorRandom implements LevelGenerator {
    private final TexturePreferences texturePreferences;

    int treesQuantity;
    int tanksQuantity;

    public LevelGeneratorRandom(int treesQuantity,
                                int tanksQuantity, TexturePreferences texturePreferences) {
        this.tanksQuantity = tanksQuantity;
        this.treesQuantity = treesQuantity;
        this.texturePreferences = texturePreferences;

        checkTreesAndTanksQuantity(treesQuantity, tanksQuantity);
        checkTotalObjectsQuantity(texturePreferences, tanksQuantity, treesQuantity);
    }

    @Override
    public Level generate() {
        int width = texturePreferences.getMapWidth();
        int height = texturePreferences.getMapHeight();

        List<LogicObstacle> trees = new ArrayList<>();
        List<LogicTank> botTanks = new ArrayList<>();

        int totalObjectsQuantity = treesQuantity + tanksQuantity;
        List<Point> randomPoints = generateRandomPoints(width, height, totalObjectsQuantity);

        int i;
        for (i = 0; i < treesQuantity; i++) {
            trees.add(new LogicObstacle(randomPoints.get(i)));
        }

        if (tanksQuantity > 1) {
            for (; i < totalObjectsQuantity - 1; i++) {
                botTanks.add(new LogicTank(randomPoints.get(i)));
            }
        }

        LogicTank playerTank = new LogicTank(
                new Point(randomPoints.get(totalObjectsQuantity - 1))
        );

        return new Level(trees, botTanks, playerTank, texturePreferences);
    }

    private List<Point> generateRandomPoints(int width, int height, int totalObjectsQuantity) {
        Random random = new Random();

        List<Point> randomPoints = new ArrayList<>();
        for (int i = 0; i < totalObjectsQuantity; i++) {
            randomPoints.add(new Point(random.nextInt(width), random.nextInt(height)));
        }
        return randomPoints;
    }
}
