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

public class CollidingManagerTest {
    private static CollidingManager collidingManager;
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
        collidingManager = new CollidingManager(level, preferences);
        TANK = new LogicTank(collidingManager, level, new Point(5, 5));

        logicTanks.add(TANK);
        logicTanks.add(new LogicTank(collidingManager, level, new Point(5, 6)));
        logicTanks.add(new LogicTank(collidingManager, level, new Point(6, 5)));
        logicTanks.add(new LogicTank(collidingManager, level, new Point(4, 5)));
        logicTanks.add(new LogicTank(collidingManager, level, new Point(5, 4)));

        collidingManager.init();
    }

    @Test
    public void testMoveDown_negative() {
        Assertions.assertFalse(collidingManager.isSafeDirection(Direction.DOWN, TANK));
    }

    @Test
    public void testMoveUp_negative() {
        Assertions.assertFalse(collidingManager.isSafeDirection(Direction.UP, TANK));
    }

    @Test
    public void testMoveLeft_negative() {
        Assertions.assertFalse(collidingManager.isSafeDirection(Direction.LEFT, TANK));
    }

    @Test
    public void testMoveRight_negative() {
        Assertions.assertFalse(collidingManager.isSafeDirection(Direction.RIGHT, TANK));
    }

    @Test
    public void test_positive() {
        LogicTank logicTank = new LogicTank(collidingManager, level, new Point(0, 0));
        Assertions.assertTrue(collidingManager.isSafeDirection(Direction.UP, logicTank));
    }
}
