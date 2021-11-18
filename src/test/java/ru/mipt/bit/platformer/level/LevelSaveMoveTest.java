package ru.mipt.bit.platformer.level;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.geometry.Point;
import ru.mipt.bit.platformer.geometry.Direction;
import ru.mipt.bit.platformer.models.logic.LogicTank;
import ru.mipt.bit.platformer.preferences.TexturePreferences;

import java.util.*;

public class LevelSaveMoveTest {
    private static Level LEVEL;
    private final static LogicTank LOGIC_TANK = new LogicTank(new Point(5, 5));

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
        logicTanks.add(LOGIC_TANK);
        logicTanks.add(new LogicTank(new Point(5, 6)));
        logicTanks.add(new LogicTank(new Point(6, 5)));
        logicTanks.add(new LogicTank(new Point(4, 5)));
        logicTanks.add(new LogicTank(new Point(5, 4)));

        LEVEL = new Level(new ArrayList<>(), logicTanks, LOGIC_TANK, preferences);
    }

    @Test
    public void testMoveDown_negative() {
        Assertions.assertFalse(LEVEL.isMoveSafe(Direction.DOWN, LOGIC_TANK));
    }

    @Test
    public void testMoveUp_negative() {
        Assertions.assertFalse(LEVEL.isMoveSafe(Direction.UP, LOGIC_TANK));
    }

    @Test
    public void testMoveLeft_negative() {
        Assertions.assertFalse(LEVEL.isMoveSafe(Direction.LEFT, LOGIC_TANK));
    }

    @Test
    public void testMoveRight_negative() {
        Assertions.assertFalse(LEVEL.isMoveSafe(Direction.RIGHT, LOGIC_TANK));
    }

    @Test
    public void test_positive() {
        LogicTank logicTank = new LogicTank(new Point(0, 0));
        Assertions.assertTrue(LEVEL.isMoveSafe(Direction.UP, logicTank));
    }
}
