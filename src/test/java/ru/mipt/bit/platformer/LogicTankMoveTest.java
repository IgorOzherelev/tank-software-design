package ru.mipt.bit.platformer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.geometry.Point;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.managers.CollidingLogicManager;
import ru.mipt.bit.platformer.models.logic.LogicTank;
import ru.mipt.bit.platformer.geometry.Direction;
import ru.mipt.bit.platformer.models.state.LightLogicTankState;
import ru.mipt.bit.platformer.preferences.TexturePreferences;

import java.util.ArrayList;
import java.util.List;

public class LogicTankMoveTest {
    private static final List<LogicTank> logicTanks = new ArrayList<>();

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

        logicTanks.clear();
        Level level = new Level(new ArrayList<>(), logicTanks);
        CollidingLogicManager collidingLogicManager = new CollidingLogicManager(level, preferences);
        logicTanks.add(new LogicTank(collidingLogicManager, level, new Point(3, 5), new LightLogicTankState()));

        level.init();
    }
    
    @Test
    public void TankMoveTest_Right() {
        logicTanks.get(0).move(Direction.RIGHT);
        logicTanks.get(0).live(1);
        Assertions.assertEquals(logicTanks.get(0).getDestinationCoordinates(), new Point(4, 5));
        Assertions.assertEquals(logicTanks.get(0).getMovementProgress(), 1f);
        Assertions.assertEquals(logicTanks.get(0).getRotation(), Direction.RIGHT.getRotation());
    }

    @Test
    public void TankMoveTest_Left() {
        logicTanks.get(0).move(Direction.LEFT);
        logicTanks.get(0).live(1);
        Assertions.assertEquals(logicTanks.get(0).getDestinationCoordinates(), new Point(2, 5));
        Assertions.assertEquals(logicTanks.get(0).getMovementProgress(), 1f);
        Assertions.assertEquals(logicTanks.get(0).getRotation(), Direction.LEFT.getRotation());
    }

    @Test
    public void TankMoveTest_Up() {
        logicTanks.get(0).move(Direction.UP);
        logicTanks.get(0).live(1);
        Assertions.assertEquals(logicTanks.get(0).getDestinationCoordinates(), new Point(3, 6));
        Assertions.assertEquals(logicTanks.get(0).getMovementProgress(), 1f);
        Assertions.assertEquals(logicTanks.get(0).getRotation(), Direction.UP.getRotation());
    }

    @Test
    public void TankMoveTest_Down() {
        logicTanks.get(0).move(Direction.DOWN);
        logicTanks.get(0).live(1);
        Assertions.assertEquals(logicTanks.get(0).getDestinationCoordinates(), new Point(3, 4));
        Assertions.assertEquals(logicTanks.get(0).getMovementProgress(), 1f);
        Assertions.assertEquals(logicTanks.get(0).getRotation(), Direction.DOWN.getRotation());
    }
}
