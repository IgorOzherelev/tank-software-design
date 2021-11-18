package ru.mipt.bit.platformer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.geometry.Point;
import ru.mipt.bit.platformer.managers.CollidingManager;
import ru.mipt.bit.platformer.models.logic.LogicTank;
import ru.mipt.bit.platformer.geometry.Direction;

import java.util.List;

public class LogicTankMoveTest {
    private static LogicTank logicTank;
    private static CollidingManager collidingManager;

    @BeforeEach
    public void beforeEach() {
        collidingManager = new CollidingManager(10, 10);
        logicTank = new LogicTank(new Point(3, 5));
        collidingManager.addCollidings(List.of(logicTank));
    }
    
    @Test
    public void TankMoveTest_Right() {
        logicTank.move(Direction.RIGHT, collidingManager);
        logicTank.tick(1);
        Assertions.assertEquals(logicTank.getDestinationCoordinates(), new Point(4, 5));
        Assertions.assertEquals(logicTank.getMovementProgress(), 1f);
        Assertions.assertEquals(logicTank.getRotation(), Direction.RIGHT.getRotation());
    }

    @Test
    public void TankMoveTest_Left() {
        logicTank.move(Direction.LEFT, collidingManager);
        logicTank.tick(1);
        Assertions.assertEquals(logicTank.getDestinationCoordinates(), new Point(2, 5));
        Assertions.assertEquals(logicTank.getMovementProgress(), 1f);
        Assertions.assertEquals(logicTank.getRotation(), Direction.LEFT.getRotation());
    }

    @Test
    public void TankMoveTest_Up() {
        logicTank.move(Direction.UP, collidingManager);
        logicTank.tick(1);
        Assertions.assertEquals(logicTank.getDestinationCoordinates(), new Point(3, 6));
        Assertions.assertEquals(logicTank.getMovementProgress(), 1f);
        Assertions.assertEquals(logicTank.getRotation(), Direction.UP.getRotation());
    }

    @Test
    public void TankMoveTest_Down() {
        logicTank.move(Direction.DOWN, collidingManager);
        logicTank.tick(1);
        Assertions.assertEquals(logicTank.getDestinationCoordinates(), new Point(3, 4));
        Assertions.assertEquals(logicTank.getMovementProgress(), 1f);
        Assertions.assertEquals(logicTank.getRotation(), Direction.DOWN.getRotation());
    }
}
