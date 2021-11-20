package ru.mipt.bit.platformer.managers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.geometry.Point;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.geometry.Direction;
import ru.mipt.bit.platformer.models.logic.LogicTank;
import ru.mipt.bit.platformer.preferences.TexturePreferences;

import java.util.*;

public class CollidingLogicManagerTest {
    private static CollidingLogicManager collidingLogicManager;
    private static Level level;
    private static LogicTank TANK;

    @BeforeAll
    public static void beforeAll() {
        TexturePreferences preferences = new TexturePreferences() {
            @Override
            public int getMapWidth() {
                return 10;
            }

            @Override
            public int getMapHeight() {
                return 8;
            }
        };

        List<LogicTank> logicTanks = new ArrayList<>();
        level = new Level(new ArrayList<>(), logicTanks);
        collidingLogicManager = new CollidingLogicManager(level, preferences);
        TANK = new LogicTank(collidingLogicManager, level, new Point(5, 5));

        logicTanks.add(TANK);
        logicTanks.add(new LogicTank(collidingLogicManager, level, new Point(5, 6)));
        logicTanks.add(new LogicTank(collidingLogicManager, level, new Point(6, 5)));
        logicTanks.add(new LogicTank(collidingLogicManager, level, new Point(4, 5)));
        logicTanks.add(new LogicTank(collidingLogicManager, level, new Point(5, 4)));

        level.init();
    }

    @Test
    public void testMoveDown_negative() {
        Assertions.assertFalse(collidingLogicManager.isSafeDirection(Direction.DOWN, TANK));
    }

    @Test
    public void testMoveUp_negative() {
        Assertions.assertFalse(collidingLogicManager.isSafeDirection(Direction.UP, TANK));
    }

    @Test
    public void testMoveLeft_negative() {
        Assertions.assertFalse(collidingLogicManager.isSafeDirection(Direction.LEFT, TANK));
    }

    @Test
    public void testMoveRight_negative() {
        Assertions.assertFalse(collidingLogicManager.isSafeDirection(Direction.RIGHT, TANK));
    }

    @Test
    public void test_positive() {
        LogicTank logicTank = new LogicTank(collidingLogicManager, level, new Point(0, 0));
        Assertions.assertTrue(collidingLogicManager.isSafeDirection(Direction.UP, logicTank));
    }
}
