package ru.mipt.bit.platformer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.geometry.Point;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.models.logic.LogicTank;
import ru.mipt.bit.platformer.geometry.Direction;
import ru.mipt.bit.platformer.preferences.TexturePreferences;

import java.util.ArrayList;
import java.util.List;

public class LogicTankMoveTest {
    private static LogicTank logicTank;
    private static Level level;

    @BeforeEach
    public void beforeEach() {
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

        logicTank = new LogicTank(new Point(3, 5));
        level = new Level(new ArrayList<>(), List.of(logicTank), new LogicTank(new Point(0,0)), preferences);
    }
    
    @Test
    public void TankMoveTest_Right() {
        logicTank.move(Direction.RIGHT, level);
        logicTank.tick(1);
        Assertions.assertEquals(logicTank.getDestinationCoordinates(), new Point(4, 5));
        Assertions.assertEquals(logicTank.getMovementProgress(), 1f);
        Assertions.assertEquals(logicTank.getRotation(), Direction.RIGHT.getRotation());
    }

    @Test
    public void TankMoveTest_Left() {
        logicTank.move(Direction.LEFT, level);
        logicTank.tick(1);
        Assertions.assertEquals(logicTank.getDestinationCoordinates(), new Point(2, 5));
        Assertions.assertEquals(logicTank.getMovementProgress(), 1f);
        Assertions.assertEquals(logicTank.getRotation(), Direction.LEFT.getRotation());
    }

    @Test
    public void TankMoveTest_Up() {
        logicTank.move(Direction.UP, level);
        logicTank.tick(1);
        Assertions.assertEquals(logicTank.getDestinationCoordinates(), new Point(3, 6));
        Assertions.assertEquals(logicTank.getMovementProgress(), 1f);
        Assertions.assertEquals(logicTank.getRotation(), Direction.UP.getRotation());
    }

    @Test
    public void TankMoveTest_Down() {
        logicTank.move(Direction.DOWN, level);
        logicTank.tick(1);
        Assertions.assertEquals(logicTank.getDestinationCoordinates(), new Point(3, 4));
        Assertions.assertEquals(logicTank.getMovementProgress(), 1f);
        Assertions.assertEquals(logicTank.getRotation(), Direction.DOWN.getRotation());
    }
}
