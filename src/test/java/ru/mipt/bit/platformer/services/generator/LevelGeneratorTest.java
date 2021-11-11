package ru.mipt.bit.platformer.services.generator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.level.LevelGeneratorFromFile;
import ru.mipt.bit.platformer.level.LevelGeneratorRandom;
import ru.mipt.bit.platformer.level.LevelGenerator;
import ru.mipt.bit.platformer.geometry.Point;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.models.logic.LogicObstacle;
import ru.mipt.bit.platformer.preferences.TexturePreferences;

import java.util.List;

public class LevelGeneratorTest {
    private static TexturePreferences preferences;
    private Level level;
    private LevelGenerator generator;

    @BeforeAll
    public static void beforeAll() {
        preferences = new TexturePreferences() {
            @Override
            public int getMapWidth() {
                return 10;
            }

            @Override
            public int getMapHeight() {
                return 8;
            }
        };
    }

    @Test
    public void test001_gameObjectsFromFileMapGenerator_positive() {
        generator = new LevelGeneratorFromFile("level.map", preferences);
        List<LogicObstacle> expected = List.of(
                new LogicObstacle(new Point(0, 2)),
                new LogicObstacle(new Point(0, 6)),
                new LogicObstacle(new Point(1, 4)),
                new LogicObstacle(new Point(3, 1)),
                new LogicObstacle(new Point(3, 7)),
                new LogicObstacle(new Point(4, 4)),
                new LogicObstacle(new Point(5, 7))
        );

        level = generator.generate();
        List<LogicObstacle> logicObstacles = level.getLogicObstacles();
        Assertions.assertEquals(7, logicObstacles.size());

        Assertions.assertTrue(expected.containsAll(logicObstacles));
        Assertions.assertEquals(level.getPlayerLogicTank().getCurrentCoordinates(), new Point(5, 2));
    }

    @Test
    public void test002_gameObjectsRandomMapGenerator_positive() {
        generator = new LevelGeneratorRandom(5, 1, preferences);

        level = generator.generate();
        Assertions.assertEquals(5, level.getLogicObstacles().size());
        Assertions.assertNotNull(level.getPlayerLogicTank());
    }
}
