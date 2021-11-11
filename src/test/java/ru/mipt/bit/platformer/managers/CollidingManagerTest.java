package ru.mipt.bit.platformer.managers;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.geometry.Point;
import ru.mipt.bit.platformer.models.Colliding;
import ru.mipt.bit.platformer.geometry.Direction;
import ru.mipt.bit.platformer.models.logic.LogicTank;

import java.util.*;

public class CollidingManagerTest {
    private final static CollidingManager COLLIDING_MANAGER = new CollidingManager(10, 8);
    private final static LogicTank LOGIC_TANK = new LogicTank(new Point(5, 5));

    @BeforeAll
    public static void beforeAll() {
        List<Colliding> collidingList = new ArrayList<>();
        collidingList.add(LOGIC_TANK);
        collidingList.add(new LogicTank(new Point(5, 6)));
        collidingList.add(new LogicTank(new Point(6, 5)));
        collidingList.add(new LogicTank(new Point(4, 5)));
        collidingList.add(new LogicTank(new Point(5, 4)));

        COLLIDING_MANAGER.addCollidings(collidingList);
    }

    @Test
    public void testMoveDown_negative() {
        Assertions.assertFalse(COLLIDING_MANAGER.isMoveSafe(Direction.DOWN, LOGIC_TANK));
    }

    @Test
    public void testMoveUp_negative() {
        Assertions.assertFalse(COLLIDING_MANAGER.isMoveSafe(Direction.UP, LOGIC_TANK));
    }

    @Test
    public void testMoveLeft_negative() {
        Assertions.assertFalse(COLLIDING_MANAGER.isMoveSafe(Direction.LEFT, LOGIC_TANK));
    }

    @Test
    public void testMoveRight_negative() {
        Assertions.assertFalse(COLLIDING_MANAGER.isMoveSafe(Direction.RIGHT, LOGIC_TANK));
    }

    @Test
    public void test_positive() {
        LogicTank logicTank = new LogicTank(new Point(0, 0));
        Assertions.assertTrue(COLLIDING_MANAGER.isMoveSafe(Direction.UP, logicTank));
    }
}
